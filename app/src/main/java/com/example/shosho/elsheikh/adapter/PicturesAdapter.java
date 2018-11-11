package com.example.shosho.elsheikh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.model.PictureData;
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
    public void onBindViewHolder(@NonNull PicturesAdapter.ViewHolder holder, final int position) {
        Picasso.with( context ).load( "http://alhabib-abobakr.com/uploads/"+
                pictuesData.get( position ).getCImg() )
        .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return pictuesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super( itemView );
            imageView=itemView.findViewById( R.id.row_pictures_image );
        }
    }
}
