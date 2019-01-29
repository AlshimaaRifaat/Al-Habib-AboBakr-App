package com.example.shosho.elsheikh.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.elsheikh.api.Client;
import com.example.shosho.elsheikh.api.Service;
import com.example.shosho.elsheikh.model.SuggestionResponse;
import com.example.shosho.elsheikh.model.User;
import com.example.shosho.elsheikh.view.SuggestionView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestionPresenter {
    Context context;
    SuggestionView suggestionView;

    public SuggestionPresenter(Context context, SuggestionView suggestionView) {
        this.context = context;
        this.suggestionView = suggestionView;
    }

    public void  getSuggestionResult(User user)
    {
        Map<String,String> map=new HashMap<>(  );
        {
            map.put( "api_token", "222");
            map.put( "lang","en" );
            map.put( "section","inbox" );
            map.put( "name",user.getName() );
            map.put( "email",user.getEmail() );
            map.put( "phone",user.getPhone() );
            map.put( "msg",user.getMsg() );

            Service service=Client.getClient().create( Service.class );
            Call<SuggestionResponse> call =service.getSuggestionData(  map);
            call.enqueue( new Callback<SuggestionResponse>() {
                @Override
                public void onResponse(Call<SuggestionResponse> call, Response<SuggestionResponse> response) {
                    if (response.isSuccessful())
                    {
                        if (response.body().getResult().equals(  "message sent successfuly"))
                        {
                            suggestionView.showSuggestionResult( response.body().getResult() );

                        }

                    }

                }

                @Override
                public void onFailure(Call<SuggestionResponse> call, Throwable t) {
                    Toast.makeText( context, "لا يتوفر انترنت",
                            Toast.LENGTH_SHORT).show();
                }
            } );
        }
    }
}
