package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.view.PicturesDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicturesFragment extends Fragment  {


    public PicturesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_pictures, container, false );
    }


}
