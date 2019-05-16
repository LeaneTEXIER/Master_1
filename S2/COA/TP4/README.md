# Noms

	Marine Deffontaine
	Léane Texier

# Description du travail:

    TP4: Templates

## Question 1 : fonctions sur vecteurs d'entiers

    Les fonctionnalités demandées ont été codées.  
    Les fonctions se trouvent dans le fichier 'set_operations.hpp'

    Les tests pour set_intersection ont été fait dans le fichier : 'test_intersect.cpp'

	Nous avons testé notre fonction pour différents cas:  
    * Intersection nulle  
    * Intersection de deux ensembles vides  
    * Intersection non nulle  

    Les tests pour set_union ont été fait dans le fichier : 'test_union.cpp'

	Nous avons testé notre fonction pour différents cas:  
    * Union de deux ensembles vides  
    * Union de deux ensembles non vides  

## Question 2 : containers

    Les fonctionnalités demandées ont été codées.  
    Nous avons fait les tests sur des vecteurs et des listes avec les mêmes cas que dans la question 1.  

## Question 3 : containers d'objets

    1. Les tests ont été fait dans 'test_intersect.cpp' et test_union.cpp' avec les mêmes cas que dans la question 1.  

    2. Il faut au minimum que la classe possède l'opérateur de comparaison pour pouvoir faire l'intersection et l'union.

## Question 4 : types differents

    On ne peut pas appeler set_intersect sur des containers contenant des objets de type différents car, d'après la définition de la fonction, nous indiquons que chaque container (ceux en entrées et celui en sortie) ont tous le même type d'objets. Cependant, il est possible de faire une intersection d'objets comparables en créant la classe adéquate et en créant ainsi des containers de cette classe.

## Question 5 : maps et vecteurs

	Il n'est pas possible d'appeler nos fonctions sur des map<int, string>. En effet, cette classe n'a pas de fonction 'push_back' qui est une fonction nous servant à ajouter un élément au container de retour. Cela est du au fait que c'est une map et qu'elle ne contient donc non pas un mais deux éléments.

## Question 6 : template additionnel

		Nous avons généraliser la fonction set_intersect en set_intersect_comp avec un paramétre template additionnel F.
		Pour cela nous avons du ajouter la possibilité d'avoir des containers avec des objets de type différents.

		Nous avons testé cette fonction dans le fichier 'test_intersect.cpp'.
