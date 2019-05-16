// Global variables
var x_label;
var y_label;
var min_x;
var min_y;
var axesPossiblesMinimize;
var axesPossiblesMaximize;
var decimalSeparator;
var data;
var pareto;
var site;
var nameData;
var nameAxisX;
var nameAxisY;
var isWaiting = false;

/**
 * Draw graph depending on data and axes
 */
function drawGraph() {
  findParetoPoints();
  var svgElement = document.getElementsByTagName("svg")[0];
  if (svgElement) {
    svgElement.remove();
  } else {
    document.getElementsByClassName("informations")[0].innerHTML = "To display informations, hover on the point. To delete a point, click on it. To save your modifications (points deleted), click on 'Save changes'. The green points are better than the blue ones.";
  }
  // Display the title
  document.getElementsByClassName("titleGraph")[0].innerHTML = "Graph representing the " + x_label + " depending on the " + y_label;

  // Set the margins
  var margin = { top: 20, right: 20, bottom: 30, left: 80 },
    width = 700 - margin.left - margin.right,
    height = 400 - margin.top - margin.bottom;

  // x-axis data
  var xValue = function (d) { return my_toFloat(d[x_label]); },
    xScale = d3.scale.linear().range([0, width]),
    xMap = function (d) { return xScale(xValue(d)); },
    xAxis = d3.svg.axis().scale(xScale).orient("bottom");

  // y-axis data
  var yValue = function (d) { return my_toFloat(d[y_label]); },
    yScale = d3.scale.linear().range([height, 0]),
    yMap = function (d) { return yScale(yValue(d)); },
    yAxis = d3.svg.axis().scale(yScale).orient("left");

  // Add graph to the body
  var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

  // Add tooltip
  var tooltip = d3.select("body").append("div")
    .attr("class", "tooltip")
    .style("opacity", 0);

  // Adjust the values ​​of the axes (minimum / maximum)
  var minX = d3.min(data, xValue);
  var maxX = d3.max(data, xValue);
  if(minX == maxX){
    if(minX == 0){
      xScale.domain([-1, 1]);
    }else{
      xScale.domain([minX*0.95, minX*1.05]);
    }
  }else{
    var diffX = (maxX - minX) * 0.05;
    xScale.domain([minX - diffX, maxX + diffX]);
  }

  var minY = d3.min(data, yValue);
  var maxY = d3.max(data, yValue);
  if(minY == maxY){
    if(minY == 0){
      yScale.domain([-1, 1]);
    }else{
      yScale.domain([minY*0.95, minY*1.05]);
    }
  }else{
    var diffY = (maxY - minY) * 0.05;
    yScale.domain([minY - diffY, maxY + diffY]);
  }

  // x axis
  svg.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0," + height + ")")
    .call(xAxis)
    .append("text")
    .attr("class", "label")
    .attr("x", width)
    .attr("y", -6)
    .style("text-anchor", "end")
    .text(x_label)
    .attr("class", "xLabel");

  // y axis
  svg.append("g")
    .attr("class", "y axis")
    .call(yAxis)
    .append("text")
    .attr("class", "label")
    .attr("transform", "rotate(-90)")
    .attr("y", 6)
    .attr("dy", ".71em")
    .style("text-anchor", "end")
    .text(y_label)
    .attr("class", "yLabel");

  // points
  svg.selectAll(".dot")
    .data(data)
    .enter()
    .append("circle")
    .attr("class", "dot")
    .attr("r", 5)
    .attr("cx", xMap)
    .attr("cy", yMap)
    .style("fill", function (d) { return colorPoint(d) })
    .on("mouseover", function (d) { // Display informations of the point in the tooltip
      tooltip.transition()
        .duration(100)
        .style("opacity", 0.9);
      tooltip.html(display_all_informations(d))
        .style("left", function () {
          if (d3.event.pageX > (width / 2 + margin.left)) {
            return (d3.event.pageX - 230) + "px";
          } else {
            return (d3.event.pageX + 15) + "px";
          }
        }
        )
        .style("top", function () {
          if (d3.event.pageY > (height / 2 + margin.top + 150)) {
            var nbEstimatedLines = 0;
            for (var cle in d) {
              nbEstimatedLines += Math.ceil((cle.length + d[cle].length + 3)/30);
            }
            return (d3.event.pageY - 18 * nbEstimatedLines + 20) + "px";
          } else {
            return (d3.event.pageY - 20) + "px";
          }
        }
        );
      d3.select(this)
        .style("fill", "red")
        .attr("r", 10);
    })
    .on("mouseout", function (d) { // Hide the tooltip
      tooltip.transition()
        .duration(100)
        .style("opacity", 0);
      d3.select(this)
        .style("fill", function (d) { return colorPoint(d) })
        .attr("r", 5);
    })
    .on("click", function (d) { // Delete the point
      tooltip.transition()
        .duration(500)
        .style("opacity", 0);
      d3.select(this).remove();
      // Delete the point in data
      var index = data.indexOf(d);
      data.splice(index, 1);
    });
}

