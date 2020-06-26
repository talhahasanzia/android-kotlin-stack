package com.example.kotlinstack.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kotlinstack.R
import com.example.kotlinstack.home.vm.DefaultHomeViewModel
import com.example.kotlinstack.home.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // Shared viewModel
    private val viewModel: HomeViewModel by activityViewModels<DefaultHomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherUpdate.observe(viewLifecycleOwner,
            Observer {
                tvMessage.text = it
            })

        viewModel.weatherError.observe(viewLifecycleOwner,
            Observer {
                tvMessage.text = it
            }
        )
    }
}