package com.ekosp.dicoding.moviefavorite.helper;

import android.net.Uri;

/**
 * Created by Eko.Purnomo on 2019-06-25.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class GlobalVar {

    public static final String baseUrl_image98 = "https://image.tmdb.org/t/p/w185/";
    public static final String baseUrl_image500 = "https://image.tmdb.org/t/p/w500/";

    private static final String TABEL_MOVIE = "tbl_movie";
    private static final String TABEL_TVSHOW = "tbl_tvshow";

    public static final String LOADER_ID = "loader_id";

    public static final int MOVIE_LOADER = 21;
    public static final int TVSHOW_LOADER = 22;



    /**
     * The authority of this content provider.
     */
    private static final String AUTHORITY = "com.ekosp.dicoding.moviecatalogue.feature.provider.ItemContentProvider";


    /**
     * The URI for the Movie table.
     */
    public static final Uri URI_MOVIE = Uri.parse(
            "content://" + AUTHORITY + "/" + GlobalVar.TABEL_MOVIE);

    /**
     * The URI for the Tv Show table.
     */
    public static final Uri URI_TVSHOW = Uri.parse(
            "content://" + AUTHORITY + "/" + GlobalVar.TABEL_TVSHOW);

}
