class form extends component{

    values(){
        return {
            name: "marc",
            car: "toyota"
        }
    }

    depict(){
        return  {"is" : "layout", "orientation": enums.VERTICAL , "with": [
                    {"is": "text", "value":"hello, I'm {name}, and I drive a {car}"},
                    {"is": "button", "value":"click me", "onClick":this.onClick}
               ]};

    }

    onStart(){

    }

    onClick(button){
        this.value.name = "johnny"
    }


}

depict.initComponent(new form())