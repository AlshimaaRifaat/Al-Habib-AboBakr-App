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
public class DetailsTranslationFragment extends Fragment {

    ImageView imageView;
    TextView title;
    TextView name;
    TextView description;

    String Image;
    String Title;
    String Name;
    String Desc;
    public DetailsTranslationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_details_translation, container, false );
        imageView=view.findViewById( R.id.details_translation_image );
        title=view.findViewById( R.id.details_translation_title );
        name=view.findViewById( R.id.details_translation_name );
        description=view.findViewById( R.id.details_translation_description );
        Bundle bundle=this.getArguments();
        if(bundle !=null)
        {
            Image=bundle.getString( "image" );
            Title=bundle.getString( "title" );
            Name=bundle.getString( "name" );
            Desc=bundle.getString( "desc" );

            Picasso.with( getContext() ).load( "http://alhabib-abobakr.com/uploads/"+Image )
                    .into(imageView);
            title.setText(Title);
            name.setText( Name );

            Typeface customFontLight = Typeface.createFromAsset( getActivity().getAssets(), "Fonts/SST Arabic Light.ttf" );
            description.setTypeface( customFontLight );
            description.setText( Desc );
        }
        return view;
    }

}
