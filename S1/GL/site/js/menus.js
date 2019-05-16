function display(){
    const categories = menu.getCategories();
    let div = document.getElementById("CategoryNames");

    categories.forEach(function(category, index){
        let content = document.createElement("div");
        content.className = "menu";

        let header = document.createElement("div");
        header.className = "menu-header Button";
        header.id = "category"+index;
        header.setAttribute("data-target", "collapse"+index);
        header.innerHTML = category.name;

        let collapse = document.createElement("div");
        collapse.className = "collapse";
        collapse.id = "collapse"+index;

        const subcategories = category.getSubCategories();
        const products = category.getProducts();

        if(subcategories.length){
            subcategories.forEach(function(subcat, index2){
                let subdiv = document.createElement("div");let content = document.createElement("div");
                subdiv.className = "sousmenu";

                let header2 = document.createElement("div");
                header2.className = "menu-header Button";
                header2.id = "sous-category"+index+index2;
                header2.setAttribute("data-target-SC", "collapse-s"+index+index2);
                header2.innerHTML = subcat.name;

                let collapse2 = document.createElement("div");
                collapse2.className = "collapse";
                collapse2.id = "collapse-s"+index+index2;

                const productsSC = subcat.getProducts();
                productsSC.forEach(function(product){
                    let infos = document.createElement("div");
                    infos.className = "product-infos Button";
                    infos.setAttribute("data-name", product.productName);
                    infos.setAttribute("data-image", product.productImg);
                    infos.setAttribute("data-price", product.price);
                    infos.setAttribute("data-ingredients", product.ingredients.join(":"));
                    infos.addEventListener('click', seeProduct);
                    let name = document.createElement("div");
                    let price = document.createElement("div");
                    let dispo = document.createElement("div");
                    name.innerText = product.productName;
                    if(!product.disponible){
                      price.innerText = "Indisponible";
                    } else if(product.disponible){
                      price.innerText = "Prix: "+ product.price + "€";
                    }
                    dispo.className = product.estDisponible() ? "avaible" : "unavaible";
                    infos.appendChild(name);
                    infos.appendChild(price);
                    infos.appendChild(dispo);
                    collapse2.appendChild(infos);
                });
                header2.addEventListener('click', hideSousC, false);
                subdiv.appendChild(header2);
                subdiv.appendChild(collapse2);

              collapse.appendChild(subdiv);
            });
        }

        header.addEventListener('click', hide, false);

        content.appendChild(header);
        content.appendChild(collapse);
        div.appendChild(content);
    });
}

function hide(e){
    const id = e.target.getAttribute("data-target");
    const cible = document.getElementById(id);
    const showed = document.getElementsByClassName("collapse-show");

    for(let i=0; i<showed.length; i++){
        if(showed[i].id != cible.id){
            showed[i].className = "collapse";
        }

    }

    cible.classList.toggle("collapse");
    cible.classList.toggle("collapse-show");
}

function hideSousC(e){

    const id = e.target.getAttribute("data-target-SC");
    const cible = document.getElementById(id);
    const showed = cible.parentNode;

    cible.classList.toggle("collapse");
    cible.classList.toggle("collapse-show");
}

function seeProduct(){
    const name = this.getAttribute("data-name");
    const image = this.getAttribute("data-image");
    const price = this.getAttribute("data-price");
    const ingredients = this.getAttribute("data-ingredients");

    document.getElementById("ProductDetails").style.display = "flex";

    document.getElementById("ProductName").innerText = name;
    document.getElementById("ProductImage").setAttribute("src", image);
    // alert()
    if(price==="Indisponible"){
      document.getElementById("ProductPrice").innerText = "Indisponible";
      document.getElementById("addToCart-Button").style.display ="none";
    } else{
      document.getElementById("ProductPrice").innerText = "Prix: "+ price + "€";
      document.getElementById("addToCart-Button").style.display ="block";
    }
    let ingred = document.getElementById("ProductIngredients");
    ingred.innerHTML = "";

    ingredients.split(":").forEach(function(elem, index){
        let div = document.createElement("div");
        div.className = "IngreditDeleter";
        div.id = "Ingredient"+index;
        let span = document.createElement("span");
        let img = document.createElement("div");
        span.innerText = elem;
        img.className = "deleteElem Button";
        img.setAttribute("data-target", "Ingredient"+index);
        img.addEventListener('click', delectIngredient, false);
        div.appendChild(span);
        div.appendChild(img);
        ingred.appendChild(div);
    });
    document.getElementById("qteValue").value = "1";
}

function delectIngredient(e){
    const id = e.target.getAttribute("data-target");
    document.getElementById(id).className = "hide";
}


function searchProd(){
    let input =  document.getElementById("shearch-input");
    let result = document.getElementById("search-result");
    filter =  input.value.toUpperCase();

    let ul = document.createElement("ul");
    result.innerHTML = "";
    searchProduct.forEach(function(product){
        let li = document.createElement("li");
        let productName = product.productName;
        if(filter.length > 0 && productName.toUpperCase().indexOf(filter) > -1){
            li.innerText = productName;
            li.className = "Button";
            li.setAttribute("data-name", product.productName);
            li.setAttribute("data-image", product.productImg);
            li.setAttribute("data-price", product.price);
            li.setAttribute("data-ingredients", product.ingredients.join(":"));
            li.addEventListener('click', seeProduct, false);
            ul.appendChild(li);
        }
    });
    result.appendChild(ul);
}


display();
