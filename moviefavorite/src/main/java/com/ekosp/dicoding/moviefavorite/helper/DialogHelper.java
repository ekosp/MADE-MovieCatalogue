package com.ekosp.dicoding.moviefavorite.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.ekosp.dicoding.moviefavorite.R;
import com.rey.material.app.Dialog;

/**
 * Created by Eko S.P on 30/10/2018.
 * email : ekosetyopurnomo@gmail.com
 * about me : http://ekosp.com
 */

public class DialogHelper {

    private final Context ctx;
    private Dialog loadingDialog;

    public DialogHelper(Context context) {
        this.ctx = context;
    }

    @SuppressLint("InflateParams")
    public void initProgressDialog() {
        View loadingView = LayoutInflater.from(ctx).inflate(R.layout.dialog_progress, null);
        loadingDialog = new Dialog(ctx);
        loadingDialog.backgroundColor(Color.parseColor("#ffffff"));
        loadingDialog.elevation(0);
        loadingDialog.setCancelable(false);
        loadingDialog.setContentView(loadingView);
    }

    public void showProgressDialog() {
        if (loadingDialog != null) {
            if (!loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        }
    }

    public void dismissProgressDialog() {
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        }
    }

}
