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

            // All values to be exported to search activity using Intent
            val arts = if(binding.artsCB.isChecked) "arts" else ""
            val business = if(binding.businessCB.isChecked) "business" else ""
            val entrepreneurs = if(binding.entrepreneursCB.isChecked) "entrepreneurs" else ""
            val politics = if(binding.politicsCB.isChecked) "politics" else ""
            val sports = if(binding.sportsCB.isChecked) "sports" else ""
            val travel = if(binding.travelCB.isChecked) "travel" else ""

            val searchQuery = binding.textInputSearchQuery.text.toString()

            Intent(baseContext, SearchedArticlesResultActivity::class.java).also{

                // NEWS DESK VALUES
                it.putExtra("EXTRA_ARTS", arts)
                it.putExtra("BUSINESS", business)
                it.putExtra("EXTRA_ENTREPRENEURS", entrepreneurs)
                it.putExtra("EXTRA_POLITICS", politics)
                it.putExtra("EXTRA_SPORTS", sports)
                it.putExtra("EXTRA_TRAVEL", travel)

                // Search query
                it.putExtra("EXTRA_QUERY", searchQuery)

                // Date values
                it.putExtra("EXTRA_DATE_START", startDateConvertedString)
                it.putExtra("EXTRA_DATE_END", endDateConvertedString)

                startActivity(it)

            }
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
}