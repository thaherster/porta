package com.example.thaherm.postagrafi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Thaher.m on 14-12-2017.
 */

public class Singat {
    byte[] byteArray;
    int height;
    int width;
    float rotation;
    float positionX;
    float positionY;

    public Singat() {
    }
    public Singat(byte[] byteArray) {
        this.byteArray = byteArray;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }
}
