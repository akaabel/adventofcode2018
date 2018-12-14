(ns adventofcode2018
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str]))


(defn tonum [astr]
  (Integer/valueOf astr))

(def tallene (slurp "inputday1.txt"))

(def lines (str/split-lines tallene))

(def intlist (map tonum lines))

(defn findfreq [sum numlist dict]
  (if (empty? numlist)
    [sum [] dict]
    (let [[firstnum & the-rest] numlist
          newsum (+ firstnum sum)
          numfreq (get dict newsum 0)]
      (if (= numfreq 2)
        [newsum nil nil]
        (findfreq newsum the-rest (assoc dict newsum (+ numfreq 1)))))))


(defn findall [alist]
  (loop [currsum 0
         thelist alist
         dict {}]
    (let [[sum somelist somedict] (findfreq currsum alist dict) ]
      (if (nil? somelist)
        (println (str "Found solution: " sum))
        (recur sum somelist somedict)))))



(comment
  ;;(def data-file (io/resource "https://adventofcode.com/2018/day/1/input"))

  ;;(println (reduce + (map tonum lines)))

  (defn get-lines []
    (str/split-lines (slurp file)))
)
