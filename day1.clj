(ns adventofcode2018
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str]))


(def tallene (slurp "inputday1.txt"))

(def lines (str/split-lines tallene))

(println "hello")

(defn tonum [astr]
  (Integer/valueOf astr))

(println (reduce + (map tonum lines)))

(comment
  ;;(def data-file (io/resource "https://adventofcode.com/2018/day/1/input"))

  (defn get-lines []
    (str/split-lines (slurp file)))
)