/**
 * Get the first number of the string if there is one (= a float depending on the decimal separator), null otherwise
 * @param {*} x the string to transform
 */
function my_toFloat(x) {
  var res;
  var regex = /[0-9.,\s]+/g;
  var regexDigit = /[^0-9]+/g;
  var nbFound = x.match(regex);
  var found = false;
  var i = 0;
  while (!found && i < nbFound.length){
    res = nbFound[i].replace(/[\s]+/g, '');
    if(res.replace(regexDigit, '')!=""){
      found = true;
    }
    i++;
  }
  if(found){
    if (decimalSeparator == "coma") {
      res = res.replace(',', '.');
    } else {
      res = res.replace(',', '');
    }
    return parseFloat(res);
  }else{
    return null;
  }
}


/**
 * Sort the points in data depending on the value of min_x and the value of min_y
 */
function sortedPoints() {
  if (min_x) {
    if (min_y) {
      data = data.sort(function (a, b) {
        var ax = my_toFloat(a[x_label]);
        var ay = my_toFloat(a[y_label]);
        var bx = my_toFloat(b[x_label]);
        var by = my_toFloat(b[y_label]);
        return d3.ascending(ax, bx) || d3.ascending(ay, by);
      });
    }
    else {
      data = data.sort(function (a, b) {
        var ax = my_toFloat(a[x_label]);
        var ay = my_toFloat(a[y_label]);
        var bx = my_toFloat(b[x_label]);
        var by = my_toFloat(b[y_label]);
        return d3.ascending(ax, bx) || d3.descending(ay, by);
      });
    }
  } else {
    if (min_y) {
      data = data.sort(function (a, b) {
        var ax = my_toFloat(a[x_label]);
        var ay = my_toFloat(a[y_label]);
        var bx = my_toFloat(b[x_label]);
        var by = my_toFloat(b[y_label]);
        return d3.descending(ax, bx) || d3.ascending(ay, by);
      });
    }
    else {
      data = data.sort(function (a, b) {
        var ax = my_toFloat(a[x_label]);
        var ay = my_toFloat(a[y_label]);
        var bx = my_toFloat(b[x_label]);
        var by = my_toFloat(b[y_label]);
        return d3.descending(ax, bx) || d3.descending(ay, by);
      });
    }
  }
}


/**
 * Find the pareto points in data and save them in a list (global variable pareto)
 */
function findParetoPoints() {
  sortedPoints();
  pareto = [];
  var paretoObject = [];
  paretoObject.push(data[0]);
  for (var i = 1; i < data.length; i++) {
    // if value already exists in data, delete it
    if (JSON.stringify(data[i - 1]) === JSON.stringify(data[i])) {
      data.splice(i--, 1);
    } else {
      // else, check if it's a pareto point or not
      isPareto = true;
      // If we have to minimize y value
      if (min_y) {
        for (var j = 0; j < paretoObject.length; j++) {
          if (my_toFloat(paretoObject[j][y_label]) <= my_toFloat(data[i][y_label])) {
            isPareto = false;
          }
        }
      } else { // else
        for (var j = 0; j < paretoObject.length; j++) {
          if (my_toFloat(paretoObject[j][y_label]) >= my_toFloat(data[i][y_label])) {
            isPareto = false;
          }
        }
      }
      if (isPareto) {
        paretoObject.push(data[i]);
      }
    }
  }
  // Save pareto points find, in the global variable
  for (var k = 0; k < paretoObject.length; k++) {
    pareto.push(JSON.stringify(paretoObject[k]));
  }
}


/**
 * Return the color of the point depending if it's a pareto point or not
 * @param {*} d the point
 */
function colorPoint(d) {
  if (pareto.indexOf(JSON.stringify(d)) != -1) {
    return "rgb(50,205,50)";
  } else {
    return "rgb(30,144,255)";
  }
}

/**
 * Display the informations of a point
 * @param {*} d data of the point
 */
function display_all_informations(d) {
  var res = "";
  for (var cle in d) {
    if (res != "") { res += "<br/>"; }
    res += "<strong>" + cle + "</strong> : " + d[cle];
  }
  return res;
}


