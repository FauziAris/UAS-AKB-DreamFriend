package com.aarisfauji.dreamfriend.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aarisfauji.dreamfriend.R;
import com.aarisfauji.dreamfriend.presenter.IPresenter;
import com.aarisfauji.dreamfriend.presenter.SignInPresenter;

/**
 * A simple {@link Fragment} subclass.
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class SignInFragment extends Fragment implements IView.ISignIn, View.OnClickListener {

    View myView;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignIn;
    private Button btnClear;
    private Button btnSignUp;
    private IPresenter.ISignInPresenter signInPresenter;
    private ProgressBar progressBar;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout._sign_in, container, false);
        edtUsername = (EditText) myView.findViewById(R.id.edtUsername);
        edtPassword = (EditText) myView.findViewById(R.id.edtPassword);
        btnClear = (Button) myView.findViewById(R.id.btnClear);
        btnSignIn = (Button) myView.findViewById(R.id.btnSignIn);
        btnSignUp = (Button) myView.findViewById(R.id.btnSignUp);
        progressBar = (ProgressBar) myView.findViewById(R.id.progressLogin);
        btnSignIn.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        //int
        signInPresenter = new SignInPresenter(this);
        signInPresenter.setProgressBarVisibility(View.INVISIBLE);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn :
                int error = 0;
                String username = edtUsername.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    edtUsername.setError("Kolom username harus diisi");
                    error++;
                }
                if (TextUtils.isEmpty(pass)){
                    edtPassword.setError("Kolom password harus diisi");
                    error++;
                }
                if ( error ==0){
                    signInPresenter.setProgressBarVisibility(View.VISIBLE);
                    btnSignIn.setEnabled(false);
                    btnSignUp.setEnabled(false);
                    btnClear.setEnabled(false);
                    signInPresenter.doSignIn(getContext(),username,pass);
                }
                break;
            case R.id.btnClear:
                signInPresenter.clear();
                break;
            case R.id.btnSignUp:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_login, new SignUpFragment());
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public void onClear() {
        edtUsername.setText("");
        edtPassword.setText("");
    }

    @Override
    public void onLoginResult(Boolean result) {
        signInPresenter.setProgressBarVisibility(View.INVISIBLE);
        btnClear.setEnabled(true);
        btnSignIn.setEnabled(true);
        btnSignUp.setEnabled(true);

        if(result){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(),"Login suksess",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"Login Gagal",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgresBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
