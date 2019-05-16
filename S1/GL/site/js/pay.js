function chargerFormulairePaiementAPlusieurs(){
  htmlToPush =
                "<form>" +
                  "<input type=\"radio\" id=\"nbPers\" name=\"choixPlusieurs\" checked><label for=\"nbPers\">Diviser par un nombre de personnes</label><br>" +
                  "<input type=\"radio\" id=\"conso\" name=\"choixPlusieurs\"><label for=\"nbPers\">Chacun paye ce qu'il a consommé</label>" +
                "</form>";

  var footerToPush =
    "<button type='button' class='btn btn-danger' data-dismiss='modal'>Annuler</button>" +
    "<button type='button' class='btn btn-success' onclick='openNextModal()'>Suivant</button>";

  document.getElementById("labelFormulairePaiementAPlusieurs").innerHTML = "Paiement à plusieurs"
  document.getElementById("modal-content").innerHTML = htmlToPush;
  document.getElementById("modal-footer").innerHTML = footerToPush;
}


function openNextModal(){
  if(document.getElementById('nbPers').checked==true){
    //Clients souhaitent diviser en un nb de personnes
    afficherFormulaireChoixNbPers();
  }
  else if (document.getElementById('conso').checked==true){
    //Clients souhaitent payer chacun leurs consos
    afficherFormulaireChacunPayeSesConsos();
  }
}



function afficherFormulaireChoixNbPers(){
    // modal nb pers
    htmlToPush =
                  "<form>" +
                    "<div class='ppl-num'>" +
                      "Nombre de personnes" +
                      "<input type='number' id='ppl-nb' value='1' min='1'/>" +
                    "</div>" +
                  "</form>";


    var footerToPush =
      "<button type='button' class='btn btn-danger' data-dismiss='modal'>Annuler</button>" +
      "<button type='button' class='btn btn-warning' onclick='chargerFormulairePaiementAPlusieurs()'>Retour</button>" +
      "<button type='button' class='btn btn-success' onclick='afficherFormulaireChoixTypePaiementNbPers()'>Suivant</button>";

    document.getElementById("labelFormulairePaiementAPlusieurs").innerHTML = "Choix du nombre de personnes"
    document.getElementById("modal-content").innerHTML = htmlToPush;
    document.getElementById("modal-footer").innerHTML = footerToPush;
}



function afficherFormulaireChoixTypePaiementNbPers(){
  var nbPers = document.getElementById("ppl-nb").value;
  htmlToPush =  "<div>" +
                  "Cela fait un total de 15,50€ par personne" +
                "</div>" +
                "<form>" +
                  "<input type=\"checkbox\" id=\"cb\">Carte bancaire</input><br>" +
                  "<input type=\"checkbox\" id=\"cheque\">Chèque</input><br>" +
                  "<input type=\"checkbox\" id=\"especes\">Espèces</input><br>" +
                  "<input type=\"checkbox\" id=\"tr\">Tickets restaurants</input><br>";
  for(var i=1; i<=nbPers; i++){
    htmlToPush += "<div class='form-group'>" +
                      "<label for='formGroupExampleInput'><b>Email de la "+i+" ere/eme personne (si vous souhaitez recevoir le ticket de caisse par mail)</b></label>" +
                      "<input type='text' class='form-control' id='formEmail"+i+"' placeholder='Email...'>" +
                    "</div>";
  }
  htmlToPush += "</form>";


  var footerToPush =
    "<button type='button' class='btn btn-danger' data-dismiss='modal'>Annuler</button>" +
    "<button type='button' class='btn btn-warning' onclick='afficherFormulaireChoixNbPers()'>Retour</button>" +
    "<button type='button' class='btn btn-success' onclick='checkAuMoinsUnMoyenDePaiementChoisi()'>Confirmer</button>";

  document.getElementById("labelFormulairePaiementAPlusieurs").innerHTML = "Choix des différents moyens de paiement - Adresse mail"
  document.getElementById("modal-content").innerHTML = htmlToPush;
  document.getElementById("modal-footer").innerHTML = footerToPush;
}