// Add events on the buttons of the web extension
document.addEventListener("click", (e) => {
  if (e.target.classList.contains("resetAndLoadData")) {
    // Delete old data on the site
    browser.storage.local.remove(nameAxisX);
    browser.storage.local.remove(nameAxisY);
    browser.storage.local.remove(nameData);
    data = [];
    // Add data of the current page
    addPage();
    // Add new buttons
    displayButtons();
  }

  if (e.target.classList.contains("displayPreviousData")) {
    // Display the graph without new data
    data = [];
    displayGraph();
    // Add new buttons
    displayButtons();
  }
});

/**
 * Display the buttons of the webextension
 */
function displayButtons() {
  // Display the buttons if they are not present yet
  if (document.getElementsByClassName("addPage").length == 0) {
    // Delete the button to display old data
    if (document.getElementsByClassName("displayPreviousData").length != 0) {
      document.getElementsByClassName("displayPreviousData")[0].remove();
    }
    // Button to add page data
    var button = document.createElement("button");
    button.innerHTML = "Add page data";
    button.className = "addPage";
    button.addEventListener("click", addPage);
    document.getElementsByClassName("title")[0].appendChild(button);

    // Button to save changes
    var button2 = document.createElement("button");
    button2.innerHTML = "Save changes";
    button2.className = "saveChanges";
    button2.addEventListener("click", saveChanges);
    document.getElementsByClassName("title")[0].appendChild(button2);

    // Button to change axes
    var button3 = document.createElement("button");
    button3.innerHTML = "Change axes";
    button3.className = "changeAxes";
    button3.addEventListener("click", changeAxes);
    document.getElementsByClassName("title")[0].appendChild(button3);
  }
}

/**
 * Add the choices for the axis in the corresponding select
 * @param {*} nameAxis the axis
 */
function addOptions(nameAxis) {
  document.getElementsByClassName(nameAxis)[0].innerHTML = "";
  var parsed = data[0];
  var option;
  for (var property in axesPossiblesMinimize) {
    option = document.createElement("option");
    option.value = option.innerHTML = axesPossiblesMinimize[property];
    document.getElementsByClassName(nameAxis)[0].appendChild(option);
  }
  for (var property in axesPossiblesMaximize) {
    option = document.createElement("option");
    option.value = option.innerHTML = axesPossiblesMaximize[property];
    document.getElementsByClassName(nameAxis)[0].appendChild(option);
  }
}

/**
 * Change axes of the graph depending on the choice of the user
 */
function changeAxes() {
  document.getElementsByClassName("changeAxis")[0].style.display = "inline";
  addOptions("selectAxisX");
  addOptions("selectAxisY");

  document.getElementsByClassName("changeAxisSubmit")[0].addEventListener("click", function () {
    // Recover the values
    x_label = document.getElementsByClassName("selectAxisX")[0].value;
    if (axesPossiblesMinimize.indexOf(x_label) != -1) {
      min_x = true;
    } else {
      min_x = false;
    }

    y_label = document.getElementsByClassName("selectAxisY")[0].value;
    if (axesPossiblesMinimize.indexOf(y_label) != -1) {
      min_y = true;
    } else {
      min_y = false;
    }

    // Save new data in the storage
    var axisXToStore = {};
    axisXToStore[nameAxisX] = x_label;
    browser.storage.local.set(axisXToStore);

    var axisYToStore = {};
    axisYToStore[nameAxisY] = y_label;
    browser.storage.local.set(axisYToStore);

    document.getElementsByClassName("changeAxis")[0].style.display = "none";

    drawGraph();
  });
}

/**
 * Send a request to get data from the current page
 */
function addPage() {
  var gettingActiveTab = browser.tabs.query({ active: true, currentWindow: true });
  gettingActiveTab.then((tabs) => {
    isWaiting = true;
    browser.tabs.sendMessage(tabs[0].id, { "site": site });
  });
}

/**
 * Save data
 */
function saveChanges() {
  var contentToStore = {};
  contentToStore[nameData] = data;
  browser.storage.local.set(contentToStore);
  drawGraph();
}

// Upon receiving the response, display the graph
browser.runtime.onMessage.addListener(treatMessage);

/**
 * Send a message to initialize data
 * @param {*} result the result of the execution of the script
 */
function onExecuted(result) {
  var gettingActiveTab = browser.tabs.query({ active: true, currentWindow: true });
  gettingActiveTab.then((tabs) => {
    isWaiting = true;
    browser.tabs.sendMessage(tabs[0].id, { "init": site });
  });
}

/**
 * Treat the error
 * @param {*} error the error
 */
