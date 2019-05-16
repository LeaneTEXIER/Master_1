# Noms

	Marine Deffontaine
	Léane Texier

# Description du travail:

	TP 2: Opérations sur les stacks

## Question 1 : Opérateur d'affectaction

  Les fonctionnalités demandées ont été codées.   
  Le fichier de test se nomme : 'testAffectation.cpp'  
	Comme demandé, nous avons tester l'équivalence de l'opération d'affectation avec le constructeur par copie. Nous l'avons testé pour 2 cas différents :  
			* Cas d'une pile vide :  
				- Création de la pile  
				- Affection de la pile initiale à une nouvelle pile (avec l'opérateur codé)  
				- Copie de la stack initiale avec le constructeur par copie  
				- Vérification que le nombre d'éléments des deux piles obtenues sont égaux ('REQUIRE(sv2.size() == sv3.size())')  
				- Vérification que les tailles des deux tableaux sont égales ('REQUIRE(sv2.maxsize() == sv3.maxsize())')  
		 	* Cas d'une pile non vide :  
				- Création de la pile  
				- Ajout de 2 éléments différents dans cette pile  
				- Affection de la pile initiale à une nouvelle pile (avec l'opérateur codé)  
				- Copie de la stack initiale avec le constructeur par copie  
				- Vérification que le nombre d'éléments des deux piles obtenues sont égaux ('REQUIRE(s2.size() == s3.size())')  
				- Vérification que les deux piles obtenues ont le même top ('REQUIRE(s2.top() == s3.top())')  
				- Vérification que les tailles des deux tableaux sont égales ('REQUIRE(s2.maxsize() == s3.maxsize())')  
				- Vérification que le contenu des deux piles obtenues sont égaux  

## Question 2 : Opérateur de comparaison

  Les fonctionnalités demandées ont été codées.  
  Le fichier de test se nomme : 'testComparaison.cpp'  
	Comme demandé, nous avons tester que deux piles sont égales après une opération d’affectation. Nous l'avons testé pour 2 cas différents :  
			* Cas d'une pile vide :  
				- Création de la pile  
				- Affection de la pile initiale à une nouvelle pile  
				- Vérification que les deux piles sont égales ('REQUIRE (sv == sv2)')  
		 	* Cas d'une pile non vide :  
				- Création de la pile  
				- Ajout de 2 éléments différents dans cette pile  
				- Affection de la pile initiale à une nouvelle pile  
				- Vérification que les deux piles sont égales ('REQUIRE (s == s2)')  
				- Retire le top de chaque pile  
				- Vérification que les deux piles sont égales ('REQUIRE (s == s2)')  		

## Question 3 : Opérateur d'addition

  Les fonctionnalités demandées ont été codées.  
  Le fichier de test se nomme : 'testOperateurAddition.cpp'  
	Comme demandé, nous avons testé l'équivalence entre notre opérateur et le résultat obtenu avec le code donné.  
		- Création de deux piles identiques avec 2 éléments différents  
		- Vérification qu'elles sont égales ('REQUIRE(s == sm)')  
		- Création d'une pile avec 2 éléments différents et différents des éléments des premières piles  
		- Opération d'addition des deux listes différentes avec l'opérateur codé  
		- Opération d'addition des deux listes différentes avec le code donné  
		- Vérification de l'équivalence des deux résultats obtenus ('REQUIRE(s == sm)')  

## Question 4 : Opérateur globale d'addition

  Les fonctionnalités demandées ont été codées.  
  Le fichier de test se nomme : 'testOperateurGlobalAddition.cpp'  
	Comme demandé, nous avons tester l'équivalence entre l'opérateur globale d'addition et l'opérateur membre d'addition.  
		- Création de deux piles identiques avec 2 éléments différents  
		- Vérification qu'elles sont égales ('REQUIRE(s == sm)')  
		- Création de deux piles identiques avec 2 éléments différents et différents des éléments des premières piles  
		- Vérification qu'elles sont égales ('REQUIRE(s2 == sm2)')  
		- Opération d'addition des deux listes différentes avec l'opérateur globale  
		- Opération d'addition des deux listes différentes avec l'opérateur membre  
		- Vérification de l'équivalence des deux résultats obtenus ('REQUIRE(s == sm)')  

## Question 5 : Opérateur d'affichage

  Les fonctionnalités demandées ont été codées.  
  Le fichier de test se nomme : 'testOperateurAffichage.cpp'  
	Comme demandé, nous avons tester notre opérateur sur le stringstream.  Nous l'avons testé pour 2 cas différents :  
			* Cas d'une pile vide :  
				- Création de la pile  
				- Impression du contenu de la pile vide sur la sortie s1 grâce à notre opérateur d'affichage  
				- Impression du contenu desiré sur la sortie s2  
				- Vérification que les deux chaines sont équivalentes ('REQUIRE (s1.str() == s2.str())')  
		 	* Cas d'une pile non vide :  
				- Ajout de 2 éléments différents dans la pile  
				- Impression du contenu de la pile sur la sortie s1 grâce à notre opérateur d'affichage  
				- Impression du contenu desiré sur la sortie s2  
				- Vérification que les deux chaines sont équivalentes ('REQUIRE (s1.str() == s2.str())')  

## Question 6 : Safety push

  Les fonctionnalités demandées ont été codées.  
  Le code se trouve dans le fichier 'Stack.cpp'  
	La fonction ajoute l'élément qui est en paramètre si la pile est vide ou si l'élément est inférieur au top de cette pile. Dans la cas contraire, elle lève une exception de type IncorrectPush.

## Question 7 : Programme "Les Tours de Hanoï"

  Les fonctionnalités demandées ont été codées.  
  Le code se trouve dans le fichier : 'hanoi.cpp'  

  On peut lancer le programme grâce à la commande
  ```
  ./hanoi [nb disques]
  ```
