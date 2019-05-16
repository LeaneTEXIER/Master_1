//module Produit.js

let CarteMenu = function(){
    this.categories = [];
}

CarteMenu.prototype.addCategory = function(category){
    this.categories.push(category);
};

CarteMenu.prototype.getCategories = function(){
    return this.categories;
}

const dame_blanche = "./resources/dame_blanche.jpg";
const chocolat = "./resources/chocolat_liegeois.jpg";
const eau_gazeuse = "./resources/eau_gazeuse.jpeg";
const poulet = "./resources/poulet_curry.jpg";
const spaghetti = "./resources/spaghettis_bolo.jpg";
const magnum = "./resources/magnum.png";
const steak = "./resources/steak_frites.jpg";
const mojito = "./resources/mojito.jpg";
const paix_dieu = "./resources/paix-dieu.jpg"

let prod1 = new Produit("Chocolat liégeois", 4.5, "recette recette recette", chocolat, true);
let prod2 = new Produit("Dame blanche", 4.5, "recette recette recette", dame_blanche, true);
let prod3 = new Produit("Eau gazeuse", 2.5, "recette recette recette", eau_gazeuse, true);
let prod4 = new Produit("Magnum macadamia", 4.5, "recette recette recette", magnum, true);
let prod5 = new Produit("Poulet Curry", 10.5, "recette recette recette", poulet, true);
let prod6 = new Produit("Steaks Frites", 8.5, "recette recette recette", steak, true);
let prod7 = new Produit("Mojito", 6, "recette recette recette", mojito, true);
let prod8 = new Produit("Spaghettis bolognaise", 8.5, "recette recette recette", spaghetti, true);
let prod9 = new Produit("Paix-Dieu 50cl", "Indisponible", "recette recette recette", paix_dieu, false);

prod1.addIngredient("Chantilly", 2);
prod1.addIngredient("Chocolat", 2);
prod1.addIngredient("Glace au chocolat", 2);

prod2.addIngredient("Chantilly", 2);
prod2.addIngredient("Chocolat", 2);
prod2.addIngredient("Glace à la vanille", 2);

prod3.addIngredient("Eau gazeuse", 2);

prod4.addIngredient("Magnum", 2);

prod5.addIngredient("Crême fraîche", 2);
prod5.addIngredient("Curry", 2);
prod5.addIngredient("Oignons", 2);
prod5.addIngredient("Poulet", 2);
prod5.addIngredient("Riz", 2);

prod6.addIngredient("Steah haché", 2);
prod6.addIngredient("Pommes de terre", 2);

prod7.addIngredient("Rhum", 2);
prod7.addIngredient("Sirop de sucre", 2);
prod7.addIngredient("Feuille de mente", 2);
prod7.addIngredient("Citron vert", 2);
prod7.addIngredient("Eau gazeuse", 2);
prod7.addIngredient("Glaçon", 2);

prod8.addIngredient("Carotte", 2);
prod8.addIngredient("Oignon", 2);
prod8.addIngredient("Spaghettis", 2);
prod8.addIngredient("Tomate", 2);
prod8.addIngredient("Viande hachée", 2);

prod9.addIngredient("Paix-Dieu", 2)

let cat2 = new Categorie("Plats");
let cat3 = new Categorie("Desserts");
let cat4 = new Categorie("Boissons");

let scat41 = new SousCategorie("Boissons alcoolisées");
let scat42 = new SousCategorie("Boissons non-alcoolisées");

let scat21 = new SousCategorie("Viandes");
let scat22 = new SousCategorie("Pâtes");

let scat31 = new SousCategorie("Glaces");

scat22.addProduct(prod8);
scat21.addProduct(prod5);
scat21.addProduct(prod6);
cat2.addSubCategory(scat21);
cat2.addSubCategory(scat22);

scat31.addProduct(prod1);
scat31.addProduct(prod2);
scat31.addProduct(prod4);
cat3.addSubCategory(scat31);

scat42.addProduct(prod3);
scat41.addProduct(prod7);
scat41.addProduct(prod9);
cat4.addSubCategory(scat41);
cat4.addSubCategory(scat42);

let menu = new CarteMenu();
menu.addCategory(cat2);
menu.addCategory(cat3);
menu.addCategory(cat4);

let searchProduct = [];
searchProduct.push(prod1);
searchProduct.push(prod2);
searchProduct.push(prod3);
searchProduct.push(prod4);
searchProduct.push(prod5);
searchProduct.push(prod6);
searchProduct.push(prod7);
searchProduct.push(prod8);
searchProduct.push(prod9);
