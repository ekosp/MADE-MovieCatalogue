package com.ekosp.dicoding.moviefavorite.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ekosp.dicoding.moviefavorite.helper.DialogHelper;

/**
 * Created by Eko S.P on 30/06/2019.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

public class BaseFragment extends Fragment {

    private DialogHelper dialogHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogHelper = new DialogHelper(getActivity());
        dialogHelper.initProgressDialog();
    }

    protected DialogHelper getDialogHelper() {
        if (dialogHelper == null) {
            return new DialogHelper(getActivity());
        }
        return dialogHelper;
    }

}
