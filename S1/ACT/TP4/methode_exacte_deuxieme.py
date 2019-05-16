from methode_exacte import *


def borne_inf_simplifie_donnee(D, n):
    matrice = copy_matrice(D)
    inf = 0
    ##Pour chaque ligne, soustraire valeur mini de la ligne
    for c, v in D.items():
        mini = 9999
        for v2 in v.values():
            if (v2 < mini):
                mini = v2
        for c2, v2 in v.items():
            matrice[c][c2] -= mini
        inf += mini
    ##Pour chaque colonne, soustraire valeur mini de la colonne
    ### Recherche mini de chaque colonne
    colonnes_valeur_mini = {}
    for c, v in matrice.items():
        for c2, v2 in v.items():
            if (c2 not in colonnes_valeur_mini):
                colonnes_valeur_mini[c2] = v2
            elif (v2 < colonnes_valeur_mini[c2]):
                colonnes_valeur_mini[c2] = v2
    for c, v in matrice.items():
        for c2, v2 in v.items():
            matrice[c][c2] -= colonnes_valeur_mini[c2]
    for val in colonnes_valeur_mini.values():
        inf += val
    return inf, matrice


def approche_sophistique_using_heuristique(n, D, f_iter):
    tournee, distance = f_iter(n, D)
    return methode_exacte(n, D, tournee, distance, borne_inf_simplifie_donnee)


if __name__ == "__main__":
    if ((len(sys.argv)==3 and sys.argv[2]!="-a") and (len(sys.argv)!=2)):
        print("Pour lancer le programme, taper : python3 methode_exacte_deuxieme.py nomFichieDeDonnees (-a) \n -a pour que le choix du premier sommet soit aléa")
        exit()
    ##Récupération du fichier de données
    file = sys.argv[1]
    try:
        fichier = open(file, "r")
    except FileNotFoundError:
        print("Pour lancer le programme, taper : python3 methode_exacte_deuxieme.py nomFichieDeDonnees (-a) \n -a pour que le choix du premier sommet soit aléa")
        print("L'argument nomFichierDeDonnees doit être un fichier!")
        exit()
    dimension, matrice, typeF = read_file_tsp(fichier)
    fichier.close()
    if (len(sys.argv)==3):
        f_iter = heuristique_iter_alea
    else:
        f_iter = heuristique_iter_chaque_sommet_debut
    print(approche_sophistique_using_heuristique(dimension, matrice, f_iter))
