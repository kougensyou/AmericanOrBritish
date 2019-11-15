package com.example.americanorbritish.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_IMAGE
                + " BLOB, " + KEY_OPT1 + "TEXT, " + KEY_OPT2 + "TEXT, " + KEY_ANS_US + " TEXT, " + KEY_ANS_UK + "TEXT, " + KEY_OPT1 + " TEXT, "
                + KEY_OPT2 + " TEXT)")
        db.execSQL(sql1)
        addQuizFood()

    }

    private fun addQuizFood() {
        val q101 = QuizFood(
            "What is the decimal equivalent of the binary number 10111",
            "21",
            "23",
            "39",
            "42",
            "23",
            "B"
        )
        this.addQuizFood(q101)

    }


    fun addQuizFood(quest: QuizFood) {
        //SQLiteDatabase db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(KEY_IMAGE, quest.image)
        values.put(KEY_OPT1, quest.option1)
        values.put(KEY_OPT2, quest.option2)
        values.put(KEY_ANS_US, quest.ans_US)
        values.put(KEY_ANS_UK, quest.ans_UK)
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
                quest1.image= cursor.getInt(0)
                quest1.option1 = cursor.getString(1)
                quest1.option2 = cursor.getString(2)
                quest1.ans_US = cursor.getString(3)
                quest1.ans_UK = cursor.getString(4)
                quesList1.add(quest1)
            } while (cursor.moveToNext())
        }
        // return quest list
        return quesList1
    }

    companion object {

        private val DATABASE_NAME = "Quiz.db"
        private val KEY_ID = "id"
        private val KEY_IMAGE = "image"
        private val KEY_OPT1 = "opt1" //option 1
        private val KEY_OPT2 = "opt2" //option 2
        private val KEY_ANS_US = "ans_US"
        private val KEY_ANS_UK = "ans_UK"
        private val TABLE_QUIZ1 = "quizFood"

    }

}