package com.example.ngocsonit.smartclothing.ui.custom_fragments;

import android.view.View;

/**
 * Created by ngocsonit on 12/03/2016.
 */
public interface OnCollectionFragmentInit {
    /**
     * with paricular index, fragment with same UI but has different data inside
     *
     * @param index index of page
     * @param view  : view
     */
    public void initializeComponents(int index, View view);
}
