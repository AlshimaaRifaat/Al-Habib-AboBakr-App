package com.example.shosho.elsheikh.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.elsheikh.api.Client;
import com.example.shosho.elsheikh.api.Service;
import com.example.shosho.elsheikh.model.BooksResponse;
import com.example.shosho.elsheikh.model.PicturesResponse;
import com.example.shosho.elsheikh.view.PictureView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PicturePresenter {
    Context context;
    PictureView pictureView;

    public PicturePresenter(Context context, PictureView pictureView) {
        this.context = context;
        this.pictureView = pictureView;
    }
    public void getPicturesResult(String lang,String section)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "lang","ar" );
        map.put( "section","gallary" );
        Service service= Client.getClient().create( Service.class );
        Call<PicturesResponse> call=service.getPicturseData( map );
        call.enqueue( new Callback<PicturesResponse>() {
            @Override
            public void onResponse(Call<PicturesResponse> call, Response<PicturesResponse> response) {
                if (response.isSuccessful())
                {
                    pictureView.showPicturesData( response.body().getData() );

                }
            }

            @Override
            public void onFailure(Call<PicturesResponse> call, Throwable t) {
                pictureView.error();
                Toast.makeText( context, "غير متصل بالانترنت ,من فضلك تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}
