package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.elsheikh.NetworkConnection;
import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.adapter.PicturesAdapter;
import com.example.shosho.elsheikh.model.BookDetails;
import com.example.shosho.elsheikh.model.PictureData;
import com.example.shosho.elsheikh.presenter.PicturePresenter;
import com.example.shosho.elsheikh.view.DetailsBookView;
import com.example.shosho.elsheikh.view.PictureView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicturesFragment extends Fragment implements PictureView,SwipeRefreshLayout.OnRefreshListener {
RecyclerView recyclerView;
PicturePresenter picturePresenter;
PicturesAdapter picturesAdapter;
private SwipeRefreshLayout mSwipeRefreshLayout;
NetworkConnection networkConnection;

    int position;
    List<PictureData> banne=new ArrayList<>();
    Boolean end;

    public PicturesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_pictures, container, false );
        recyclerView=view.findViewById( R.id.pictures_recycler_view );
        networkConnection=new NetworkConnection( getContext() );
        mSwipeRefreshLayout=view.findViewById( R.id.pictures_swip_refresh );
        picturePresenter=new PicturePresenter( getContext(),this );
        picturePresenter.getPicturesResult( "ar","gallary" );
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
                    picturePresenter.getPicturesResult( "ar","gallary" );

                }
            }
        } );
    }


    @Override
    public void showPicturesData(List<PictureData> pictureData) {
        banne=pictureData;
        picturesAdapter=new PicturesAdapter( getContext(),pictureData );
        mSwipeRefreshLayout.setRefreshing( true );
      LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
      recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter( picturesAdapter );
  if(banne.size()>1) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate( new AutoScrollTask(), 1000, 2000 );
   }
   mSwipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void error() {
        mSwipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing( true );
        picturePresenter.getPicturesResult( "ar","gallary" );
    }

    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            if(position == banne.size() -1){
                end = true;
            } else if (position == 0) {
                end = false;
            }
            if(!end){
                position++;
            } else {
                position--;
            }
            recyclerView.smoothScrollToPosition(position);
        }
    }


}
