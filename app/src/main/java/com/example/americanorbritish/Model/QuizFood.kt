package com.example.americanorbritish.Model

class QuizFood {

    lateinit var image: ByteArray
    lateinit var option1: String
    lateinit var option2: String
    lateinit var ans_US: String
    lateinit var ans_UK: String


    constructor() {
        image = empty
        option1 = ""
        option2 = ""
        ans_US = ""
        ans_UK = ""
    }

    constructor(iMAGE: ByteArray, oPTION1: String, oPTION2: String, aNS_US: String, aNS_UK: String) {
        image = iMAGE
        option1 = oPTION1
        option2 = oPTION2
        ans_US = aNS_US
        ans_UK = aNS_UK
    }
}