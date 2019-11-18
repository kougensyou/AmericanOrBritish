package com.example.americanorbritish.Model

class ListModel {

    lateinit var question: String
    lateinit var ans_us: String
    lateinit var ans_uk: String

    constructor() {

    }

    constructor(qstn: String, ansus: String, ansuk: String) {
        question = qstn
        ans_us = ansus
        ans_uk = ansuk
    }

}