//module Ingredient.js

let Ingredient = function(nom, qte){
    this.name = nom;
    this.quantity = qte;
}

Ingredient.prototype.estDisponible = function(){
   return this.quantity !== 0;
};
