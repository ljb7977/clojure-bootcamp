(ns dev-dive.06-portal
  (:require [dev-dive.data :refer [test-results
                                   generate-test-results]]))

(defn ->tap [x]
  (tap> x)
  x)

(defn filter-students [test-results]
  ;; 3. 사이사이 ->tap 사용
  (->> test-results
       (filter #(> (:score %) 80))
       (map :name)
       (set)))

(comment
  ;; 1. 기본 사용법
  (tap> "Hello world")
  (tap> [1 2 3 4 5])
  (tap> {:name "Jubeen"
         :age 26})

  ;; 2. 스레딩 매크로
  (->> test-results
       (filter #(< (:score %) 60))
       ;; 여기서 filter의 결과값을 알고 싶다면..?
       (map :score))

  ;; 3
  (filter-students test-results)

  ;; 4. 많은 양의 데이터
  (tap> (generate-test-results)))
