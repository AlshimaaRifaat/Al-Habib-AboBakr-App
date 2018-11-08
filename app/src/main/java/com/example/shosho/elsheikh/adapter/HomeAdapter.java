package com.example.shosho.elsheikh.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.view.ItemsView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolders> {

    private Context context;
    private String []namesArray;
    private int []imageArray;

    ItemsView details;

    public HomeAdapter(Context context, String[] namesArray, int[] imageArray) {
        this.context = context;
        this.namesArray = namesArray;
        this.imageArray = imageArray;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.row_home,parent,false);
       ViewHolders holders=new ViewHolders(view);
        return holders;
    }
     public void OnClick(ItemsView details){
        this.details=details;
     }
    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, final int position) {
        holder.textName.setText( namesArray[position] );
        holder.imageView.setImageResource( imageArray[position] );
        Typeface customFontMedium = Typeface.createFromAsset( context.getAssets(), "Fonts/SST Arabic Medium.ttf" );
        holder.textName.setTypeface( customFontMedium );

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details.showItemsDetails( position );
            }
        } );

    }
    @Override
    public int getItemCount() {
        return namesArray.length;
    }


    public class ViewHolders extends RecyclerView.ViewHolder{
        private TextView textName;
        private ImageView imageView;
        private CardView cardView;

        public ViewHolders(View itemView) {
            super( itemView );
            textName=itemView.findViewById( R.id.row_home_names );

            imageView=itemView.findViewById( R.id.row_home_image );
            cardView=itemView.findViewById( R.id.row_home_card_container );
            /*customFontMedium=Typeface.createFromAsset( context.getAssets(),"font/SST Arabic Medium.ttf" );
            textName.setTypeface(customFontMedium );*/

        }
    }
}
