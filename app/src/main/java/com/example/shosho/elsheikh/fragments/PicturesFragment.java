package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.adapter.PicturesAdapter;
import com.example.shosho.elsheikh.model.PictureData;
import com.example.shosho.elsheikh.presenter.PicturePresenter;
import com.example.shosho.elsheikh.view.PictureView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicturesFragment extends Fragment implements PictureView {
RecyclerView recyclerView;
PicturePresenter picturePresenter;
PicturesAdapter picturesAdapter;

    public PicturesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_pictures, container, false );
        recyclerView=view.findViewById( R.id.pictures_recycler_view );
        picturePresenter=new PicturePresenter( getContext(),this );
        picturePresenter.getBooksResult( "ar","gallary" );
        return view;
    }


    @Override
    public void showPicturesData(List<PictureData> pictureData) {
        picturesAdapter=new PicturesAdapter( getContext(),pictureData );
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(),2 ) );
        recyclerView.setAdapter( picturesAdapter );

    }

    @Override
    public void error() {

    }
}
