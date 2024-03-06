package com.mortatval.my_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mortatval.my_quiz.databinding.FragmentResultsBinding
import androidx.appcompat.app.AppCompatActivity

private const val ARG_QUIZ_RESULT = "quizResults"
class FragmentResults : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!
    private var resultsOfQuiz: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        arguments?.let {
            resultsOfQuiz = it.getString(ARG_QUIZ_RESULT)
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resultText.text = resultsOfQuiz

        binding.startAgainButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentResults_to_fragmentQuestions)
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_fragmentResults_to_fragmentStart)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance(param1: String, param2: String) =
            FragmentResults().apply {
                arguments = Bundle().apply {
                    putString(ARG_QUIZ_RESULT, param1)
                }
            }
    }
}