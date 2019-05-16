# Léane Texier et Marine Deffontaine - M1S2
# TP10

## Exercice 1

# Question 1
    La requête retourne pour tous les éléments du document les couples (x,t) avec x le nom de l'élement et t le type de cet élément.

    On obtient 33 réponses.  
    Cela s'explique par le fait que certains résultats sont induits.

    John est de type 'Person'.

# Question 2

    Cette requête nous retourne les couples de mariés. Nous avons 6 résultats.

# Question 3

    La propriété utilisée pour donner l’âge d’une personne est "age".
    La requête permettant de trouver les personnes qui ont plus de 20 ans est la suivante :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT ?x WHERE {
            ?x humans:age ?y
            FILTER (xsd:integer(?y) > 20)
      }
  ```

# Question 4

    La propriété utilisée pour donner la pointure des chaussures des personnes est "shoesize".

    1. La requête pour extraire toutes les personnes (Person) avec leur pointure est la suivante :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT ?p ?shoesize WHERE {
            ?p a humans:Person
            ?p humans:shoesize ?shoesize
      }
  ```

    2. La requête pour extraire toutes les personnes et, si elle est disponible, leur pointure est la suivante :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT ?p ?shoesize WHERE {
            ?p a humans:Person
            OPTIONAL {?p humans:shoesize ?shoesize}
      }
   ```

    3. La requête pour extraire toutes les personnes et, si elle est disponible, leur pointure à condition que celle-ci soit supérieure à 8 est la suivante :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT ?p ?shoesize WHERE {
            ?p a humans:Person
            OPTIONAL {?p humans:shoesize ?shoesize
            FILTER (xsd:integer(?shoesize) > 8)}
      }
   ```

    4. La requête pour extraire toutes les personnes dont la pointure est supérieure à 8 ou dont la taille de chemise est supérieure à 12 (3 réponses) est la suivante :
  ```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT DISTINCT ?p
          WHERE {
            {
              ?p humans:shoesize ?shoesize
              FILTER (xsd:integer(?shoesize) > 8)
            } UNION {
              ?p humans:shirtsize ?shirtsize
              FILTER (xsd:integer(?shirtsize) > 12)
            }
          }
  ```

# Question 5

    Dans la réponse précédente, on remarque que l’URI de John est "http://www.inria.fr/2007/09/11/humans.rdfs-instances#John".

    1. La requête pour trouver toutes les propriétés de John est la suivante :
  ```
      PREFIX humansInstances: <http://www.inria.fr/2007/09/11/humans.rdfs-instances#>
      SELECT ?propriete ?valeur WHERE {
            humansInstances:John ?propriete ?valeur
      }
  ```

    2. La requête permettant de demandez une description de John au moteur en utilisant la clause SPARQL prévue pour cela est la suivante :
  ```
      DESCRIBE <http://www.inria.fr/2007/09/11/humans.rdfs-instances#John>
   ```

# Question 6

    La propriété utilisée pour donner les enfants d’une personne est "hasChild".
    1. La requête pour donner tous les couples (parent,enfant) d'après la propriété donnée ci-dessus est la suivante :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT ?parent ?enfant WHERE {
            ?parent  humans:hasChild ?enfant
      }
   ```

    On obtient ainsi les couples suivants :
      (Flora,Pierre)
      (Gaston,Jack)
      (Gaston,Pierre)
      (Harry,John)
      (Jack,Harry)

    2. La requête pour trouver les personnes qui ont au moins un enfant est la suivante :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT ?parent WHERE {
            ?parent  humans:hasChild ?enfant
      }
   ```

    3. On obtiens 5 réponses dont 1 doublon.

    4.Afin d'éviter les doublons, il suffit d'ajouter le mot-clé "DISTINCT" après le "SELECT". On obtient alors 4 résultats.

    5. La requête pour trouver les hommes (Man) qui n’ont pas d’enfant est la suivante :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT DISTINCT ?p WHERE {
            ?p a humans:Man
             OPTIONAL {?p humans:hasChild ?child}
             FILTER (!bound(?child) )
      }
  ```

    6. La requête pour avoir les femmes (Woman) mariées, avec éventuellement leurs enfants est la suivante :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT DISTINCT ?woman ?child WHERE {
            ?woman a humans:Woman
            ?woman humans:hasSpouse ?spouse
            OPTIONAL {?woman humans:hasChild ?child}
      }
  ```

      On obtient ainsi deux résultats :
          * Flora qui a un fils : Pierre
          * Jennifer

