#define N 10  /* nombre de places dans le tampon */
#define C 100 /* nombre de tours de boucles */

typedef int objet_t;

void produire_objet(objet_t *obj);

void mettre_objet(objet_t obj);

void producteur (void *args);

void retirer_objet(objet_t *obj);

void utiliser_objet(objet_t obj);

void consommateur (void *args);
