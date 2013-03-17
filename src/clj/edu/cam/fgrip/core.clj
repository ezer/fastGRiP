(ns edu.cam.fgrip.core
  (:require [noir.server :as server]
            [noir.response :as response])
  (:import (edu.cam.fgrip MarkovChainGenerator
    SwitchSlideModel Parameters State))
  (use noir.core hiccup.core hiccup.page))

(defpage "/" []
  (html
    (html5
      (include-js "/js/bootstrap.js")
      (include-css "/css/bootstrap.css")
      [:body
        [:div {:class "navbar"}
          [:div {:class "navbar-inner"}
            [:a {:class "brand" :href "#"} "Fast GRiP"]]]
        [:div {:class "container"}
          [:form {:class "form-horizontal" :method "post" :action "/"}
            [:div {:class "row"}
              [:div {:class "span6"} 
                [:div {:class "control-group"}
                  [:label "Transcription Factors"]
                  [:div {:class "control-group"}
                    [:textarea {:rows "10" :name "tfs" :class "span5"  :placeholder "TF    start    end    weight    conc"}]]
                  [:div {:class "control-group"}
                    [:label {:class "radio"}
                      [:input {:type "radio" :name "runTime" :id "time" :value "time"} "Run for"]
                      [:input {:type "text" :name "time" :placeholder "300" :class "span1"} "ms"]]
                    [:label {:class "radio"}
                      [:input {:type "radio" :name "runTime" :id "occurance" :value "occurance"} "Run until configuration"]
                      [:input {:type "text" :name "config" :placeholder "(TF1 AND TF2) OR TF3" :class "span3"}]]]  
                  [:div {:class "control-group"}
                    [:button {:type "submit" :class "btn"} "Submit"]]]]
              [:div {:class "span6"}
                [:div {:class "control-group"}
                  [:label "Advanced Options (Defaults are fine in most applications)"]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "t_r"} "sec in random walk"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "t_r" :id "t_r" :placeholder "0.005"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "nonCognateTF"} "num noncognate TFs"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "nonCognateTF" :id "nonCognateTF" :placeholder "10000"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "TFlength"} "avg len of noncognate"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "TFlength" :id "TFlength" :placeholder "46"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "DNAlength"} "length of genome"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "DNAlength" :id "DNAlength" :placeholder "4600000"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "f"} "proportion on DNA"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "f" :id "f" :placeholder "0.9"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "s_l_obs"} "sliding with hops"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "s_l_obs" :id "s_l_obs" :placeholder "90"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "s_l_real"} "sliding without hops"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "s_l_real" :id "s_l_real" :placeholder "36"}]]]]]]]]])))              

;(def ^:dynamic *results*  {})

;(defn save-result-data [id data]
 ; (set! *results* (assoc *results* id data)))

(def results  {})

(defn save-result-data [id data]
  (def results (assoc results id data)))

(defn results-data [id]
  (get results id))

(defpage [:post "/"] {:keys [tfs runTime time config t_r nonCognateTF TFlength DNAlength f s_l_obs s_l_real]}
    (let [sm     (new MarkovChainGenerator)
          pm     (new SwitchSlideModel (new Parameters "params2"))
          new-id (Integer/toString (rand-int (* 10000 10000)) 16)
          data   ( if (.equals runTime "time")
                      (.getJSONs
                        (.buildMarkovChainFromStream sm (new String tfs) 400 pm "params2")
                        (new Double time) 10 "cluster")
                      ;figure out what to do here
                      (.getJSONs
                        (.buildMarkovChain sm "mimickingGRiP1" 2 400 pm "params2")
                        3000 10 "cluster"))]
                    ;(.runSimulationUntil 
                    ;    (.buildMarkovChain sm "mimickingGRiP1" 2 10 pm "params2")
                     ;   (new Double time) "cluster")
                    ;(.runSimulationUntilStateConfigReached
                      ;  (.buildMarkovChain sm "mimickingGRiP1" 2 10 pm "params2")
                       ;   config "cluster"))]
          ;data   ( if (.equals runTime "time")
           ;           ((.runSimulationUntil 
            ;            (.buildMarkovChain sm "Cluster" 2 10 pm "params")
             ;           time "cluster")))
             ;         (.runSimulationUntilStateReached
              ;          (.buildMarkovChain sm "Cluster" (int 2) (int 10) pm "params")
               ;           config "cluster"))]
     (save-result-data new-id data)
     (response/redirect (str "/results/" new-id) data)
     )
  
    )

