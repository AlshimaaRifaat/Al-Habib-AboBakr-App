package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.elsheikh.NetworkConnection;
import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.adapter.PicturesAdapter;
import com.example.shosho.elsheikh.adapter.PoetryAdapter;
import com.example.shosho.elsheikh.model.PictureData;
import com.example.shosho.elsheikh.presenter.PicturePresenter;
import com.example.shosho.elsheikh.view.PictureView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoetryFragment extends Fragment implements PictureView,SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView;
    PicturePresenter picturePresenter;
    PoetryAdapter poetryAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    NetworkConnection networkConnection;
    public PoetryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_poetry, container, false );
        recyclerView=view.findViewById( R.id.poetry_recycler_view );
        networkConnection=new NetworkConnection( getContext() );
        mSwipeRefreshLayout=view.findViewById( R.id.poetry_swip_refresh );
        picturePresenter=new PicturePresenter( getContext(),this );
        picturePresenter.getPicturesResult( "ar","peotry" );
        swipRefresh();
        return view;
    }

    private void swipRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources( android.R.color.holo_orange_dark  );
        mSwipeRefreshLayout.setEnabled( true );
        mSwipeRefreshLayout.setOnRefreshListener( this );
        mSwipeRefreshLayout.post( new Runnable() {
            @Override
            public void run() {
                if (networkConnection.isNetworkAvailable( getContext() ))
                {
                    mSwipeRefreshLayout.setRefreshing( true );
                    picturePresenter.getPicturesResult( "ar","peotry" );

                }
            }
        } );
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing( true );
        picturePresenter.getPicturesResult( "ar","peotry" );
    }

    @Override
    public void showPicturesData(List<PictureData> pictureData) {
         poetryAdapter=new PoetryAdapter( getContext(),pictureData );
        mSwipeRefreshLayout.setRefreshing( true );
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(), 2 ) );
        recyclerView.setAdapter( poetryAdapter );
        mSwipeRefreshLayout.setRefreshing( false );

    }

    @Override
    public void error() {
        mSwipeRefreshLayout.setRefreshing( false );

    }
}