function checkAuMoinsUnMoyenDePaiementChoisi(){
  if(document.getElementById('cb').checked==true || document.getElementById('cheque').checked==true || document.getElementById('especes').checked==true || document.getElementById('tr').checked==true){
    alert("Validation des choix de paiement effectuée avec succès");
    $('#paiementAPlusieurs').modal('hide');
    chargerFormulairePaiementAPlusieurs();
  }
  else{
    alert("Il faut sélectionner au moins un moyen de paiement !");
  }
}

var toutesConsos = [{nom : "Eau gazeuse", quantite:3, prix_unitaire: 2.50},
                    {nom : "Paix-Dieu 50cl", quantite:2, prix_unitaire: 6},
                    {nom : "Poulet au curry", quantite:4, prix_unitaire: 10.50},
                    {nom : "Steak frites", quantite:1, prix_unitaire: 8.50},
                    {nom : "Dame blanche", quantite:5, prix_unitaire: 4.50}];

var nomPers = [];

var nieme_pers = 1;

function afficherFormulaireChacunPayeSesConsos(){
  htmlToPush ="<form>";
  for(var conso in toutesConsos){
    var plat = toutesConsos[conso];
    if(plat.quantite>0){
      htmlToPush +=
            "<div class=''>"+
              "<b id='nom-plat_"+plat.nom+"'>"+plat.nom+"</b>" +
              "<input id='quantite-plat_"+plat.nom+"' type='number' value='0' min='0' max='"+plat.quantite+"'/>" +
            "</div>";
    }
  }
  htmlToPush += "<div class='form-group'>" +
                  "<label for='formGroupExampleInput'><b>Prénom</b></label>" +
                  "<input type='text' class='form-control' id='formPrenom' placeholder='Prénom...'>" +
                "</div>";
  htmlToPush += "<div class='form-group'>" +
                  "<label for='formGroupExampleInput'><b>Email (si vous souhaitez recevoir le ticket de caisse par mail)</b></label>" +
                  "<input type='text' class='form-control' id='formGroupExampleInput' placeholder='Email...'>" +
                "</div>";
  htmlToPush += "<div class='form-group'>" +
                  "<label for='formGroupExampleInput'><b>Choix du paiement</b></label>" +
                  "<div class='choix_paiement_personne'>" +
                    "<input type=\"radio\" id=\"cb\" name=\"choixPaiementPlusieurs\" checked><label for=\"cb\">Carte bancaire</label><br>" +
                    "<input type=\"radio\" id=\"cheque\" name=\"choixPaiementPlusieurs\"><label for=\"cheque\">Chèque</label><br>" +
                    "<input type=\"radio\" id=\"especes\" name=\"choixPaiementPlusieurs\"><label for=\"especes\">Espèces</label><br>" +
                    "<input type=\"radio\" id=\"tr\" name=\"choixPaiementPlusieurs\"><label for=\"tr\">Tickets restaurants</label><br>" +
                  "</div>" +
                "</div>";
  htmlToPush +="</form>"

  var footerToPush =
    "<button type='button' class='btn btn-danger' data-dismiss='modal'>Annuler</button>" +
    "<button type='button' class='btn btn-success' onclick='checkPrenomOkEtMajQuantite()'>Confirmer</button>";

  document.getElementById("labelFormulairePaiementAPlusieurs").innerHTML = nieme_pers+" ere/eme personne";
  document.getElementById("modal-content").innerHTML = htmlToPush;
  document.getElementById("modal-footer").innerHTML = footerToPush;
}

