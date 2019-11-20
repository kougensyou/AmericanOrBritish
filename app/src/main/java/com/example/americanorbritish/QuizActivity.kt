package com.example.americanorbritish

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.americanorbritish.DBHelper.DBHelper
import com.example.americanorbritish.Model.QuizFood

import java.util.*
import android.widget.ArrayAdapter
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import java.util.concurrent.TimeUnit


class QuizActivity : AppCompatActivity() {

    lateinit internal var quesList1: List<QuizFood>
    var score = 0
    internal var ctr1 = 1
    lateinit internal var currentQ1: QuizFood
    lateinit internal var question: TextView
    lateinit internal var item1: Spinner
    lateinit internal var item2: Spinner
    lateinit internal var butNext: Button
    internal var random1 = Random()
    lateinit internal var textViewTime1: TextView
    internal var list = ArrayList<Int>()
    var wrongQuestListFood = ArrayList<QuizFood>()
    var actualAnswerFood = ArrayList<String>()
    lateinit internal var progressBar: ProgressBar
    internal var progress = 1
    internal var tableName = ""
    lateinit internal var qstnNo: TextView
    internal var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        qstnNo = findViewById<View>(R.id.qstnNo) as TextView

        val iin = intent
        val b = iin.extras

        if (b != null) {
            tableName = b.get("table_name") as String
            Log.d("Table Name", tableName)
        }
        number = 0
        val db = DBHelper(this)
        textViewTime1 = findViewById<View>(R.id.textViewTime) as TextView
        val timer = CounterClass(1800000, 1000)
        timer.start()

        quesList1 = db.getAllQuestions(tableName)
        for (i in 0..9) {
            while (true) {
                val next = random1.nextInt(10)
                if (!list.contains(next)) {
                    list.add(next)
                    break
                }
            }
        }
        currentQ1 = quesList1[list[0]]
        question = findViewById<View>(R.id.question) as TextView
        item1 = findViewById<View>(R.id.American_spinner) as Spinner
        item2 = findViewById<View>(R.id.British_spinner) as Spinner
        butNext = findViewById<View>(R.id.buttonNext) as Button

        setQuestionView()

        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        progressBar.max = 10
        progressBar.progress = 1
        butNext.setOnClickListener {
            progress = progress + 1
            progressBar.progress = progress
            if (item1.selectedItem == currentQ1.aNS_US && item2.selectedItem == currentQ1.aNS_UK) {
                score++
                //Log.d("score", "Your score" + score1);
            } else {
                wrongQuestListFood.add(number, currentQ1)
                number++
            }
            if (ctr1 < 11) {
                if (ctr1 == 10) {
                    butNext.text = "End Test"
                }
                currentQ1 = quesList1[list[ctr1-1]]
                setQuestionView()
            } else {
                timer.onFinish()
                timer.cancel()
            }
        }
    }

    private fun setQuestionView() {
        question.text = currentQ1.qUESTION
        var arraySpinner = arrayOf(currentQ1.oPTION1, currentQ1.oPTION2)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        item1.setAdapter(adapter);
        item2.setAdapter(adapter);
        if (ctr1 < 10)
            qstnNo.text = "0$ctr1/10"
        else
            qstnNo.text = "$ctr1/10"
        ctr1++
    }

    inner class CounterClass(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            val hms = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
            textViewTime1.setText(hms)
        }

        override fun onFinish() {
            showResult()
        }
    }


    fun showResult() {
        val intent = Intent(this@QuizActivity, ResultActivity::class.java)
        val b = Bundle()
        b.putInt("score", score)//Your score
        b.putString("section", tableName)//Your table name
        intent.putExtra("wrongQuestions", wrongQuestListFood)
        intent.putExtras(b) //Put your score to your next Intent
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        //super.onBackPressed();
        val builder = AlertDialog.Builder(this)
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage("If you close all your progress would not be saved... Do you wish to exit ?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id -> finish() }
            .setNegativeButton("No") { dialog, id ->
                //  Action for 'NO' Button
                dialog.cancel()
            }

        //Creating dialog box
        val alert = builder.create()
        //Setting the title manually
        // alert.setTitle("CompQuiz");
        alert.show()
    }



}