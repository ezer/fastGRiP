function drawGeneOptions(divName, maxTime, ylab){
   



  d3.select("#genepics").append("form");


  for (var i=0; i<Math.pow(2, genes[0].length); i=i+2){
     d3.select("form").append("div").attr("class", "row").attr("id", "".concat("row", i)).attr("name", "".concat("row", i));
     d3.select("".concat("#row", i)).append("div").attr("id", "".concat("genepics1", i)).attr("name", "".concat("genepics1", i));
     d3.select("".concat("#genepics1", i)).append("div").attr("id", "".concat("gene", i)).attr("name", "".concat("gene", i));
     drawGenesParams("".concat("#gene", i), genes[0], i, ["The nodes on in the network depict gene configurations", "and the edge widths depict the frequency of", "that transition occuring across a 10 second interval."], 10, 125, 30, 1);
     d3.select("".concat("#gene", i)).attr("class", "span2");
     d3.select("".concat("#genepics1", i)).append("div").attr("id", "".concat("text", i)).attr("name", "".concat("text", i)).attr("class", "span1");
     d3.select("".concat("#text", i)).append("input").attr("type", "text").attr("style", "width:30px").attr("class", "span1").attr("name", "".concat("t", i));
   if((i+1)<Math.pow(2, genes[0].length)){
     d3.select("".concat("#genepics1", i)).append("div").attr("id", "".concat("gene2_", i)).attr("name", "".concat("gene2_", i));
     drawGenesParams("".concat("#gene2_", i), genes[0], i+1, ["The nodes on in the network depict gene configurations", "and the edge widths depict the frequency of", "that transition occuring across a 10 second interval."], 10, 125, 30, 1);
     d3.select("".concat("#gene2_", i)).attr("class", "span2");
     d3.select("".concat("#genepics1", i)).append("div").attr("id", "".concat("text2_", i)).attr("name", "".concat("text2_", i)).attr("class", "span1");
     var temp=i+1;
     d3.select("".concat("#text2_", i)).append("input").attr("type", "text").attr("style", "width:30px").attr("class", "span1").attr("name", "".concat("t", temp));
   }

    /*
    if((i+1)<genes[0].length){
       drawGenesParams("".concat("#genepics1", i), genes[0], i+1, ["The nodes on in the network depict gene configurations", "and the edge widths depict the frequency of", "that transition occuring across a 10 second interval."], 10, 125, 30, 1);
        //d3.select("".concat("#genepics1", i)).append("div").attr("id", "".concat("gene", i)).attr("name", "".concat("gene", i));
    
       d3.select("".concat("#genepics1", i)).append("div").attr("class", "span2");
       d3.select("".concat("#genepics1", i)).append("div").attr("id", "".concat("text2_", i)).attr("name", "".concat("text2_", i));
       d3.select("".concat("#text2_", i)).append("input").attr("type", "text").attr("style", "width:40px").attr("class", "span1");
  }   */}
  d3.select("form").append("div").attr("class", "row").attr("name", "sub").attr("id", "sub");
  d3.select("#sub").append("div").attr("class", "span6").attr("name", "s").attr("id", "s");
  d3.select("#s").append("button").attr("type", "button").attr("class", "btn").text("Submit").attr("name", "sub").attr("id", "sub");
  d3.select("#genepics").append("div").attr("name", "legend").attr("id", "legend");
  


  width=900;
  height=300;
  margin=10;

  var svg = d3.select("#netflow").append("svg")
    .attr("width", width + margin + margin)
    .attr("height", height + margin + margin)
  .append("g")
    .attr("transform", "translate(" + margin + "," + margin + ")");
/*
var sankey = d3.sankey()
    .nodeWidth(15)
    .nodePadding(10)
    .size([width, height]);

var path = sankey.link();



  sankey
      .nodes(names)
      .links(edges)
      .layout(32);

  var link = svg.append("g").selectAll(".link")
      .data(edges)
    .enter().append("path")
      .attr("class", "link")
      .attr("d", path)
      .style("stroke-width", function(d) { return Math.max(1, d.dy); })
      .sort(function(a, b) { return b.dy - a.dy; });

  //link.append("title")
    //  .text(function(d) { return d.source.name + " → " + d.target.name + "\n" + format(d.value); });

  var node = svg.append("g").selectAll(".node")
      .data(names)
    .enter().append("g")
      .attr("class", "node")
      .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
    .call(d3.behavior.drag()
      .origin(function(d) { return d; })
      .on("dragstart", function() { this.parentNode.appendChild(this); })
      .on("drag", dragmove));

  node.append("rect")
      .attr("height", function(d) { return d.dy; })
      .attr("width", sankey.nodeWidth())
      .style("fill", function(d) { return d.color = "steelblue"; })
      .style("stroke", function(d) { return d3.rgb(d.color).darker(2); });

  node.append("text")
      .attr("x", -6)
      .attr("y", function(d) { return d.dy / 2; })
      .attr("dy", ".35em")
      .attr("text-anchor", "end")
      .attr("transform", null)
      .text(function(d) { return d.name; })
    .filter(function(d) { return d.x < width / 2; })
      .attr("x", 6 + sankey.nodeWidth())
      .attr("text-anchor", "start");

  function dragmove(d) {
    d3.select(this).attr("transform", "translate(" + d.x + "," + (d.y = Math.max(0, Math.min(height - d.dy, d3.event.y))) + ")");
    sankey.relayout();
    link.attr("d", path);
  }
*/

 // d3.select("#netflow").append(sankey);
/*

  for (var i=0;i<genes[0].length;i=i+2)
  { 
    d3.select("#genepics1").append("div").attr("id", "".concat("gene", i)).attr("name", "".concat("gene", i));
    drawGenesParams("".concat("#gene", i), genes[0], i, ["The nodes on in the network depict gene configurations", "and the edge widths depict the frequency of", "that transition occuring across a 10 second interval."], 10, 125, 30, 1);
  }


  for (var i=1;i<genes[0].length;i=i+2)
  { 
    d3.select("#genepics2").append("div").attr("id", "".concat("gene", i)).attr("name", "".concat("gene", i));
    drawGenesParams("".concat("#gene", i), genes[0], i, ["The nodes on in the network depict gene configurations", "and the edge widths depict the frequency of", "that transition occuring across a 10 second interval."], 10, 125, 30, 1);
  }
  /*
   d3.select("#genepics1").append("div").attr("id", "gene0").attr("name", "gene0");
   drawGenesParams("#gene0", genes[0], 0, ["The nodes on in the network depict gene configurations", "and the edge widths depict the frequency of", "that transition occuring across a 10 second interval."], 10, 125, 30, 1);
   d3.select("#genepics1").append("div").attr("id", "gene1").attr("name", "gene1");            
   drawGenesParams("#gene1", genes[0], 1, ["The nodes on in the network depict gene configurations", "and the edge widths depict the frequency of", "that transition occuring across a 10 second interval."], 10, 125, 30, 1);
    

  d3.select("#groups1").append("form").attr("method", "post").attr("action", "/");
  d3.select("form").append("input").attr("type", "text").attr("style", "width:40px");
  d3.select("form").append("p");
  d3.select("form").append("input").attr("type", "text").attr("style", "width:40px");
  d3.select("form").append("p");
  d3.select("form").append("input").attr("type", "text").attr("style", "width:40px");
  d3.select("form").append("p");
  d3.select("form").append("input").attr("type", "text").attr("style", "width:40px");
*/

$(".btn").click(function()
           {
            var dots=new Array();
            for (var i=0; i<Math.pow(2, genes[0].length); i++){
              if($("".concat('input[name=t', i, ']')).val()!=""){
                 dots[$("".concat('input[name=t', i, ']')).val()]=new Array();
               }
               
            }

            for (var i=0; i<Math.pow(2, genes[0].length); i++){
              if($("".concat('input[name=t', i, ']')).val()!=""){
                var v=$("".concat('input[name=t', i, ']')).val();
                 dots[v][dots[v].length]=i;
               }
            }
            drawArea("#zoomplot", maxTime, ylab, dots);
            drawLegend(dots);
           }
        );

}
colors=["black", "darkblue", "darkred", "seagreen", "mediumorchid", "slategrey", "steelblue", "peru", "indigo", "orange", "lawngreen", "teal"];
       
