package com.aarisfauji.dreamfriend.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.aarisfauji.dreamfriend.Preferences.Preferences;
import com.aarisfauji.dreamfriend.view.IView;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class MainPresenter implements IPresenter.IMain {

    IView.IMain mainView;

    public MainPresenter(IView.IMain mainView) {
        this.mainView = mainView;
    }

    @Override


    public void showUsername(Context context) {
        mainView.setUsername(Preferences.getRegisteredUser(context));
    }

    @Override
    public void loadFragment(Fragment fragment) {
        mainView.setFragment(fragment);
    }

    @Override
    public void signOut(Context context) {
        Preferences.clearLoggedInUser(context);
        mainView.signOut();

    }

    @Override
    public void exitApp() {
        mainView.exitApp();
    }
}
