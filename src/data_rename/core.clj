(ns data-rename.core
  (:use [clojure.java.io])
  (:require [clojure.string :as str]))
(import '(java.io File))
(def cur_dir (-> (java.io.File. ".") .getCanonicalPath)) 
(defn walk [dirpath pattern]
  (doall (filter #(re-matches pattern (.getName %))
                 (file-seq (file dirpath)))))


;(def all-files (map str (walk "C:\\Users\\Labuser\\Documents\\Github\\keewii-visual\\data\\Patients\\jp\\Polar\\dat" #".*\.dat")))
(def all-files  (into []  (map str (walk "C:\\Users\\Labuser\\Documents\\Github\\keewii-visual\\data" #".*\.dat"))))
;(def data (atom 0))
;(doseq [] (reset! data all-files))
; copy files andchange name


(defn get-symbols [s]  (str/split s #"\\"))
(defn new-name [symbols] (str/join "_" (take 5 (drop 7 symbols))))
(def all-filename (into [] (map #(new-name (get-symbols %)) all-files)))
(defn file-merge [filepath filename] 
  (let [Name (first (str/split filename #"\."))] 
    (spit (str cur_dir "\\data\\" Name) (slurp filepath))
  )) 
(doseq [i (range 0 (- (count all-files) 0))]
  (file-merge (all-files i) (all-filename i)) 
  ;(spit (str cur_dir "\\data\\" )) 
  ;(spit (str cur_dir "\\dd.txt") (slurp (all-files 1)))
  )


(new-name symbols)
