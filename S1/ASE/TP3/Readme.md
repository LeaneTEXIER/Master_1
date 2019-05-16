# Léane TEXIER

## Compilation et nettoyage
Pour compiler, il faut taper make                 
Pour supprimer tous les fichiers .o, il faut taper make clean                     
Pour supprimer tous les fichiers .o, les executables et le disque, il faut taper make realclean                 


## Exercice 1
### Question 1
Affichage infini de "tentative d'accès illégal à l'adresse 0"
### Question 2
Afin que le programme se  comporte correctement lorsqu’une erreur d’accès à la mémoire se produit, il est nécessaire de rajouter "exit(EXIT_FAILURE);" à la fin de la fonction mmuhandler.

## Exercice 2
### Question 1
Le programme imprime "tentative d'accès illégal à l'adresse 0" du au deuxième appel à mmuhandler étant donné que dans ce cas là nous sommes en mode utilisateur.
### Question 2
Aucune adresse ne peut fonctionner car il y a aucune association entre les adresses physiques et les adresses virtuelles.
