package com.example.shosho.elsheikh.fragments;


import android.graphics.Typeface;
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
public class DetailsTripFragment extends Fragment {
    ImageView imageView;
    TextView title;
    TextView date;
    TextView name;
    TextView description;

    public DetailsTripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_details_trip, container, false );
        imageView = view.findViewById( R.id.details_trip_image);
        title = view.findViewById( R.id.details_trip_title );
        date = view.findViewById( R.id.details_trip_date );
        name = view.findViewById( R.id.details_trip_name );
        description = view.findViewById( R.id.details_trip_description );
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String Image = bundle.getString( "image" );
            String Title = bundle.getString( "title" );
            String Date = bundle.getString( "date" );
            String Name = bundle.getString( "name" );
            String Description = bundle.getString( "description" );

            Picasso.with( getContext() )
                    .load( "http://alhabib-abobakr.com/uploads/" + Image )
                    .into( imageView );
            title.setText( Title );
            date.setText(Date);
            name.setText(Name);
            Typeface customFontLight = Typeface.createFromAsset( getActivity().getAssets(), "Fonts/SST Arabic Light.ttf" );
            description.setTypeface( customFontLight );
            description.setText( Description );
        }
        return view;
    }

}
