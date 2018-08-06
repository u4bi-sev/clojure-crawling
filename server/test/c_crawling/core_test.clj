(ns c-crawling.core-test
  (:require [clojure.test :refer :all]
            [clj-http.client :as client]
            [c-crawling.core :refer [body-parser get-title]]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest title-test
  (testing "dcleaner title")
  (is (= (get-title
            (body-parser "https://dcleaner.github.io/"))
          "DCleaner - 디시 클리너")))