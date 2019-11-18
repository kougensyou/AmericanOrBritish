package com.example.americanorbritish

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import com.example.americanorbritish.Adapter.ListAdapter
import com.example.americanorbritish.Model.ListModel
import com.example.americanorbritish.Model.QuizFood
import java.util.ArrayList

class ResultActivity : AppCompatActivity() {

    var wrongQuests = ArrayList<QuizFood>()

    private val m_parts = ArrayList<ListModel>()

    lateinit internal var listView: ListView

    internal var quizActivity = QuizActivity()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        listView = findViewById<ListView>(R.id.listView1)

        wrongQuests = quizActivity.wrongQuestListFood

        wrongQuests = intent.getParcelableExtra<Parcelable>("wrongQuestions") as ArrayList<QuizFood>

        var strQstn = arrayOfNulls<String>(wrongQuests.size)

        //strQstn = wrongQuests.toTypedArray<String?>()




    }



}