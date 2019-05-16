## La bibliothéque CImg

	Pour visualiser les formes, on utilise la librairie /CImg/
[[http://cimg.eu/]]. La librairie est composée d'un seul fichier
CImg.h.

	La documentation de la librairie est fourni dans
[docs/CImg_reference.pdf](docs/CImg_reference.pdf).


# Noms

	Marine Deffontaine
	Léane Texier

# Description du travail:

	TP 3: Inheritance sur les formes

## Question 1 : Les formes

	Les fonctionnalités demandées ont été codées.  
	La fonction 'draw' doit être virtuelle car la forme à dessiner est différente.  
	Cela implique donc que la méthode appelée dans la fonction 'draw' est différente suivant le cas.

## Question 2 : Mémoriser les formes

	Les fonctionnalités demandées ont été codées.  
	Le programme qui gère les formes est dans le fichier 'main.cpp'.  
	Il a ensuite été modifié et amélioré au fur et à mesure des questions.

## Question 3 : Déplacer une forme

	Les fonctionnalités demandées ont été codées.  
	La fonction 'is_inside' est virtuelle car la (ou les) formule(s) utile(s) pour savoir si un point est dans une forme dépend de la forme (rectangle, cercle ou triangle).  
	Les méthodes 'is_selected' et 'select' ne sont pas virtuelles.  
	Afin de montrer qu'une forme est sélectionnée, nous avons dans ce cas, mis l'opacité de la forme à 0.3 au lieu de 1.

## Question 4 : Créer, deplacer, effacer des formes

	Les fonctionnalités demandées ont été codées.  
	La touche 't' permet de créer un triangle.  
	La touche 'r' permet de créer un rectangle.  
	La touche 'c' permet de créer un cercle.  
	La touche 'm' permet de bouger toutes les formes sélectionnées en les déplaçant de sorte que le centre de la (ou les) forme(s) est (sont) à la position de la souris.  
	La touche 'd' permet de supprimer toutes les formes sélectionnées.


## Question 5 : Regrouper des formes

	Les fonctionnalités demandées ont été codées.  
	Afin de créer un groupe de formes, il faut sélectionner les différentes formes et/ou groupes de formes que vous voulez regrouper, puis appuyer sur 'g'.  
	Afin de déplacer un groupe de formes, il faut le sélectionner, se placer où vous souhaitez avec la souris et appuyer sur la touche 'm'.  
 	La première forme de la liste de formes se retrouve alors au centre de la souris et les autres sont déplacées du même vecteur.  
	Afin de décomposer un groupe de formes, il faut le selectionner et appuyer sur la touche ’u’.  
