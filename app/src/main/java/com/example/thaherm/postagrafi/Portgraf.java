package com.example.thaherm.postagrafi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thaher.m on 14-12-2017.
 */

public class Portgraf {
    byte[] byteArray;
    private List<Singat> signatureList;

    public Portgraf() {
        signatureList = new ArrayList<>();
    }

    public byte[] getByteArray() {
        return byteArray;
    }
 public Bitmap getBitmap() {
     return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public List<Singat> getSignatureList() {
        return signatureList;
    }

    public void setSignatureList(List<Singat> signatureList) {
        this.signatureList = signatureList;
    }
}
