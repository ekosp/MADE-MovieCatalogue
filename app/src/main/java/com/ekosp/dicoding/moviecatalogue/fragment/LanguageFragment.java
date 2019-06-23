package com.ekosp.dicoding.moviecatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.ekosp.dicoding.moviecatalogue.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eko.Purnomo on 2019-06-23.
 * about me visit https://ekosp.com
 * or contact me at ekosetyopurnomo@gmail.com
 */

public class LanguageFragment extends Fragment {

    @BindView(R.id.radioGroupNb)
    RadioGroup radioGroupNb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_language, container, false);
        ButterKnife.bind(this, view);

        addListenerOnButtonJawab();

        return  view;

    }

    public void addListenerOnButtonJawab() {

//        btnJawab.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                //pilih radio button yang ada di radio button group
//                int selectedId = radioGroupNb.getCheckedRadioButtonId();
//
//                // mencari radio button
//                radioButtonNb = (RadioButton) findViewById(selectedId);
//                //menampilkan pesan teks / toast
//                Toast.makeText(getBaseContext(),
//                        "Kamu Memilih Notebook " + radioButtonNb.getText(),
//                        Toast.LENGTH_SHORT).show();
//
//            }
//
//        });

    }

}