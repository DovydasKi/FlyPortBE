package com.example.flyport.userProfile;

public class UserProfileModel {
    private UserLoginInfoModel userLoginInfoModel;
    private UserPersonalInfoModel userPersonalInfoModel;

    UserProfileModel(UserLoginInfoModel userLoginInfoModel, UserPersonalInfoModel userPersonalInfoModel) {
        this.userLoginInfoModel = userLoginInfoModel;
        this.userPersonalInfoModel = userPersonalInfoModel;
    }

    public UserLoginInfoModel getUserLoginInfoModel() {
        return userLoginInfoModel;
    }

    public UserPersonalInfoModel getUserPersonalInfoModel() {
        return userPersonalInfoModel;
    }
}