function checkPrenomOkEtMajQuantite(){
  if(document.getElementById('formPrenom').value==""){
    alert("Merci d'indiquer un prénom");
  }
  else{
    var plusConso = true;
    for(var conso in toutesConsos){
      var plat = toutesConsos[conso];
      if(plat.quantite>0){
        var quantite = document.getElementById("quantite-plat_"+plat.nom).value;
        if (quantite>=0 && quantite<=plat.quantite){
          plat.quantite = plat.quantite - quantite;
          if(plat.quantite>0){
            plusConso = false;
          }
        }
      }
    }
    if (plusConso){
      alert("Validation des choix de paiement effectuée avec succès");
      $('#paiementAPlusieurs').modal('hide');
      chargerFormulairePaiementAPlusieurs();
      toutesConsos = [{nom : "Eau gazeuse", quantite:3, prix_unitaire: 2.50},
                          {nom : "Paix-Dieu 50cl", quantite:2, prix_unitaire: 6},
                          {nom : "Poulet au curry", quantite:4, prix_unitaire: 10.50},
                          {nom : "Steak frites", quantite:1, prix_unitaire: 8.50},
                          {nom : "Dame blanche", quantite:5, prix_unitaire: 4.50}];
      nomPers = [];
      nieme_pers = 1;
    }else{
      nieme_pers ++;
      nomPers.push(document.getElementById('formPrenom').value);
      if(nieme_pers<=5){
        afficherFormulaireChacunPayeSesConsos();
      }else{
        afficherFormulaireResteConsommations();
      }
    }
  }
}


function valid_choice_person(nomPers, nomPlat, i){
  document.getElementById("conso-button-"+nomPlat+"_"+i).textContent = nomPers;
}

function afficherFormulaireResteConsommations(){
  htmlToPush ="<form>";
  for(var conso in toutesConsos){
    var plat = toutesConsos[conso];
    if(plat.quantite>0){
      for(var i=0; i<plat.quantite; i++){
        htmlToPush +=
              "<div class='choix_reste_conso'>"+
                "<b id='nom-plat_"+plat.nom+"'>"+plat.nom+"</b>" +
                "<div id='choix-de-conso-"+plat.nom+"_"+i+"'>"+
                  "<button type='button' id='conso-button-"+plat.nom+"_"+i+"' class='btn btn-secondary dropdown-toggle order-state' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" +
                      nomPers[0]+
                  "</button>"+
                  "<div class='dropdown-menu'>"+
                      "<a class='dropdown-item' onclick='valid_choice_person(\""+nomPers[0]+"\",\""+plat.nom+"\",\""+i+"\")'>"+nomPers[0]+"</a>" +
                      "<a class='dropdown-item' onclick='valid_choice_person(\""+nomPers[1]+"\",\""+plat.nom+"\",\""+i+"\")'>"+nomPers[1]+"</a>" +
                      "<a class='dropdown-item' onclick='valid_choice_person(\""+nomPers[2]+"\",\""+plat.nom+"\",\""+i+"\")'>"+nomPers[2]+"</a>" +
                      "<a class='dropdown-item' onclick='valid_choice_person(\""+nomPers[3]+"\",\""+plat.nom+"\",\""+i+"\")'>"+nomPers[3]+"</a>" +
                      "<a class='dropdown-item' onclick='valid_choice_person(\""+nomPers[4]+"\",\""+plat.nom+"\",\""+i+"\")'>"+nomPers[4]+"</a>" +
                  "</div>"+
                "</div>" +
              "</div>";
      }
    }
  }
  htmlToPush +="</form>"

  var footerToPush =
    "<button type='button' class='btn btn-danger' data-dismiss='modal'>Annuler</button>" +
    "<button type='button' class='btn btn-success' onclick='paiementAPlusieursConsoOk()'>Confirmer</button>";

  document.getElementById("labelFormulairePaiementAPlusieurs").innerHTML = "Reste des consommations";
  document.getElementById("modal-content").innerHTML = htmlToPush;
  document.getElementById("modal-footer").innerHTML = footerToPush;
}

function paiementAPlusieursConsoOk(){
  alert("Validation des choix de paiement et des consommations effectuée avec succès");
  $('#paiementAPlusieurs').modal('hide');
  chargerFormulairePaiementAPlusieurs();
  toutesConsos = [{nom : "Eau gazeuse", quantite:3, prix_unitaire: 2.50},
                      {nom : "Paix-Dieu 50cl", quantite:2, prix_unitaire: 6},
                      {nom : "Poulet au curry", quantite:4, prix_unitaire: 10.50},
                      {nom : "Steak frites", quantite:1, prix_unitaire: 8.50},
                      {nom : "Dame blanche", quantite:5, prix_unitaire: 4.50}];
  nomPers = [];
  nieme_pers = 1;
}
