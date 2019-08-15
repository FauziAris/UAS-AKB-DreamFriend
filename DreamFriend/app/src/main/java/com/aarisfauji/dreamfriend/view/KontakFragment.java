package com.aarisfauji.dreamfriend.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aarisfauji.dreamfriend.R;

/**
 * A simple {@link Fragment} subclass.
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 14 Agustus 2019
 */
public class KontakFragment extends Fragment implements  View.OnClickListener{

    View view;
    Button btnIg,btnTlp,btnKirimEmail;
    EditText edtSubjek,edtPesan;
    TextView tvIg,tvTlp,tvEmail;

    public KontakFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kontak, container, false);
        btnIg = view.findViewById(R.id.btn_instagramK);
        btnTlp = view.findViewById(R.id.btn_tlpK);
        tvIg = view.findViewById(R.id.tvIgK);
        tvTlp = view.findViewById(R.id.tvTlpK);
        tvEmail = view.findViewById(R.id.tvEmailK);
        btnKirimEmail = view.findViewById(R.id.btnKirimEmailK);
        edtPesan = view.findViewById(R.id.edtSubjekK);
        edtSubjek = view.findViewById(R.id.edtSubjekK);
        btnIg.setOnClickListener(this);
        btnTlp.setOnClickListener(this);
        btnKirimEmail.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_instagramK:
                String url = "https://www.instagram.com/as_fauzi24";
                Intent bukaWeb = new Intent(Intent.ACTION_VIEW);
                bukaWeb.setData(Uri.parse(url));
                startActivity(bukaWeb);
                break;
        case R.id.btn_tlpK:
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+tvTlp.getText().toString().toString()));
            startActivity(callIntent);
            break;
            case  R.id.btnKirimEmailK:
                Toast.makeText(getContext(),"fitur kirim email belum berfungsi",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
