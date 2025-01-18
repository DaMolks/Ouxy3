package com.damolks.ouxy3.debug.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.damolks.ouxy3.debug.ui.ErrorListFragment
import com.damolks.ouxy3.debug.ui.ModuleListFragment

class ModulePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ModuleListFragment()
            1 -> ErrorListFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}