function drawLegend(dots){
   var margin = {top: 20, right: 20, bottom: 30, left: 50},
        width = 499 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom;
     divName="#legend";
     d3.select(divName).select("svg").remove();

        var svg = d3.select(divName).append("svg")
         .attr("width", width + margin.left + margin.right)
         .attr("height", height + margin.top + margin.bottom)
         .append("g")
         .attr("transform", "translate(" + margin.left + "," + margin.top + ")");      
  xshift=300;
  yshift=40; 

    svg.selectAll("circle")
       .data(Object.keys(dots))
       .enter()
       .append("circle")
       .attr("cx", function(d, i){ return 30+xshift; })
       .attr("cy", function(d, i){ return 30+20*i-yshift; })
       .attr("fill", function(d, i){ return colors[i]; })
       .attr("r", function(d, i){ return 2; });
   
   svg.selectAll("text")
      .data(Object.keys(dots))
      .enter()
      .append("text")
      .attr("x", function(d, i){ return 40+xshift; })
      .attr("y", function(d, i){ return 35+20*i-yshift; })
      .text(function(d){ return d; })


  dummy=new Array();
  dummy[0]=0;
  
  svg.selectAll("rect")
    .data(dummy)
    .enter()
    .append("rect")
    .attr("x", function(d, i){ return 20+xshift; })
    .attr("y", function(d, i){return 20-yshift; })
    .attr("width", function(d, i){return 80; })
    .attr("height", function(d, i){return 20*Object.keys(dots).length;})
    .attr("stroke-width", function(d, i){return 1; })
    .attr("stroke", function(d, i){return "black"})
    .attr("fill", function(d, i){return "none"; });
    //stroke-width:1;stroke:rgb(0,0,0)
}

