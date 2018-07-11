class form extends component{


    depict(){
        return {"is":"layout","direction":1,"with":[
            {"is":"layout","direction":0,"with":[
                {"is": "text", "value":"row"},
                {"is": "text", "color":"#f44141", "value":" 1"}
            ]},
            {"is":"layout","direction":0,"with":[
                {"is": "text", "value":"row"},
                {"is": "text", "color":"#f44141","value":" 2"}
            ]},
            {"is":"button","value":"Depict!","onClick":this.clicked}
        ]};

    }




    clicked(){
        print("clicked!");
    }

}


depict.initComponent(new form());
