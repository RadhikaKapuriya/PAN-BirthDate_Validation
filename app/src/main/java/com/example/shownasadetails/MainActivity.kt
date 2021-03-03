package com.example.shownasadetails

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    var edpan: EditText? = null
    var btnsubmit: Button? = null
    var btnbd: Button? = null
    var tvdate: TextView? = null
    private var year = 0
    private var month = 0
    private var day = 0
    private var calendar: Calendar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        edpan = findViewById<View>(R.id.edpan) as EditText
        btnsubmit = findViewById<View>(R.id.btnsubmit) as Button
        tvdate = findViewById<View>(R.id.tvdate) as TextView
        btnbd = findViewById<View>(R.id.btnbd) as Button
        calendar = Calendar.getInstance()
        year = calendar!!.get(Calendar.YEAR)
        month = calendar!!.get(Calendar.MONTH)
        day = calendar!!.get(Calendar.DAY_OF_MONTH)
        showDate(year, month + 1, day)
        btnbd!!.setOnClickListener {
            showDialog(999)
            Toast.makeText(applicationContext, "ca",
                    Toast.LENGTH_SHORT)
                    .show()
        }
        edpan!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                if (s != "") {
                    if (matchPaNNumber(s.toString())) {
                        btnsubmit!!.visibility = View.VISIBLE
                    }
                    else
                    {
                        btnsubmit!!.visibility = View.GONE
                    }
                } else {
                    btnsubmit!!.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
        findViewById<View>(R.id.tvclose).setOnClickListener { finish() }
        btnsubmit!!.setOnClickListener {
            if (matchPaNNumber(edpan!!.text.toString())) {
                Toast.makeText(applicationContext, "Details submitted successfully", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateDialog(id: Int): Dialog? {
        // TODO Auto-generated method stub
        if (id == 999) {
            val datePickerDialog = DatePickerDialog(this,
                    myDateListener, year, month, day)
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            return datePickerDialog
        }
        return null
    }

    private val myDateListener = OnDateSetListener { arg0, arg1, arg2, arg3 -> // TODO Auto-generated method stub
        // arg1 = year
        // arg2 = month
        // arg3 = day
        showDate(arg1, arg2 + 1, arg3)
    }

    private fun showDate(year: Int, month: Int, day: Int) {
        tvdate!!.text = StringBuilder().append(day).append("/")
                .append(month).append("/").append(year)
    }

    private fun matchPaNNumber(s: String): Boolean {
        val pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
        val matcher = pattern.matcher(s)
        return if (matcher.matches()) {
            true
        } else false
    }
}