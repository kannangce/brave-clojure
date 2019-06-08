(def wc (atom {}))

(defn  wait-for
  [futures]
  (every? #(@%) futures))

(defn download
  []
  (slurp "https://www.braveclojure.com/random-quote"))

(defn update-atom
  [val]
  (swap! wc #(merge-with (fn[a b] (+ a b)) % val)))


(defn quote-word-count
  [no-of-quotes]
  (wait-for (repeatedly
              no-of-quotes (fn[]
                            (do
                             (future
                              #(->> (download)
                                (.split #"\s")
                                (frequencies)
                                (update-atom)))))))
  @wc)


(quote-word-count 5)
