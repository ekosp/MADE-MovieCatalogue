package com.ekosp.dicoding.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityBck extends AppCompatActivity {

//    private String[] dataTitle;
//    private String[] dataOverview;
//    private String[] dataReleaseDate;
//    private int[] dataScore;
//    private TypedArray dataCover;
//    private MovieAdapter adapter;
//    private ArrayList<Movie> movies;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        adapter = new MovieAdapter(this);
//        ListView listView = findViewById(R.id.lv_list);
//
//        prepare();
//        addItem();
//
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                openMovieDetail(movies.get(i));
//            }
//        });
//    }
//
//    private void openMovieDetail(Movie movie) {
//        Intent intent = new Intent(this, MovieDetailActivity.class);
//        intent.putExtra("movie", movie);
//        startActivity(intent);
//    }
//
//    private void addItem() {
//        movies = new ArrayList<>();
//        for (int i = 0; i < dataTitle.length; i++) {
//            Movie movie = new Movie();
//            movie.setCover(dataCover.getResourceId(i, -1));
//            movie.setTitle(dataTitle[i]);
//            movie.setOverview(dataOverview[i]);
//            movie.setScore(dataScore[i]);
//            movie.setReleaseDate(dataReleaseDate[i]);
//            movies.add(movie);
//        }
//        adapter.setMoview(movies);
//    }
//
//    private void prepare() {
//        dataTitle = getResources().getStringArray(R.array.data_name);
//        dataReleaseDate = getResources().getStringArray(R.array.data_release_date);
//        dataOverview = getResources().getStringArray(R.array.data_description);
//        dataScore = getResources().getIntArray(R.array.movie_score);
//        dataCover = getResources().obtainTypedArray(R.array.data_photo);
//    }
}
