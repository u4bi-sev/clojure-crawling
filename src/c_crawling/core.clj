(ns c-crawling.core
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.handler :as h]
            [compojure.route :as route]
            [ring.middleware.json :as m-json]
            [ring.middleware.cors :refer [wrap-cors]]
            [clj-http.client :as client]
            [net.cgrand.enlive-html :as html]
            [clojure.string :as string]))

(defn get-user [id]
  (client/get (str "https://api.github.com/users/" id)))
 
(defn body-parser [url]
  (-> (client/get url)
      :body
      html/html-snippet))

(defn get-title [dom]
  (-> (html/select dom [:title])
      first
      :content
      first))

(defroutes handler
           (GET "/user/:id" [id] (get-user id))
           (GET "/title" [url]
                (response {:title (get-title 
                                   (body-parser "https://dcleaner.github.io/"))}))
           (route/not-found (response {:message "not found"})))

(def app
  (-> (h/api handler)
      (m-json/wrap-json-params)
      (m-json/wrap-json-response)
      (wrap-cors :access-control-allow-origin [#"http://127.0.0.1:5500"] ; #".*"
                 :access-control-allow-methods [:get :put :post :delete])))