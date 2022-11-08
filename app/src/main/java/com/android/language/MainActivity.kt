package com.android.language

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.language.LocaleHelper.setLocale
import com.android.language.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        onClick()

        lSpinner()
    }

    private fun lSpinner() {
        val languages = resources.getStringArray(R.array.Languages)

        // access the spinner
        val spinner = binding.spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, languages
        )
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                var language: String? = null
                when (position){
                    0 -> language = "en"
                    1 -> language = "ta"
                    2 -> language = "hi"
                }
                context = setLocale(this@MainActivity, language!!)
                binding.textView.text = context!!.resources.getString(R.string.language)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }

    private fun onClick() {
        binding.btnEnglish.setOnClickListener {
            context = setLocale(this@MainActivity, "en")
            binding.textView.text = context!!.resources.getString(R.string.language)
        }

        binding.btnHindi.setOnClickListener {
            context = setLocale(this@MainActivity, "hi")
            binding.textView.text = context!!.resources.getString(R.string.language)
        }

        binding.btnTamil.setOnClickListener {
            context = setLocale(this@MainActivity, "ta")
            binding.textView.text = context!!.resources.getString(R.string.language)
        }
    }
}