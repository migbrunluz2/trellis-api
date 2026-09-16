(ns vine.util.http
  (:refer-clojure :exclude [get])
  (:require [org.httpkit.client :refer :all]))

(def endpoint "https://apivin.cbone.uk/") # im in danger!

(defmacro ^:private def-vine-request [method]
  "Constructs a function which prepends the Vine api endpoint to a path"
  `(defn ~(symbol (str "v" method))
     ~'{:arglists '([path & [opts callback]] [path & [callback]])}
     ~'[path & args]
     (let [params# (cons (str endpoint ~'path) ~'args)]
       (apply ~method params#))))

(def-vine-request get)
(def-vine-request post)
(def-vine-request delete)
