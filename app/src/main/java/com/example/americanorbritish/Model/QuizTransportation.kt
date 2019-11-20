package com.example.americanorbritish.Model

import java.io.Serializable

class QuizTransportation : Serializable {
    var iD1: Int = 0
    var qUESTION: String
    var oPTION1: String
    var oPTION2: String
    var aNS_US: String
    var aNS_UK: String

    constructor() {
        iD1 = 0
        qUESTION = ""
        oPTION1 = ""
        oPTION2 = ""
        aNS_US = ""
        aNS_UK = ""
    }

    constructor(quESTION: String , opTION1: String, opTION2: String, anS_US: String, anS_UK: String) {
        qUESTION = quESTION
        oPTION1 = opTION1
        oPTION2 = opTION2
        aNS_US = anS_US
        aNS_UK = anS_UK
    }
}