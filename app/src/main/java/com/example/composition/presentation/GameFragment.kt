package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult

class GameFragment : Fragment() {

    //private lateinit var level: Level
    private val args by navArgs<GameFragmentArgs>()
    private val viewModelFactory by lazy {
        GameViewModelFactory(
            //level,
            args.level,
            requireActivity().application
        )
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

    private var _binding:FragmentGameBinding? = null

    private val binding:FragmentGameBinding
        get() = _binding?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return FragmentGameBinding.inflate(inflater, container, false).apply {
            _binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
        //setClickListenersToOptions()
    }

//    private fun setClickListenersToOptions(){
//        for (tvOption in tvOptions){
//            tvOption.setOnClickListener {
//                viewModel.chooseAnswer(tvOption.text.toString().toInt())
//            }
//        }
//    }

    private fun observeViewModel(){
//        viewModel.question.observe(viewLifecycleOwner){
//           // binding.tvSum.text = it.sum.toString()
//           // binding.tvLeftNumber.text = it.visibleNumber.toString()
//            for (i in 0 until tvOptions.size){
//                tvOptions[i].text = it.options[i].toString()
//            }
//        }

//        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner){
//            binding.progressBar.setProgress(it, true)
//        }

//        viewModel.enoughCountOfrightAnswers.observe(viewLifecycleOwner){
//            val color = getColorByState(it)
//            binding.tvAnswersProgress.setTextColor(color)
//        }

//        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner){
//            val color = getColorByState(it)
//            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
//        }


//        viewModel.minPercent.observe(viewLifecycleOwner){
//            binding.progressBar.secondaryProgress = it
//        }

        viewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishedFragment(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment(gameResult: GameResult){
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult))
    }

}