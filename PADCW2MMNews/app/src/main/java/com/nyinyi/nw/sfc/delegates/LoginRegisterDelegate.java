package com.nyinyi.nw.sfc.delegates;

/**
 * Created by User on 11/26/2017.
 */

public interface LoginRegisterDelegate {

    void onTapLogin();
    void onTapForgotPassword();
    void onTapToRegister();

    void setScreenTitle(String title);
}
