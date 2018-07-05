;; # 1 Use the str, vector, list, hash-map, and hash-set functions.
;----------------------
;str
(def aStr (str "expected " "to " "concatenate."))
(println aStr)

;list
(def lst (list 2 3 4))
(println lst)

; vector
(def vct (vector 4 5 6))
(println vct)

;hash-map
(def aMap (hash-map :a 5 :b 6 :c 7))
(println (:a aMap))

;hash-set
(def aSet (hash-set 1 1 1 2 2 3))
(println aSet)

;; #2 Write a function that takes a number and adds 100 to it.
;-----------------------
(defn add100
  [num]
  "Adds 10 to the given number"
  (+ num 100))
(add100 10)

;; 3 Write a function, dec-maker, that works exactly like the function inc-maker
; except with subtraction:)
;-----------------
(defn dec-maker
  [dec-by]
  "Decrements one from the give number"
  #(- %1 dec-by))

(def dec9 (dec-maker 9))
(dec9 10)


;; Write a function, mapset, that works like map except the return value is a set:
(defn mapset
  [fxn param]
  (println fxn)
  (hash-set (map fxn param)))

(mapset inc [1 1 2 2])
