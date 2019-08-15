package com.aarisfauji.dreamfriend.view;


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
import android.widget.Toast;

import com.aarisfauji.dreamfriend.R;
import com.aarisfauji.dreamfriend.presenter.IPresenter;
import com.aarisfauji.dreamfriend.presenter.SignUpPresenter;

/**
 * A simple {@link Fragment} subclass.
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class SignUpFragment extends Fragment implements IView.ISignUp, View.OnClickListener {

    View myView;
    private EditText edtUsername;
    private  EditText edtPassword,edtPasswordComfir;
    private Button btnClear;
    private Button btnSignUp;
    private Button btnSignIn;
    private  ProgressBar progressBar;
    private IPresenter.ISignUpPresenter signUpPresenter;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout._sign_up, container, false);
        edtUsername = (EditText) myView.findViewById(R.id.edtUsername);
        edtPassword = (EditText) myView.findViewById(R.id.edtPassword);
        edtPasswordComfir = (EditText) myView.findViewById(R.id.edtPasswordComfir);
        btnClear = (Button) myView.findViewById(R.id.btnClear);
        btnSignIn = (Button) myView.findViewById(R.id.btnSignIn);
        btnSignUp = (Button) myView.findViewById(R.id.btnSignUp);
        progressBar = (ProgressBar) myView.findViewById(R.id.progressLogin);

        //new ojb
        signUpPresenter = new SignUpPresenter(this);
        btnSignUp.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        signUpPresenter.setProgressBarVisibility(View.INVISIBLE);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn :
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_login, new SignInFragment());
                fragmentTransaction.commit();
                break;
            case R.id.btnClear:
                signUpPresenter.clear();
                break;
            case R.id.btnSignUp:
                int error = 0;
                String username = edtUsername.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();
                String passComfir = edtPasswordComfir.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    edtUsername.setError("Kolom username harus diisi");
                    error++;
                }else if (username.length() < 5 || username.length() > 5){
                    edtUsername.setError("Username harus 5 karakter");
                    error++;
                }

                if (TextUtils.isEmpty(pass)){
                    edtPassword.setError("Kolom password harus diisi");
                    error++;
                }else if (username.length() < 5 ){
                    edtPassword.setError("Password minimal 5 karakter");
                    error++;
                }

                if (TextUtils.isEmpty(passComfir)){
                    edtPasswordComfir.setError("Kolom password komfirmasi harus diisi");
                    error++;
                }else if (!pass.equals(passComfir) ){
                    edtPasswordComfir.setError("Password tidak sama dengan password komfirmasi");
                    error++;
                }
                if (error == 0){
                    btnSignIn.setEnabled(false);
                    btnSignUp.setEnabled(false);
                    btnClear.setEnabled(false);
                    signUpPresenter.setProgressBarVisibility(View.VISIBLE);
                    signUpPresenter.doSignUp(getContext(),username.toLowerCase(),pass);
                }
                break;
        }
    }

    @Override
    public void onClear() {
        edtPasswordComfir.setText("");
        edtPassword.setText("");
        edtUsername.setText("");
    }

    @Override
    public void onSignUpResult(Boolean result) {
        signUpPresenter.setProgressBarVisibility(View.INVISIBLE);
        btnSignIn.setEnabled(true);
        btnSignUp.setEnabled(true);
        btnClear.setEnabled(true);
        if(result){
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_login, new SignInFragment());
            fragmentTransaction.commit();
            Toast.makeText(getActivity(),"Sign Up berhasil silahkan login",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"Sign Up Gagal",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgresBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }
}
