package com.mortatval.my_quiz

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.quiz.Question
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import com.mortatval.my_quiz.databinding.FragmentQuestionsBinding

class FragmentQuestions : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quiz = QuizStorage.getQuiz(QuizStorage.Locale.Ru)
        addQuestions(quiz.questions)
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentQuestions_to_fragmentStart)
        }
        binding.buttonSend.setOnClickListener {
            val chooseAnswer = mutableListOf< String>()
            for (i in 0 until binding.quizContainer.childCount) {
                val view = binding.quizContainer.getChildAt(i)
                if (view is RadioGroup) {
                    val radioButtonId = view.checkedRadioButtonId
                    if (radioButtonId != -1) {
                        val radioButton = view.findViewById<RadioButton>(radioButtonId)
                        chooseAnswer.add(radioButton.text.toString())
                    } else {
                        Toast.makeText(context, "вы не выбрали ответ", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
            }
            val chooseAnswerToString = chooseAnswer.joinToString { "," }
            val bundle = Bundle().apply {
                putString("quizResult", chooseAnswerToString)
            }
            findNavController().navigate(R.id.fragmentResults, bundle)
        }
    }

    private fun addQuestions(questions: List<Question>) {
        questions.forEach { question ->

            val textViewQuestion = TextView(context).apply {
                text = question.question
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                setPadding(10, 16, 10, 16)
            }
            binding.quizContainer.addView(textViewQuestion)

            val answersGroup = RadioGroup(context).also { group ->
                question.answers.forEachIndexed { index, answer ->
                    val radioButton = RadioButton(context).apply {
                        text = answer
                        id = View.generateViewId()
                    }
                    group.addView(radioButton)
                }
            }
            binding.quizContainer.addView(answersGroup)
        }
    }



}