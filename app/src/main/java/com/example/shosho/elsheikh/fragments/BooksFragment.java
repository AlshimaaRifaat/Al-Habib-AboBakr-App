package com.example.shosho.elsheikh.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.elsheikh.Language;
import com.example.shosho.elsheikh.NetworkConnection;
import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.adapter.BooksAdapter;
import com.example.shosho.elsheikh.model.BookData;
import com.example.shosho.elsheikh.presenter.BookPresenter;
import com.example.shosho.elsheikh.view.BookView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends Fragment implements BookView ,SwipeRefreshLayout.OnRefreshListener{
    RecyclerView recyclerView;
    BookPresenter bookPresenter;
    BooksAdapter booksAdapter;
    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    NetworkConnection networkConnection;
    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_books, container, false );
       recyclerView=view.findViewById( R.id.books_recycler_items );
       swipeRefreshLayout=view.findViewById( R.id.books_swipRefresh );

       bookPresenter=new BookPresenter( getContext(),this );
       bookPresenter.getBooksResult( "en","book" );
       initViews();
       swipeRefresh();
        return view;
    }




    private void initViews() {
        progressDialog=new ProgressDialog( getContext() );
        progressDialog.setMessage( "wait while Fetching Books..." );
        progressDialog.setCancelable( false );
        progressDialog.show();

    }
    private void swipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener( this);
        swipeRefreshLayout.setColorSchemeResources( android.R.color.holo_green_dark );
        swipeRefreshLayout.post( new Runnable() {
            @Override
            public void run() {
                if(networkConnection.isNetworkAvailable( getContext() ))
                {
                    if (Language.isRTL())
                    {
                        swipeRefreshLayout.setRefreshing( true );
                        bookPresenter.getBooksResult( "ar","book" );
                    }
                    else
                    {
                        swipeRefreshLayout.setRefreshing( true );
                        bookPresenter.getBooksResult( "en","book" );
                    }
                }
                else
                {
                    Toast.makeText( getContext(), R.string.no_internet, Toast.LENGTH_SHORT ).show();
                }

            }
        } );

    }

    @Override
    public void showBooksData(List<BookData> booksData) {
        booksAdapter = new BooksAdapter( getContext(), booksData );
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(), 2 ) );
        recyclerView.setAdapter( booksAdapter );
    }

    @Override
    public void Error() {
        swipeRefreshLayout.setRefreshing( false );
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing( true );

    }
}
