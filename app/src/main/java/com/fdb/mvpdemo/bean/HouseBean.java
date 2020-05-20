package com.fdb.mvpdemo.bean;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class HouseBean {
    public int id;
    public String mainPhotoUrl;
    public String communityName;

    @Override
    public String toString() {
        return "HouseBean{" +
                "id=" + id +
                ", mainPhotoUrl='" + mainPhotoUrl + '\'' +
                ", communityName='" + communityName + '\'' +
                '}';
    }
}
