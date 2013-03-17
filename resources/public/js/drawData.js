

function drawArea(divName, maxTime, ylab){
  
        var margin = {top: 20, right: 20, bottom: 30, left: 50},
        width = 499 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom;

        var xScale = d3.scale.linear()
          .range([0, width])
          .domain([0, maxTime]);
        var numSims=400;
        var yScale = d3.scale.linear()
          .range([height, 0])
          .domain([0, numSims]); //number of simulations

        var color = d3.scale.category20();

        var xAxis = d3.svg.axis()
          .scale(xScale)
          .orient("bottom");

        var yAxis = d3.svg.axis()
          .scale(yScale)
          .orient("left");

        var area = d3.svg.area()
          .x(function(d) { return xScale(d.time); })
          .y0(function(d) { return yScale(d.y0); })
          .y1(function(d) { return yScale(d.y0 + d.y); });

        
        var stack = d3.layout.stack()
          .values(function(d) { return d.values; });

        var svg = d3.select(divName).append("svg")
         .attr("width", width + margin.left + margin.right)
         .attr("height", height + margin.top + margin.bottom)
         .append("g")
         .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

         

        color.domain(d3.keys(configs[0]).filter(function(key) { return key !== "time"; }));

        var browsers = stack(color.domain().map(function(name) {
                       return {
                        name: name,
                        values: configs.map(function(d) {
                           return {time: d.time, y: d[name]};
                          })
                       };
                 }));
        //xScale.domain(d3.extent(configs, function(d) { return d.time; }));

        var browser = svg.selectAll(".browser")
                         .data(browsers)
                         .enter().append("g")
                         .attr("class", "browser");

        browser.append("path")
               .attr("class", "area")
               .attr("d", function(d) { return area(d.values); })
               .style("fill", function(d) { return color(d.name); })
               .on("mouseover", function(d) {  
                  drawGenes("#geneplot", genes[0], d.name, [""], 350);
                  
                  

                }
                );

        browser.append("text")
               .datum(function(d) { return {name: d.name, value: d.values[d.values.length - 1]}; })
               .attr("transform", function(d) { return "translate(" + xScale(d.value.time) + "," + yScale(d.value.y0 + d.value.y / 2) + ")"; })
               .attr("x", -50)
               .attr("dy", ".35em")
               .attr("opacity", function(d){
                if(d.value.y >= numSims/10.0){
                  return 1;
                }
                return 0;
               })
               .text(function(d) { return d.name; });

        svg.append("text")
               .attr("class", "x label")
               .attr("text-anchor", "end")
               .attr("x", width)
               .attr("y", height +30 )
               .text("time");
        if(ylab){
        svg.append("text")
               .attr("class", "y label")
               .attr("text-anchor", "end")
               .attr("y", -50)
               .attr("dy", ".75em")
               .attr("transform", "rotate(-90)")
               .text("simulations");
        }
        svg.append("g")
           .attr("class", "x axis")
           .attr("transform", "translate(0," + height + ")")
           .call(xAxis);

        svg.append("g")
           .attr("class", "y axis")
           .call(yAxis);

}

function drawGenes(divName, config, stateType, description, leftBuffer){
           var w=300;
           var h=500;
           var topBuffer=100;
           var bins=[0, 0];
           
           d3.select(divName).selectAll("svg").remove();

           var svg = d3.select(divName).append("svg")
              .attr("width", w+leftBuffer)
              .attr("height", h);
            var xScale = d3.scale.linear()
                     .domain([d3.min(config, function(d){ return d.start; }), d3.max(config, function(d){ return d.end; })])
                     .range([0, w]);
            var rects = svg.selectAll("rect")
              .data(config)
              .enter()
              .append("rect")
              .attr("x", function(d, i){
                return leftBuffer+xScale(d.start);
              })
              .attr("y", function(d, i){
                if(i==0){
                  bins[0]=d.end;
                  return 5+topBuffer;
                }
                for(var j = 0; j<bins.length; j++){
                  if(bins[j]<d.start){
                    bins[j]=d.end;
                    return (h/50*j)+(5*(1+j))+topBuffer;
                  }
                }
                bins[bins.length]=d.end;
                return (h/50*(bins.length-1))+(5*(bins.length))+topBuffer;
              })
              .attr("width", function(d, i){
                return xScale(d.end)-xScale(d.start);
              })
              .attr("height", function(d, i){
                return h/50;
              })
              .attr("fill", function(d, i){
                if( ((stateType >> i) & 0x1) )
                {
                  return "red";
                }

                return "black";
              });

              var bins=[0, 0];
              svg.selectAll("text").data(config)
                                   .enter()
                                   .append("svg:text")
                                   .attr("x", function(d, i){
                return xScale(d.start)+5+leftBuffer;
              })
              .attr("y", function(d, i){
                if(i==0){
                  bins[0]=d.end;
                  return 3+h/50+topBuffer;
                }
                for(var j = 0; j<bins.length; j++){
                  if(bins[j]<d.start){
                    bins[j]=d.end;
                    return (h/50*j)+(5*(1+j))+h/50-2+topBuffer;
                  }
                }
                bins[bins.length]=d.end;
                return (h/50*(bins.length-1))+(5*(bins.length))+h/50-2+topBuffer;
              })
              .text(function(datum) {return datum.name;})
              .attr("fill", "white")
              .attr("font-size", 8);

            svg.append("text")
               .attr("y", topBuffer-50)
               .attr("dy", ".75em")
               .attr("x", leftBuffer)
               .text("Gene Configuration of Highlighted Element:");

/*
            svg.append("text").data(description)
                                 .attr("y", function(d, i){ return topBuffer+bins.length*h/50+100+h/25*i; })
                                 .attr("x", leftBuffer);
                                 */

             }


