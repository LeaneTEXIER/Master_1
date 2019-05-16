let SousCategorie = function(nom, image){
    this.name = nom;
    this.image = image;
    this.products = [];
}

SousCategorie.prototype.addProduct = function(product){
    this.products.push(product);
};

SousCategorie.prototype.getProducts = function(){
    return this.products;
}
