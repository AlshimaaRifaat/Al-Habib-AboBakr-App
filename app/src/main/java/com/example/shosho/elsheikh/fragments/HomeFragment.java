package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.adapter.HomeAdapter;
import com.example.shosho.elsheikh.view.PicturesDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PicturesDetails {
    RecyclerView recyclerView;
    HomeAdapter recyclerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_home, container, false );
        int []images={R.drawable.colleges,R.drawable.trips,R.drawable.books,
                R.drawable.poetry,R.drawable.translation,R.drawable.pictures};
        String []names={"الكليات والمعاهد","الرحلات الدعويه", "كتب مؤلفات",
                "اشعار","ترجمه","صور"};
        recyclerView=view.findViewById( R.id.home_recycler_items );
        recyclerAdapter=new HomeAdapter( getContext(),names,images );
        recyclerAdapter.OnClick( this );
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(),2 ) );
        recyclerView.setAdapter( recyclerAdapter );
        return view;
    }

    @Override
    public void picturesDetails(int position) {
        if(position==0){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new CollegesFragment()).commit();
        }else if(position==1){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new TripsFragment()).commit();
        }else if(position==2){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new BooksFragment()).commit();
        }else if(position==3){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new PoetryFragment()).commit();
        }else if(position==4){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new TranslationFragment()).commit();
        }else if(position==5){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new PicturesFragment()).commit();
        }
    }
}
