Multicriteria
==============

Les sites marchands ne permettent à l'utilisateur de trier les produits que selon un seul critère à la fois (prix, notes, etc.) alors que la décision doit souvent se baser sur plusieurs critères.  

Cette web extension permet donc à l'utilisateur d'avoir un diagramme récapitulant tous les articles présents sur la page.  
Les articles sont représentées sur le diagramme suivant deux critères que l'utilisateur peut choisir dans une liste déroulante.  
Il a la possibilité de supprimer un point (un article) du diagramme en cliquant dessus.  
Il peut également voir les points de pareto (points en vert) afin de savoir quels articles sont les meilleurs.  


***********************************************************************************************************************


Merchant sites only allow the user to sort products according to only one criterion at a time (price, notes, etc.) whereas the decision must often be based on several criteria.

This web extension allows the user to have a graph summarizing all the articles on the page.
Articles are represented on the graph according to two criteria that the user can choose from a drop-down list.
He has the possibility to delete a point (an article) of the graph by clicking on it.
He can also see the pareto points (points in green) to find out which items are the best.



Configuration
--------------

Cette web extension est actuellement disponible sur le site de "La Centrale" ainsi que sur "Auchan drive" et "Ali Express".  
Afin de rendre la web extension accessible sur d'autres sites, il faut ajouter des données dans le fichier 'multicriteres.js'.
Pour chaque nouveau site, il suffit de créer un dictionnaire permettant de récupérer les informations sur la page grâce à des xpaths.

**Exemple de dictionnaire**
```
var auchandrive = {
    "Articles" : "//article[@class='product-item']",
    "DecimalSeparator" : "coma",
    "Name" : ".//dt[@class='product-item__title']/span",
    "Price" : "@data-price",
    "Average rating" : ".//div[@class='rating-average']",
    "Quantity rating" : ".//div[@class='rating-quantity']",
    "Price per kilo" : ".//span[@class='product-item__price-per']",
    "Maximize" : ["Average rating", "Quantity rating"],
    "Minimize" : ["Price", "Price per kilo"]
}
```

Le nom de la variable doit être le même que le nom du site.

Les champs "Articles", "DecimalSeparator", "Maximize" et "Minimize" sont obligatoires.

"DecimalSeparator" correspond au séparateur pour les nombres décimals présents sur le site. Sa valeur doit être "coma" (= virgule) ou "dot" (= point) (Exemple : "coma" pour les sites français et "dot" pour les sites anglais).

Le résultat du xpath présent dans "Articles" correspond à un article. Chaque article de la page doit pouvoir être récupéré avec ce xpath.

Toutes les données à récupérer sur le site sont des chemins relatifs par rapport à "Articles".
Une donnée est représentée comme ceci :  
"Nom de la donnée" : "Chemin relatif à partir de 'Articles'"
Par exemple => "Price" : "@data-price"

"Maximize" est une liste des clés (présentes dans le dictionnaire) dont les informations sont à maximiser sur le diagramme (Exemple : Notes des utilisateurs).

"Minimize" est à l'inverse une liste des clés (présentes dans le dictionnaire) dont les informations sont à minimiser (Exemple : Prix de l'article).

Il faut obligatoirement 2 valeurs au total dans ces deux listes.


***********************************************************************************************************************


This web extension is currently available on the site of "La Centrale" and on the site "Auchan drive" and "Ali Express".
In order to make the web extension accessible on other sites, you have to add data in the file 'multicriteres.js'.
For each new site, you just have to create a dictionary to retrieve the information on the page through xpaths.

** Dictionary example **
```
var auchandrive = {
    "Articles" : "//article[@class='product-item']",
    "DecimalSeparator" : "coma",
    "Name" : ".//dt[@class='product-item__title']/span",
    "Price" : "@data-price",
    "Average rating" : ".//div[@class='rating-average']",
    "Quantity rating" : ".//div[@class='rating-quantity']",
    "Price per kilo" : ".//span[@class='product-item__price-per']",
    "Maximize" : ["Average rating", "Quantity rating"],
    "Minimize" : ["Price", "Price per kilo"]
}
```

The name of the variable must be the same as the name of the site.

The fields "Articles", "DecimalSeparator", "Maximize" and "Minimize" are mandatory.

"DecimalSeparator" is the separator for the decimal numbers on the site. Its value must be "coma" or "dot" (Example: "coma" for French sites and "dot" for English sites).

The result of the xpath present in "Articles" is an article. Each article of the page must be find with this xpath.

All data to be retrieved on the site are relative paths to "Articles".
A data is represented like this:
"Name of the data": "Relative path from 'Articles'"
For example => "Price" : "@data-price"

"Maximize" is a list of keys
(present in the dictionary) whose information is to be maximized on the graph (Example: User Notes).

"Minimize" is a list of keys
(present in the dictionary) whose information is to be minimized (Example: Price of the article).

It is mandatory to have 2 values ​​in total in these two lists.
