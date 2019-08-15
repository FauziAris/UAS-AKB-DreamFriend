package com.aarisfauji.dreamfriend.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.aarisfauji.dreamfriend.adapter.TemanAdapter;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public interface IView {
     interface ISignIn{
         void onClear();
         void onLoginResult(Boolean result);
         void onSetProgresBarVisibility(int visibility);
    }
      interface ISignUp{
         void onClear();
         void onSignUpResult(Boolean result);
         void onSetProgresBarVisibility(int visibility);
    }

     interface IMain{
        void setUsername(String username);
        void setFragment(Fragment fragment);
        void signOut();
        void exitApp();
    }

    interface ITeman{
         void showTeman(TemanAdapter temanAdapter);
         void temanDetail(int id,String nim,String nama);
         void updateTeman(Bundle bundle);
         void setFragment(Fragment fragment);


    }
    interface ITemanUpdate{
    }
    interface ITemanAdd{
    }
}