# Question 7

    La requête qui permet d'avoir les couples de personnes qui ont la même taille de chemise en enlevant les redondances est la suivante :
  ```
        PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
        SELECT DISTINCT ?p1 ?p2 WHERE {
              ?p1 humans:shirtsize ?size
              ?p2 humans:shirtsize ?size
               FILTER (?p1 > ?p2)
        }
  ```

      On obtient ainsi les couples suivants :
          * (John, Gaston)
          * (Mark, Karl)
          * (Pierre, Karl)
          * (Pierre, Mark)

# Question 8
    Clôture symétrique de la relation hasFriend :
  ```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
            CONSTRUCT {
                  ?p2 humans:hasFriend ?p1
            } WHERE {
                  ?p1 humans:hasFriend ?p2
                  FILTER
                    NOT EXISTS {?p2 humans:hasFriend ?p1}
          }
  ```



# Question 9

    La requête qui permet de rechercher toutes les personnes qui ne sont pas des hommes est la suivante :

  ```
        PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
        SELECT ?x WHERE {
            ?x a humans:Person
            FILTER NOT EXISTS {?x a humans:Man}
        }
  ```

    On retrouve des hommes étant donné qu'ils sont définis seulement comme des personnes et non comme des hommes.


## Exercice 2

# Question 1

    L’espace de nom associé à l’ontologie est base.
    Animal et Person sont des domaines de propriétés :  
    Animal est un domaine et co-domaine de la propriété hasAncestor  
    Male est un co-domaine de hasFather  
    Female est un co-domaine de hasMother  
    hasBrother a comme domaine Animal et co-domaine Male  
    hasSister a comme domaine Animal et co-domaine Female  
    hasFriend et hasSpouse ont comme domaine et co-domaine Person  
    shoesize, shirtsize, trouserssize ont comme domaine Person  

    La propriété âge peut être porté par toutes les classes car elle n'a pas de domaine.  

# Question 2

    La requête permettant de retrouver toutes les classes de l’ontologie est la suivante :
  ```
        SELECT ?x WHERE {
          ?x a rdfs:Class
        }
  ```

# Question 3

    La requête permettant de retrouver tous les liens subClassOf de l’ontologie est la suivante :  
        ```
        SELECT ?sousClasse ?classe WHERE {
              ?sousClasse rdfs:subClassOf ?classe
        }
        ```

# Question 4

  La requête peremettant d'avoir les définitions et la traduction de "shoe size" est la suivante :
```
  PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
  SELECT  ?definition ?traduction WHERE {
    {
      ?class rdfs:comment ?definition
      ?class rdfs:label ?l
      FILTER (str(?l) = "shoe size")
    } UNION {
        humans:shoesize rdfs:label ?traduction
        ?class rdfs:label ?l
        FILTER (str(?l) = "shoe size")
        FILTER (lang(?traduction)="fr")
      }
    }
```

# Question 5

    Les synonymes du terme "personne" en Français sont :  
    * homme  
    * humain   
    * être humain  

    On les trouve grâce à la requête suivante :  
 ```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT ?synonyme WHERE {
      ?class rdfs:label ?synonyme
      FILTER (lang(?synonyme) = "fr")
      FILTER (str(?synonyme) != "personne")
      {
        SELECT ?class WHERE {
          ?class rdfs:label ?l
          FILTER (lang(?l) = "fr")
          FILTER (str(?l) = "personne")
        }
      }
    }
 ```

