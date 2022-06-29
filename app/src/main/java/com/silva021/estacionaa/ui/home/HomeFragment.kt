package com.silva021.estacionaa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.silva021.estacionaa.databinding.FragmentHomeBinding
import com.silva021.estacionaa.ui.home.adapter.MyViewPagerAdapter

class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewPager.adapter = MyViewPagerAdapter(childFragmentManager)
            tabLayout.setupWithViewPager(viewPager)
        }
    }
}