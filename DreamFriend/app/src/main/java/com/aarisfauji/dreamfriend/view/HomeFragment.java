package com.aarisfauji.dreamfriend.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aarisfauji.dreamfriend.R;
import com.aarisfauji.dreamfriend.adapter.CustomPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class HomeFragment extends Fragment {

    View view;
    ViewPager viewPager;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(getActivity()));
        return view;
    }

}
