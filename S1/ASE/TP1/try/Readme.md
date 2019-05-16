# Léane TEXIER

# TP1 - Partie 1

## Etude de registre
Pour compiler, il faut taper make display_stack                   
Pour executer, il faut taper ./display_stack                  

### Résultats et explications
Le registre étant alimenté de l'adresse la plus grande à l'adresse la plus petite, il est nécessaire d'étudier dans le sens inverse (i.e de bas en haut) les adresses.           
0x7ffd5838e150 -> 00000001         
0x7ffd5838e154 -> 00000000         
0x7ffd5838e158 -> 02F18728         
0x7ffd5838e15c -> 00000000 => Enregistrement de la valeur de la variable deepth (ici 0)         
0x7ffd5838e160 -> 02F18100         
0x7ffd5838e164 -> 00007FEA         
0x7ffd5838e168 -> 00000001         
0x7ffd5838e16c -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(0)         
0x7ffd5838e170 -> 5838E1A0 => Adresse du registre ...         
0x7ffd5838e174 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e178 -> BAC4C6BF => Adresse de retour dans la ...                    
0x7ffd5838e17c -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e180 -> 02F18710         
0x7ffd5838e184 -> 00007FEA         
0x7ffd5838e188 -> 00000000         
0x7ffd5838e18c -> 00000001 => Enregistrement de la valeur de la variable deepth (ici 1)         
0x7ffd5838e190 -> 00000000         
0x7ffd5838e194 -> 00000000         
0x7ffd5838e198 -> 583C8298         
0x7ffd5838e19c -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(1)         
0x7ffd5838e1a0 -> 5838E1D0 => Adresse du registre ...         
0x7ffd5838e1a4 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e1a8 -> BAC4C6BF => Adresse de retour dans la ...          
0x7ffd5838e1ac -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e1b0 -> 5838E370         
0x7ffd5838e1b4 -> 00007FFD         
0x7ffd5838e1b8 -> 583C8180         
0x7ffd5838e1bc -> 00000002 => Enregistrement de la valeur de la variable deepth (ici 2)         
0x7ffd5838e1c0 -> 00000002         
0x7ffd5838e1c4 -> 00007FEA         
0x7ffd5838e1c8 -> 00000000         
0x7ffd5838e1cc -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(2)         
0x7ffd5838e1d0 -> 5838E200 => Adresse du registre ...         
0x7ffd5838e1d4 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e1d8 -> BAC4C6BF => Adresse de retour dans la ...          
0x7ffd5838e1dc -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e1e0 -> 5838E2C0         
0x7ffd5838e1e4 -> 00007FFD         
0x7ffd5838e1e8 -> 00000000         
0x7ffd5838e1ec -> 00000003 => Enregistrement de la valeur de la variable deepth (ici 3)         
0x7ffd5838e1f0 -> 02F18738         
0x7ffd5838e1f4 -> 00007FEA         
0x7ffd5838e1f8 -> 00000000         
0x7ffd5838e1fc -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(3)         
0x7ffd5838e200 -> 5838E230 => Adresse du registre ...         
0x7ffd5838e204 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e208 -> BAC4C6BF => Adresse de retour dans la ...          
0x7ffd5838e20c -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e210 -> 00000000         
0x7ffd5838e214 -> 00000000         
0x7ffd5838e218 -> 6562B026         
0x7ffd5838e21c -> 00000004 => Enregistrement de la valeur de la variable deepth (ici 4)         
0x7ffd5838e220 -> 02F18A98         
0x7ffd5838e224 -> 00007FEA         
0x7ffd5838e228 -> 5838E368         
0x7ffd5838e22c -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(4)         
0x7ffd5838e230 -> 5838E260 => Adresse du registre ...         
0x7ffd5838e234 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e238 -> BAC4C6BF => Adresse de retour dans la ...          
0x7ffd5838e23c -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e240 -> 00000000         
0x7ffd5838e244 -> 00000000         
0x7ffd5838e248 -> 02CFA1EF         
0x7ffd5838e24c -> 00000005 => Enregistrement de la valeur de la variable deepth (ici 5)         
0x7ffd5838e250 -> 00000000         
0x7ffd5838e254 -> 00000000         
0x7ffd5838e258 -> 5838E3A0         
0x7ffd5838e25c -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(5)         
0x7ffd5838e260 -> 5838E290 => Adresse du registre ...         
0x7ffd5838e264 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e268 -> BAC4C6BF => Adresse de retour dans la ...          
0x7ffd5838e26c -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e270 -> 00000000         
0x7ffd5838e274 -> 00000000         
0x7ffd5838e278 -> 02F18710         
0x7ffd5838e27c -> 00000006 => Enregistrement de la valeur de la variable deepth (ici 6)         
0x7ffd5838e280 -> 02AB1787         
0x7ffd5838e284 -> 00007FEA         
0x7ffd5838e288 -> 00000000         
0x7ffd5838e28c -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(6)         
0x7ffd5838e290 -> 5838E2C0 => Adresse du registre ...         
0x7ffd5838e294 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e298 -> BAC4C6BF => Adresse de retour dans la ...          
0x7ffd5838e29c -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e2a0 -> 02F18A98         
0x7ffd5838e2a4 -> 00007FEA         
0x7ffd5838e2a8 -> 00000000         
0x7ffd5838e2ac -> 00000007 => Enregistrement de la valeur de la variable deepth (ici 7)         
0x7ffd5838e2b0 -> 00000000         
0x7ffd5838e2b4 -> 00000000         
0x7ffd5838e2b8 -> 5838E2E0         
0x7ffd5838e2bc -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(7)         
0x7ffd5838e2c0 -> 5838E2F0 => Adresse du registre ...         
0x7ffd5838e2c4 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e2c8 -> BAC4C6BF => Adresse de retour dans la ...          
0x7ffd5838e2cc -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e2d0 -> 583C8268         
0x7ffd5838e2d4 -> 00007FFD         
0x7ffd5838e2d8 -> 02F18710         
0x7ffd5838e2dc -> 00000008 => Enregistrement de la valeur de la variable deepth (ici 8)         
0x7ffd5838e2e0 -> 00000000         
0x7ffd5838e2e4 -> 00000000         
0x7ffd5838e2e8 -> 00000000         
0x7ffd5838e2ec -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(8)         
0x7ffd5838e2f0 -> 5838E320 => Adresse du registre ...         
0x7ffd5838e2f4 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e2f8 -> BAC4C6BF => Adresse de retour dans la ...          
0x7ffd5838e2fc -> 00005634 => ... fonction rec au niveau du if         
0x7ffd5838e300 -> 00000009         
0x7ffd5838e304 -> 00000000         
0x7ffd5838e308 -> 02CF1660         
0x7ffd5838e30c -> 00000009 => Enregistrement de la valeur de la variable deepth (ici 9)         
0x7ffd5838e310 -> 5838E378         
0x7ffd5838e314 -> 00007FFD         
0x7ffd5838e318 -> 00F0B6FF         
0x7ffd5838e31c -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(9)         
0x7ffd5838e320 -> 5838E350 => Adresse du registre ...          
0x7ffd5838e324 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e328 -> BAC4C6BF => Adresse de retour dans la ...        
0x7ffd5838e32c -> 00005634 => ... fonction rec au niveau du if
0x7ffd5838e330 -> 02CFF9A0         
0x7ffd5838e334 -> 00007FEA         
0x7ffd5838e338 -> 00000000         
0x7ffd5838e33c -> 0000000A => Enregistrement de la valeur de la variable deepth (ici 10)         
0x7ffd5838e340 -> BAC4C740         
0x7ffd5838e344 -> 00005634         
0x7ffd5838e348 -> BAC4C540         
0x7ffd5838e34c -> FACADE00 => Enregistrement de la variable locale l lors de l'appel de la fonction rec(10)         
0x7ffd5838e350 -> 5838E360 => Adresse du registre ...            
0x7ffd5838e354 -> 00007FFD => ... rbp de la pile précédente          
0x7ffd5838e358 -> BAC4C6FF => Adresse de retour dans la fonction main ...        
0x7ffd5838e35c -> 00005634 => ... après l'appel de la fonction rec       
Adresse fonction main : 0x5634bac4c6e7         
Adresse fonction de recurrence : 0x5634bac4c69a          
Toutes les autres valeurs sont des anciennes valeurs de la mémoire. En effet, ces espaces sont reservées à des variables locales ou des arguments hors nous n'en avons pas (assez) donc aucune valeur n'est mise dedans.        

## Try - Multiplication
Pour compiler, il faut taper make try_mul                   
Pour executer, il faut taper par exemple : echo 1 2 3 | ./try_mul     
On peut executer le programme également, en tapant ./try_mul    
Ensuite, il faut entrer des nombres, quand on met un 0 le programme nous retourne alors le resultat du produit (product = 0).      
