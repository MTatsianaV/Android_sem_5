package com.mortatval.my_quiz
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mortatval.my_quiz.databinding.FragmentStartBinding


class FragmentStart : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonMainScreen.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentStart_to_fragmentQuestions)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}