window.onload = function() {
                // id of Cytoscape Web container div
                var div_id = "cytoscapeweb";
                
                // create a network model object
                /*
                var network_json = {
                        // you need to specify a data schema for custom attributes!
                        "dataSchema": {
                            "nodes": [ { "name": "label", "type": "string" },
                                     { "name": "foo", "type": "string" },
                                     { "name": "config", "type": "int"}
                            ],
                            "edges": [ { "name": "label", "type": "string" },
                                     { "name": "weight", "type": "float" }
                            ]
                        },
                        // NOTE the custom attributes on nodes and edges
                        "data": {
                            "nodes": [ { "id": "1", "label": x.toString(), "foo": "2", "config": 1 },
                                     { "id": "2", "label": "2", "foo": "3", "config": 2 }
                            ],
                            "edges": [ { "id": "2to1", "target": "1", "source": "2", "label": "2 to 1", "weight": 0.2 }
                            ]
                        }
                };
                */
                // initialization options
                var options = {
                    swfPath: "/js/CytoscapeWeb",
                    flashInstallerPath: "/js/playerProductInstall"
                };
                var maxTime= d3.max(configs, function(d){ return d.time; });
                drawArea("#areaplot", maxTime, true);
                
                if(maxTime>200){
                  drawArea("#zoomplot", 100, false);
                }
                
                var vis = new org.cytoscapeweb.Visualization(div_id, options);
                
               

                

                // callback when Cytoscape Web has finished drawing
                vis.ready(function() {
                
                    // add a listener for when nodes and edges are clicked
                    vis.addListener("click", "nodes", function(event) {
                        handle_click(event);
                    })
                    .addListener("click", "edges", function(event) {
                        handle_click(event);
                    });
                    // This edge width mapper specifies the minimum and maximum data values for the scale.
                    // Weights lower than 0.1 are given a width of 1, and weights higher than 1.0 are given a width of 4.
                    var widthMapper = { attrName: "weight",  minValue: 1, maxValue: 10, minAttrValue: d3.min(network_json.data.edges, function(d){return d.weight; }), maxAttrValue: d3.max(network_json.data.edges, function(d){return d.weight; }) };
                     var visual_style = {
                    edges: {
                        targetArrowShape: "ARROW",
                        width: {defaultValue: 1,
                        	    continuousMapper: widthMapper }
                    }
                    };

                    vis.visualStyle(visual_style);
 

                    function handle_click(event) {
                         var target = event.target;
                       
                         clear();
                         printConfig(target.data.label);
                         /*
                         for (var i in target.data) {
                            var variable_name = i;
                            var variable_value = target.data[i];
                            print(  );
                            d3.select("svg").remove();
                            
                         }*/
                    }
                    
                    function clear() {
                        document.getElementById("note").innerHTML = "";
                    }
                
                    function printConfig(msg) {
                       // document.getElementById("note").innerHTML += "<p>" + msg + "</p>";
                      
                       drawGenes("#note", genes[0], msg, ["The nodes on in the network depict gene configurations", "and the edge widths depict the frequency of", "that transition occuring across a 10 second interval."], 100);
                    }
                });

                // draw options
                var draw_options = {
                    // your data goes here
                    network: network_json,
                    // hide pan zoom
                    panZoomControlVisible: false 
                };
                
                vis.draw(draw_options);
            };

