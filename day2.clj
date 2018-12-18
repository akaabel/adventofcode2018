(ns adventofcode2018
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str])
  (:use clojure.test))


(defn tonum [astr]
  (Integer/valueOf astr))

(def tallene (slurp "inputday1.txt"))

(def lines (str/split-lines tallene))

(def intlist (map tonum lines))

(defn findfreq [alist]
  (loop [currsum 0
         thelist alist
         dict {}]
    (let [[firstnum & rest] thelist
          found? (not= 0 (get dict currsum 0))]
      (if found?
        currsum
        (recur (+ currsum firstnum) rest (assoc dict currsum 1))))))


(deftest solution-test
  (is (= 83173 (findfreq (cycle intlist)))))


(deftest test-1
  (is (= 0 (findfreq (cycle [1 -1])))))

(deftest test-2
  (is (= 10 (findfreq (cycle [3 3 4 -2 -4])))))

(deftest test-3
  (is (= 5 (findfreq (cycle [-6 3 8 5 -6])))))

(deftest test-4
  (is (= 14 (findfreq (cycle [7 7 -2 -7 -4])))))




(comment
  ;; Eksempel på test

  (deftest entest
    (is (= "ABC" (str/capitalize "abc"))))

  ;;(def data-file (io/resource "https://adventofcode.com/2018/day/1/input"))

  ;;(println (reduce + (map tonum lines)))

  (defn get-lines []
    (str/split-lines (slurp file)))

  ;; Old try
  (defn findfreq [sum numlist dict]
    (if (empty? numlist)
      [sum [] dict]
      (let [[firstnum & the-rest] numlist
            newsum (+ firstnum sum)
            numfreq (get dict newsum 0)]
        (if (= numfreq 2)
          [newsum nil nil]
          (findfreq newsum the-rest (assoc dict newsum (+ numfreq 1)))))))

  (defn findfreq [alist]
    (loop [currsum 0
           thelist alist
           dict {}]
      (let [[sum somelist somedict] (findfreq currsum alist dict) ]
        (if (nil? somelist)
          (println (str "Found solution: " sum))
          (recur sum somelist somedict)))))  )
