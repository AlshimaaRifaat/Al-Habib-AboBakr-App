package com.example.shosho.elsheikh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.model.PictureData;
import com.example.shosho.elsheikh.view.DetailsBookView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.ViewHolder> {
    Context context;
    List<PictureData> pictuesData;



    public PicturesAdapter(Context context, List<PictureData> pictuesData) {
        this.context = context;
        this.pictuesData = pictuesData;
    }

    @NonNull
    @Override
    public PicturesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_pictures,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final PicturesAdapter.ViewHolder holder, final int position) {
        holder.progressBar.setVisibility( View.VISIBLE );
        Picasso.with( context ).load( "http://alhabib-abobakr.com/uploads/"+
                pictuesData.get( position ).getCImg() )
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

    }

    @Override
    public int getItemCount() {
        return pictuesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
       private ProgressBar progressBar;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_pictures_image );
            progressBar=itemView.findViewById( R.id.row_pictures_progress );
        }
    }
}
