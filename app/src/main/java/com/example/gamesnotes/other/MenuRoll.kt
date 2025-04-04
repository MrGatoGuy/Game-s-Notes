package com.example.gamesnotes.other

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

class MenuRoll(private val opM: View) : DrawerLayout.DrawerListener {
    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        opM.animate().apply {
            duration = 500
            rotation(200f * slideOffset)
        }.start()
    }
    override fun onDrawerStateChanged(newState: Int) {}
    override fun onDrawerOpened(drawerView: View) {}
    override fun onDrawerClosed(drawerView: View) {}
}