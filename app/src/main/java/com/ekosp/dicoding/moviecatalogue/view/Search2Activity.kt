package com.ekosp.dicoding.moviecatalogue.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ekosp.dicoding.moviecatalogue.R
import com.ekosp.dicoding.moviecatalogue.api.ApiClient
import com.ekosp.dicoding.moviecatalogue.api.MovieService
import com.ekosp.dicoding.moviecatalogue.database.entity.NewMovie
import kotlinx.android.synthetic.main.app_bar_search.*

/**
 * Created by Eko.Purnomo on 2019-07-07.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

class Search2Activity : AppCompatActivity() {

    var movies: MutableList<NewMovie> = mutableListOf()
    val movieService: MovieService = ApiClient.getClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        img_back.setOnClickListener {
            finish()
        }

        searchView.onActionViewExpanded()
        searchView.queryHint = getString(R.string.search_hint)
    }

}