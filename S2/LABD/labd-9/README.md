# Léane Texier et Marine Deffontaine - M1S2
# TP9

## Exercice 1

    Les fichiers relatifs à cet exercice sont dans le dossier 'Exercice1'.

# Question 1 et 2

    Nous avons exprimé les triplets de la phrase "La C4 est une voiture dont la marque est Citroën" dans le fichier 'exercice1.turtle' pour le format Turtle et dans le fichier 'exercice1.rdf' pour le format RDF/XML. Puis, nous avons ajouté l'information suivante "La société Citroën a été fondée en 1919" dans ces fichiers. Cette information entraîne l'ajout d'un triplet dont le sujet est 'Citroen', le prédicat est 'year' et l'objet est '1919'.
    Le graphe correspondant est visible en ouvrant 'exercice1.png'.

## Exercice 2

    Les fichiers relatifs à cet exercice sont dans le dossier 'Exercice2'.

    Les triplets définis pour la phrase "Boby pense que Léon est malade" sont dans le fichier 'exercice2.turtle' pour le format Turtle et dans le fichier 'exercice2.rdf' pour le format RDF/XML.
    Le graphe correspondant est visible en ouvrant 'exercice2.png'.

## Exercice 3

    Les fichiers relatifs à cet exercice sont dans le dossier 'Exercice3'.

    La liste (a b c d) a été exprimé grâce à un conteneur dans le fichier 'conteneur.turtle' pour le format Turtle et dans le fichier 'conteneur.rdf' pour le format RDF/XML. Le graphe correspondant est visible en ouvrant 'conteneur.png'.
    Cette même liste a été exprimé à l'aide de rdf:first et rdf:rest dans le fichier 'firstRest.turtle' pour le format Turtle et dans le fichier 'firstRest.rdf' pour le format RDF/XML. Le graphe correspondant est visible en ouvrant 'firstRest.png'.

## Exercice 4

    Les fichiers relatifs à cet exercice sont dans le dossier 'Exercice4'.

# Question 1

    Les 10 triplets définis dans ce fichier sont :
        * 'Personne' est une classe.
        * 'Stagiaire' est une classe.
        * 'Stagiaire' est une sous-classe de 'Personne'.
        * 'Tuteur' est une classe.
        * 'Tuteur' est une sous-classe de 'Personne'.
        * 'encadre' est une propriété.
        * 'encadre' a comme sujet 'Tuteur'.
        * 'encadre' a comme objet 'Stagiaire'.
        * 'encadre' est une sous-propriété de 'http://xmlns.com/foaf/0.1/knows'.
        * 'Ali' encadre 'Louis'.

# Question 2

    Les 5 triplets supplémentaires que l'on peut intégrer grâce à RDFS sont :
    * 'Ali' est un 'Tuteur'.
    * 'Ali' est une 'Personne'.
    * 'Louis est un 'Stagiaire'.
    * 'Louis' est une 'Personne'.
    * 'Ali' connait 'Louis'.

## Exercice 5
    Les fichiers relatifs à cet exercice sont dans le dossier 'Exercice5'.
