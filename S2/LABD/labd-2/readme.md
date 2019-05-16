# Léane Texier et Marine Deffontaine - M1S2
# TP2

## Exercice 1
### Question 1
	//livre[titre="edition"] => Retourne les éléments livre qui ont comme titre "edition"
	//livre[titre=edition] => Retourne les éléments livre qui ont leur titre et leur edition identiques.

	Exemple pour lequel le résultat identique :
	<livre>
		<titre>edition</titre>
		<edition>edition</edition>
	</livre>

### Question 2
	* L'expression 1 retourne le dernier élément livre, sous-élément de item, si son attribut titre est "labd", sinon retourne aucun résultat.
	* L'expression 2 retourne le dernier élément livre, sous-élément de item, dont l'attribut titre est "labd".
	* L'expression 3 retourne le dernier élément livre, sous-élément de item, si son attribut titre est "labd", sinon retourne aucun résultat.

	1 et 3 donnent toujours le même résultat.

	Exemple pour lequel le résultat est différent selon l'expression :
	<item>
		<livre titre="labd"></livre>
		<livre titre="coa"></livre>
	</item>

	- Pour les expressions 1 et 3, cela nous donne alors aucun resultat.
	- Pour l'expression 2, cela nous retourne l'élément livre dont l'attribut titre est "labd".

### Question 3
	/descendant::livre[1] => Retourne 1 seul resultat qui est le premier livre du document.
	//livre[1] <=> /descendant-or-self::./child::livre[1] => Retourne tous les premiers fils livres de tous les noeuds du document. C'est en deux étapes.

## Exercice 2
	1 - //fruit/producteur
	2 - //legume[origine="Espagne"]
	3 - //fruit[@type="clementine" and @calibre="1"]/bio/../origine
	4 - //origine[@region="Bretagne"]/../producteur

## Exercice 3
### recettes1.xml
	1 - //recette/titre
	2 - //nom_ing
	3 - //recette[2]/titre
	4 - //recette//etape[position()=last()]
	5 - count(//recette)
	6 - //recette[count(./ingredients/ingredient) < 7]
	7 - //recette[count(./ingredients/ingredient) < 7]/titre
	8 - //recette[contains(lower-case(ingredients/ingredient/nom_ing), "farine")]
	9 - //recette[contains(lower-case(categorie),"entrée")]

### recettes2.xml
	1 - //recette/titre
	2 - //ingredient/@nom
	3 - //recette[2]/titre
	4 - //recette//etape[position()=last()]
	5 - count(//recette)
	6 - //recette[count(./ingredients/ing-recette) < 7]
	7 - //recette[count(./ingredients/ing-recette) < 7]/titre
	8 - //recette[contains(lower-case(ingredients/ing-recette/@ingredient), "farine")]
	9 - //recette[contains(lower-case(@categ),"entree")]

## Exercice 4
	1 - count(//dict[key="Tracks"]/dict/key)
	2 - //key[text()="Album"]/following-sibling::string[1]
	3 - //key[text()="Genre"]/following-sibling::string[1]
	4 - count(//key[text()="Genre"]/following-sibling::string[1][text()="Jazz"])
	5 - distinct-values(//key[text()="Genre"]/following-sibling::string[1])
	6 - //key[text()="Play Count" and following-sibling::integer[1]>=1]/parent::dict/key[text()="Name"]/following-sibling::string[1]
	7 - //dict[key="Tracks"]/dict/dict[not(key="Play Count")]/key[text()="Name"]/following-sibling::string[1] | //key[text()="Play Count" and following-sibling::integer[1]<1]/parent::dict/key[text()="Name"]/following-sibling::string[1]

	ou

	//dict[key="Tracks"]/dict/dict/key[text()="Name"]/following-sibling::string[1]
	except
	//key[text()="Play Count" and following-sibling::integer[1]>=1]/parent::dict/key[text()="Name"]/following-sibling::string[1]

	8 - //dict[key="Tracks"]/dict/dict[not(key[text()="Year"]/following-sibling::integer[1] > //key[text()="Year"]/following-sibling::integer[1])]/key[text()="Year"]/../key[text()="Name"]/following-sibling::string[1]
