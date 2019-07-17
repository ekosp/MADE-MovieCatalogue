package com.ekosp.dicoding.moviecatalogue.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.ekosp.dicoding.moviecatalogue.R
import com.ekosp.dicoding.moviecatalogue.api.ApiClient
import com.ekosp.dicoding.moviecatalogue.api.MovieService
import com.ekosp.dicoding.moviecatalogue.helper.GlobalVar
import com.ekosp.dicoding.moviecatalogue.model.MovieListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.KeyEvent.KEYCODE_HOME
import android.view.KeyEvent
import com.ekosp.dicoding.moviecatalogue.database.entity.Movie


/**
 * Created by Eko.Purnomo on 2019-07-07.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

class SearchActivity : AppCompatActivity() {
    //    lateinit var apiKey : String
    var movies: MutableList<Movie> = mutableListOf()
    //    var adapter = MovieAdapter(movies)
    val movieService: MovieService = ApiClient.getClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
//        setActionBar(toolbar)
//        setSupportActionBar(toolbar)

//        val actionBar = supportActionBar
//        val searchView = SearchView(this)
//        actionBar!!.customView = searchView
//        actionBar.displayOptions = ActionBar.DISPLAY_HOME_AS_UP
//        searchView.setQuery("test", true)
//        searchView.isFocusable = true
//        searchView.isIconified = false
//        searchView.requestFocusFromTouch()

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KEYCODE_HOME) {
            Toast.makeText(this, "Click Home ", Toast.LENGTH_LONG).show()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.searchMenu)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchItem.expandActionView()
//        searchView.requestFocus()
        searchView.isIconified = false

        searchQuery(searchView)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(
//                R.menu.menu_search,
//                menu
//        )
//
//        val searchView = menu?.findItem(R.id.searchMenu)?.actionView as SearchView
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.setOnSearchClickListener {
////            LoadQuery("null")
//        }
//        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (query != null) {
////                    if (query.isNotEmpty()) LoadQuery("%$query%")
////                    if (query.isEmpty()) LoadQuery("null")
//                }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if (newText != null) {
////                    if (newText.length > 1) LoadQuery("%$newText%")
////                    if (newText.isEmpty()) LoadQuery("null")
//                }
//                return false
//            }
//        })
//        searchView.setOnCloseListener {
////            LoadQuery("%")
//            false
//        }
//
//        return super.onCreateOptionsMenu(menu)
//    }

//    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
//
//        val itemId = item.itemId
//        when (itemId) {
//            android.R.id.home -> toggle()
//        }// Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
//
//        return true
//    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> ""
            android.R.id.home -> { onBackPressed();  return true; }


        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchQuery(searchView: SearchView) {
        var options: MutableMap<String, String> = mutableMapOf()
        options.put("api_key", GlobalVar.moviedb_apikey)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    return false
                }

                options.put("query", query!!.toString())
                val call: Call<MovieListResponse> = movieService.searchMovies(options)
//                getMovieData(call)
                val movieResponse = call.execute()


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    fun getPopularMovies(apiKey: String) {
//        val call: Call<MovieListResponse> = movieService.getPopularMovies(apiKey)
//        getMovieData(call)
    }

    fun getMovieData(call: Call<MovieListResponse>) {
        call.enqueue(object : Callback<MovieListResponse> {
            override fun onFailure(call: Call<MovieListResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext, "${t.toString()}", Toast.LENGTH_SHORT).show()
                Log.d("Movie Erorr", "${t?.message}")
            }

            override fun onResponse(call: Call<MovieListResponse>?, response: Response<MovieListResponse>?) {
                if (response?.body() != null) {
//                    movies = response.body()!!.movies.toMutableList()
                    movies = response.body()!!.results.toMutableList()
//                    adapter = MovieAdapter(movies)
//                    rvMovie.adapter = adapter
                }
                Log.d("Movie", "${response?.body()}")
            }

        })
    }
}