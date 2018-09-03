; Hobbit data to be made symmetric
(def hobbit-parts [{:name "head" :size 3}
                   {:name "left-eye" :size 1}
                   {:name "left-ear" :size 1}
                   {:name "mouth" :size 1}
                   {:name "nose" :size 1}
                   {:name "neck" :size 2}
                   {:name "left-shoulder" :size 3}
                   {:name "left-upper-arm" :size 3}
                   {:name "chest" :size 10}
                   {:name "back" :size 10}
                   {:name "left-forearm" :size 3}
                   {:name "abdomen" :size 6}
                   {:name "left-kidney" :size 1}
                   {:name "left-hand" :size 2}
                   {:name "left-knee" :size 2}
                   {:name "left-thigh" :size 4}
                   {:name "left-lower-leg" :size 3}
                   {:name "left-achilles" :size 1}
                   {:name "left-foot" :size 2}])


(defn left-to-right
  "Generates the right equivalent of the left part"
  [part]
  (clojure.string/replace part #"^left-" "right-"))

(defn equivalent-part
  "Generate the equivalent right part of the given left part"
  [part]
  {:name (left-to-right (:name part)) :size (:size part)})

(defn match-parts
  "Returns the vector with given parts along with the generated
   right part"
  [parts]
  (reduce #(into %1 (set [%2 (equivalent-part %2)])) [] parts))


(match-parts hobbit-parts)
