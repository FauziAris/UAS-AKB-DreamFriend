package com.aarisfauji.dreamfriend.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aarisfauji.dreamfriend.R;
import com.aarisfauji.dreamfriend.presenter.IPresenter;
import com.aarisfauji.dreamfriend.presenter.TemanPresenter;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * A simple {@link Fragment} subclass.
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class TemanAddFragment extends Fragment implements  IView.ITemanAdd,View.OnClickListener{

    View myView;
    Button btnSimpan, btnTampil;
    EditText edtNim, edtNama,edtKelas,edtEmail,edtTlp,edtIg;
    String nim,nama,kelas,email,tlp,ig;
    IPresenter.ITeman temanPresenter;
    public TemanAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_teman_add, container, false);
        temanPresenter = new TemanPresenter(this,getActivity());
        btnSimpan = (Button)  myView.findViewById(R.id.btnSimpan);
        btnTampil = (Button) myView.findViewById(R.id.btnTampil);
        edtNim = (EditText) myView.findViewById(R.id.edtNim);
        edtNama =(EditText) myView.findViewById(R.id.edtNama);
        edtKelas = (EditText) myView.findViewById(R.id.edtKelas);
        edtEmail = (EditText)myView.findViewById(R.id.edtEmail);
        edtTlp = (EditText)myView.findViewById(R.id.edtTlp);
        edtIg = (EditText)myView.findViewById(R.id.edtIg);
        btnSimpan.setOnClickListener(this);
        btnTampil.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSimpan:
                int error1 =0;
                nim = edtNim.getText().toString().trim();
                nama = edtNama.getText().toString().trim();
                kelas = edtKelas.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                tlp = edtTlp.getText().toString().trim();
                ig = edtIg.getText().toString().trim();
                if( TextUtils.isEmpty(nim)){
                    edtNim.setError("Kolom NIM harus diisi");
                    error1++;
                }
                if(TextUtils.isEmpty(nama)){
                    edtNama.setError("Kolom nama harus diisi");
                    error1++;
                }
                if(TextUtils.isEmpty(kelas)){
                    edtKelas.setError("Kolom kelas harus diisi");
                    error1++;
                }
                if(TextUtils.isEmpty(email)){
                    edtEmail.setError("Kolom email harus diisi");
                    error1++;
                }
                if(TextUtils.isEmpty(tlp)){
                    edtTlp.setError("Kolom nomor telepon harus diisi");
                    error1++;
                }
                if(TextUtils.isEmpty(ig)){
                    edtIg.setError("Kolom instagram harus diisi");
                    error1++;
                }
                if(error1 ==0){
                    Bundle bundle = new Bundle();
                    bundle.putString("nim",nim);
                    bundle.putString("nama",nama);
                    bundle.putString("kelas",kelas);
                    bundle.putString("email",email);
                    bundle.putString("tlp",tlp);
                    bundle.putString("ig",ig);
                    try {
                        temanPresenter.temanAdd(bundle);
                        Toast.makeText(getActivity(), "Data teman berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        setClearEdt();
                        closeKeyword();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Data teman gagal Disimpan", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnTampil:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, new TemanFragment());
                fragmentTransaction.commit();
                break;
        }
    }
    void closeKeyword(){
        View view = getActivity().getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    private  void setClearEdt(){
        edtNim.setText("");
        edtNama.setText("");
        edtKelas.setText("");
        edtEmail.setText("");
        edtTlp.setText("");
        edtIg.setText("");
    }
}
