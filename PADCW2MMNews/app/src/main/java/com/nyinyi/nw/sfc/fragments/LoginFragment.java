package com.nyinyi.nw.sfc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nyinyi.nw.sfc.R;
import com.nyinyi.nw.sfc.delegates.LoginRegisterDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 11/26/2017.
 */

public class LoginFragment extends BaseFragment {

    private LoginRegisterDelegate mLoginRegisterDelegate;


    public static LoginFragment newInstance()
    {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginRegisterDelegate = (LoginRegisterDelegate) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mLoginRegisterDelegate.setScreenTitle("Login");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, loginView);

        return loginView;
    }

    @OnClick(R.id.btn_to_register)
    public void onTapToRegister(View view)
    {
        mLoginRegisterDelegate.onTapToRegister();
    }
}
