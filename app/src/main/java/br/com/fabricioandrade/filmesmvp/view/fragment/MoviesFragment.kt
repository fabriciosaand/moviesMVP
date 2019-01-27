package br.com.fabricioandrade.filmesmvp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import br.com.fabricioandrade.filmesmvp.R
import br.com.fabricioandrade.filmesmvp.common.enumeration.MoviesTabEnum
import br.com.fabricioandrade.filmesmvp.contract.MoviesContract
import br.com.fabricioandrade.filmesmvp.model.Movie
import br.com.fabricioandrade.filmesmvp.presenter.MoviesPresenter
import br.com.fabricioandrade.filmesmvp.view.activity.MovieDetailActivity
import br.com.fabricioandrade.filmesmvp.view.adapter.MoviesAdapter


class MoviesFragment() : Fragment(), MoviesAdapter.ItemMovieClickedListener,MoviesContract.MoviesView{

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var textViewEmpty: TextView
    private lateinit var textViewError: TextView
    private lateinit var progressBarLoading: ProgressBar
    private lateinit var presenter: MoviesPresenter
    private var genre: String = ""
    private var query: String = ""

    companion object {

        const val GENRE = "genre"
        const val QUERY = "query"

        @JvmStatic fun newInstance(genre: String, query: String) : MoviesFragment{
            val args = Bundle()
            args.putString(GENRE, genre)
            args.putString(QUERY, query)
            val fragment = MoviesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun getBundle(){
        val bundle = this.arguments
        if (bundle != null) {
            genre = bundle.getString(GENRE)
            query = bundle.getString(QUERY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        getBundle()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        componentInitialization(view)

        adapterConfiguration()

        presenter = MoviesPresenter(this)
        when {
            query.isBlank() -> presenter.getMoviesByGenre(genre)
            else -> presenter.getMovies(query)
        }
    }

    private fun componentInitialization(view: View){
        mRecyclerView = view?.findViewById(R.id.recycler_view)
        textViewEmpty = view?.findViewById(R.id.text_view_empty)
        textViewError = view?.findViewById(R.id.text_view_error)
        progressBarLoading = view?.findViewById(R.id.progress_bar_loading)
    }

    private fun adapterConfiguration(){
        moviesAdapter = MoviesAdapter(this)
        mRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        mRecyclerView.adapter = moviesAdapter
        mRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onClick(movie: Movie) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE, movie)
        startActivity(intent)
    }

    override fun showMovies(movies: List<Movie>) {
        moviesAdapter.setData(movies)
        textViewError.visibility = View.INVISIBLE
        textViewEmpty.visibility = View.INVISIBLE
        progressBarLoading.visibility = View.INVISIBLE
        mRecyclerView.visibility = View.VISIBLE
    }

    override fun showError() {
        textViewError.text = getString(R.string.error)
        textViewError.visibility = View.VISIBLE
        textViewEmpty.visibility = View.INVISIBLE
        progressBarLoading.visibility = View.INVISIBLE
        mRecyclerView.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        val inflater = activity?.menuInflater
        inflater?.inflate(R.menu.options_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as (android.support.v7.widget.SearchView)
        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.getMovies(query);
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                presenter.getMoviesByGenre(MoviesTabEnum.ACAO.id)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progressBarLoading.visibility = View.VISIBLE
    }
}