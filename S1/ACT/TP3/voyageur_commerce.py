import random
import sys
import abc


def verification_certificat(c, n, D, l):
    #Verification que chaque ville y est une et une seule fois
    verif = [False for i in range (n)]
    for i in c:
        if(i<0 or i>=n or verif[i]):
            
            return False
        verif[i] = True
    for i in verif:
        if not(i):
            return False
    #Verification de la distance
    distance = 0
    for i in range (0, n-1):
        #distance entre chaque ville
        distance += D[c[i]][c[i+1]]
    #distance afin de revenir à la première ville
    distance += D[c[n-1]][c[0]]
    return distance <= l


def generation_alea_certificat(n):
    villes = [i for i in range (n)]
    villes_perm = []
    while villes:
        j = random.randint(0, len(villes)-1)
        villes_perm += [villes[j]]
        villes.remove(villes[j])
    return villes_perm


def non_deterministe_poly(n, D, l):
    c = generation_alea_certificat(n)
    return verification_certificat(c, n, D, l), c


def certif_suivant(c, n):
    ##Cherche l'indice i le plus grand tq c[i]<c[i+1]
    i = n-2
    while (i>=0 and c[i]>=c[i+1]):
        i-=1
    ##Si plus de permutation suivante possible, on a alors pas trouvé de i
    if(i < 0):
        return False
    ##Cherche l'indice j tq c[j]>c[i]
    j = n-1
    while (c[j]<=c[i]):
        j-=1
    #Echange de i et j
    c[i],c[j] = c[j],c[i]
    #Rotation pour garder notre ordre
    c[i+1:] = c[n-1:i:-1]
    return c


def pb_a_solution(n, D, l):
    c = [i for i in range (n)]
    while (c):
        if (verification_certificat(c, n, D, l)):
            return True, c
        c = certif_suivant(c, n)
    return False, []

        
def read_file_tsp(fichier):
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
        ligne = fichier.readline().split()
        ligne_matrice = []
        for j in ligne:
            ligne_matrice += [int(j)]
        matrice += [ligne_matrice]
    return dimension, matrice
    


if __name__ == "__main__":
    if(len(sys.argv)!=4):
        print("Pour lancer le programme, taper : python3 voyageur_commerce.py nomFichieDeDonnees mode longueurMaximale")
        exit()
    ##Récupération des arguments
    file = sys.argv[1]
    mode = sys.argv[2]
    if (mode != "verif" and mode != "nondet" and mode!="exhaust"):
        print("L'argument mode doit être soit verif, soit nondet, soit exhaust!")
        exit()
    try:
        l = int(sys.argv[3])
    except ValueError:
        print("L'argument longueurMaxi doit être un entier!")
        exit()
    try:
        fichier = open(file, "r")
    except FileNotFoundError:
        print("L'argument file doit être un fichier!")
        exit()
    dimension, matrice = read_file_tsp(fichier)
    fichier.close()
    ##Appelle méthode en fonction du mode
    if(mode=="nondet"):
        res, c = non_deterministe_poly(dimension, matrice, l)
        print("Test du certificat suivant : ", c)
        print(res)
    elif(mode=="exhaust"):
        res, c = pb_a_solution(dimension, matrice, l)
        if(res):
            print("Le certificat suivant est valide : ", c)
        print(res)
    else: ##demande certif à l'utilisateur
        print("Entrer un certificat de la forme i j ... z (des nombres) :")
        c = [int(x) for x in input().split()]
        print(verification_certificat(c, dimension, matrice, l))
