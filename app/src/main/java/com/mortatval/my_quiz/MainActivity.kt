package com.mortatval.my_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.findNavController
import com.mortatval.my_quiz.databinding.FragmentStartBinding
import com.mortatval.my_quiz.databinding.ActivityMainBinding
import com.mortatval.my_quiz.FragmentQuestions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