function drawArea(divName, maxTime, ylab, dots){
  
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

        d3.select(divName).select("svg").remove();

        var svg = d3.select(divName).append("svg")
         .attr("width", width + margin.left + margin.right)
         .attr("height", height + margin.top + margin.bottom)
         .append("g")
         .attr("transform", "translate(" + margin.left + "," + margin.top + ")");      
       var count=0;
        for (var j in dots){
          var points = svg.selectAll("".concat("circle.v", count))
           .data(configs)
           .enter()
           .append("circle")
           .attr("cx", function(d, i){ return xScale(d.time); })
           .attr("cy", function(d, i){ 
            var sum=0;
              for(var k=0; k<dots[j].length; k++){
                if(d[dots[j][k]]>0){
                 sum=sum+d[dots[j][k]];
               }
              }


            return yScale(sum); })
           .attr("r", function(d, i){return 2; })
           .attr("fill", function(d, i){return colors[count]; });

           count++;
        }
      
        
/*
      var points = svg.selectAll("circle.v2")
           .data(configs)
           .enter()
           .append("circle")
           .attr("cx", function(d, i){ return xScale(d.time); })
           .attr("cy", function(d, i){ return yScale(d[0]); })
           .attr("r", function(d, i){return 1; });
/*
/*
        var area = d3.svg.area()
          .x(function(d) { return xScale(d.time); })
          .y0(function(d) { return yScale(d.y0); })
          .y1(function(d) { return yScale(d.y0 + d.y); });

        
        var stack = d3.layout.stack()
          .values(function(d) { return d.values; });

        color.domain(d3.keys(configs[0]).filter(function(key) { return key !== "time"; }));

        var svg = d3.select(divName).append("svg")
         .attr("width", width + margin.left + margin.right)
         .attr("height", height + margin.top + margin.bottom)
         .append("g")
         .attr("transform", "translate(" + margin.left + "," + margin.top + ")");      

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

      svg.selectAll(".dot")
        .data(browsers)
        .enter().append("circle")
        .attr("class", "dot")
        .attr("r", 3.5)
        .attr("cx", function(d) { return xScale(d.time); })
        .attr("cy", function(d) { return yScale(d.y); })
        .style("fill", function(d) { return color(1); });


        browser.append("path")
               .attr("class", "area")
               .attr("d", function(d) { return area(d.values); })
               .style("fill", function(d) { return color(d.name); })
               .on("mouseover", function(d) {  
                  drawGenes("#geneplot", genes[0], d.name, [""], 350);   
                }
                );
/*
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
*/
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

            // This is to keep styling when converting to pdf!
      // general styling
  svg.
    style("font-family", "Helvetica").
    style("font-size", "12px").
    style("font-style", "normal").
    style("font-variant", "normal").
    style("font-weight", "normal").
    style("text-rendering", "optimizeLegibility").
    style("shape-rendering", "default");
 
// style the axes
  d3.selectAll("path.domain").
    style("opacity", "0.0").
    style("stroke-width", "0").
    style("fill", "none");
 
  d3.selectAll(".tick").
    style("opacity", "0.4").
    style("stroke", "black").
    style("stroke-width", "1");
      //*******

}

