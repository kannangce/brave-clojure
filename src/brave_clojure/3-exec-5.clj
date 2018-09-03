; Hobbit data to be made symmetric


(def hobbit-parts [{:name "head" :size 3}
                   {:name "1-eye" :size 1}
                   {:name "1-ear" :size 1}
                   {:name "mouth" :size 1}
                   {:name "nose" :size 1}
                   {:name "neck" :size 2}
                   {:name "1-shoulder" :size 3}
                   {:name "1-upper-arm" :size 3}
                   {:name "chest" :size 10}
                   {:name "back" :size 10}
                   {:name "1-forearm" :size 3}
                   {:name "abdomen" :size 6}
                   {:name "1-kidney" :size 1}
                   {:name "1-hand" :size 2}
                   {:name "1-knee" :size 2}
                   {:name "1-thigh" :size 4}
                   {:name "1-lower-leg" :size 3}
                   {:name "1-achilles" :size 1}
                   {:name "1-foot" :size 2}])


(defn replace-part-number
  "Replaces the part number in the part with the given number"
  [part number]
  (clojure.string/replace part #"^\d-" (str number "-")))

(defn generate-parts
  "Generates the part for the given number"
  [part number]
  {:name (replace-part-number (:name part) number) :size (:size part)})

(defn equivalent-parts
  "Generate the equivalent parts for the given count starting from
  1 to count"
  [part count]
  (map #(generate-parts part %1)(range 1 (inc count))))


(defn match-parts
  "For each given part in the given parts if the part is starting
  with a digit generates an equivalent parts from 1 to the given count."
  [parts count]
  (reduce
    #(into
      %1
      (set
        (flatten [%2 (equivalent-parts %2 count)])))

    [] parts))


(match-parts hobbit-parts 2)
