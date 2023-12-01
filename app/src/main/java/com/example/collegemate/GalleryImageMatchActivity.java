package com.example.collegemate;

import android.graphics.drawable.Drawable;

public class GalleryImageMatchActivity {
    private String Info;
    private String ImgName;

    private String major;
    private int ImgPic;

    private Drawable ImgDrawable;
    private String ImgPath;

    public GalleryImageMatchActivity(String info, String imgName, Drawable imgDrawable,String imgPath, String major) {
        Info = info;
        ImgName = imgName;
        ImgDrawable = imgDrawable;
        ImgPath=imgPath;
        this.major = major;

    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getImgName() {
        return ImgName;
    }

    public void setImgName(String imgName) {
        ImgName = imgName;
    }

    public int getImgPic() {
        return ImgPic;
    }

    public void setImgPic(int imgPic) {
        ImgPic = imgPic;
    }

    public Drawable getImgDrawable() {
        return ImgDrawable;
    }

    public void setImgDrawable(Drawable imgDrawable) {
        ImgDrawable = imgDrawable;
    }
}
