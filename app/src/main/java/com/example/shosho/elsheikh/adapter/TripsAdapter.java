package com.example.shosho.elsheikh.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.model.BookData;
import com.example.shosho.elsheikh.model.BookDetails;
import com.example.shosho.elsheikh.view.DetailsBookView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {
private Context context;
private List<BookData> booksData;
DetailsBookView detailsBookView;

    public TripsAdapter(Context context, List<BookData> booksData) {
        this.context = context;
        this.booksData = booksData;
    }

    @NonNull
    @Override
    public TripsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_trips,parent,false);
        return new TripsAdapter.ViewHolder(view);
    }
    public void onClick(DetailsBookView detailsBookView)
    {
        this.detailsBookView=detailsBookView;
    }


    @Override
    public void onBindViewHolder(@NonNull final TripsAdapter.ViewHolder holder, final int position) {


        holder.title.setText(booksData.get( position ).getTitle());
        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );
        holder.title.setTypeface( customFontMedium );

        holder.progressBar.setVisibility( View.VISIBLE );
        Picasso.with( context )
                .load( "http://alhabib-abobakr.com/uploads/"+booksData.get( position ).getCImg() )
                .into( holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility( View.GONE );
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility( View.GONE );
                    }
                } );
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDetails bookDetails=new BookDetails();
                bookDetails.setImg( booksData.get( position ).getCImg() );
                bookDetails.setTitle( booksData.get( position ).getTitle() );
                bookDetails.setDate( booksData.get( position ).getCDate() );
                bookDetails.setName( booksData.get( position ).getCName() );
                bookDetails .setDesc( booksData.get( position ).getDescription() );
                detailsBookView.showBookDetails( bookDetails );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return booksData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView date;
        private TextView title;
        private TextView name;
        private TextView description;
        private ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_trips_image );
            date=itemView.findViewById( R.id.row_trips_date );
            title=itemView.findViewById( R.id.row_trips_title );
            name=itemView.findViewById( R.id.row_trips_name);
            description=itemView.findViewById( R.id.row_trips_description);
            progressBar=itemView.findViewById( R.id.row_trips_progress_bar );
        }
    }
}
