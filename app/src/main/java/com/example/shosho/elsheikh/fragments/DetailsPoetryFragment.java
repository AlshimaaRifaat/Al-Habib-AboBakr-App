package com.example.shosho.elsheikh.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.shosho.elsheikh.Config;
import com.example.shosho.elsheikh.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsPoetryFragment extends Fragment implements
        YouTubePlayer.OnInitializedListener {


    public DetailsPoetryFragment() {
        // Required empty public constructor
    }
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    // YouTube player view
    private YouTubePlayerSupportFragment youTubePlayerFragment;
    String link;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//
         view= inflater.inflate( R.layout.fragment_details_poetry, container, false );
//        youTubeView =view.findViewById(R.id.youtube_view);

         youTubePlayerFragment = (YouTubePlayerSupportFragment)
                getChildFragmentManager()
                .findFragmentById(R.id.youtube_fragment);

        // Initializing video player with developer key
        youTubePlayerFragment.initialize(Config.DEVELOPER_KEY, this);
        Bundle bundle=this.getArguments();
        if (bundle!=null)
        {
            link=bundle.getString( "video_link" );
        }


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
           String url=link.substring(link.lastIndexOf("=") + 1);
            youTubePlayer.loadVideo(url);

            // Hiding player controls
            //youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
        } else {
//            String errorMessage = String.format(
//                    getString(R.string.error_player), youTubeInitializationResult.toString());
//            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }

    }
    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerSupportFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.youtube_fragment);
    }

}
