class form extends component{


    depict(){
        return {"is": "text", "value":"hmm","onClick":this.clicked};
    }

    clicked(){
        print("woaahhhh clicked");
    }

}


depict.initComponent(new form());
