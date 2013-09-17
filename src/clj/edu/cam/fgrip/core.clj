(ns edu.cam.fgrip.core
  (:require [noir.server :as server]
            [noir.response :as response])
  (:import (edu.cam.fgrip MarkovChainGenerator
    SwitchSlideModel Parameters State MarkovResult))
  (use noir.core hiccup.core hiccup.page))

(defpage "/" []
  (html
    (html5
      (include-js "/js/bootstrap.js")
      (include-css "/css/bootstrap.css")
      (include-js "/js/jquery.js")
      [:body
        [:div {:class "navbar"}
          [:div {:class "navbar-inner"}
            [:a {:class "brand" :href "#"} "fastGRiP"]
            [:ul {:class "nav"} 
              [:li {:class "active"}
                [:a {:href "/"} "Uniform Landscape"]]
              [:li
                [:a {:href "pwm_uniform"} "Uniform Landscape, PWM weights"]]
              [:li
                [:a {:href "nonuniform"} "Nonuniform Landscape"]]
             [:li
                [:a {:href "promoter_arhitecture.pdf"} "Help"]]
              ]]]
        [:div {:class "container"}
          [:form {:class "form-horizontal" :method "post" :onsubmit "inputValue=new String($(tfs).val());
                                                                     arrVals=(inputValue.trim()).split(/\\s+/);
                                                                     if($(tfs).val()==\"\"){ window.alert(\"User must include binding sites.\"); return false; }; 
                                                                     if($(time).val().length>4){ window.alert(\"Please run for less time. Limit is currently 9999 seconds.\"); return false; }; 
                                                                     if( (($(tfs).val()).trim().split(/\\s+/).length)%5!=0){window.alert(\"TF binding site table does not have valid number of elements.\"); return false;};
                                                                     if( (arrVals.length/5)>8 ){ window.alert(\"We are currently limiting the number of binding sites to 8.\"); return false;}
                                                                    
                                                                     var tfSet={};
                                                                     
                                                                     for (var i=0; i<arrVals.length; i++)
                                                                     {
                                                                      
                                                                       
                                                                      if( (i%5)!=0 && (isNaN(5+arrVals[i])  || parseFloat(arrVals[i]) <0  )){
                                                                        window.alert(\"Start and end locations, binding affinities, and abundances must be positive numbers:  \"+arrVals[i]);
                                                                        return false;
                                                                      }

                                                                      if( ( ((i+4)%5)==0  ||  ((i+3)%5)==0 )&& ((parseFloat(arrVals[i]))%1)!=0){
                                                                        window.alert(\"Start and end locations must be Integers:  \"+arrVals[i]);
                                                                        return false;
                                                                      }

                                                                      if( ((i+4)%5)==0 &&  parseFloat(arrVals[i+1]) <= parseFloat(arrVals[i])){
                                                                         window.alert(\"TF binding site must have a length >0bp:  \"+arrVals[i-1]);
                                                                        return false;
                                                                      }

                                                                      if( (i%5)==0 ){
                                                                        var str=arrVals[i]+arrVals[i+1];
                                                                        
                                                                        if( (str in tfSet) ){
                                                                           window.alert(\"Duplicate TF binding sites (with the same TF name and location):  \"+arrVals[i]);
                                                                           return false;
                                                                        }

                                                                        tfSet[str] = true;
                                                                        
                                                                      }
                                                                      
                                                                      
                                                                     }
                                                                    
                                                                     
                                                                     return true; 
                                                                      " :action "/"}
            [:div {:class "row"}
              [:div {:class "span6"} 
                [:div {:class "control-group"}
                  [:label "Transcription Factors"]
                  [:div {:class "control-group"}
                    [:textarea {:rows "10" :name "tfs" :class "span5"  :id "tfs" :placeholder "TF_name    start    end    tau_0    copy_number"}]]
                  [:div {:class "control-group"}
                      [:input {:type "text" :name "time" :id "time" :placeholder "300" :class "span1"} " seconds of simulation time"]]
                  [:div {:class "control-group"}
                    [:button {:type "submit" :class "btn"} "Submit"]]
                  [:div {:class "control-group"}
                     [:button {:type "button" :class "btn" :onclick "$(\"#tfs\").val(\"Lrp 1 10 0.33 10 \\nHNS 11 20 3.3 100 \\nLrp 15 35 0.33 10\\n\");"} "Demo Data"]]]]
              [:div {:class "span6"}
                [:div {:class "control-group"}
                  [:label "Advanced Options (Defaults are fine in most applications)"]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "k_a"} "propensity of association"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "k_a" :id "k_a" :placeholder "0.8654"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "nonCognateTF"} "noncognate TFs"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "nonCognateTF" :id "nonCognateTF" :placeholder "0"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "TFlength"} "avg length noncognate"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "TFlength" :id "TFlength" :placeholder "46"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "DNAlength"} "bases simulated"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "DNAlength" :id "DNAlength" :placeholder "20000"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "f"} "proportion of time bound"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "f" :id "f" :placeholder "0.9"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "beta"} "beta"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "beta" :id "beta" :placeholder "2"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "s_l"} "sliding length"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "s_l" :id "s_l" :placeholder "90"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "e_star"} "e*"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "e_star" :id "e_star" :placeholder "16"}]]]]]]]]])))              


