//module Produit.js

let Produit = function(nom, prix, recette, image, dispo){
    this.productName = nom;
    this.price = prix;
    this.recette = recette;
    this.productImg = image;
    this.disponible = dispo;
    this.ingredients = [];
}

Produit.prototype.addIngredient = function(nom, qte){
    this.ingredients.push((new Ingredient(nom, qte)).name);
};

Produit.prototype.estDisponible = function(){
    return this.disponible;
}


