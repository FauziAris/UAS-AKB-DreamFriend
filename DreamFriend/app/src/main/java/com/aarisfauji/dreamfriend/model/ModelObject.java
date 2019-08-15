package com.aarisfauji.dreamfriend.model;


import com.aarisfauji.dreamfriend.R;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 14 Agustus 2019
 */
public enum ModelObject {
    HAL_1(R.string.halaman2, R.layout.view_1),
    HAL_2(R.string.halaman2, R.layout.view_2),
    HAL_3(R.string.halaman3, R.layout.view_3);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
