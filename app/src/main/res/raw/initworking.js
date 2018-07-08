class form extends component{



    depict(){
        return [
           { "is" : "layout", "orientation": enums.VERTICAL , "with": [
                {"is": "text", "value":"hmm"},
                {"is": "text", "value":"hello!"},
                {"is": "button", "value":"wow button!!!", "onClick": this.press}
           ]}
       ]
    }
    
    press(button){
        print(button.value)
    }
    

}


new form();
