package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FragmentGameFinishedBinding


class GameFinishedFragment : Fragment() {

   // private lateinit var gameResult: GameResult
    private val args by navArgs<GameFinishedFragmentArgs>()
    private var _binding : FragmentGameFinishedBinding? = null
    private val binding : FragmentGameFinishedBinding
        get() = _binding?:throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return FragmentGameFinishedBinding.inflate(inflater, container, false).apply {
            _binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindViews()
    }

    private fun bindViews() {
        binding.gameResult = args.gameResult
    }

    private fun setupClickListeners(){
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame(){
        findNavController().popBackStack()
    }

}