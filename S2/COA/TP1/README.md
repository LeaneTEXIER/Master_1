# Noms

	Marine Deffontaine
	Léane Texier

# Description du travail:

	TP 1: Stack class

## Question 1

	Toutes les fonctionnalités demandées ont été codées.
	Tests de régression :
		1. Le copy-constructor => 'testCopyConstructor.cpp'
		Nous avons tester cette fonctionnalité pour 2 cas différents :
			* Cas d'une pile vide :
				- Création de la pile
				- Copie de la pile
				- Vérification que le nombre d'éléments des deux piles sont égaux ('REQUIRE(s0.size() == s02.size())')
				- Vérification que les tailles des deux tableaux sont égales ('REQUIRE(s0.maxsize() == s02.maxsize())')
				- Ajout d'un élément dans la pile initiale
				- Vérification que la pile initiale a alors un élément en plus que la pile copiée ('REQUIRE(s0.size() == s02.size()+1)')
				- Suppression de l'élément ajoutée dans la pile initiale
				- Ajout d'un élément dans la pile copiée
				- Vérification que la pile copiée a alors un élément en plus que la pile initiale ('REQUIRE(s0.size()+1 == s02.size())')
			* Cas d'une pile non vide :
				- Création de la pile
				- Ajout d'un élément dans la pile
				- Copie de la pile
				- Vérification que le nombre d'éléments des deux piles sont égaux ('REQUIRE(s.size() == s2.size())')
				- Vérification que les tops des deux piles sont identiques ('REQUIRE(s.top() == s2.top())')
				- Vérification que les tailles des deux tableaux sont égales ('REQUIRE(s.maxsize() == s2.maxsize())')
				- Ajout d'un élément dans la pile initiale
				- Vérification que la pile initiale a alors un élément en plus que la pile copiée ('REQUIRE(s.size() == s2.size()+1)')
				- Vérification que les tops des deux piles sont différents ('REQUIRE(s.top() != s2.top())')
				- Suppression de l'élément ajoutée dans la pile initiale
				- Ajout d'un élément dans la pile copiée
				- Vérification que la pile copiée a alors un élément en plus que la pile initiale ('REQUIRE(s.size()+1 == s2.size())')
				- Vérification que les tops des deux piles sont différents ('REQUIRE(s.top() != s2.top())')
		2. Le destructeur
			Afin de tester cette fonctionnalité, nous avons créé un main 'testDestructeur.cpp' qui crée une pile et y ajoute un élément. Nous avons alors lancé ce main avec valgrind :
				'valgrind --tool=memcheck ./destructeur_main'
			Grâce à cela, nous avons remarqué que le destructeur est opérationnel car il y a autant d'"alloc" que de "free" effectués.
				'==1278== HEAP SUMMARY:
				==1278==     in use at exit: 0 bytes in 0 blocks
				==1278==   total heap usage: 2 allocs, 2 frees, 72,744 bytes allocated'
		3. La taille du tableau qui s'adapte => 'testTaille.cpp'
			- Création d'une pile
			- Récupération de la taille initiale du tableau
			- Ajout d'éléments dans la pile suivant le nombre d'éléments que peut contenir le tableau pour avoir une pile pleine
			- Vérification que la pile est bien pleine, c'est-à-dire que le nombre d'éléments présents dans le tableau est le même que la taille de la pile ('REQUIRE(s.size() == s.maxsize())')
			- Ajout d'un élément dans la pile
			- Vérification que le nombre d'éléments dans la pile a bien été incrémenté de 1 ('REQUIRE(s.size() == n+1)')
			- Vérification que la taille du tableau a bien été augmentéé ('REQUIRE(s.maxsize() > n)')

## Question 2

	Toutes les fonctionnalités demandées ont été codées.
	Tests de régression de la fonction 'reduce' => 'testReduce.cpp'
	Nous avons tester cette fonction pour 3 cas différents :
		* Cas d'une pile vide :
			- Création de la pile
			- Réduction de cette pile
			- Vérification que le nombre d'éléments présents dans le tableau est le même que la taille de la pile ('REQUIRE(s.size() == s.maxsize())')
			- Vérification que le nombre d'éléments présents dans le tableau est bien nul ('REQUIRE(s.size() == 0)')
		* Cas d'une pile non pleine (qui contient 1 élément sachant que le tableau initial peut contenir jusqu'à 10 éléments) :
			- Création de la pile
			- Ajout d'un élément dans cette pile
			- Récupération du top de cette pile
			- Réduction de la pile
			- Vérification que le nombre d'éléments présents dans le tableau est le même que la taille de la pile ('REQUIRE(s1.size() == s1.maxsize())')
			- Vérification que le nombre d'éléments présents dans le tableau est bien égale à 1 étant donné qu'on a inséré un seul élément dans cette pile ('REQUIRE(s1.size() == 1)')
			- Vérification que la réduction n'a pas modifié le top de notre pile ('REQUIRE(s1.top() == top)')
		* Cas d'une pile pleine
			- Création de la pile
			- Ajout d'éléments dans la pile suivant le nombre d'éléments que peut contenir le tableau pour avoir une pile pleine
			- Récupération du top de cette pile
			- Récupération de la taille de la pile
			- Vérification que la pile est bien pleine, c'est-à-dire que le nombre d'éléments présents dans le tableau est le même que la taille de la pile ('REQUIRE(s2.size() == s2.maxsize())')
			- Réduction de la pile
			- Vérification que le nombre d'éléments présents dans le tableau est le même que la taille de la pile ('REQUIRE(s2.size() == s2.maxsize())')
			- Vérification que le nombre d'éléments présents dans le tableau est bien égale à la taille de la pile créée initialement ('REQUIRE(s2.size() == size)').
			- Vérification que la réduction n'a pas modifié le top de notre pile ('REQUIRE(s2.top() == top)')

