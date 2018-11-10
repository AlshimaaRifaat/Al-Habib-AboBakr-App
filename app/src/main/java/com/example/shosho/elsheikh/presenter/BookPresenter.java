package com.example.shosho.elsheikh.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.shosho.elsheikh.adapter.BooksAdapter;
import com.example.shosho.elsheikh.api.Client;
import com.example.shosho.elsheikh.api.Service;
import com.example.shosho.elsheikh.model.BookData;
import com.example.shosho.elsheikh.model.BooksResponse;
import com.example.shosho.elsheikh.view.BookView;
import com.example.shosho.elsheikh.view.ItemsView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookPresenter {
    Context context;
    BookView bookView;

   // RecyclerView recyclerView;

    public BookPresenter(Context context, BookView bookView) {
        this.context = context;
        this.bookView = bookView;
    }

    public void getBooksResult( String Lang, String section)
    {
        Map<String,String> map=new HashMap<>(  );
//        map.put( "api_token", "100");
        map.put( "lang","ar" );
        map.put( "section","book" );
        Service service= Client.getClient().create( Service.class );
        Call<BooksResponse> call=service.getBooksData( map );
        call.enqueue( new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
           if(response.isSuccessful()) {
               bookView.showBooksData( response.body().getData());

           }else {
               bookView.Error();
           }

            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Toast.makeText( context, "error", Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}
