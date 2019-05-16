var auchandrive = {
  "Articles": "//article[contains(@class, 'product-item')][@data-price]",
  "DecimalSeparator": "coma",
  "Name": ".//dt[@class='product-item__title']/span",
  "Price": "@data-price",
  "Average rating": ".//div[@class='rating-average']",
  "Quantity rating": ".//div[@class='rating-quantity']",
  "Price per kilo": ".//span[@class='product-item__price-per']",
  "Maximize": ["Average rating", "Quantity rating"],
  "Minimize": ["Price", "Price per kilo"]
}

var lacentrale = {
  "Articles": "//div[contains(@class,'adContainer')]/a/div/div[@class='subContRight']",
  "DecimalSeparator": "coma",
  "Price": "div[@class='vehicleCont']/div[@class='kmYearPrice']/div[contains(@class,'fieldPrice')]/nobr/span[2]",
  "Year": "div[@class='vehicleCont']/div[@class='kmYearPrice']/div[@class='fieldYear']",
  "Km": "div[@class='vehicleCont']/div[@class='kmYearPrice']/div[@class='fieldMileage']",
  "Warranty": "div[@class='vehicleCont']/div[contains(@class,'warranty')]/span",
  "Brand": "h3[@class='brandModelTitle']/span[contains(@class,'brandModel')]",
  "Version": "h3[@class='brandModelTitle']/span[contains(@class,'version')]",
  "Maximize": ["Warranty", "Year"],
  "Minimize": ["Price", "Km"]
}

var aliexpress = {
  "Articles": "//li[contains(@class, 'list-item')]",
  "DecimalSeparator": "coma",
  "Name" : ".//h3/a/@title",
  "Price": ".//span[@class='price price-m']/span[@class='value']",
  "Average rating" : ".//span[@class='star star-s']/@title",
  "Quantity rating": ".//a[@class='rate-num ']",
  "Number of orders" :  ".//em[@title='Commandes Totales']",
  "Maximize": ["Average rating", "Quantity rating", "Number of orders"],
  "Minimize": ["Price"]
}










/**
 * Treat the message received
 * @param {*} request the request
 * @param {*} sender
 * @param {*} sendResponse
 */
function search(request, sender, sendResponse) {
  var res;
  if (request.site) {
    res = loadInformations(request.site);
  } else {
    res = getInitInformations(request.init);
  }
  browser.runtime.sendMessage(null, res);
}

/**
 * Load informations of the current page
 * @param {*} site the site
 */
function loadInformations(site) {
  var res = [];
  var iterator = document.evaluate(eval(site).Articles, document, null, XPathResult.ORDERED_NODE_ITERATOR_TYPE, null);
  try {
    var nbInfos = Object.keys(eval(site)).length-4;
    var thisNode = iterator.iterateNext();
    while (thisNode) {
      var infos = {};
      var nbInfosToZero = 0;
      for (var property in eval(site)) {
        if (property != "Articles" && property != "DecimalSeparator" && property != "Maximize" && property != "Minimize") {
          var dataToAdd = document.evaluate(eval(site)[property], thisNode, null, XPathResult.ORDERED_NODE_ITERATOR_TYPE, null);
          try {
            infos[property] = dataToAdd.iterateNext().textContent;
          } catch (e) {
            infos[property] = "0 (undefined value)";
            nbInfosToZero++;
          }
        }
      }
      if(nbInfosToZero!=nbInfos){
        var infosJson = JSON.stringify(infos);
        res.push(infosJson);
      }
      thisNode = iterator.iterateNext();
    }
    return res;
  }
  catch (e) {
    console.log('Erreur : L\'arbre du document a été modifié pendant l\'itération ' + e);
  }
}

/**
 * Get the initialization informations
 * @param {*} site the site
 */
function getInitInformations(site) {
  try {
    eval(site)
  } catch (e) {
    return { "PROBLEM": "The extension is not available on this site. To add it, you have to add the XPaths needed in the configuration file." };
  }
  if (!eval(site).Articles) {
    return { "PROBLEM": "The extension is not available on this site. Check the configuration file and its requirements (dict 'Articles' undefined)." };
  }
  if (!eval(site).DecimalSeparator) {
    return { "PROBLEM": "The extension is not available on this site. Check the configuration file and its requirements (dict 'DecimalSeparator' undefined)." };
  } else if (eval(site).DecimalSeparator.toLowerCase().trim() != "coma" && eval(site).DecimalSeparator.toLowerCase().trim() != "dot") {
    return { "PROBLEM": "The extension is not available on this site. Check the configuration file and its requirements ('DecimalSeparator' must be 'coma' or 'dot')." };
  }
  if (eval(site).Maximize == undefined) {
    return { "PROBLEM": "The extension is not available on this site. Check the configuration file and its requirements (dict 'Maximize' undefined)." };
  }
  if (eval(site).Minimize == undefined) {
    return { "PROBLEM": "The extension is not available on this site. Check the configuration file and its requirements (dict 'Minimize' undefined)." };
  }
  var nbNumValues = 0;
  var val;
  var maxim = eval(site).Maximize;
  for (v in maxim) {
    val = maxim[v].trim();
    if (val) {
      if (val in eval(site)) {
        nbNumValues++;
      } else {
        return { "PROBLEM": "The extension is not available on this site. Check the configuration file and its requirements (dict 'Maximize' must contains only other keys of the site)." };
      }
    }
  }
  var minim = eval(site).Minimize;
  for (v in minim) {
    val = minim[v].trim();
    if (val) {
      if (minim[v].trim() in eval(site)) {
        nbNumValues++;
      } else {
        return { "PROBLEM": "The extension is not available on this site. Check the configuration file and its requirements (dict 'Minimize' must contains only other keys of the site)." };
      }
    }
  }
  if (nbNumValues < 2) {
    return { "PROBLEM": "The extension is not available on this site. Check the configuration file and its requirements (dict 'Minimize' and 'Maximize' must contains at least 2 values in all." };
  }
  res = [];
  res["DecimalSeparator"] = eval(site)["DecimalSeparator"];
  res["Minimize"] = minim;
  res["Maximize"] = maxim;
  return res;
}

/*
 Assign the function as a listener for messages from the extension
*/
browser.runtime.onMessage.addListener(search);
