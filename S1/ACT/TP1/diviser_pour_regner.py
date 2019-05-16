#TP1 - Diviser pour régner - Le plus grand rectangle
#Léane Texier

#Question 1 - Une première approche
"""
    Calcul l'aire maximale du rectangle dont la base est sur l'axe des x
    et qui ne contient aucun des n points à l'interieur
    (Algorithme en n^3)

    :param l: longueur du rectangle
    :param h: hauteur du rectangle
    :param n: nombre de points dans le rectangle
    :param points: Coordonnées des n points du rectangle triés par abscisse croissante
"""
def rect_q1(l, h, n, points):
    maxi = 0
    points = [(0,0)] + points + [(l,0)]
    for i in range (0, n+1):
        for j in range (i+1, n+2):
            haut = h
            for k in range (i+1, j):
                if (haut > points[k][1] and points[k][1] > 0):
                    haut = points[k][1]
            aire = (points[j][0]-points[i][0])*haut
            if (aire > maxi):
                maxi = aire
    return maxi



"""
    Calcul l'aire maximale du rectangle dont la base est sur l'axe des x
    et qui ne contient aucun des n points à l'interieur
    (Algorithme en n^2)

    :param l: longueur du rectangle
    :param h: hauteur du rectangle
    :param n: nombre de points dans le rectangle
    :param points: Coordonnées des n points du rectangle triés par abscisse croissante
"""
def rect_q1_v2(l, h, n, points):
    maxi = 0
    points = [(0,0)] + points + [(l,0)]
    for i in range (0, n+1):
        haut = h
        for j in range (i+1, n+2):
            aire = (points[j][0]-points[i][0])*haut
            if (aire > maxi):
                maxi = aire
            if (haut > points[j][1] and points[j][1] > 0):
                haut = points[j][1]
    return maxi



#Question 2 - Diviser pour régner
"""
    Calcul l'aire maximale du rectangle dont la base est sur l'axe des x
    et qui ne contient aucun des n points à l'interieur
    (Algorithme diviser pour régner)

    :param l: longueur du rectangle
    :param h: hauteur du rectangle
    :param n: nombre de points dans le rectangle
    :param points: Coordonnées des n points du rectangle triés par abscisse croissante
"""
def diviser_pour_regner(l, h, n, points):
    #On enleve les points d'ordonnée 0 ou h
    i = 0
    while (i < n):
        if (points[i][1] == 0 or points[i][1] == h):
            points.remove(points[i])
            n-=1
        else :
            i+=1
    return rect_q2(0, l, h, n, points)

"""
    Calcul l'aire maximale du rectangle dont la base est sur l'axe des x
    et qui ne contient aucun des n points à l'interieur
    (Algorithme diviser pour régner)

    :param l: longueur du rectangle
    :param h: hauteur du rectangle
    :param n: nombre de points dans le rectangle
    :param points: Coordonnées des n points du rectangle triés par abscisse croissante et dont aucun a son ordonnée égale à 0 ou à h
"""
def rect_q2(x, l, h, n, points):
    if n==0:
        return (l-x) * h
    h_min = h
    for i in range (0,n):
        if points[i][1] < h_min:
            h_min = points[i][1]
            x_h_min = points[i][0]
            ind_mini = i
    aire_bas = (l-x) * h_min
    aire_gauche = rect_q2(x, x_h_min, h, ind_mini, points[0:ind_mini])
    aire_droite = rect_q2(x_h_min, l, h, n-ind_mini-1, points[ind_mini+1:n])
    aire = max(aire_bas,aire_gauche,aire_droite)
    return (aire)



#Question - Linéaire
"""
    Calcul l'aire maximale du rectangle dont la base est sur l'axe des x
    et qui ne contient aucun des n points à l'interieur
    (Algorithme en n)

    :param l: longueur du rectangle
    :param h: hauteur du rectangle
    :param n: nombre de points dans le rectangle
    :param points: Coordonnées des n points du rectangle triés par abscisse croissante
"""
def rect(l, h, n, points):
    #On enleve les points d'ordonnées 0 et h et on regarde la plus grande distance entre 2 points consécutifs
    i = 0
    prec = 0
    diff_x_max = 0
    while (i < n):
        if (points[i][1] == 0 or points[i][1] == h):
            points.remove(points[i])
            n-=1
        else:
            d = points[i][0] - prec
            prec = points[i][0]
            if (d > diff_x_max):
                diff_x_max = d
            i+=1
    #Distance entre le dernier point et la largeur l
    d = l - prec
    if (d > diff_x_max):
        diff_x_max = d
    #On enregistre l'aire du plus grand rectangle qui va jusqu'en haut (hauteur h)
    maxi = diff_x_max * h
    
    #On ajoute les points (0,0) et (l,0)
    points = [(0,0)] + points + [(l,0)]
    n+=2

    #Cherche plus grand rectangle
    i = 0
    points_order_by_y = []
    while (i < n):
        if (points_order_by_y==[]) or (points_order_by_y[-1][1]<=points[i][1]):
            points_order_by_y.append(points[i])
            i+=1
        else:
            last = points_order_by_y[-1]
            points_order_by_y.remove(last)
            aire = last[1] * (points[i][0] - points_order_by_y[-1][0])
            if (aire > maxi):
                maxi = aire
    return maxi        

##Lancement pour plateforme
l_et_h = input().split(" ")
n = int(input())
points = list()
for i in range (0,n):
    x_et_y = input().split(" ")
    points+= [(int(x_et_y[0]),int(x_et_y[1]))]
print(diviser_pour_regner(int(l_et_h[0]),int(l_et_h[1]),n,points))
