class component {

    constructor(){

        this.onStart();
    }

    depict(){
        return {}
    }

    onStart(){

    }

}

enums = {

    "HORIZONTAL" : 0,
    "VERTICAL" : 1

}


propertyMap = {

    "value" : {
        "android" : ["setText","getText"],
        "ios" : "",
    },

    "hint" : {
        "android" : ["setHint","getHint"],
        "ios" : "",
    },

    "orientation" : {
        "android" : ["setOrientation","getOrientation"],
        "ios" : "",
    },
}