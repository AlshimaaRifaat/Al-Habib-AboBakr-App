package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shosho.elsheikh.NetworkConnection;
import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.adapter.BooksAdapter;
import com.example.shosho.elsheikh.model.BookData;
import com.example.shosho.elsheikh.presenter.BookPresenter;
import com.example.shosho.elsheikh.view.BookView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends Fragment implements BookView {
    RecyclerView recyclerView;
    BookPresenter bookPresenter;
    BooksAdapter booksAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
   // NetworkConnection networkConnection;
    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_books, container, false );
       recyclerView=view.findViewById( R.id.books_recycler_items );
       mSwipeRefreshLayout=view.findViewById( R.id.books_swipRefresh );
       bookPresenter=new BookPresenter( getContext(),this );
       bookPresenter.getBooksResult( "en","book" );

        return view;
    }









    @Override
    public void showBooksData(List<BookData> booksData) {
        booksAdapter = new BooksAdapter( getContext(), booksData );
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(), 2 ) );
        recyclerView.setAdapter( booksAdapter );

      //swipeRefreshLayout.setRefreshing( false );
    }


    @Override
    public void Error() {
       mSwipeRefreshLayout.setRefreshing( false );
    }



}