## Question 3

	Toutes les fonctionnalités demandées ont été codées.
	Tests de régression de la fonction 'half' => 'testHalf.cpp'
	La fonction half enlève la moitié des éléments du tableau. Si le tableau contient un nombre impair k*2+1 d'éléments, alors seulement les k premiers éléments sont gardées dans la pile, le reste étant enlevé. 
	Nous avons tester cette fonction pour 3 cas différents :
		* Cas d'une pile vide :
			- Création de la pile
			- Appelle de la fonction 'half'
			- Vérification que le nombre d'éléments présents dans le tableau est toujours nul ('REQUIRE(s0.size() == 0)')
		* Cas d'une pile contenant un nombre pair d'éléments :
			- Création de la pile
			- Ajout de 10 éléments différents dans cette pile
			- Récupération du top de cette pile
			- Appelle de la fonction 'half'
			- Vérification que le nombre d'éléments présents dans la pile a bien été divisée par 2, donc égal à 5 ('REQUIRE(s.size() == 5)')
			- Vérification que le top de la pile a été modifié étant donné que nous n'avons que des éléments différents ('REQUIRE(si.top() != top)')
		* Cas d'une pile contenant un nombre impair d'éléments :
			- Création de la pile
			- Ajout de 9 éléments différents dans cette pile
			- Récupération du top de cette pile
			- Appelle de la fonction 'half'
			- Vérification que le nombre d'éléments présents dans la pile a bien été divisée par 2, donc égal à 4 ('REQUIRE(s.size() == 4)')
			- Vérification que le top de la pile a été modifié étant donné que nous n'avons que des éléments différents ('REQUIRE(si.top() != top)')

## Question 4

	Toutes les fonctionnalités demandées ont été codées.
	Tests de régression de la fonction 'half_copy' => 'testHalfCopy.cpp'
	Nous avons tester cette fonction pour 3 cas différents :
		* Cas d'une pile vide :
			- Création de la pile
			- Récupération de la taille du tableau
			- Appelle de la fonction 'half_copy'
			- Vérification qu'il n'y a pas de changement sur la stack initiale :
				_ Pile vide, donc nombre d'éléments dans la pile égal à 0 ('REQUIRE(s0.size() == 0)')
				_ Taille du tableau de la pile initiale inchangé ('REQUIRE(s0.maxsize() == maxsize)')
			- Vérification des changements sur la pile obtenue (ici aucun changement étant donné que la pile est vide) :
				_ Nombre d'éléments dans la pile égal à la moitié du nombre d'éléments de la pile initiale, dans ce cas 0 ('REQUIRE(s02.size() == 0)')
		* Cas d'une pile contenant un nombre pair d'éléments :
			- Création de la pile
			- Ajout de 10 éléments différents dans cette pile
			- Récupération du top de cette pile
			- Récupération de la taille du tableau
			- Appelle de la fonction 'half_copy'
			- Vérification qu'il n'y a pas de changement sur la stack initiale :
				_ Nombre d'éléments dans la pile initiale égal au nombre d'éléments qu'on a inséré ('REQUIRE(s.size() == n)')
				_ Top de la pile inchangé ('REQUIRE(s.top() == top)')
				_ Taille du tableau de la pile initiale inchangé ('REQUIRE(s.maxsize() == maxsize)')
			- Vérification des changements sur la pile obtenue :
				_ Nombre d'éléments dans la pile égal à la moitié du nombre d'éléments de la pile initiale, dans ce cas 5 ('REQUIRE(s2.size() == 5)')
				_ Vérification que le top de la pile obtenue est différent du top de la pile initiale étant donné que nous n'avons que des éléments différents ('REQUIRE(s2.top() != top)')
		* Cas d'une pile contenant un nombre impair d'éléments :
			- Création de la pile
			- Ajout de 9 éléments différents dans cette pile
			- Récupération du top de cette pile
			- Récupération de la taille du tableau
			- Appelle de la fonction 'half_copy'
			- Vérification qu'il n'y a pas de changement sur la stack initiale :
				_ Nombre d'éléments dans la pile initiale égal au nombre d'éléments qu'on a inséré ('REQUIRE(si.size() == n)')
				_ Top de la pile inchangé ('REQUIRE(si.top() == top)')
				_ Taille du tableau de la pile initiale inchangé ('REQUIRE(si.maxsize() == maxsize)')
			- Vérification des changements sur la pile obtenue :
				_ Nombre d'éléments dans la pile égal à la moitié du nombre d'éléments de la pile initiale, dans ce cas 4 ('REQUIRE(si2.size() == 4)')
				_ Vérification que le top de la pile obtenue est différent du top de la pile initiale étant donné que nous n'avons que des éléments différents ('REQUIRE(s2.top() != top)')