(defpage "/pwm_uniform" []
  (html
    (html5
      (include-js "/js/jquery.js")
      (include-js "/js/bootstrap.js")
      (include-css "/css/bootstrap.css")
      [:body
    [:div {:class "navbar"}
        [:div {:class "navbar-inner"}
            [:a {:class "brand" :href "#"} "fastGRiP"]
            [:ul {:class "nav"} 
              [:li 
                [:a {:href "/"} "Uniform Landscape"]]
              [:li {:class "active"}
                [:a {:href "pwm_uniform"} "Uniform Landscape, PWM weights"]]
              [:li
                [:a {:href "nonuniform"} "Nonuniform Landscape"]]
             [:li
                [:a {:href "promoter_arhitecture.pdf"} "Help"]]
              ]]]
        [:div {:class "container"}
          [:form {:class "form-horizontal" :method "post" :onsubmit "inputValue=new String($(tfs).val());
                                                                     arrVals=(inputValue.trim()).split(/\\s+/);
                                                                     if($(tfs).val()==\"\"){ window.alert(\"User must include binding sites.\"); return false; }; 
                                                                     if($(time).val().length>4){ window.alert(\"Please run for less time. Limit is currently 9999 seconds.\"); return false; }; 
                                                                     if( (($(tfs).val()).trim().split(/\\s+/).length)%5!=0){window.alert(\"TF binding site table does not have valid number of elements.\"); return false;};
                                                                     if( (arrVals.length/5)>8 ){ window.alert(\"We are currently limiting the number of binding sites to 8.\"); return false;}
                                                                    
                                                                     var tfSet={};
                                                                     
                                                                     for (var i=0; i<arrVals.length; i++)
                                                                     {
                                                                      
                                                                       
                                                                      if( (i%5)!=0 && (isNaN(5+arrVals[i])  || parseFloat(arrVals[i]) <0  )){
                                                                        window.alert(\"Start and end locations, binding affinities, and abundances must be positive numbers:  \"+arrVals[i]);
                                                                        return false;
                                                                      }

                                                                      if( ( ((i+4)%5)==0  ||  ((i+3)%5)==0 )&& ((parseFloat(arrVals[i]))%1)!=0){
                                                                        window.alert(\"Start and end locations must be Integers:  \"+arrVals[i]);
                                                                        return false;
                                                                      }

                                                                      if( ((i+4)%5)==0 &&  parseFloat(arrVals[i+1]) <= parseFloat(arrVals[i])){
                                                                         window.alert(\"TF binding site must have a length >0bp:  \"+arrVals[i-1]);
                                                                        return false;
                                                                      }

                                                                      if( (i%5)==0 ){
                                                                        var str=arrVals[i]+arrVals[i+1];
                                                                        
                                                                        if( (str in tfSet) ){
                                                                           window.alert(\"Duplicate TF binding sites (with the same TF name and location):  \"+arrVals[i]);
                                                                           return false;
                                                                        }

                                                                        tfSet[str] = true;
                                                                        
                                                                      }
                                                                      
                                                                      
                                                                     }
                                                                    
                                                                     
                                                                     return true; 
                                                                      " :action "/"}
            [:div {:class "row"}
              [:div {:class "span6"} 
                [:div {:class "control-group"}
                  [:label "Transcription Factors"]
                  [:div {:class "control-group"}
                    [:textarea {:rows "10" :name "tfs" :id "tfs" :class "span5"  :placeholder "TF_name    start    end    PWM_score    copy_number"}]]
                  [:div {:class "control-group"}
                      [:input {:type "text" :name "time" :id "time" :placeholder "300" :class "span1"} " seconds of simulation time"]]
                  [:div {:class "control-group"}
                    [:button {:type "submit" :class "btn"} "Submit"]]]]
              [:div {:class "span6"}
                [:div {:class "control-group"}
                  [:label "Advanced Options (Defaults are fine in most applications)"]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "k_a"} "propensity of association"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "k_a" :id "k_a" :placeholder "0.8654"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "nonCognateTF"} "noncognate TFs"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "nonCognateTF" :id "nonCognateTF" :placeholder "0"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "TFlength"} "avg length noncognate"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "TFlength" :id "TFlength" :placeholder "46"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "DNAlength"} "bases simulated"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "DNAlength" :id "DNAlength" :placeholder "20000"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "f"} "proportion of time bound"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "f" :id "f" :placeholder "0.9"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "beta"} "beta"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "beta" :id "beta" :placeholder "2"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "s_l"} "sliding length"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "s_l" :id "s_l" :placeholder "90"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "t_r"} "seconds for random walk"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "t_r" :id "t_r" :placeholder "0.005"}]]]]]]]]])))              


