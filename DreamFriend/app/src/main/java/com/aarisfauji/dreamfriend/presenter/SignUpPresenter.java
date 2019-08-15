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
public class SignUpPresenter implements IPresenter.ISignUpPresenter{

    IView.ISignUp iSignUpView;
    Handler handler;
    public SignUpPresenter() {
    }

    public SignUpPresenter(IView.ISignUp iSignUpView) {
        this.iSignUpView = iSignUpView;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clear() {
        iSignUpView.onClear();
    }

    @Override
    public void doSignUp(Context context, String username, String password) {
        boolean isSignUp = false;
        try {
            Preferences.setRegisteredUser(context,username);
            Preferences.setRegisteredPass(context,password);
            isSignUp = true;
        }catch (Exception e){
            isSignUp=false;
        }
        final boolean result = isSignUp ;
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                iSignUpView.onSignUpResult(result);
            }
        },3000);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        iSignUpView.onSetProgresBarVisibility(visibility);
    }
}
