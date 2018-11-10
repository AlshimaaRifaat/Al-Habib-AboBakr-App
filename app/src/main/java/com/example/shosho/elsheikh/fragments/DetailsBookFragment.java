package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.elsheikh.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsBookFragment extends Fragment  {

ImageView imageView;
TextView title;
TextView date;
TextView description;
    public DetailsBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_book_details, container, false );
        imageView=view.findViewById( R.id.details_image );
        title=view.findViewById( R.id.details_title );
        date=view.findViewById( R.id.details_date );
        description=view.findViewById( R.id.details_description );
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String Image = bundle.getString("image");
            String Title = bundle.getString("title");
            String Date = bundle.getString("date");
            String Description = bundle.getString("description");

        Picasso.with( getContext() )
                .load( "http://alhabib-abobakr.com/uploads/"+Image )
                .placeholder( R.drawable.ic_launcher_background )
                .into(imageView);
        title.setText( Title );
        date.setText( Date );
        description.setText( Description );

        }
        return view;
    }

}
