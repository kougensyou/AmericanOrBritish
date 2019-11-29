package com.example.americanorbritish.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.americanorbritish.Model.QuizClothes
import com.example.americanorbritish.Model.QuizFood
import com.example.americanorbritish.Model.QuizRandom
import com.example.americanorbritish.Model.QuizTransportation
import java.util.*


class DBHelper : SQLiteOpenHelper {

    private lateinit var dbase: SQLiteDatabase

    constructor(context: Context) : super(context, DATABASE_NAME, null, 1) {}
    constructor(
        context: Context,
        name: String,
        factory: SQLiteDatabase.CursorFactory,
        version: Int
    ) : super(context, DATABASE_NAME, factory, version) {
    }

    override fun onCreate(db: SQLiteDatabase) {
        dbase = db

        val sql1 = ("CREATE TABLE IF NOT EXISTS " + TABLE_QUIZ1 + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION
                + " TEXT, " + KEY_OPT1 + " TEXT, " + KEY_OPT2 + " TEXT, " + KEY_ANS_US + " TEXT, " + KEY_ANS_UK + " TEXT)")
        db.execSQL(sql1)
        addQuizFood()

        val sql2 = ("CREATE TABLE IF NOT EXISTS " + TABLE_QUIZ2 + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION
                + " TEXT, " + KEY_OPT1 + " TEXT, " + KEY_OPT2 + " TEXT, " + KEY_ANS_US + " TEXT, " + KEY_ANS_UK + " TEXT)")
        db.execSQL(sql2)
        addQuizTransportation()

        val sql3 = ("CREATE TABLE IF NOT EXISTS " + TABLE_QUIZ3 + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION
                + " TEXT, " + KEY_OPT1 + " TEXT, " + KEY_OPT2 + " TEXT, " + KEY_ANS_US + " TEXT, " + KEY_ANS_UK + " TEXT)")
        db.execSQL(sql3)
        addQuizClothes()

        val sql4 = ("CREATE TABLE IF NOT EXISTS " + TABLE_QUIZ4 + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION
                + " TEXT, " + KEY_OPT1 + " TEXT, " + KEY_OPT2 + " TEXT, " + KEY_ANS_US + " TEXT, " + KEY_ANS_UK + " TEXT)")
        db.execSQL(sql4)
        addQuizRandom()

    }

    fun addQuizFood() {
        val q01 = QuizFood("ポテト", "Fries", "Chips", "Fries", "Chips")
        this.addQuizFood(q01)
        val q02 = QuizFood("クッキー", "Biscuits", "Cookies", "Cookies", "Biscuits")
        this.addQuizFood(q02)
        val q03 = QuizFood("お菓子", "Candy", "Sweet", "Candy", "Sweet")
        this.addQuizFood(q03)
        val q04 = QuizFood("トウモロコシ", "Maize", "Corn", "Corn", "Maize")
        this.addQuizFood(q04)
        val q05 = QuizFood("ゼリー", "Jello", "Jelly", "Jello", "Jelly")
        this.addQuizFood(q05)
        val q06 = QuizFood("ナス", "Eggplant", "Bunglespleen", "Eggplant", "Bunglespleen")
        this.addQuizFood(q06)
        val q07 = QuizFood("サンドイッチ", "Butty", "Sandwich", "Sandwich", "Butty")
        this.addQuizFood(q07)
        val q08 = QuizFood("バー", "Bar", "Pub", "Bar", "Pub")
        this.addQuizFood(q08)
        val q09 = QuizFood("買い物入れ", "Trolley", "Shopping cart", "Shopping cart", "Trolley")
        this.addQuizFood(q09)
        val q10 = QuizFood("サーモン", "Lox", "Smoked salmon", "Lox", "Smoked salmon")
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
        dbase.insert(TABLE_QUIZ1, null, values)
    }

