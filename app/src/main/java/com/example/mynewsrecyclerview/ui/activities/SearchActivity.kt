package com.example.mynewsrecyclerview.ui.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynewsrecyclerview.databinding.ActivitySearchBinding
import java.text.SimpleDateFormat
import java.util.*

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    private var startDateConvertedString: String? = null
    private var endDateConvertedString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        artsClick(); businessClick();entrepreneursClick(); politicsClick(); sportsClick(); travelClick()

        // Set up START DATE with Date Dialog
        binding.datePickerStart.setOnClickListener{
            showDatePickerDialog{
                updateStartDate(it)
            }
        }

        // Set up END date with date dialog
        binding.datePickerEnd.setOnClickListener{
            showDatePickerDialog{
                updateEndDate(it)
            }
        }

        // Search Button INTENT click takes us to show Search results
        binding.button.setOnClickListener{
            val searchArticlesResultActivity = Intent(baseContext, SearchedArticlesResultActivity::class.java)
            startActivity(searchArticlesResultActivity)
        }


    }

    private fun showDatePickerDialog(onDateSetAction: (Calendar) -> Unit) {
        val calendar = Calendar.getInstance()

        val listener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, monthOfYear)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)

            }
            onDateSetAction(calendar)
        }

        DatePickerDialog(
            this, listener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }


    private fun updateStartDate(calendar: Calendar) {
        val myFormat = "MM-dd-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)

        binding.datePickerStart.setText(sdf.format(calendar.time))
        startDateConvertedString = SimpleDateFormat("yyyyMMdd", Locale.UK).format(calendar.time)

    }

    private fun updateEndDate(calendar: Calendar) {
        val myFormat = "MM-dd-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)

        binding.datePickerEnd.setText(sdf.format(calendar.time))
        endDateConvertedString = SimpleDateFormat("yyyyMMdd", Locale.UK).format(calendar.time)

    }





    private fun artsClick() {
        val artsText = binding.artsCB.text.toString()

        binding.artsCB.setOnClickListener {
            if (binding.artsCB.isChecked) {
                binding.textView3.text = artsText
            } else {
                binding.textView3.text = null
            }
        }
    }

    private fun businessClick() {
        val businessText = binding.businessCB.text.toString()

        binding.businessCB.setOnClickListener {
            if (binding.businessCB.isChecked) {
                binding.textView3.text = businessText
            } else {
                binding.textView3.text = null
            }
        }
    }

    private fun entrepreneursClick() {
        val entrepreneursText = binding.entrepreneursCB.text.toString()

        binding.entrepreneursCB.setOnClickListener {
            if (binding.entrepreneursCB.isChecked) {
                binding.textView3.text = entrepreneursText
            } else {
                binding.textView3.text = null
            }
        }
    }

    private fun politicsClick() {
        val politicsText = binding.politicsCB.text.toString()

        binding.politicsCB.setOnClickListener {
            if (binding.politicsCB.isChecked) {
                binding.textView3.text = politicsText
            } else {
                binding.textView3.text = null
            }
        }
    }

    private fun sportsClick() {
        val sportsText = binding.sportsCB.text.toString()

        binding.sportsCB.setOnClickListener {
            if (binding.sportsCB.isChecked) {
                binding.textView3.text = sportsText
            } else {
                binding.textView3.text = null
            }
        }
    }

    private fun travelClick() {
        val travelText = binding.travelCB.text.toString()

        binding.travelCB.setOnClickListener {
            if (binding.travelCB.isChecked) {
                binding.textView3.text = travelText
            } else {
                binding.textView3.text = null
            }
        }
    }
}