(defpage "/results/:id" {id :id data :data}
  (let [ geneConfigs (.toString (.get (results-data id) 0))
         network (.get (results-data id) 1)
         area (.get (results-data id) 2)
         jsArray (clojure.string/join [" var genes=" geneConfigs "; \n var network_json = " network "\n var configs = " area])
         firstURL (clojure.string/join ["/download/first/" id ])
         graphURL (clojure.string/join ["/download/graph/" id ])
         timecourseURL (clojure.string/join ["/download/time/" id ])
         statefirstURL (clojure.string/join ["/download/statefirst/" id ])]

    ; render

    
  (html
    (html5
      (include-css "/css/bootstrap.css" "/css/cytoscape.css")
      (include-js "/js/jquery.js" "/js/bootstrap.js" "/js/d3.v3.js" "/js/drawData.js" "/js/json2.min.js" "/js/AC_OETags.min.js" "/js/cytoscapeweb.min.js")
      [:body
        [:div {:class "navbar"}
          [:div {:class "navbar-inner"}
            [:a {:class "brand" :href "#"} "fGRiP"]
            [:ul {:class "nav"} 
              [:li {:class "active"}
                [:a {:href "#"} "Graphs"]]
              [:li
                [:a {:href graphURL} "Network Data"]]
              [:li
                [:a {:href timecourseURL} "Time Course Data"]]
              [:li 
                [:a {:href firstURL} "First Occupancy (TF)"]]
              [:li
                [:a {:href statefirstURL} "First Occupancy (Config)"]]
              ]]]
     ; <button id="myid">I am a button!</button>
      ;  [:button {:id "myid"} "Hihi"]
      
      ;<div id="cytoscapeweb">
       ;     Cytoscape Web will replace the contents of this div with your graph.
       ; </div>
       ; <div id="note">
       ;     <p>Click nodes or edges.</p>
       ; </div>
         [:script {:type "text/javascript"} jsArray]

         [:div {:class "container"}
         [:div {:class "row"}
           [:div {:id "cytoscapeweb" :class "span6"} "replaced text"]
           [:div {:id "note" :class "span6"} "click stuff"]
         ]
         [:div {:class "row"}
           [:div {:id "areaplot" :class "span6"} ]
           [:div {:id "zoomplot" :class "span6"} ]
         ]
         [:div {:class "row"}
           [:div {:id "geneplot" :class "span12"}  ]
         ]
         ]

      ]
      )
  )
  )
)


(defpage "/download/first/:id" {id :id}
  (let [data     (results-data id)
        relevantData (.get data 3)
        formated (clojure.string/join ["\n" relevantData ])]
      (html
        (html5
          [:body
        
            [:div
              formated]]))
    ; render logic
    ; set Content-Disposition: attachment
    ; set filename to filename
   ))

(defpage "/download/graph/:id" {id :id}
  (let [data     (results-data id)
        relevantData (.get data 1)
        formated (clojure.string/join ["\n" relevantData ])]
      (html
        (html5
          [:body
        
            [:div
              formated]]))
    ; render logic
    ; set Content-Disposition: attachment
    ; set filename to filename
   ))

(defpage "/download/time/:id" {id :id}
  (let [data     (results-data id)
        relevantData (.get data 2)
        formated (clojure.string/join ["\n" relevantData ])]
      (html
        (html5
          [:body
        
            [:div
              formated]]))
    ; render logic
    ; set Content-Disposition: attachment
    ; set filename to filename
   ))


(defpage "/download/statefirst/:id" {id :id}
  (let [data     (results-data id)
        relevantData (.get data 4)
        formated (clojure.string/join ["\n" relevantData ])]
      (html
        (html5
          [:body

            [:div
              formated]]))
    ; render logic
    ; set Content-Disposition: attachment
    ; set filename to filename
   ))

(defn -main [& m]
  (server/start 8080))