function drawGenesParams(divName, config, stateType, description, leftBuffer, w, h, topBuffer){
           //var w=300;
           //var h=500;
           //var topBuffer=100;
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
                return h/30;
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
              });
/*
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
/*
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




var vis;
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
                drawGeneOptions("#genepics1", maxTime, true);
                
                if(maxTime>200){
                  var arr=new Array();
                  arr[0]=0;
                  drawArea("#zoomplot", 100, false, arr);
                }
                
                vis = new org.cytoscapeweb.Visualization(div_id, options);
                
               

                

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
 /*
                    d3.select("body")
                       .append("text")
                       .text(vis.svg());
*/
                    
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

/*
d3.sankey = function() {
  var sankey = {},
      nodeWidth = 24,
      nodePadding = 8,
      size = [1, 1],
      nodes = [],
      links = [];

  sankey.nodeWidth = function(_) {
    if (!arguments.length) return nodeWidth;
    nodeWidth = +_;
    return sankey;
  };

  sankey.nodePadding = function(_) {
    if (!arguments.length) return nodePadding;
    nodePadding = +_;
    return sankey;
  };

  sankey.nodes = function(_) {
    if (!arguments.length) return nodes;
    nodes = _;
    return sankey;
  };

  sankey.links = function(_) {
    if (!arguments.length) return links;
    links = _;
    return sankey;
  };

  sankey.size = function(_) {
    if (!arguments.length) return size;
    size = _;
    return sankey;
  };

  sankey.layout = function(iterations) {
    computeNodeLinks();
    computeNodeValues();
    computeNodeBreadths();
    computeNodeDepths(iterations);
    computeLinkDepths();
    return sankey;
  };

  sankey.relayout = function() {
    computeLinkDepths();
    return sankey;
  };

  sankey.link = function() {
    var curvature = .5;

    function link(d) {
      var x0 = d.source.x + d.source.dx,
          x1 = d.target.x,
          xi = d3.interpolateNumber(x0, x1),
          x2 = xi(curvature),
          x3 = xi(1 - curvature),
          y0 = d.source.y + d.sy + d.dy / 2,
          y1 = d.target.y + d.ty + d.dy / 2;
      return "M" + x0 + "," + y0
           + "C" + x2 + "," + y0
           + " " + x3 + "," + y1
           + " " + x1 + "," + y1;
    }

    link.curvature = function(_) {
      if (!arguments.length) return curvature;
      curvature = +_;
      return link;
    };

    return link;
  };

  // Populate the sourceLinks and targetLinks for each node.
  // Also, if the source and target are not objects, assume they are indices.
  function computeNodeLinks() {
    nodes.forEach(function(node) {
      node.sourceLinks = [];
      node.targetLinks = [];
    });
    links.forEach(function(link) {
      var source = link.source,
          target = link.target;
      if (typeof source === "number") source = link.source = nodes[link.source];
      if (typeof target === "number") target = link.target = nodes[link.target];
      source.sourceLinks.push(link);
      target.targetLinks.push(link);
    });
  }

  // Compute the value (size) of each node by summing the associated links.
  function computeNodeValues() {
    nodes.forEach(function(node) {
      node.value = Math.max(
        d3.sum(node.sourceLinks, value),
        d3.sum(node.targetLinks, value)
      );
    });
  }

  // Iteratively assign the breadth (x-position) for each node.
  // Nodes are assigned the maximum breadth of incoming neighbors plus one;
  // nodes with no incoming links are assigned breadth zero, while
  // nodes with no outgoing links are assigned the maximum breadth.
  function computeNodeBreadths() {
    var remainingNodes = nodes,
        nextNodes,
        x = 0;

    while (remainingNodes.length) {
      nextNodes = [];
      remainingNodes.forEach(function(node) {
        node.x = x;
        node.dx = nodeWidth;
        node.sourceLinks.forEach(function(link) {
          nextNodes.push(link.target);
        });
      });
      remainingNodes = nextNodes;
      ++x;
    }

    //
    moveSinksRight(x);
    width=900;
    scaleNodeBreadths((width - nodeWidth) / (x - 1));
  }

  function moveSourcesRight() {
    nodes.forEach(function(node) {
      if (!node.targetLinks.length) {
        node.x = d3.min(node.sourceLinks, function(d) { return d.target.x; }) - 1;
      }
    });
  }

  function moveSinksRight(x) {
    nodes.forEach(function(node) {
      if (!node.sourceLinks.length) {
        node.x = x - 1;
      }
    });
  }

  function scaleNodeBreadths(kx) {
    nodes.forEach(function(node) {
      node.x *= kx;
    });
  }

  function computeNodeDepths(iterations) {
    var nodesByBreadth = d3.nest()
        .key(function(d) { return d.x; })
        .sortKeys(d3.ascending)
        .entries(nodes)
        .map(function(d) { return d.values; });

    //
    initializeNodeDepth();
    resolveCollisions();
    for (var alpha = 1; iterations > 0; --iterations) {
      relaxRightToLeft(alpha *= .99);
      resolveCollisions();
      relaxLeftToRight(alpha);
      resolveCollisions();
    }

    function initializeNodeDepth() {
      var ky = d3.min(nodesByBreadth, function(nodes) {
        return (size[1] - (nodes.length - 1) * nodePadding) / d3.sum(nodes, value);
      });

      nodesByBreadth.forEach(function(nodes) {
        nodes.forEach(function(node, i) {
          node.y = i;
          node.dy = node.value * ky;
        });
      });

      links.forEach(function(link) {
        link.dy = link.value * ky;
      });
    }

    function relaxLeftToRight(alpha) {
      nodesByBreadth.forEach(function(nodes, breadth) {
        nodes.forEach(function(node) {
          if (node.targetLinks.length) {
            var y = d3.sum(node.targetLinks, weightedSource) / d3.sum(node.targetLinks, value);
            node.y += (y - center(node)) * alpha;
          }
        });
      });

      function weightedSource(link) {
        return center(link.source) * link.value;
      }
    }

    function relaxRightToLeft(alpha) {
      nodesByBreadth.slice().reverse().forEach(function(nodes) {
        nodes.forEach(function(node) {
          if (node.sourceLinks.length) {
            var y = d3.sum(node.sourceLinks, weightedTarget) / d3.sum(node.sourceLinks, value);
            node.y += (y - center(node)) * alpha;
          }
        });
      });

      function weightedTarget(link) {
        return center(link.target) * link.value;
      }
    }

    function resolveCollisions() {
      nodesByBreadth.forEach(function(nodes) {
        var node,
            dy,
            y0 = 0,
            n = nodes.length,
            i;

        // Push any overlapping nodes down.
        nodes.sort(ascendingDepth);
        for (i = 0; i < n; ++i) {
          node = nodes[i];
          dy = y0 - node.y;
          if (dy > 0) node.y += dy;
          y0 = node.y + node.dy + nodePadding;
        }

        // If the bottommost node goes outside the bounds, push it back up.
        dy = y0 - nodePadding - size[1];
        if (dy > 0) {
          y0 = node.y -= dy;

          // Push any overlapping nodes back up.
          for (i = n - 2; i >= 0; --i) {
            node = nodes[i];
            dy = node.y + node.dy + nodePadding - y0;
            if (dy > 0) node.y -= dy;
            y0 = node.y;
          }
        }
      });
    }

    function ascendingDepth(a, b) {
      return a.y - b.y;
    }
  }

  function computeLinkDepths() {
    nodes.forEach(function(node) {
      node.sourceLinks.sort(ascendingTargetDepth);
      node.targetLinks.sort(ascendingSourceDepth);
    });
    nodes.forEach(function(node) {
      var sy = 0, ty = 0;
      node.sourceLinks.forEach(function(link) {
        link.sy = sy;
        sy += link.dy;
      });
      node.targetLinks.forEach(function(link) {
        link.ty = ty;
        ty += link.dy;
      });
    });

    function ascendingSourceDepth(a, b) {
      return a.source.y - b.source.y;
    }

    function ascendingTargetDepth(a, b) {
      return a.target.y - b.target.y;
    }
  }

  function center(node) {
    return node.y + node.dy / 2;
  }

  function value(link) {
    return link.value;
  }

  return sankey;
};
*/

