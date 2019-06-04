
(defn bing-search
  "Searches the given string from bing"
  [search-str]
  (slurp (str "https://www.bing.com/search?q=" search-str))
  "from bing")


(defn google-search
  "Searches the given string from google"
  [search-str]
  (slurp (str "https://google.com?q=" search-str))
  "from google")


(let [search-promise (promise)]
 (doseq [search [bing-search google-search]]
   (future (if-let [result (search "some_string")]
             (deliver search-promise result))))
 (println (str "Fastest result " @search-promise)))
