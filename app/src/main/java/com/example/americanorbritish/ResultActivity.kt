package com.example.americanorbritish

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import com.example.americanorbritish.Adapter.ListAdapter
import com.example.americanorbritish.Model.ListModel
import com.example.americanorbritish.Model.QuizFood
import java.util.ArrayList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ResultActivity : AppCompatActivity() {

    var wrongQuests = ArrayList<QuizFood>()

    private val m_parts = ArrayList<ListModel>()

    lateinit internal var listView: ListView
    lateinit internal var yourScore: TextView
    var Score = 0
    lateinit internal var Table:String
    private var mDatabase: DatabaseReference? = null
    private var auth: FirebaseAuth? = null

    internal var quizActivity = QuizActivity()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        mDatabase = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        listView = findViewById<ListView>(R.id.listView1)
        yourScore = findViewById<View>(R.id.YourScore) as TextView

        val b = intent.extras

        Score = b!!.getInt("score")
        Table = b!!.getString("section") as String

        yourScore.text = "スコア：" + Score

        wrongQuests = quizActivity.wrongQuestListFood

        wrongQuests = intent.getSerializableExtra("wrongQuestions") as ArrayList<QuizFood>

        var strQstn = arrayOfNulls<String>(wrongQuests.size)

        for (i in strQstn.indices) {
            m_parts.add(ListModel(wrongQuests.get(i).qUESTION, wrongQuests.get(i).aNS_US, wrongQuests.get(i).aNS_UK))
        }

        val listAdapter = ListAdapter(this, R.layout.list_row, m_parts)
        listView?.adapter = listAdapter


        mDatabase!!.child("Scores").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {

                var historyscore = (datasnapshot.child(auth!!.uid!!).child(Table).getValue()!!).toString().toInt()

                if(Score > historyscore){
                    mDatabase!!.child("Scores").child(auth!!.uid!!).child(Table).setValue(Score)
                }
                Log.e("The read success: ", "sucess")

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("The read failed: ", databaseError.message)
            }
        })
    }
}