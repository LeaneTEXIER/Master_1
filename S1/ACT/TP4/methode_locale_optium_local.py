from heuristique_globale_iterative import *

def eval_tournee(tournee, n, D):
    distance = 0
    for i in range (n-1):
        distance += D[i][i+1]
    distance += D[i+1][0]
    return distance

def optium_local_tsp(n, D, tournee, distance):
    tourneeMeilleure = tournee
    distanceMeilleure = distance
    i = 0
    for j in range (i+1, n-1):
        tourneeRecherche = tournee[j:i:-1]+[tournee[i]]+tournee[j+1:]
        distanceRecherche = eval_tournee(tourneeRecherche, n, D)
        if (distanceRecherche < distanceMeilleure):
            distanceMeilleure = distanceRecherche
            tourneeMeilleure = tourneeRecherche
    for i in range (1, n-1):
        for j in range (i+1, n):
            tourneeRecherche = tournee[:i]+tournee[j:i:-1]+[tournee[i]]+tournee[j+1:]
            distanceRecherche = eval_tournee(tourneeRecherche, n, D)
            if (distanceRecherche < distanceMeilleure):
                distanceMeilleure = distanceRecherche
                tourneeMeilleure = tourneeRecherche
    if(tourneeMeilleure!=tournee):
        return optium_local_tsp(n, D, tourneeMeilleure, distanceMeilleure)
    else:
        return tourneeMeilleure, distanceMeilleure

def optium_local_atsp(n, D, tournee, distance):
    tourneeMeilleure = tournee
    distanceMeilleure = distance
    for i in range (0, n-1):
        for j in range (i+1, n):
            tourneeRecherche = tournee[:i]+tournee[j:i:-1]+[tournee[i]]+tournee[j+1:]
            distanceRecherche = eval_tournee(tourneeRecherche, n, D)
            if (distanceRecherche < distanceMeilleure):
                distanceMeilleure = distanceRecherche
                tourneeMeilleure = tourneeRecherche
    if(tourneeMeilleure!=tournee):
        return optium_local_atsp(n, D, tourneeMeilleure, distanceMeilleure)
    else:
        return tourneeMeilleure, distanceMeilleure


def optium_local_using_heuristique(n, D, f, f_iter):
    tournee, distance = f_iter(n, D)
    return f(n, D, tournee, distance)


if __name__ == "__main__":
    if ((len(sys.argv)==3 and sys.argv[2]!="-a") and (len(sys.argv)!=2)):
        print("Pour lancer le programme, taper : python3 methode_locale_optium_local.py nomFichierDeDonnees (-a) \n -a pour que le choix du premier sommet soit aléa")
        exit()
    ##Récupération du fichier de données
    file = sys.argv[1]
    try:
        fichier = open(file, "r")
    except FileNotFoundError:
        print("Pour lancer le programme, taper : python3 methode_locale_optium_local.py nomFichierDeDonnees (-a) \n -a pour que le choix du premier sommet soit aléa")
        print("L'argument nomFichierDeDonnees doit être un fichier!")
        exit()
    dimension, matrice, typeF = read_file_tsp(fichier)
    fichier.close()
    if(typeF=="ATSP"):
        f = optium_local_atsp
    else:
        f = optium_local_tsp
    if (len(sys.argv)==3):
        f_iter = heuristique_iter_alea
    else:
        f_iter = heuristique_iter_chaque_sommet_debut
    print(optium_local_using_heuristique(dimension, matrice, f, f_iter))
