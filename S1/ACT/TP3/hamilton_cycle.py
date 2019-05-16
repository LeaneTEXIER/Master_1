from voyageur_commerce import *
import sys

def read_file_hamilton(fichier):
    ##On lit les lignes où on ne doit pas récupérer d'informations
    fichier.readline()
    fichier.readline()
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
        matrice += [fichier.readline().split()]
    return dimension, matrice

def transform_cycle_h_to_TSP(matrice_h):
    matrice = []
    for ligne in matrice_h:
        ligne_matrice = []
        for j in ligne:
            if j=="True":
                ligne_matrice += [1]
            else:
                ligne_matrice += [2]
        matrice += [ligne_matrice]
    return matrice

def non_deterministe_poly_cycle_h(dimension, matrice_h):
    matrice = transform_cycle_h_to_TSP(matrice_h)
    return non_deterministe_poly(dimension, matrice, dimension)

def pb_a_solution_cycle_h(dimension, matrice_h):
    matrice = transform_cycle_h_to_TSP(matrice_h)
    return pb_a_solution(dimension, matrice, dimension)

def verification_certificat_cycle_h(c, dimension, matrice_h):
    matrice = transform_cycle_h_to_TSP(matrice_h)
    return verification_certificat(c, dimension, matrice, dimension)

if __name__ == "__main__":
    if(len(sys.argv)!=3):
        print("Pour lancer le programme, taper : python3 voyageur_commerce.py nomFichieDeDonnees mode")
        exit()
    ##Récupération des arguments
    file = sys.argv[1]
    mode = sys.argv[2]
    if (mode != "verif" and mode != "nondet" and mode!="exhaust"):
        print("L'argument mode doit être soit verif, soit nondet, soit exhaust!")
        exit()
    try:
        fichier = open(file, "r")
    except FileNotFoundError:
        print("L'argument file doit être un fichier!")
        exit()
    dimension, matrice = read_file_hamilton(fichier)
    fichier.close()
    ##Appelle méthode en fonction du mode
    if(mode=="nondet"):
        res, c = non_deterministe_poly_cycle_h(dimension, matrice)
        print("Test du certificat suivant : ", c)
        print(res)
    elif(mode=="exhaust"):
        res, c = pb_a_solution_cycle_h(dimension, matrice)
        if(res):
            print("Le certificat suivant est valide : ", c)
        print(res)
    else: ##demande certif à l'utilisateur
        print("Entrer un certificat de la forme i j ... z (des nombres) :")
        c = [int(x) for x in input().split()]
        print(verification_certificat_cycle_h(c, dimension, matrice))
