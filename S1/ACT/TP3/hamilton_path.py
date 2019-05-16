from hamilton_cycle import *
import sys


def transform_path_h_to_cycle_h(matrice_h, d):
    matrice = []
    for ligne in matrice_h:
        ligne_matrice = ligne+['True']
        matrice += [ligne_matrice]
    matrice += [['True' for i in range (d+1)]]
    return matrice


def non_deterministe_poly_path_h(dimension, matrice_h):
    matrice = transform_path_h_to_cycle_h(matrice_h, dimension)
    return non_deterministe_poly_cycle_h(dimension+1, matrice)

def pb_a_solution_path_h(dimension, matrice_h):
    matrice = transform_path_h_to_cycle_h(matrice_h, dimension)
    return pb_a_solution_cycle_h(dimension+1, matrice)

def verification_certificat_path_h(c, dimension, matrice_h):
    matrice = transform_path_h_to_cycle_h(matrice_h, dimension)
    return verification_certificat_cycle_h(c, dimension+1, matrice)


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
        res, c = non_deterministe_poly_path_h(dimension, matrice)
        ind_sommet_aj = c.index(dimension)
        c = c[ind_sommet_aj+1:]+c[:ind_sommet_aj]
        print("Test du certificat suivant : ", c)
        print(res)
    elif(mode=="exhaust"):
        res, c = pb_a_solution_path_h(dimension, matrice)
        if(res):
            ind_sommet_aj = c.index(dimension)
            c = c[ind_sommet_aj+1:]+c[:ind_sommet_aj]
            print("Le certificat suivant est valide : ", c)
        print(res)
    else: ##demande certif à l'utilisateur
        print("Entrer un certificat de la forme i j ... z (des nombres) :")
        c = [int(x) for x in input().split()]
        c += [dimension]
        print(verification_certificat_path_h(c, dimension, matrice))
