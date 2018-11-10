package com.example.shosho.elsheikh.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.model.BookData;
import com.example.shosho.elsheikh.model.BookDetails;
import com.example.shosho.elsheikh.view.DetailsBookView;
import com.squareup.picasso.Picasso;

import java.util.List;

import static java.security.AccessController.getContext;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private Context context;
    private List<BookData> booksData;
    DetailsBookView detailsBookView;
  //  BookData bookData=new BookData();




    public BooksAdapter(Context context, List<BookData> booksData) {
        this.context = context;
        this.booksData = booksData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_books,parent,false);
        return new ViewHolder(view);
    }
    public void onClick(DetailsBookView detailsBookView)
    {
        this.detailsBookView=detailsBookView;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.date.setText(booksData.get( position ).getCDate());
        holder.title.setText(booksData.get( position ).getTitle());
        Picasso.with( context )
                .load( "http://alhabib-abobakr.com/uploads/"+booksData.get( position ).getCImg() )
                .placeholder( R.drawable.ic_launcher_background )
                .into(holder.imageView);
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDetails bookDetails=new BookDetails();
                bookDetails.setcImg( booksData.get( position ).getCImg() );
                bookDetails.setTitle( booksData.get( position ).getTitle() );
                bookDetails.setcDate( booksData.get( position ).getCDate() );
               bookDetails .setDescription( booksData.get( position ).getDescription() );
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
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_books_image );
            date=itemView.findViewById( R.id.row_books_date );
            title=itemView.findViewById( R.id.row_books_title );


        }
    }
}
