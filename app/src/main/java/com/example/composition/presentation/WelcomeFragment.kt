package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding?:throw RuntimeException("FragmentWelcomeBinding==null")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOk.setOnClickListener {
            launchChooseLevelFragment()
        }
    }

    private fun launchChooseLevelFragment(){
        //old navigation
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, ChooseLevelFragment.newInstance())
//            .addToBackStack(ChooseLevelFragment.NAME)
//            .commit()
        findNavController().navigate(R.id.action_welcomeFragment_to_chooseLevelFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}