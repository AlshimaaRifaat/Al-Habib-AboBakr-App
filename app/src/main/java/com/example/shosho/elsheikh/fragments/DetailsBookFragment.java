package com.example.shosho.elsheikh.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.elsheikh.PDFUtils;
import com.example.shosho.elsheikh.R;
import com.squareup.picasso.Picasso;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsBookFragment extends Fragment  {

ImageView imageView;
TextView title;
TextView date;
TextView description;
Button downloadPDF;
    String PDFurl,URL;
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
        downloadPDF=view.findViewById( R.id.details_book_download );
        downloadPDF.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(URL!=null)
                {
                    PDFUtils.showPDFUrl(getContext(),URL);
                }else
                {
                    downloadPDF.setVisibility( View.GONE );
                }
            }
        } );
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String Image = bundle.getString("image");
            String Title = bundle.getString("title");
            String Date = bundle.getString("date");
            PDFurl=bundle.getString( "url" );
            String Description = bundle.getString("description");
         URL="http://alhabib-abobakr.com/uploads/Books"+PDFurl;
        Picasso.with( getContext() )
                .load( "http://alhabib-abobakr.com/uploads/"+Image )
                .into(imageView);
        title.setText( Title );
        date.setText( Date );

        Typeface customFontLight = Typeface.createFromAsset( getActivity().getAssets(), "Fonts/SST Arabic Light.ttf" );
        description.setTypeface( customFontLight );
        description.setText( Description );

        }
        return view;
    }

}
