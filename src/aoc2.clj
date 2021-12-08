(ns aoc2
  (:require util))

(def input-val (util/read-lines "aoc2.txt"))

; ------- Part 1 -------
(defn count-chars
  "문자열을 하나 받아서, 각 문자의 갯수를 세어 map으로 반환합니다.
  Example)
  Input: abcdee
  Output: {a 1 b 1 c 1 d 1 e 2}
  "
  [f]
  (reduce (fn [counted f1]
            (assoc counted f1 (inc (get counted f1 0))))
          {}
          f))

(defn exists-any-letter-appears-n-times?
  "문자 출현 빈도 맵을 받아서, n번 나타나는 문자가 있는지 여부를 정수(1/0)로 반환합니다.
  Input: {a 1 b 2 c 3}, 3
  Output: 1"
  [m times]
  (if (contains? (set (vals m)) times) 1 0))

(defn exists-any-letter-appears-2x? [m]
  (exists-any-letter-appears-n-times? m 2))

(defn exists-any-letter-appears-3x? [m]
  (exists-any-letter-appears-n-times? m 3))

(comment
  (->> input-val
       (map count-chars)
       (map (juxt
              exists-any-letter-appears-2x?
              exists-any-letter-appears-3x?))
       (apply map +)
       (apply *)))

; ------- Part 2 -------
(defn get-permutation
  "배열의 제곱을 반환합니다.
  Input: [a b c]
  Output: [[a a] [a b] [a c] [b a] [b b] [b c] [c a] [c b] [c c]]
  "
  [xs]
  (for [a xs, b xs] [a b]))

(defn get-hamming-distance [f g]
  "두 string f, g간의 해밍 거리를 구합니다.
  Example: asdf, asff -> 1
           abcde, abdcb -> 2
  "
  (reduce (fn [distance, [f1 g1]]
            (if (= f1 g1) distance (inc distance)))
          0
          (map vector f g)))

(defn hamming-distance-1? [[a b]]
  (= 1 (get-hamming-distance a b)))

; 이거 함수로 묶지 말고 펼치는게 나을수도?
(defn find-correct-box-pair
  "string pair의 리스트를 받아서, 그중에 hamming distance가 1인 pair를 반환합니다.
  Input: [[aa ab] [ac de]]
  Output [aa ab]
  "
  [box-pairs]
  (->> box-pairs
       (filter hamming-distance-1?)
       first))

(defn get-common-letters [f g]
  (reduce (fn [commons, [f1 g1]]
            (if (= f1 g1) (str commons f1) commons))
          ""
          (map vector f g)))

(comment
  (->> input-val
       get-permutation
       find-correct-box-pair
       (apply get-common-letters)))
