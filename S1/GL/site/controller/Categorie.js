//module Categorie.js

let Categorie = function(nom, image){
    this.name = nom;
    this.image = image;
    this.subCategories = [];
    this.products = [];
}

Categorie.prototype.addProduct = function(product){
    this.products.push(product);
};

Categorie.prototype.addSubCategory = function(subCategory){
    this.subCategories.push(subCategory);
};

Categorie.prototype.getSubCategories = function(){
    return this.subCategories;
}

Categorie.prototype.getProducts = function(){
    return this.products;
}
