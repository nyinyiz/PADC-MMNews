package com.nyinyi.nw.sfc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyinyi.nw.sfc.R;
import com.nyinyi.nw.sfc.delegates.LoginRegisterDelegate;

import butterknife.ButterKnife;

/**
 * Created by User on 11/26/2017.
 */

public class RegisterFragment extends BaseFragment {


    private LoginRegisterDelegate mLoginRegisterDelegate;

    public static RegisterFragment newInstance()
    {
        RegisterFragment registerFragment = new RegisterFragment();
        return registerFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginRegisterDelegate = (LoginRegisterDelegate) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mLoginRegisterDelegate.setScreenTitle("Register");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View registerView = inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this,registerView);
        return registerView;
    }
}
