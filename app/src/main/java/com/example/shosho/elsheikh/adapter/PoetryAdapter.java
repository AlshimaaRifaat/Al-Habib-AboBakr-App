package com.example.shosho.elsheikh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.model.PictureData;
import com.example.shosho.elsheikh.view.Link_View;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder>{
        Context context;
        List<PictureData> pictuesData;
    Link_View link_view;
    public PoetryAdapter(Context context, List<PictureData> pictuesData) {
        this.context = context;
        this.pictuesData = pictuesData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.row_poetry,parent,false);
        return new PoetryAdapter.ViewHolder(view);
    }
   public void  onclick(Link_View link_view){
        this.link_view=link_view;
   }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
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
        holder.showVideo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link_view.link( pictuesData.get( position ).getCLink() );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return pictuesData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private Button showVideo;
    ProgressBar progressBar;

    public ViewHolder(View itemView) {
        super( itemView );
        imageView = itemView.findViewById( R.id.row_poetry_image );
        showVideo=itemView.findViewById( R.id.row_poetry_show_video_btn );
        progressBar=itemView.findViewById( R.id.row_poetry_progress );
    }
}
}
