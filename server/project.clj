(defproject c-crawling "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [compojure "1.6.1"]
                 [ring/ring-json "0.4.0"]
                 [ring-cors "0.1.12"]
                 [clj-http "3.9.0"]
                 [enlive "1.1.6"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler c-crawling.core/app})