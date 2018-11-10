package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.elsheikh.NetworkConnection;
import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.adapter.BooksAdapter;
import com.example.shosho.elsheikh.model.BookData;
import com.example.shosho.elsheikh.model.BookDetails;
import com.example.shosho.elsheikh.presenter.BookPresenter;
import com.example.shosho.elsheikh.view.BookView;
import com.example.shosho.elsheikh.view.DetailsBookView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends Fragment implements BookView,
        SwipeRefreshLayout.OnRefreshListener,DetailsBookView{
    RecyclerView recyclerView;
    BookPresenter bookPresenter;
    BooksAdapter booksAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
   NetworkConnection networkConnection;


    List<BookData> booksData;
    //Fragment fragment;
    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_books, container, false );
       /* Bundle bundle = new Bundle();
        bundle.putInt("api_token", booksData.get(  ));

        fragment.setArguments(bundle);*/
       recyclerView=view.findViewById( R.id.books_recycler_items );
       networkConnection=new NetworkConnection( getContext() );
       mSwipeRefreshLayout=view.findViewById( R.id.books_swipRefresh );
       bookPresenter=new BookPresenter( getContext(),this );
       bookPresenter.getBooksResult( "ar","book" );


        swipRefresh();
        return view;
    }

    private void swipRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources( android.R.color.holo_orange_dark  );
        mSwipeRefreshLayout.setEnabled( true );
        mSwipeRefreshLayout.setOnRefreshListener( this );
        mSwipeRefreshLayout.post( new Runnable() {
            @Override
            public void run() {
                if (networkConnection.isNetworkAvailable( getContext() ))
                {
                    mSwipeRefreshLayout.setRefreshing( true );
                    bookPresenter.getBooksResult( "ar","book" );
                }
            }
        } );
    }


    @Override
    public void showBooksData(List<BookData> booksData) {
        booksAdapter = new BooksAdapter( getContext(), booksData );
        booksAdapter.onClick( this );
        mSwipeRefreshLayout.setRefreshing( true );
        recyclerView.setLayoutManager( new GridLayoutManager( getContext(), 2 ) );
        recyclerView.setAdapter( booksAdapter );
        mSwipeRefreshLayout.setRefreshing( false );
    }


    @Override
    public void Error() {
       mSwipeRefreshLayout.setRefreshing( false );
    }


    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing( true );
        bookPresenter.getBooksResult( "ar","book" );

    }

    @Override
    public void showBookDetails(BookDetails bookDetails) {
        DetailsBookFragment detailsFragment=new DetailsBookFragment();
                Bundle bundle = new Bundle();
        bundle.putString("image", bookDetails.getcImg());
        bundle.putString("title", bookDetails.getTitle());
        bundle.putString("date", bookDetails.getcDate());
        bundle.putString("description", bookDetails.getcDate());
        detailsFragment.setArguments(bundle);
       getFragmentManager().beginTransaction().replace( R.id.main_frame_container,
               detailsFragment )
                .addToBackStack( null).commit();

    }
}
