package com.fdb.mvpdemo.bean;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/16.
 */
public class HouseBean {
    public String updateDateStr;
    public int houseType;
    public int collectionSourceType;
    public boolean isPullOffShelves;
    public String h5Link;
    public int collectId;
    public int id;
    public String mainPhotoUrl;
    public String communityName;
    public int houseSource;
    public int isMls;
    public String houseLayout;
    public String areas;
    public String totalPrice;
    public String rentPrice;
    public String averagePrice;
    public int isUseVR;
    public String orientation;
    public int totalFloor;
    public int floor;
    public String createdDate;
    public int isSoleAgent;
    public int houseId;
    public boolean isSelected;

    @Override
    public String toString() {
        return "HouseBean{" +
                "updateDateStr='" + updateDateStr + '\'' +
                ", houseType=" + houseType +
                ", collectionSourceType=" + collectionSourceType +
                ", isPullOffShelves=" + isPullOffShelves +
                ", h5Link='" + h5Link + '\'' +
                ", collectId=" + collectId +
                ", id=" + id +
                ", mainPhotoUrl='" + mainPhotoUrl + '\'' +
                ", communityName='" + communityName + '\'' +
                ", houseSource=" + houseSource +
                ", isMls=" + isMls +
                ", houseLayout='" + houseLayout + '\'' +
                ", areas='" + areas + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", rentPrice='" + rentPrice + '\'' +
                ", averagePrice='" + averagePrice + '\'' +
                ", isUseVR=" + isUseVR +
                ", orientation='" + orientation + '\'' +
                ", totalFloor=" + totalFloor +
                ", floor=" + floor +
                ", createdDate='" + createdDate + '\'' +
                ", isSoleAgent=" + isSoleAgent +
                ", houseId=" + houseId +
                ", isSelected=" + isSelected +
                '}';
    }
}
