package com.example.americanorbritish.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.example.americanorbritish.Model.QuizFood
import java.util.ArrayList


class DBHelper : SQLiteOpenHelper {

    private var dbase: SQLiteDatabase? = null

    constructor(context: Context) : super(context, DATABASE_NAME, null, 1) {}

    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory, version: Int) : super(context, DATABASE_NAME, factory, version) {}


    override fun onCreate(db: SQLiteDatabase) {
        dbase = db

        val sql1 = ("CREATE TABLE IF NOT EXISTS " + TABLE_QUIZ1 + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION
                + " TEXT, " + KEY_OPT1 + " TEXT, " + KEY_OPT2 + " TEXT, " + KEY_ANS_US + " TEXT, " + KEY_ANS_UK + " TEXT)")
        db.execSQL(sql1)
        addQuizFood()

    }

    fun addQuizFood() {
        val q01 = QuizFood("ポテト", "Fries", "Chips", "Fries", "Chips")
        this.addQuizFood(q01)
        val q02 = QuizFood("クッキー", "Biscuits", "Cookies", "Cookies", "Biscuits")
        this.addQuizFood(q02)
        val q03 = QuizFood("お菓子", "candy", "sweet", "candy", "sweet")
        this.addQuizFood(q03)
        val q04 = QuizFood("お菓子", "candy", "sweet", "candy", "sweet")
        this.addQuizFood(q04)
        val q05 = QuizFood("お菓子", "candy", "sweet", "candy", "sweet")
        this.addQuizFood(q05)
        val q06 = QuizFood("お菓子", "candy", "sweet", "candy", "sweet")
        this.addQuizFood(q06)
        val q07 = QuizFood("お菓子", "candy", "sweet", "candy", "sweet")
        this.addQuizFood(q07)
        val q08 = QuizFood("お菓子", "candy", "sweet", "candy", "sweet")
        this.addQuizFood(q08)
        val q09 = QuizFood("お菓子", "candy", "sweet", "candy", "sweet")
        this.addQuizFood(q09)
        val q10 = QuizFood("お菓子", "candy", "sweet", "candy", "sweet")
        this.addQuizFood(q10)

    }

    fun addQuizFood(quest: QuizFood) {
        //SQLiteDatabase db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(KEY_QUESTION, quest.qUESTION)
        values.put(KEY_OPT1, quest.oPTION1)
        values.put(KEY_OPT2, quest.oPTION2)
        values.put(KEY_ANS_US, quest.aNS_US)
        values.put(KEY_ANS_UK, quest.aNS_UK)
        // Inserting Row
        dbase!!.insert(TABLE_QUIZ1, null, values)
    }

    fun getAllQuestions(tname: String): List<QuizFood> {
        val quesList1 = ArrayList<QuizFood>()
        val selectQuery1 = "SELECT  * FROM $tname"
        dbase = this.readableDatabase
        val cursor = dbase!!.rawQuery(selectQuery1, null)
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val quest1 = QuizFood()
                quest1.iD1 = cursor.getInt(0)
                quest1.qUESTION= cursor.getString(1)
                quest1.oPTION1 = cursor.getString(2)
                quest1.oPTION2 = cursor.getString(3)
                quest1.aNS_US = cursor.getString(4)
                quest1.aNS_UK = cursor.getString(5)
                quesList1.add(quest1)
            } while (cursor.moveToNext())
        }
        // return quest list
        return quesList1
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    companion object {

        private val DATABASE_NAME = "Quiz.db"
        private val KEY_ID = "id"
        private val KEY_QUESTION = "question"
        private val KEY_OPT1 = "opt1" //option 1
        private val KEY_OPT2 = "opt2" //option 2
        private val KEY_ANS_US = "ans_US"
        private val KEY_ANS_UK = "ans_UK"
        private val TABLE_QUIZ1 = "quizFood"

    }

}