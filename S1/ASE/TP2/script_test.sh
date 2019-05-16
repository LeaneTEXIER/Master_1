echo "Variable d'environnement pour le volume courant initialisée à 0"
export CURRENT_VOLUME=0
echo "========================="
echo "Formatage du disque"
echo "-------------------------"
./bin/frmt
echo "========================="
echo "Affichage du cyclindre 0 et secteur 0"
echo "-------------------------"
./bin/dumps 0 0
echo "========================="
echo "Création d'un volume de 121 blocs"
echo "-------------------------"
./bin/mkvol -b 121
echo "========================="
echo "Création d'un volume de 8 blocs commencant en cyclindre 9 et secteur 10"
echo "-------------------------"
./bin/mkvol -b 8 -c 9 -s 10
echo "========================="
echo "Affichage des informations des différents volumes"
echo "-------------------------"
./bin/dvol
echo "========================="
echo "Suppression du volume n°1"
echo "-------------------------"
./bin/rmvol 1
echo "========================="
echo "Affichage des informations des différents volumes => Le volume n°1 n'apparait plus"
echo "-------------------------"
./bin/dvol
echo "========================="
echo "Initialisation du volume courant (0) avec comme nom de volume : MonVolumeCourant"
echo "-------------------------"
./bin/init_vol MonVolumeCourant
echo "========================="
echo "Affichage des informations du volume"
echo "-------------------------"
./bin/dfs
echo "========================="
echo "Création d'un ifile de type FILE"
echo "-------------------------"
./bin/mk_ifile FILE
echo "========================="
echo "Création d'un ifile de type DIR"
echo "-------------------------"
./bin/mk_ifile DIR
echo "========================="
echo "Affichage des informations du volume"
echo "-------------------------"
./bin/dfs
echo "========================="
echo "Suppression de l'ifile n°2"
echo "-------------------------"
./bin/rm_ifile 2
echo "========================="
echo "Affichage des informations du volume => Plus de blocs libres dûs à la suppression de l'ifile"
echo "-------------------------"
./bin/dfs
echo "========================="
echo "vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 20 avec allocation"
echo "-------------------------"
./bin/vbloc_of_fbloc_ifile 1 20 1
echo "========================="
echo "vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 20 avec allocation => Retourne le même numéro que précédemment"
echo "-------------------------"
./bin/vbloc_of_fbloc_ifile 1 20 1
echo "========================="
echo "vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 200 sans allocation => Retourne 0 car il n'a pas encore été alloué"
echo "-------------------------"
./bin/vbloc_of_fbloc_ifile 1 200 0
echo "========================="
echo "vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 200 avec allocation"
echo "-------------------------"
./bin/vbloc_of_fbloc_ifile 1 200 1
echo "========================="
echo "vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 2000 avec allocation"
echo "-------------------------"
./bin/vbloc_of_fbloc_ifile 1 2000 1
echo "========================="
echo "vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 2001 avec allocation"
echo "-------------------------"
./bin/vbloc_of_fbloc_ifile 1 2001 1
echo "========================="
echo "Affichage des informations du volume => Moins de blocs libres dûs à l'allocation de blocs avec les différents appels à vbloc_of_fbloc_ifile avec allocation"
./bin/dfs
echo "-------------------------"
