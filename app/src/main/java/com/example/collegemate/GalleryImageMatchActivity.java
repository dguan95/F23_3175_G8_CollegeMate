package com.example.collegemate;

public class GalleryImageMatchActivity {
    private String Info;
    private String ImgName;

    private String major;
    private int ImgPic;

    public GalleryImageMatchActivity(String info, String imgName, int imgPic, String major) {
        Info = info;
        ImgName = imgName;
        ImgPic = imgPic;
        this.major = major;
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

}