    fun addQuizTransportation() {
        val q11 = QuizTransportation("タクシー", "Cab", "Taxi", "Cab", "Taxi")
        this.addQuizTransportation(q11)
        val q12 = QuizTransportation("ポスト", "Post", "Mail", "Mail", "Post")
        this.addQuizTransportation(q12)
        val q13 = QuizTransportation("駐車場", "Parking lot", "Car park", "Parking lot", "Car park")
        this.addQuizTransportation(q13)
        val q14 = QuizTransportation("トラック", "Lorry", "Truck", "Truck", "Lorry")
        this.addQuizTransportation(q14)
        val q15 = QuizTransportation("エレベーター", "Elevator", "Lift", "Elevator", "Lift")
        this.addQuizTransportation(q15)
        val q16 = QuizTransportation("フード", "Bonnet", "Hood", "Hood", "Bonnet")
        this.addQuizTransportation(q16)
        val q17 = QuizTransportation("高速道路", "Highway", "Motorway", "Highway", "Motorway")
        this.addQuizTransportation(q17)
        val q18 = QuizTransportation("ガソリン", "Petrol", "Gasoline", "Gasoline", "Petrol")
        this.addQuizTransportation(q18)
        val q19 = QuizTransportation("地下鉄", "Subway", "Underground", "Subway", "Underground")
        this.addQuizTransportation(q19)
        val q20 = QuizTransportation("バイク", "Motorbike", "Motorcycle", "Motorcycle", "Motorbike")
        this.addQuizTransportation(q20)
    }

    fun addQuizTransportation(quest: QuizTransportation) {
        //SQLiteDatabase db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(KEY_QUESTION, quest.qUESTION)
        values.put(KEY_OPT1, quest.oPTION1)
        values.put(KEY_OPT2, quest.oPTION2)
        values.put(KEY_ANS_US, quest.aNS_US)
        values.put(KEY_ANS_UK, quest.aNS_UK)
        // Inserting Row
        dbase.insert(TABLE_QUIZ2, null, values)
    }

    fun addQuizClothes() {
        val q21 = QuizClothes("パジャマ", "Pajiamas", "Pyjamas", "Pajiamas", "Pajamas")
        this.addQuizClothes(q21)
        val q22 = QuizClothes("タンス", "Wardrobe", "Closet", "Closet", "Wardrobe")
        this.addQuizClothes(q22)
        val q23 = QuizClothes("チャック", "Zipper", "Zip", "Zipper", "Zip")
        this.addQuizClothes(q23)
        val q24 = QuizClothes("スニーカー", "Sneaker", "Trainer", "Sneaker", "Trainer")
        this.addQuizClothes(q24)
        val q25 = QuizClothes("リュックサック", "Knapsack", "Ruck sack", "Knapsack", "Ruck sack")
        this.addQuizClothes(q25)
        val q26 = QuizClothes("カバン", "Handbag", "Purse", "Purse", "Handbag")
        this.addQuizClothes(q26)
        val q27 = QuizClothes("セーター", "Sweater", "Jumper", "Sweater", "Jumper")
        this.addQuizClothes(q27)
        val q28 = QuizClothes("シャツ", "Waistcoat", "Vest", "Vest", "Waistcoat")
        this.addQuizClothes(q28)
        val q29 = QuizClothes("サスペンダー", "Brace", "Suspender", "Suspender", "Brace")
        this.addQuizClothes(q29)
        val q30 = QuizClothes("口髭", "Mustache", "Moustache", "Mustache", "Moustache")
        this.addQuizClothes(q30)
    }

    fun addQuizClothes(quest: QuizClothes) {
        //SQLiteDatabase db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(KEY_QUESTION, quest.qUESTION)
        values.put(KEY_OPT1, quest.oPTION1)
        values.put(KEY_OPT2, quest.oPTION2)
        values.put(KEY_ANS_US, quest.aNS_US)
        values.put(KEY_ANS_UK, quest.aNS_UK)
        // Inserting Row
        dbase.insert(TABLE_QUIZ3, null, values)
    }

