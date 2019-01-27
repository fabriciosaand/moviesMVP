package br.com.fabricioandrade.filmesmvp.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import br.com.fabricioandrade.filmesmvp.R
import br.com.fabricioandrade.filmesmvp.common.enumeration.MoviesTabEnum
import br.com.fabricioandrade.filmesmvp.model.Movie
import br.com.fabricioandrade.filmesmvp.view.adapter.MoviesAdapter
import br.com.fabricioandrade.filmesmvp.view.adapter.PagerAdapter

class MainActivity : AppCompatActivity(),MoviesAdapter.ItemMovieClickedListener {

    private var viewPager: ViewPager? = null
    private var pagerAdapter : PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_grid)
        supportActionBar?.elevation = 0F;

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.addTab(tabLayout.newTab().setText(MoviesTabEnum.ACAO.title))
        tabLayout.addTab(tabLayout.newTab().setText(MoviesTabEnum.DRAMA.title))
        tabLayout.addTab(tabLayout.newTab().setText(MoviesTabEnum.FANTASIA.title))
        tabLayout.addTab(tabLayout.newTab().setText(MoviesTabEnum.FICCAO.title))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val tab = tabLayout.getTabAt(0)
        tab?.select()

        viewPager = findViewById<ViewPager>(R.id.viewpager)
        pagerAdapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager?.adapter = pagerAdapter
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onClick(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE, movie)
        startActivity(intent)
    }
}
