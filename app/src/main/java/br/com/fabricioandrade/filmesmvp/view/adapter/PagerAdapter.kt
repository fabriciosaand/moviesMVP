package br.com.fabricioandrade.filmesmvp.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.fabricioandrade.filmesmvp.common.enumeration.MoviesTabEnum
import br.com.fabricioandrade.filmesmvp.view.fragment.MoviesFragment

class PagerAdapter(fm: FragmentManager, private var numberOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return MoviesFragment.newInstance(MoviesTabEnum.ACAO.id,"")
            1 -> return MoviesFragment.newInstance(MoviesTabEnum.DRAMA.id,"")
            2 -> return MoviesFragment.newInstance(MoviesTabEnum.FANTASIA.id,"")
            3 -> return MoviesFragment.newInstance(MoviesTabEnum.FICCAO.id,"")
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 4
    }
}
