# Léane TEXIER

## Compilation et nettoyage
Pour compiler, il faut taper make                 
Pour supprimer tous les fichiers .o, il faut taper make clean                     
Pour supprimer tous les fichiers .o, les executables et le disque, il faut taper make realclean                 

## Scénario - Afin de tester les différentes fonctionnalités mises en place  
### Possibilité de lancer un script effectuant toutes les commandes ci-dessous          
Taper : chmod +x script_test.sh   
Puis, taper : ./script_test.sh        
##### Variable d'environnement pour le volume courant initialisée à 0        
export CURRENT_VOLUME=0      
##### Formatage du disque
./bin/frmt      
##### Affichage du cyclindre 0 et secteur 0
./bin/dumps 0 0         
##### Création d'un volume de 121 blocs
./bin/mkvol -b 121        
##### Création d'un volume de 8 blocs commencant en cyclindre 9 et secteur 10     
./bin/mkvol -b 8 -c 9 -s 10        
##### Affichage des informations des différents volumes
./bin/dvol          
##### Suppression du volume n°1
./bin/rmvol 1        
##### Affichage des informations des différents volumes => Le volume n°1 n'apparait plus    
./bin/dvol        
##### Initialisation du volume courant (0) avec comme nom de volume : MonVolumeCourant   
./bin/init_vol MonVolumeCourant         
##### Affichage des informations du volume    
./bin/dfs          
##### Création d'un ifile de type FILE
./bin/mk_ifile FILE       
#####  Affichage des informations du volume   
./bin/mk_ifile DIR      
##### Création d'un ifile de type DIR   
./bin/dfs         
##### Suppression de l'ifile n°2
./bin/rm_ifile 2       
##### Affichage des informations du volume => Plus de blocs libres dûs à la suppression de l'ifile   
./bin/dfs          
##### vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 20 avec allocation
./bin/vbloc_of_fbloc_ifile 1 20 1     
##### vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 20 avec allocation => Retourne le même numéro que précédemment   
./bin/vbloc_of_fbloc_ifile 1 20 1      
##### vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 200 sans allocation => Retourne 0 car il n'a pas encore été alloué
./bin/vbloc_of_fbloc_ifile 1 200 0      
##### vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 200 avec allocation
./bin/vbloc_of_fbloc_ifile 1 200 1      
##### vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 2000 avec allocation  
./bin/vbloc_of_fbloc_ifile 1 2000 1     
##### vbloc_of_fbloc_ifile de l'ifile n°1 pour le bloc 2001 avec allocation        
./bin/vbloc_of_fbloc_ifile 1 2001 1     
##### Affichage des informations du volume => Moins de blocs libres dûs à l'allocation de blocs avec les différents appels à vbloc_of_fbloc_ifile avec allocation  
./bin/dfs        


## Première couche logicielle : accès au matériel                           

### dumps - Afficher un secteur                        
Pour executer, il faut taper ./bin/dumps <cylinder> <secteur>                                     

### frmt - Formater un disque                      
Pour executer, il faut taper ./bin/frmt          
Pour formater dans le sens inverse, il faut taper ./bin/frmt -r               

## Seconde couche logicielle : gestion de volumes      

### mkvol - Création d'un volume                      
Pour executer, il faut taper ./bin/mkvol -b <nb_secteurs> ou ./bin/mkvol -b <nb_secteurs> -c <first_cylindre> -s <first_secteur>                                      

### dvol - Affichage d'un volume                     
Pour executer, il faut taper ./dvol                   

### rmvol - Suppression d'un volume                       
Pour executer, il faut taper ./bin/rmvol <numero_volume_a_supprimer>          

## Troisième couche logicielle, 1re partie : structure d’un volume        

### init_vol - Initialisation du super-bloc du volume courant
Pour executer, il faut taper ./bin/init_vol <nom_du_volume>          

### dfs - Affichage du super-bloc du volume courant
Pour executer, il faut taper ./dfs                 

### test_vol - Test des fonctions d'allocation et de libération de volumes
Pour compiler, il faut taper make test          
Pour executer, il faut taper ./test_vol                

## Troisième couche logicielle, 2e partie : structure d’un fichier         

### mk_ifile - Création d'un inode
Pour executer, il faut taper ./bin/mk_ifile <type_ifile> avec type_ifile = DIR pour repertoire ou FILE pour fichier           

### rm_ifile - Suppression d'un inode
Pour executer, il faut taper ./bin/rm_ifile <numero_ifile_a_supprimer>                            

### vbloc_of_fbloc_ifile - Bloc volume d’un bloc inoeud et allocation              
Pour executer, il faut taper ./bin/vbloc_of_fbloc_ifile <inumber> <fbloc> <do_alloc>          
