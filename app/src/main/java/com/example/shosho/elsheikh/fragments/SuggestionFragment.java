package com.example.shosho.elsheikh.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shosho.elsheikh.NetworkConnection;
import com.example.shosho.elsheikh.R;
import com.example.shosho.elsheikh.model.User;
import com.example.shosho.elsheikh.presenter.SuggestionPresenter;
import com.example.shosho.elsheikh.view.SuggestionView;
import com.fourhcode.forhutils.FUtilsValidation;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestionFragment extends Fragment implements SuggestionView {
    Toolbar toolbar;


    SuggestionPresenter suggestionPresenter;
    EditText userName;
    EditText userEmail;
    EditText userPhone;
    EditText userMsg;

    Button sendBtn;

    public SuggestionFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_suggestion, container, false );
        init();

        suggestionPresenter = new SuggestionPresenter( getContext(), this );
        sendBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSending();
            }
        } );


        return view;
    }

    private void performSending() {
        FUtilsValidation.isEmpty( userName, ("من فضلك اكتب اسمك") );
        FUtilsValidation.isEmpty( userEmail,((("من فضلك اكتب بريدك الالكترونى")) ));
        FUtilsValidation.isEmpty( userPhone,(("من فضلك اكتب رقم الجوال") ));
        FUtilsValidation.isEmpty( userMsg,( "من فضلك اكتب رسالتك" ));

        NetworkConnection networkConnection = new NetworkConnection( getContext() );
        if (networkConnection.isNetworkAvailable( getContext() )) {
            if (!userName.getText().toString().equals( "" )
                    && !userEmail.getText().toString().equals( "" )&&
                    !userPhone.getText().toString().equals("")&&
                    !userMsg.getText().toString().equals("")&&validateEmail()) {
                User user = new User();


                user.setName( userName.getText().toString() );
                user.setEmail( userEmail.getText().toString() );
                user.setPhone( userPhone.getText().toString() );
                user.setMsg( userMsg.getText().toString() );

                suggestionPresenter.getSuggestionResult( user );
            } else {
                Toast.makeText( getContext(), "من فضلك املا البيانات الخاصه بك", Toast.LENGTH_SHORT ).show();
            }

        } else {
            Toast.makeText( getContext(), "تاكد من اتصالك بالانترنت"
                    , Toast.LENGTH_SHORT ).show();
        }
    }

    public static boolean isValidEmail(String Email)
    {
        return !TextUtils.isEmpty( Email )&&  android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
    private Boolean validateEmail(){
        String EMAIL=userEmail.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            userEmail.setError(("خطأ في البريد الالكترونى"));

            return false;
        }else if(!userEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            userEmail.setError(("خطأ في البريد الالكترونى"));
            return false;
        }
        return true;
    }

    private void init() {

        userName=view.findViewById( R.id.sueggestion_edit_text_name );
        userEmail=view.findViewById( R.id.sueggestion_edit_text_email );
        userPhone=view.findViewById( R.id.suggestion_us_edit_text_phone );
        userMsg=view.findViewById( R.id.suggestion_edit_text_msg );

        sendBtn=view.findViewById( R.id.suggestion_btn_send );
    }

    @Override
    public void showSuggestionResult(String Msg) {
        Toast.makeText( getContext(),"تم ارسال البيانات", Toast.LENGTH_LONG ).show();
    }

    @Override
    public void showError(String Error) {

    }
}