# Question 6

    Les différents sens en français du terme "size" (homonymie) sont :  
    * taille
    * taille de chemise
    * pointure
    * taille de pantalon

    On les trouve avec la requête suivante :  
  ```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
        SELECT DISTINCT ?homonyme WHERE {
          ?class rdfs:label ?homonyme
          FILTER (lang(?homonyme) = "fr")
          {
            SELECT ?class WHERE {
               ?class rdfs:label ?l
              FILTER (str(?l) = "size")
            }
          }
        }
  ```


    Les différents sens en français du terme "homme" (homonymie) sont :  
    * homme  
    * humain  
    * personne  
    * être humain

    On les trouve avec la requête suivante :
  ```
       PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
        SELECT DISTINCT ?homonyme WHERE {
          ?class rdfs:label ?homonyme
          FILTER (lang(?homonyme) = "fr")
          {
            SELECT ?class WHERE {
               ?class rdfs:label ?l
              FILTER (str(?l) = "homme")
            }
          }
        }
  ```

## Exercice 3

# Question 1

  1. La requête pour obtenir toutes les ressources de type Person est la suivante :
  ```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT * WHERE {
        ?x rdf:type humans:Person
    }
  ```

  On trouve 7 réponses.  

  La requête pour obtenir toutes les ressources de type Animal est la suivante :
  ```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT * WHERE {
        ?x rdf:type humans:Animal
    }
  ```

  On trouve 0 réponse.  

  2. Suite à l'ajout du schéma "human_2007_09_11.rdfs", on obtient 17 résultats pour chacune des 2 requêtes précédentes. Cela est du aux relations faites dans le fichier rdfs. En effet, ce fichier nous indique que les animaux (Animal), les hommes (Man), les femmes (Woman), les professeurs (Lecturer), les chercheurs (Researcher) sont des personnes.


# Question 2

  1. La requête permettant de retrouver les mâles (type Male) et leur épouse est la suivante :
  ```
  PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
  SELECT ?male ?spouse WHERE{
		?male a humans:Male
		?male humans:hasSpouse ?spouse
	 }
  ```

  On obtient 1 résultat car dans le fichier rdf, il n'y a qu'un Male qui a une épouse.

  2. En ajoutant l'instance demandé et en refaisant la requête on obtient alors 2 résultats soit un de plus  que précédemment. Cela est dû au fait que Karl est alors considéré comme un type Male car l'attribut hasFather procure ce statut.

# Question 3
  La requête pour avoir tous les professeurs (Lecturer) et leur type est la suivante :
  ```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    SELECT DISTINCT * WHERE {
    	?prof rdf:type humans:Lecturer
    	?prof rdf:type ?type
    }
  ```

    On obtient 7 résultats. 2 types pour "Eve" et 5 types pour "Laura". Lecturer est une sous-classe de Person donc chaque Lecturer est alors également une Person.

    La requête permettant d'avoir les instances communes de Person et Mal est la suivante :
```
      PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
      SELECT DISTINCT ?x WHERE {
      	?x a humans:Person
        ?x a humans:Male
      }
```

    John est présent dans la réponse de la requête car c'est une Person.  
    Il est le père (hasFather) de Mark. De plus, d'après le typage, on sait que la propriete hasFather implique que c'est un Male. Ainsi, on en déduit que John est un Male.


# Question 4

  La requête permettant d'avoir les instances de la relation hasAncestor est la suivante :
 ```
    PREFIX humans: <http://www.inria.fr/2007/09/11/humans.rdfs#>
    	SELECT DISTINCT ?h WHERE
    	{
    		?x humans:hasAncestor ?h
    	}
 ```

    Les résultats obtenus s'explique par le fait que la propriété hasParent a comme sous-propriété hasAncestor.  
    De plus, hasParent a comme sous-propriété hasFather et hasMother. Ainsi, la requête nous donne toutes les instances des relations hasParent, hasFaher et hasMother.
