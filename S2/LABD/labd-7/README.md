# Léane Texier et Marine Deffontaine - M1S2
# TP7

## Exercice 1

# Requête 1:  

    La requête parcourt chaque livre du fichier 'biblio.xml'.  
    Pour chaque livre écrit par "Addison-Wesley" et ayant été publié après 1991, ajoute une balise <book> avec comme attribut l'année.
    Dans cette balise book, on y trouve une balise <title> contenant le titre du livre.  

# Requête 2:  

    La requête parcourt chaque livre du fichier 'biblio.xml'.  
    Pour chaque livre, chaque titre de ce livre et chaque auteur de ce livre, retourne une balise <result> contenant la balise <title> et <author>.  


# Requête 3 :

    Pour chaque nom de famille d'auteur, cherche le prénom associé.  
    Puis trie les auteurs par nom de famille et prénom.  
    La requête crée pour chaque auteur une balise <result> puis <author> et y ajoute les balises <last> et <first> en y mettant les valeurs associées.  
    Dans chaque balise <result> ajoute également les titres des livres de l'auteur.  


# Requête 4 :  

    Cette requête est équivalente à la précédente.  
    La seule différence est la délégation d'une partie du code à une fonction (trouver les titres de livre d'un auteur).  

# Requête 5 : 

    Cette requête parcourt la liste des livre et pour chaque livre ayant au moins un auteur, retourne le titre de ce livre, ainsi que son auteur ou ses  2 premiers auteurs.  
    Si le livre a plus de 2 auteurs, une balise <et-al/> est également ajouté au livre.  


# Requête 6 : 

    Pour chaque nom de famille d'auteur, cherche le prénom associé.  
    Puis trie les auteurs par nom de famille et prénom.  
    La requête crée pour chaque auteur une balise <result> puis <author> et y ajoute les balises <last> et <first> en y mettant les valeurs associées.  
    Dans chaque balise <result> ajoute également une balise <number> qui contient le nombre de livre écrit par l'auteur.

# Requête 7 :  

    Pour chaque année de parution de livre distincte, retourne une balise <year> avec en attribut 'value' l'année et 'avgprice' le prix moyen des livres de cette année là.  


## Exercice 2

    Le programme XQuery se trouve dans le fichier 'question1.xq'.
    Le fichier html correspondant se trouve dans le fichier 'maisons.html'


## Exercice 3

# Question 1

    Le programme XQuery se trouve dans le fichier 'question1.xq'.

# Question 2

    Le programme XQuery se trouve dans le fichier 'question2.xq'.
    Le résultat obtenu est identique au fichier 'exposure.xml'.

# Question 3

    Le programme XQuery se trouve dans le fichier 'question3.xq'.

# Question 4

    Le programme XQuery se trouve dans le fichier 'question4.xq'.