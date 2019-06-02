;Excercise 1 of Chapter 8 Macros

(def valid-order-details
  {:name "Mitchard Blimmons"
   :email "mitchard.blimmons@gmail.com"})


(def invalid-order-details
  {:name "Mitchard Blimmons"
   :email "mitchard.blimmonsgmail.com"})

(def order-validation
  {
    :name ["The name cannot be empty" empty?]
    :email ["The email cannot be emtpy" empty?
            "The email doesn't look like a mail id" #(not (re-matches #".*@.*\..*" %))]})

(defn error-messages-for
  "Validates the value as given "
  [value msg-validation]
  (map
    first
    (filter
      #((second %) value)
      (partition 2 msg-validation))))

(error-messages-for "" ["The email cannot be emtpy" empty?
                        "The email doesn't look like a mail id" #(not (re-matches #".*@.*\..*" %))])

(not (#(re-matches #".*@.*\..*" %) ""))

(defn validate
  "Validates a given order-detail against the given
  validation and returns the error message for
  each of the fields"
  [detail validations]
  (reduce
    (fn [er-msgs field]
      (let [[key-name value] field
            messages (error-messages-for value (validations key-name))]
        (if (empty? messages)
          er-msgs
          (assoc er-msgs key-name (cons messages (er-msgs key-name))))))
    {}
    detail))

(defn render
  "Dummy implementation for the excercise"
  [value]
  (println (str "Rendering " value)))

(defmacro when-valid
  [details validations & success-calls]
  `(let [errors# (validate ~details ~validations)]
     (if(empty? errors#)
       (do ~@success-calls))))

(when-valid valid-order-details order-validation
    (println "sucess from outside")
    (render :success))

(when-valid invalid-order-details order-validation
    (println "sucess from outside")
    (render :success))
