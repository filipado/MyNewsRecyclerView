package com.example.mynewsrecyclerview.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynewsrecyclerview.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        artsClick(); businessClick();entrepreneursClick(); politicsClick(); sportsClick(); travelClick()

        binding.button.setOnClickListener{
            val searchArticlesResultActivity = Intent(baseContext, SearchedArticlesResultsActivity::class.java)
            startActivity(searchArticlesResultActivity)
        }


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