(defpage "/nonuniform" []
  (html
    (html5
      (include-js "/js/jquery.js")
      (include-js "/js/bootstrap.js")
      (include-css "/css/bootstrap.css")
      [:body
        [:div {:class "navbar"}
        [:div {:class "navbar-inner"}
            [:a {:class "brand" :href "#"} "fastGRiP"]
            [:ul {:class "nav"} 
              [:li 
                [:a {:href "/"} "Uniform Landscape"]]
              [:li
                [:a {:href "pwm_uniform"} "Uniform Landscape, PWM weights"]]
              [:li {:class "active"}
                [:a {:href "nonuniform"} "Nonuniform Landscape"]]
             [:li
                [:a {:href "promoter_arhitecture.pdf"} "Help"]]
              ]]]
        [:div {:class "container"}
          [:form {:class "form-horizontal" :method "post" :onsubmit "inputValue=new String($(tfs).val());
                                                                     arrVals=(inputValue.trim()).split(/\\s+/);
                                                                     if($(tfs).val()==\"\"){ window.alert(\"User must include binding sites.\"); return false; }; 
                                                                     if($(time).val().length>4){ window.alert(\"Please run for less time. Limit is currently 9999 seconds.\"); return false; }; 
                                                                     if( (($(tfs).val()).trim().split(/\\s+/).length)%4!=0){window.alert(\"TF binding site table does not have valid number of elements.\"); return false;};
                                                                     if( (arrVals.length/4)>8 ){ window.alert(\"We are currently limiting the number of binding sites to 8.\"); return false;}
                                                                    
                                                                     var tfSet={};
                                                                     
                                                                     for (var i=0; i<arrVals.length; i++)
                                                                     {
                                                                      
                                                                       
                                                                      if( (i%4)!=0 && (isNaN(5+arrVals[i])  || parseFloat(arrVals[i]) <0  )){
                                                                        window.alert(\"Start and end locations, binding affinities, and abundances must be positive numbers:  \"+arrVals[i]);
                                                                        return false;
                                                                      }

                                                                      if( ( ((i+3)%4)==0  ||  ((i+2)%4)==0 )&& ((parseFloat(arrVals[i]))%1)!=0){
                                                                        window.alert(\"Start and end locations must be Integers:  \"+arrVals[i]);
                                                                        return false;
                                                                      }

                                                                      if( ((i+3)%4)==0 &&  parseFloat(arrVals[i+1]) <= parseFloat(arrVals[i])){
                                                                         window.alert(\"TF binding site must have a length >0bp:  \"+arrVals[i-1]);
                                                                        return false;
                                                                      }

                                                                      if( (i%4)==0 ){
                                                                        var str=arrVals[i]+arrVals[i+1];
                                                                        
                                                                        if( (str in tfSet) ){
                                                                           window.alert(\"Duplicate TF binding sites (with the same TF name and location):  \"+arrVals[i]);
                                                                           return false;
                                                                        }

                                                                        tfSet[str] = true;
                                                                        
                                                                      }
                                                                      
                                                                      
                                                                     }
                                                                    
                                                                     
                                                                     return true; 
                                                                      " :action "/pwm_uniform"}
            [:div {:class "row"}
              [:div {:class "span6"} 
                [:div {:class "control-group"}
                  [:label "Transcription Factor Info"]
                  [:div {:class "control-group"}
                    [:textarea {:rows "10" :name "tfs" :id "tfs" :class "span5"  :placeholder "TF_name    start    end    copy_number"}]]
                  [:label "DNA in fasta format"]
                  [:div {:class "control-group"}
                    [:textarea {:rows "10" :name "dna" :id "dna" :class "span5"  :placeholder ">seq1 GAATTC"}]]
                  [:div {:class "control-group"}
                      [:input {:type "text" :name "time" :id "time" :placeholder "300" :class "span1"} " seconds of simulation time"]]
                  [:div {:class "control-group"}
                    [:button {:type "submit" :class "btn"} "Submit"]]]]
              [:div {:class "span6"}
                [:div {:class "control-group"}
                  [:label "Advanced Options (Defaults are fine in most applications)"]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "k_a"} "propensity of association"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "k_a" :id "k_a" :placeholder "0.8654"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "nonCognateTF"} "noncognate TFs"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "nonCognateTF" :id "nonCognateTF" :placeholder "0"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "TFlength"} "avg length noncognate"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "TFlength" :id "TFlength" :placeholder "46"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "DNAlength"} "bases simulated"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "DNAlength" :id "DNAlength" :placeholder "20000"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "f"} "proportion of time bound"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "f" :id "f" :placeholder "0.9"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "beta"} "beta"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "beta" :id "beta" :placeholder "2"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "s_l"} "sliding length"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "s_l" :id "s_l" :placeholder "90"}]]]
                  [:div {:class "control-group"}
                    [:label {:class "control-label" :for "t_r"} "seconds for random walk"]
                    [:div {:class "controls"}
                      [:input {:type "text" :name "t_r" :id "t_r" :placeholder "0.005"}]]]]]]]]])))              

             







;(def ^:dynamic *results*  {})

;(defn save-result-data [id data]
 ; (set! *results* (assoc *results* id data)))

(def results  {})

(defn save-result-data [id data]
  (def results (assoc results id data)))

(defn results-data [id]
  (get results id))


(defpage "/error" []
  (html
    (html5
      (include-js "/js/bootstrap.js")
      (include-css "/css/bootstrap.css")
      (include-js "/js/jquery.js")
      [:body
        [:div {:class "navbar"}
          [:div {:class "navbar-inner"}
            [:a {:class "brand" :href "#"} "fastGRiP"]
            [:ul {:class "nav"} 
              [:li {:class "active"}
                [:a {:href "/"} "Uniform Landscape"]]
              [:li
                [:a {:href "pwm_uniform"} "Uniform Landscape, PWM weights"]]
              [:li
                [:a {:href "nonuniform"} "Nonuniform Landscape"]]
             [:li
                [:a {:href "promoter_arhitecture.pdf"} "Help"]]
              ]]]
        [:div {:class "container"} ]])))


(defpage [:post "/"] {:keys [tfs time k_a nonCognateTF TFlength DNAlength f s_l e_star]}
    (let [sm     (new MarkovChainGenerator)
          params (.updateParameters (new Parameters "params2") k_a nonCognateTF TFlength DNAlength f s_l e_star)
          pm     (try(new SwitchSlideModel params) (catch Exception t nil))
          new-id (Integer/toString (rand-int (* 10000 10000)) 16)
          data   (try (.getResults
                        (.buildMarkovChainFromStream sm (new String tfs) 400 pm params )
                        (new Double (if(.equals "" time) 300.0 time)) 2 "cluster")
                       (catch Exception t nil)
                       )]
                      ;figure out what to do here
                     
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


     (if (nil? data)
       
            (response/redirect "/error")
        
          

      )

     
     (save-result-data new-id data)
     (response/redirect (str "/results/" new-id) data))
     
  
    )



(defpage [:post "/pwm_uniform"] {:keys [tfs time k_a nonCognateTF TFlength DNAlength f s_l t_r]}
    (let [sm     (new MarkovChainGenerator)
          params (.updateParameters (new Parameters "params") k_a nonCognateTF TFlength DNAlength f s_l t_r)
          pm     (new SwitchSlideModel params)
          new-id (Integer/toString (rand-int (* 10000 10000)) 16)
          data   (.getJSONs
                        (.buildMarkovChainFromStream sm (new String tfs) 400 pm params )
                        (new Double time) 10 "cluster")]
                      ;figure out what to do here
                     
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
  (let [ geneConfigs (.toString (.get (.getJsons (results-data id)) 0))
         network (.get (.getJsons (results-data id)) 1)
         namesList (.get (.getJsons (results-data id)) 2)
         linksList (.get (.getJsons (results-data id)) 3)
         area (.get (.getJsons (results-data id)) 4)
         jsArray (clojure.string/join [" var genes=" geneConfigs "; \n var network_json = " network "\n var configs = " area "\n var edges = " linksList "\n var names = " namesList])
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
              ]]]


              ;
              ;              [:li
               ; [:a {:href statefirstURL} "First Occupancy (Config)"]]
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
           [:div {:id "genepics" :class "span6"} ]
           [:div {:id "zoomplot" :class "span6"}  ]
         ]]
         

      ]
      )
  )
  )
)


(defpage "/download/first/:id" {id :id}
  (let [data     (results-data id)
        relevantData (.getFirstOccupancy data)
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
        relevantData (.get (.getJsons data) 1)
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
        relevantData (.getTimeCourse data)
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

(defn -main [port]
  (server/start (Integer. port)))
