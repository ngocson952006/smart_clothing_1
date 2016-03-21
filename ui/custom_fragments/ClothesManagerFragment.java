package com.example.ngocsonit.smartclothing.ui.custom_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.ngocsonit.smartclothing.R;

/**
 * Created by ngocsonit on 12/03/2016.
 * ClothesManagerFragment : a fragment having own lifecycle to show all user's items from their collection
 * we divide into three types of fragment(will be extended in later version) : SHIRTS , TROUSERS , SHOES
 */
public class ClothesManagerFragment extends Fragment {
    private static final String TAG = ClothesManagerFragment.class.getSimpleName();
    private static final String FASHION_TYPE = "fashion_type";
    public FashionManagerType managerType;// indicate what type this fragment manage

    private FashionManagerType type; // type of clothes manager

    // UI View Components
    ImageView favoriteItemsImageView;
    GridView gridView;

    /**
     * static method creating fragment
     *
     * @param type : manage type see {@link FashionManagerType} to see types of manage
     * @return new instance of fragment {@link ClothesManagerFragment}
     */
    public static ClothesManagerFragment newInstance(FashionManagerType type, CharSequence title
            , int indicatorColor, int dividerColor) {

        Bundle bundle = new Bundle();
        // put into bundle
        //...
        //...
        bundle.putSerializable(FASHION_TYPE, type);
        ClothesManagerFragment clothesManagerFragment = new ClothesManagerFragment();
        clothesManagerFragment.setArguments(bundle);
        return clothesManagerFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.clothes_manage_layout, container, false); // inflate view properly

        // bind UI view components


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.favoriteItemsImageView = (ImageView) view.findViewById(R.id.favorite_shirt_imageview);
        this.gridView = (GridView) view.findViewById(R.id.all_shirts_gridview);

        // get bundles
        Bundle bundle = this.getArguments();
        this.managerType = (FashionManagerType) bundle.getSerializable(FASHION_TYPE);

        this.initializeComponentsByTypes();
    }

    @Override
    public void onStart() {
        super.onStart();

//        // for example , show sequences of default fashion images
//        //ImageView favoriteItemsImageView = findView
//        this.favoriteItemsImageView.setBackgroundResource(R.drawable.default_animation);
//        AnimationDrawable favoriteItemsAnimation = (AnimationDrawable) this.favoriteItemsImageView.getBackground();
//        // start now
//        favoriteItemsAnimation.start();


    }

    /**
     * each type of fashion has different data from database that user saved
     * via type {@link FashionManagerType} : we create data from view components
     */
    private void initializeComponentsByTypes() {
        // example , create simple list view
        String[] samleStrings = new String[]{"1", "2", "3"};
        this.gridView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, samleStrings));
    }
}
