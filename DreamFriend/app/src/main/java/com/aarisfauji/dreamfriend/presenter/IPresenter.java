package com.aarisfauji.dreamfriend.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public interface IPresenter {
    interface ISignInPresenter {
        boolean isSignIn(Context context);
        void clear();
        void doSignIn(Context context,String username , String password);
        void setProgressBarVisibility(int visibility);
    }
    interface ISignUpPresenter {
        void clear();
        void doSignUp(Context context,String username , String password);
        void setProgressBarVisibility(int visibility);
    }

    interface  IMain {
        void showUsername(Context context);
        void loadFragment(Fragment fragment);
        void signOut(Context context);
        void exitApp();
    }

    interface ITeman{
        void loadDataTeman();
        void temanAdd(Bundle bundle);
        void editDataTeman(Bundle bundle);
        void deleteDataTeman(int id);
        void updateDataTeman(int id,Bundle bundle);
    }
}
