package com.example.collegemate;

public class GalleryImageMatchActivity {
    private String Info;
    private String ImgName;
    private int ImgPic;

    public GalleryImageMatchActivity(String imgId, String imgName, int imgPic) {
        Info = imgId;
        ImgName = imgName;
        ImgPic = imgPic;
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
