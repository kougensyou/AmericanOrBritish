package com.example.americanorbritish.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.americanorbritish.Model.ListModel
import com.example.americanorbritish.R
import java.util.*

class ListAdapter(
    context: Context,
    textViewResourceId: Int,
    private val objects: ArrayList<ListModel>
) : ArrayAdapter<ListModel>(context, textViewResourceId, objects) {

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView


        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        v = inflater.inflate(R.layout.list_row, null)

        val txtqstn = v!!.findViewById<View>(R.id.txtQSTN) as TextView
        val ansus = v.findViewById<View>(R.id.ans_US) as TextView
        val ansuk = v.findViewById<View>(R.id.ans_UK) as TextView

        txtqstn.text = "Question: " + objects[position].question
        ansus.text = "US : " + objects[position].ans_us
        ansuk.text = "UK : " + objects[position].ans_uk

        return v
    }
}
