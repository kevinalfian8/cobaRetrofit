package com.bones.cobaretrofit.listener;

import android.view.View;

/**
 * Created by lenovo ip on 20/06/2017.
 */


public interface ClickInterface{
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
