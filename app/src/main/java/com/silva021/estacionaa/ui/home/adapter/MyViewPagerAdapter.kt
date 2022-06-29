package com.silva021.estacionaa.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.silva021.estacionaa.ui.entrance.EntranceFragment
import com.silva021.estacionaa.ui.exit.home.ExitFragment

class MyViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> EntranceFragment()
            else -> ExitFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }

    override fun getCount(): Int {
        return titleList.size
    }

    companion object {
        val titleList = listOf("Entrada", "Saída")
    }
}