function onError(error) { }

browser.tabs.query({ active: true, windowId: browser.windows.WINDOW_ID_CURRENT })
  .then(tabs => browser.tabs.get(tabs[0].id))
  .then(tab => {
    // Get the site
    var el = document.createElement('a');
    el.href = tab.url;
    site = el.host.split(".");
    if (site.length < 2) {
      deleteAllButtonsAndDisplayMessage("The extension is not available on this site.");
    } else {
      site = site[1];
      // Load script and get back the initialization informations
      var executing = browser.tabs.executeScript(null, { file: "/content_scripts/multicriteres.js" });
      executing.then(onExecuted, onError);

      nameData = site + "_data";
      nameAxisX = site + "_axisX";
      nameAxisY = site + "_axisY";
      // Check if data for this site is already saved, otherwise remove "Display previous data" button
      var isData = browser.storage.local.get(nameData);
      isData.then((res) => {
        if (!res[Object.keys(res)[0]]) {
          document.getElementsByClassName("displayPreviousData")[0].remove();
        }
      });
    }


  });

/**
 * Remove all buttons and display a message
 * @param {*} message the message to display
 */
function deleteAllButtonsAndDisplayMessage(message) {
  var buttons = document.getElementsByTagName("button");
  while (buttons.length != 0) {
    buttons[0].remove();
  }
  document.getElementsByClassName("informations")[0].innerHTML = message;
}

/**
 * Treat the message received
 * @param {*} request the request => the answer of the message
 * @param {*} sender
 * @param {*} sendResponse
 */
function treatMessage(request, sender, sendResponse) {
  // if we are waiting for an answer
  if (isWaiting) {
    isWaiting = false;
    if (request == null) {
      alert("There is no data in this page.");
    }
    else if (request.DecimalSeparator != undefined) {
      decimalSeparator = request.DecimalSeparator;
      axesPossiblesMinimize = request.Minimize;
      axesPossiblesMaximize = request.Maximize;
    } else if (request.PROBLEM != undefined) {
      deleteAllButtonsAndDisplayMessage(request.PROBLEM);
    } else {
      data = [];
      for (var i = 0; i < request.length; i++) {
        data.push(JSON.parse(request[i]));
      }
      displayGraph();
    }
  }
}



/**
 * Display the graph according to the data
 */
function displayGraph() {
  // Get axes and data
  var gettingAxisAndData = browser.storage.local.get([nameAxisX, nameAxisY, nameData]);
  gettingAxisAndData.then((res) => {
    // Update the x axis
    if (res[nameAxisX]) {
      x_label = res[nameAxisX];
      if (axesPossiblesMinimize.indexOf(x_label) != -1) {
        min_x = true;
      } else {
        min_x = false;
      }
    } else {
      var foundAxeX = false;
      var parsed = data[0];

      for (var property in axesPossiblesMinimize) {
        if (!foundAxeX) {
          x_label = axesPossiblesMinimize[property];
          min_x = true;
          foundAxeX = true;
        }
      }
      if (!foundAxeX) {
        for (var property in axesPossiblesMaximize) {
          if (!foundAxeX) {
            x_label = axesPossiblesMaximize[property];
            min_x = false;
            foundAxeX = true;
          }
        }
      }
      var axisXToStore = {};
      axisXToStore[nameAxisX] = x_label;
      browser.storage.local.set(axisXToStore);
    }


    // Update the y axis
    if (res[nameAxisY]) {
      y_label = res[nameAxisY];
      if (axesPossiblesMinimize.indexOf(y_label) != -1) {
        min_y = true;
      } else {
        min_y = false;
      }
    } else {
      var foundAxeY = false;
      var parsed = data[0];
      for (var property in axesPossiblesMinimize) {
        if (!foundAxeY && axesPossiblesMinimize[property] != x_label) {
          y_label = axesPossiblesMinimize[property];
          min_y = true;
          foundAxeY = true;
        }
      }
      if (!foundAxeY) {
        for (var property in axesPossiblesMaximize) {
          if (!foundAxeY && axesPossiblesMaximize[property] != x_label) {
            y_label = axesPossiblesMaximize[property];
            min_y = false;
            foundAxeY = true;
          }
        }
      }
      var axisYToStore = {};
      axisYToStore[nameAxisY] = y_label;
      browser.storage.local.set(axisYToStore);
    }

    // Update the data and display
    if (res[nameData]) {
      data = data.concat(res[nameData]);
    }
    var contentToStore = {};
    contentToStore[nameData] = data;
    browser.storage.local.set(contentToStore);
    drawGraph();
  });
}
