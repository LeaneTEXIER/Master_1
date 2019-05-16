var ConnectButton = function(){
    
    this.display_buton = function(){
        const connect_button = document.getElementById("connect");
        const connected = sessionStorage.getItem("connected");
        if(connected == "true"){
            connect_button.classList.add("hidden");
        }
    },


    process = function(){

    }
};
