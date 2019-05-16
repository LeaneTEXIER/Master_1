# Léane Texier et Marine Deffontaine - M1S2
# TP4

## Exercice 1
	Les contraintes de clé ont été ajoutées dans le fichier 'championnat.xsd'.

## Exercice 2
### Question 1
	Les documents 'examen-1.xsd' et 'examen-2.xsd' ont été complétés et testés.

## Exercice 3
### Question 1
	Deux schémas ont été créés 'a.xsd' et 'b.xsd' afin de valider le document 'a.xml'.

## Exercice 4
### Question 1
	Le fichier 'personnes.xsd' a été complété avec les définitions manquantes. Puis, il a été testé.
### Question 2
	//* => Renvois tous les couples (balises, valeurs)
	//nom => Aucun résultat
	'//nom' ne renvoies rien car l'espace de nom n'est pas défini.

### Question 3
	Afin d'obtenir la date de naissance de toutes les personnesde sexe masculin, il faut faire la requête xpath suivante (avec l'association préfixe/espace de nom défini comme ci-dessous):
	declare namespace f = "http://www.fil.univ-lille1.fr/labd" ;
	//f:personne[f:sexe="M"]/f:naissance/f:date

## Exercice 5
### Question 1
	Le schéma XML souhaité a été défini dans le fichier 'livretDeFamille.xsd'.
	
