package com.example.kotlinstack.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kotlinstack.R
import com.example.kotlinstack.databinding.FragmentMainBindingImpl
import com.example.kotlinstack.home.vm.DefaultHomeViewModel
import com.example.kotlinstack.home.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // Shared viewModel
    private val viewModel: HomeViewModel by activityViewModels<DefaultHomeViewModel>()

    private lateinit var binding: FragmentMainBindingImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        binding.homeViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}