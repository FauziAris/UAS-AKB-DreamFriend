package com.aarisfauji.dreamfriend.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.aarisfauji.dreamfriend.Preferences.Preferences;
import com.aarisfauji.dreamfriend.view.IView;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class SignInPresenter implements IPresenter.ISignInPresenter {
    IView.ISignIn iSignInView;
    Handler handler;

    public SignInPresenter() {
    }

    public SignInPresenter(IView.ISignIn iSignInView) {
        this.iSignInView = iSignInView;
        //this.userM = new UserModel();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public boolean isSignIn(Context context) {
        //return userM.statusLogin(context);
        return Preferences.getLoggedInStatus(context);
    }

    @Override
    public void clear() {
        iSignInView.onClear();
    }

    @Override
    public void doSignIn(Context context,String username, String password) {
        Boolean isSigIn = false;

        if(cekUser(context,username) && cekPassword(context,password)){
            isSigIn = true;
            Preferences.setLoggedInStatus(context,true);
        }else{
            isSigIn =false;
        }
        final Boolean result = isSigIn;
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                iSignInView.onLoginResult(result);
            }
        },3000);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        iSignInView.onSetProgresBarVisibility(visibility);
    }


    private boolean cekPassword(Context context,String password){
        return password.equals(Preferences.getRegisteredPass(context));
    }

    private boolean cekUser(Context context,String user){
        return user.equals(Preferences.getRegisteredUser(context));
    }
}
