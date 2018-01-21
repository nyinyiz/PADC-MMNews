package com.nyinyi.nw.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nyinyi.nw.sfc.R;
import com.nyinyi.nw.sfc.delegates.LoginRegisterDelegate;
import com.nyinyi.nw.sfc.fragments.LoginFragment;
import com.nyinyi.nw.sfc.fragments.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 11/26/2017.
 */

public class LoginRegisterActivity extends BaseActivity implements LoginRegisterDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public static Intent newIntent(Context context)
    {
        Intent intent = new Intent(context,LoginRegisterActivity.class);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, LoginFragment.newInstance())
                    .commit();
        }


    }

    @Override
    public void onTapLogin() {

    }

    @Override
    public void onTapForgotPassword() {

    }

    @Override
    public void onTapToRegister() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                .replace(R.id.fl_container, RegisterFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setScreenTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setTitle(title);
        }
    }
}
