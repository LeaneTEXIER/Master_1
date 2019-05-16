let Table = function(nbPlace, numTable, dispo, position){
    this.nbPlace = nbPlace;
    this.numTable = numTable;
    this.disponible = dispo;
    this.position = position;
}

Table.prototype.setDisponibility = function(dispo){
    this.disponible = dispo;
}

Table.prototype.getDisponibility = function(){
    return this.disponible;
}