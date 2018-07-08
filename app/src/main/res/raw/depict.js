class component {

    constructor(){

        __depict__(this.depict())
    }

    depict(){
        return {}
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