import random
import sys


def heuristique_iter_alea(n, D):
    if(n<0):
        return [], 0
    premier_sommet = random.randint(0, n-1)
    return heuristique_iter_premier_sommet(n, D, premier_sommet)


def heuristique_iter_chaque_sommet_debut(n, D):
    if(n<0):
        return [], 0
    tourneeM, distanceMin = heuristique_iter_premier_sommet(n, D, 0)
    for i in range (1, n):
        tournee, distance = heuristique_iter_premier_sommet(n, D, i)
        if(distance < distanceMin):
            distanceMin = distance
            tourneeM = tournee
    return tourneeM, distanceMin

    
def heuristique_iter_premier_sommet(n, D, indice_premier_sommet):
    villes = [i for i in range (n)]
    premier_sommet = villes[indice_premier_sommet]
    ##Initialiser une permutation avec le premier sommet
    sommet = premier_sommet
    tournee = [sommet]
    villes.remove(sommet)
    distanceTotale = 0
    ##A chaque étape choisir la plus proche ville du dernier sommet visité avec une ville non encore sélectionnée et l’ajouter au tour
    while villes:
        nextSommet = villes[0]
        distanceNext = D[sommet][nextSommet]
        for i in villes[1:]:
            dist = D[sommet][i]
            if (distanceNext > dist):
                distanceNext = dist
                nextSommet = i
        tournee += [nextSommet]
        distanceTotale += distanceNext
        villes.remove(nextSommet)
        sommet = nextSommet
    distanceTotale += D[nextSommet][premier_sommet]
    return tournee, distanceTotale



def read_file_tsp(fichier):
    ##On lit les lignes où on ne doit pas récupérer d'informations
    fichier.readline()
    typeF = fichier.readline().split()[1]
    fichier.readline()
    ##On récupère la dimension
    dimension = int(fichier.readline().split(" ")[-1].split("\n")[0])
    ##On lit les lignes où on ne doit pas récupérer d'informations
    fichier.readline()
    fichier.readline()
    fichier.readline()
    ##On récupère la matrice de distance
    matrice = []
    for i in range (dimension):
        ligne = fichier.readline().split()
        while(len(ligne)!=dimension):
            ligne = fichier.readline().split()
        ligne_matrice = []
        for j in ligne:
            ligne_matrice += [int(j)]
        matrice += [ligne_matrice]
    return dimension, matrice, typeF
    


if __name__ == "__main__":
    if ((len(sys.argv)==3 and sys.argv[2]!="-a") and (len(sys.argv)!=2)):
        print("Pour lancer le programme, taper : python3 heuristique_globale_iterative.py nomFichierDeDonnees (-a) \n -a pour que le choix du premier sommet soit aléa")
        exit()
    ##Récupération du fichier de données
    file = sys.argv[1]
    try:
        fichier = open(file, "r")
    except FileNotFoundError:
        print("Pour lancer le programme, taper : python3 heuristique_globale_iterative.py nomFichieDeDonnees (-a) \n -a pour que le choix du premier sommet soit aléa")
        print("L'argument nomFichierDeDonnees doit être un fichier!")
        exit()
    dimension, matrice, typeF = read_file_tsp(fichier)
    fichier.close()
    if (len(sys.argv)==3):
        print(heuristique_iter_alea(dimension, matrice))
    else:
        print(heuristique_iter_chaque_sommet_debut(dimension, matrice))
    

