from heuristique_globale_iterative import *

def retourne_minimum_matrice(D):
    dmin = 9999
    for l in D.values() :
        for c in l:
            if c < dmin:
                dmin = c
    return dmin

def copy_matrice(D):
    matrice = {}
    for c,v in D.items():
        sous_matrice = {}
        for c2, v2 in v.items():
            sous_matrice[c2] = v2
        matrice[c] = sous_matrice
    return matrice


def borne_inf_multip_nb_villes(D, n):
    inf = retourne_minimum_matrice(D)
    return (n * inf), D


def methode_exacte(n, D, tournee, sup, f):
    villes = [i for i in range (n)]
    possibilites = []
    for i in range (n):
        matrice = {}
        for j in range (n):
            sous_matrice = {}
            for k in range (n):
                if (j!=k):
                    sous_matrice[k] = D[j][k]
            matrice[j] = sous_matrice
        inf, matrice = f(matrice, n)
        ## (ma tournee de depart, ce qu'il me reste à visiter, longueur debut tournee, matrice utile) 
        possibilites += [{'tournee' : [villes[i]], 'reste' : villes[:i]+villes[i+1:], 'longueur' : inf, 'matrice' : matrice}]
    for i in range (n-1):
        possibilites_next = []
        for p in possibilites:
            inf, matrice = f(p['matrice'], n-i)
            borne_inf = p['longueur'] + inf
            if inf < sup:
                for iv in range (len(p['reste'])):
                    if (f==borne_inf_multip_nb_villes):
                        borne_inf = p['longueur']
                    long_next = borne_inf+p['matrice'][p['tournee'][-1]][p['reste'][iv]]
                    if long_next < sup:
                        matrice_next = copy_matrice(matrice)
                        del matrice_next[p['tournee'][-1]]
                        for l in matrice_next.keys():
                            if (l != p['reste'][iv]):
                                del matrice_next[l][p['reste'][iv]]
                        if (i!=n-2):
                            del matrice_next[p['reste'][iv]][p['tournee'][0]]
                        possibilites_next += [{'tournee' : p['tournee']+[p['reste'][iv]], 'reste' : p['reste'][:iv]+p['reste'][iv+1:],
                                               'longueur' : long_next, 'matrice' : matrice_next}]
        possibilites = possibilites_next
    tourneeMeilleure = tournee
    distanceMeilleure = sup
    for p in possibilites:
        distance = p['longueur']+p['matrice'][p['tournee'][-1]][p['tournee'][0]]
        if distance < distanceMeilleure:
            distanceMeilleure = distance
            tourneeMeilleure = p['tournee']
    return tourneeMeilleure, distanceMeilleure


def premiere_approche_using_heuristique(n, D, f_iter):
    tournee, distance = f_iter(n, D)
    return methode_exacte(n, D, tournee, distance, borne_inf_multip_nb_villes)



if __name__ == "__main__":
    if ((len(sys.argv)==3 and sys.argv[2]!="-a") and (len(sys.argv)!=2)):
        print("Pour lancer le programme, taper : python3 methode_exacte.py nomFichieDeDonnees (-a) \n -a pour que le choix du premier sommet soit aléa")
        exit()
    ##Récupération du fichier de données
    file = sys.argv[1]
    try:
        fichier = open(file, "r")
    except FileNotFoundError:
        print("Pour lancer le programme, taper : python3 methode_exacte.py nomFichieDeDonnees (-a) \n -a pour que le choix du premier sommet soit aléa")
        print("L'argument nomFichierDeDonnees doit être un fichier!")
        exit()
    dimension, matrice, typeF = read_file_tsp(fichier)
    fichier.close()
    if (len(sys.argv)==3):
        f_iter = heuristique_iter_alea
    else:
        f_iter = heuristique_iter_chaque_sommet_debut
    print(premiere_approche_using_heuristique(dimension, matrice, f_iter))