    fun addQuizRandom() {
        val q31 = QuizRandom("サッカー", "Soccer", "Football", "Soccer", "Football")
        this.addQuizRandom(q31)
        val q32 = QuizRandom("消しゴム", "Rubber", "Eraser", "Eraser", "Rubber")
        this.addQuizRandom(q32)
        val q33 = QuizRandom("色", "Color", "Colour", "Color", "Colour")
        this.addQuizRandom(q33)
        val q34 = QuizRandom("アパート", "Apartment", "Flat", "Apartment", "Flat")
        this.addQuizRandom(q34)
        val q35 = QuizRandom("2階", "First floor", "Second floor", "Second floor", "First floor")
        this.addQuizRandom(q35)
        val q36 = QuizRandom("持ち帰り", "Takeout", "Takeaway", "Takeout", "Takeaway")
        this.addQuizRandom(q36)
        val q37 = QuizRandom("列", "Line", "Queue", "Line", "Queue")
        this.addQuizRandom(q37)
        val q38 = QuizRandom("携帯電話", "Mobile phone", "Cell phone", "Cell phone", "Mobile phone")
        this.addQuizRandom(q38)
        val q39 = QuizRandom("休暇", "Holiday", "Vacation", "Vacation", "Holiday")
        this.addQuizRandom(q39)
        val q40 = QuizRandom("ゴミ箱", "Trash can", "Bin", "Trash can", "Bin")
        this.addQuizRandom(q40)
    }

    fun addQuizRandom(quest: QuizRandom) {
        //SQLiteDatabase db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(KEY_QUESTION, quest.qUESTION)
        values.put(KEY_OPT1, quest.oPTION1)
        values.put(KEY_OPT2, quest.oPTION2)
        values.put(KEY_ANS_US, quest.aNS_US)
        values.put(KEY_ANS_UK, quest.aNS_UK)
        // Inserting Row
        dbase.insert(TABLE_QUIZ4, null, values)
    }

    fun getAllQuestions1(tname: String): List<QuizFood> {
        val quesList1 = ArrayList<QuizFood>()
        val selectQuery1 = "SELECT  * FROM $tname"
        dbase = this.readableDatabase
        val cursor = dbase!!.rawQuery(selectQuery1, null)
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val quest1 = QuizFood()
                quest1.iD1 = cursor.getInt(0)
                quest1.qUESTION = cursor.getString(1)
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

    fun getAllQuestions2(tname: String): List<QuizTransportation> {
        val quesList1 = ArrayList<QuizTransportation>()
        val selectQuery1 = "SELECT  * FROM $tname"
        dbase = this.readableDatabase
        val cursor = dbase.rawQuery(selectQuery1, null)
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val quest1 = QuizTransportation()
                quest1.iD1 = cursor.getInt(0)
                quest1.qUESTION = cursor.getString(1)
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

    fun getAllQuestions3(tname: String): List<QuizClothes> {
        val quesList1 = ArrayList<QuizClothes>()
        val selectQuery1 = "SELECT  * FROM $tname"
        dbase = this.readableDatabase
        val cursor = dbase.rawQuery(selectQuery1, null)
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val quest1 = QuizClothes()
                quest1.iD1 = cursor.getInt(0)
                quest1.qUESTION = cursor.getString(1)
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

    fun getAllQuestions4(tname: String): List<QuizRandom> {
        val quesList1 = ArrayList<QuizRandom>()
        val selectQuery1 = "SELECT  * FROM $tname"
        dbase = this.readableDatabase
        val cursor = dbase.rawQuery(selectQuery1, null)
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val quest1 = QuizRandom()
                quest1.iD1 = cursor.getInt(0)
                quest1.qUESTION = cursor.getString(1)
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
        private val TABLE_QUIZ2 = "quizTransportation"
        private val TABLE_QUIZ3 = "quizClothes"
        private val TABLE_QUIZ4 = "quizRandom"

    }

}