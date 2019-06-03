(defmacro my-or
  "Equivalent of or"
  ([] nil)
  
  ([expr]
   `~expr)

  ([expr & other-exprs]
   `(let [val# (my-or ~expr)]
      (if val# val# (my-or ~@other-exprs)))))
      
;;;;;;;;;;;;;;;;;
; Tests
(my-or
  (do (println "(= 1 2)") (= 1 2))
  (do (println "(= 2 3)") (= 2 3))
  (do (println "(= 1 1)") (= 1 1)))

;  (= 1 2)
;  (= 2 3)
;  (= 1 1)
;  user=>
;  true)  


(my-or
  (do (println "(= 1 1)") (= 1 1))
  (do (println "(= 1 2)") (= 1 2))
  (do (println "(= 2 3)") (= 2 3)))

; (= 1 1)
; user=>
; true


(my-or
  (do (println "(= 1 2)") (= 1 2))
  (do (println "(= 1 1)") (= 1 1))
  (do (println "(= 2 3)") (= 2 3)))

; (= 1 2)
; (= 1 1)
; user=>
; true

(my-or
  (do (println "(= 1 2)") (= 1 2)))

;(= 1 2)
;user=>
;false


(my-or
  (do (println "(= 1 1)") (= 1 1)))

; (= 1 1)
; user=>
; true

(my-or)

; user=>
; nil
