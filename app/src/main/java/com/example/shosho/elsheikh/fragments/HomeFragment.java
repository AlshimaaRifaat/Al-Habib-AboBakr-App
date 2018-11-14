package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.shosho.elsheikh.NetworkConnection;
import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.adapter.HomeAdapter;
import com.example.shosho.elsheikh.adapter.SliderAdapter;
import com.example.shosho.elsheikh.model.PictureData;
import com.example.shosho.elsheikh.presenter.PicturePresenter;
import com.example.shosho.elsheikh.view.ItemsView;
import com.example.shosho.elsheikh.view.PictureView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ItemsView,PictureView,SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    HomeAdapter recyclerAdapter;

    RecyclerView recyclerView_slider;
    PicturePresenter picturePresenter;
    SliderAdapter sliderAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    NetworkConnection networkConnection;


    int position;
    boolean end;
    List<PictureData> banner=new ArrayList<>(  );
    View view;
    RelativeLayout relativeLayout;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate( R.layout.fragment_home, container, false );
         swipeRefreshLayout=view.findViewById( R.id.home_swip_refresh_slider );
         relativeLayout=view.findViewById( R.id.Relative );
        networkConnection=new NetworkConnection( getContext() );
        Recycle();
        Home();
        Slider();
        SwipRefresh();


        return view;
    }
    public void SwipRefresh(){
        swipeRefreshLayout.setColorSchemeResources( android.R.color.holo_orange_dark );
        swipeRefreshLayout.setEnabled( true );
        swipeRefreshLayout.setOnRefreshListener( this );
        swipeRefreshLayout.post( new Runnable() {
            @Override
            public void run() {
                if(networkConnection.isNetworkAvailable( getContext() ));
                swipeRefreshLayout.setRefreshing( true );
                picturePresenter.getPicturesResult( "ar","slider" );

            }
        } );
    }
    public void Slider(){
        picturePresenter=new PicturePresenter( getContext(),this );
        picturePresenter.getPicturesResult( "ar","slider" );

    }
    public void Home(){
        //.....
        int []images={R.drawable.colleges,R.drawable.trips,R.drawable.books,
                R.drawable.poetry,R.drawable.translation,R.drawable.pictures,
                R.drawable.talking_pic,R.drawable.graphics};
        String []names={"الكليات والمعاهد","الرحلات الدعويه", "كتب مؤلفات",
                "قصائد","ترجمه","صور",
                "صور ناطقه","رسومات"};

        recyclerAdapter=new HomeAdapter( getContext(),names,images );
        recyclerAdapter.OnClick( this );
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(),2 ) );
        recyclerView.setAdapter( recyclerAdapter );

    }
    public void Recycle(){
        recyclerView=view.findViewById( R.id.home_recycler_items );
        recyclerView_slider=view.findViewById( R.id.home_recycler_slider );

    }
    @Override
    public void showItemsDetails(int position) {
        if(position==0){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new CollegesFragment()).addToBackStack( null ).commit();
        }else if(position==1){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new TripsFragment()).addToBackStack( null ).commit();
        }else if(position==2){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new BooksFragment()).addToBackStack( null ).commit();
        }else if(position==3){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new PoetryFragment()).addToBackStack( null ).commit();
        }else if(position==4){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new TranslationFragment()).addToBackStack( null ).commit();
        }else if(position==5){
            PicturesFragment detailsPicture=new PicturesFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image","gallary");
            detailsPicture.setArguments(bundle);
            getFragmentManager().beginTransaction().replace( R.id.main_frame_container,
                    detailsPicture )
                    .addToBackStack( null).commit();

        }
        else if(position==6){
            PicturesFragment detailsPicture=new PicturesFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image","taking");
            detailsPicture.setArguments(bundle);
            getFragmentManager().beginTransaction().replace( R.id.main_frame_container,
                    detailsPicture )
                    .addToBackStack( null).commit();
        }
        else if(position==7){
            PicturesFragment detailsPicture=new PicturesFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image","garphics");
            detailsPicture.setArguments(bundle);
            getFragmentManager().beginTransaction().replace( R.id.main_frame_container,
                    detailsPicture )
                    .addToBackStack( null).commit();
        }
    }



    @Override
    public void showPicturesData(List<PictureData> pictureData) {
        banner=pictureData;
        sliderAdapter=new SliderAdapter( getContext(),pictureData );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( getContext() );
        linearLayoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
        recyclerView_slider.setLayoutManager( linearLayoutManager );
        recyclerView_slider.setAdapter( sliderAdapter );
        if(pictureData.size()>1) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate( new AutoScrollTask(), 2000, 4000 );
        }
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void onRefresh() {
        if(networkConnection.isNetworkAvailable( getContext() ))
        {
            swipeRefreshLayout.setRefreshing( true );
            picturePresenter.getPicturesResult( "ar", "slider" );
        }
        else
        {
            Snackbar.make(relativeLayout ,getResources().getString( R.string.no_internet ),1500 ).show();
        }



    }

    public class AutoScrollTask extends TimerTask
    {

        @Override
        public void run() {
            if(position==banner.size()-1)
            {
                end=true;
            }else if(position==0)
            {
                end=false;
            }

            if (!end)
            {
                position++;
            }
            else
            {
                position--;
            }
            recyclerView_slider.smoothScrollToPosition( position );
        }
    }

    @Override
    public void error() {
swipeRefreshLayout.setRefreshing( false );
    }
}
