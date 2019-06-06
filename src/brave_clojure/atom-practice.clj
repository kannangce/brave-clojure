(defn alter-fn
  [ref-var tag update-using]
  (future
    (dosync
      (println (str "Entering - " tag))
      (println (str @ref-var "-" tag))
      (update-using ref-var inc)
      (println (str "After first inc - " tag))
      (println (str @ref-var "-" tag))
      (Thread/sleep 1000)
      (println (str "Before second inc - " tag))
      (println (str @ref-var "-" tag))
      (update-using ref-var inc)
      (println (str "After second inc - " tag))
      (println (str @ref-var "-" tag))
      (println (str "Exiting - " tag)))))

(do
  (def v1 (ref 0))
  (alter-fn v1 "1" alter)
  (alter-fn v1 "2" alter)
  (Thread/sleep 2200)
  (println @v1))

  ; Entering - 1
  ; 0-1
  ; After first inc - 1
  ; 1-1
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Entering - 2
  ; 0-2
  ; Before second inc - 1
  ; 1-1
  ; After second inc - 1
  ; 2-1
  ; Exiting - 1
  ; Entering - 2
  ; 2-2
  ; After first inc - 2
  ; 3-2
  ; Before second inc - 2
  ; 3-2
  ; After second inc - 2
  ; 4-2
  ; Exiting - 2
  ; 4)

; Multiple entries of "Entering - 2" says that the dosyn retries
; Finaly "2" is not retired only after "1" exits

