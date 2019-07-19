package com.example.paymat;

public class Upload {
    private String mName;
    private String mRoute;
    private String mGroup;
    private String mImageUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name,String Route,String Group, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mRoute=Route;
        mGroup=Group;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
    public String getgroup(){
        return mGroup;
    }
    public void setgroup(String Group){
        mGroup=Group;
    }
    public String getroute(){
        return mRoute;
    }
    public void setroute(String route){
        mRoute=route;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}

