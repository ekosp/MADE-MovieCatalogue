package com.ekosp.dicoding.moviecatalogue.view.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ekosp.dicoding.moviecatalogue.helper.DialogHelper;

/**
 * Created by Eko S.P on 30/10/2018.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

public class BaseFragment extends Fragment {

    private DialogHelper dialogHelper;
//    private AppComponent appComponent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogHelper = new DialogHelper(getActivity());
        dialogHelper.initProgressDialog();
//        appComponent = DaggerAppComponent.builder().appModule(new AppModule(getContext())).utilsModule(new UtilsModule()).build();
    }

    private DialogHelper getDialogHelper() {
        if (dialogHelper == null) {
            return new DialogHelper(getActivity());
        }
        return dialogHelper;
    }

    protected void dismissLoading() {
        getDialogHelper().dismissProgressDialog();
    }

    protected void showLoading() {
        getDialogHelper().showProgressDialog();
    }

//    protected AppComponent getAppComponent() {
//        return appComponent;
//    }


}
