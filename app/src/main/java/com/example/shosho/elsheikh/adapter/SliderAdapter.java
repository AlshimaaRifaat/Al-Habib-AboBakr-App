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

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {
        Context context;
        List<PictureData> pictuesData;

    public SliderAdapter(Context context, List<PictureData> pictuesData) {
        this.context = context;
        this.pictuesData = pictuesData;
    }

    @NonNull
    @Override
    public SliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_slider,parent,false);
        return new SliderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.ViewHolder holder, int position) {
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
            imageView=itemView.findViewById( R.id.home_image_slider);
        }
    }
}

