class form extends component{

    values(){
        return {
            name: "marc",
            car: "toyota"
        }
    }

    depict(){
        return  {"is" : "layout", "orientation": enums.VERTICAL , "with": [
                    {"is": "text", "value":"hello, I'm {name}, and I drive a {car}"}
               ]};

    }

    onStart(){

    }
    

}

depict.initComponent(new form())