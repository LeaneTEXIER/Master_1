"""
    Copie une disposition suivant celle donnée et la retourne

    :param h: hauteur du plateau de jeu
    :param disposition: la disposition à copier
"""
def copier_dispo(h, disposition):
    copy_dispo = []
    for i in range (0,h):
        copy_dispo += [disposition[i][::]]
    return copy_dispo

"""
    Retourne toutes les configurations possibles suivantes quand c'est au joueur blanc de jouer

    :param h: hauteur du plateau de jeu
    :param l: largeur du plateau de jeu
    :param disposition: la configuration de base
"""
def tour_blanc(h, l, disposition):
    dispo_next_poss = []
    for i in range (0,h):
        for j in range (0,l):
            if (disposition[i][j] == 'P'):
                #Avance tout droit
                if(disposition[i-1][j] == ' '):
                   dispo_aux = copier_dispo(h, disposition)
                   dispo_aux[i][j] = ' '
                   dispo_aux[i-1][j]= 'P'
                   dispo_next_poss += [dispo_aux]
                #Avance diago droite
                if((j+1<l) and (disposition[i-1][j+1] == 'p')):
                   dispo_aux = copier_dispo(h, disposition)
                   dispo_aux[i][j] = ' '
                   dispo_aux[i-1][j+1]= 'P'
                   dispo_next_poss += [dispo_aux]
                #Avance diago gauche
                if((j-1>=0) and (disposition[i-1][j-1] == 'p')):
                   dispo_aux = copier_dispo(h, disposition)
                   dispo_aux[i][j] = ' '
                   dispo_aux[i-1][j-1]= 'P'
                   dispo_next_poss += [dispo_aux]
    return dispo_next_poss

"""
    Retourne toutes les configurations possibles suivantes quand c'est au joueur noir de jouer

    :param h: hauteur du plateau de jeu
    :param l: largeur du plateau de jeu
    :param disposition: la configuration de base
"""
def tour_noir(h, l, disposition):
    dispo_next_poss = []
    for i in range (0,h):
        for j in range (0,l):
            if (disposition[i][j] == 'p'):
                #Avance tout droit
                if(disposition[i+1][j] == ' '):
                   dispo_aux = copier_dispo(h, disposition)
                   dispo_aux[i][j] = ' '
                   dispo_aux[i+1][j]= 'p'
                   dispo_next_poss += [dispo_aux]
                #Avance diago droite
                if((j-1>=0) and (disposition[i+1][j-1] == 'P')):
                   dispo_aux = copier_dispo(h, disposition)
                   dispo_aux[i][j] = ' '
                   dispo_aux[i+1][j-1]= 'p'
                   dispo_next_poss += [dispo_aux]
		#Avance diago gauche
                if((j+1<l) and (disposition[i+1][j+1] == 'P')):
                   dispo_aux = copier_dispo(h, disposition)
                   dispo_aux[i][j] = ' '
                   dispo_aux[i+1][j+1]= 'p'
                   dispo_next_poss += [dispo_aux]
    return dispo_next_poss

"""
    Evalue la configuration de l'hexapawn d'une manière naive suivant si c'est au tour du joueur blanc de jouer ou non et retourne l'évaluation de la configuration

    :param h: hauteur du plateau de jeu
    :param l: largeur du plateau de jeu
    :param disposition: la configuration de base
    :param tourBlanc: booléen qui vaut True si c'est au joueur blanc de jouer, sinon False
"""
def eval_hexapawn(h, l, disposition, tourBlanc):
    if(tourBlanc):
       if ('p' in disposition[h-1]):
            return 0
       dispo_next = tour_blanc(h, l, disposition)
    else:
       if ('P' in disposition[0]):
           return 0
       dispo_next = tour_noir(h, l, disposition)
    #Pas de coup possible
    if (not(dispo_next)):
       return 0
    #Evaluation config
    val_neg_config = []
    val_pos_config = []
    for d in dispo_next:
        val_config = eval_hexapawn(h, l, d, not(tourBlanc))
        if(val_config <= 0):
            val_neg_config.append(val_config)
        else:
            val_pos_config.append(val_config)
    if(val_neg_config):
        return -1*max(val_neg_config)+1
    else:
        return -1*max(val_pos_config)-1

"""
    Evalue la configuration de l'hexapawn et imprime soit 'Le joueur blanc a déjà gagné', soit le résultat de l'évaluation de la configuration

    :param h: hauteur du plateau de jeu
    :param l: largeur du plateau de jeu
    :param disposition: la configuration de base
"""
def hexapawn_naif(h, l, disposition):
    if ('P' in disposition[0]):
        print("Le joueur blanc a déjà gagné")
    else:
        print(eval_hexapawn(h, l, disposition, True))



h = int(input())
l = int(input())
disposition = []
for i in range (0,h):
    ligne = list(input())
    while(len(ligne)<l):
        ligne += [' ']
    disposition+= [ligne]
hexapawn_naif(h,l,disposition)
