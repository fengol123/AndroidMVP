package com.fdb.mvpdemo.bean;

import com.fdb.baselibrary.bean.OldBaseBean;

import java.util.List;

/**
 * Desc
 * Author dontlo
 * Date   2020/4/18.
 */
public class DemandDetail extends OldBaseBean<DemandDetail.DataBean> {
    public class DataBean{
        public boolean IsPullOffShelves;
        public Object Score;
        public ShareUserInfoBean ShareUserInfo;
        public CustomerDemandMainBean CustomerDemandMain;
        public Object HouseCommission;
        public List<AreaArrayBean> AreaArray;
        public List<CustomerDemandCitiesBean> CustomerDemandCities;
        public List<CustomerDemandAreasBean> CustomerDemandAreas;
        public List<?> CustomerDemandSubDistricts;
        public List<?> CustomerDemandCommunities;
        public List<CustomerDemandBedroomsBean> CustomerDemandBedrooms;
        public List<CustomerDemandPropertyTypesBean> CustomerDemandPropertyTypes;
        public List<CustomerDemandFloorTypesBean> CustomerDemandFloorTypes;
        public List<CustomerDemandCardinalDirectionsBean> CustomerDemandCardinalDirections;
        public List<CustomerDemandHouseFeaturesBean> CustomerDemandHouseFeatures;
    }

    public static class ShareUserInfoBean {
        public String ProfilePhotoPath;
        public String TraderName;
        public String ShopName;
        public boolean CerStatus;
        public String Mobile;
        public String IMUserName;
    }

    public static class CustomerDemandMainBean {
        public boolean IsValid;
        public boolean IsTobeMatch;
        public String CustomerName;
        public int MatchHouseCount;
        public String LastMatchTime;
        public boolean IsCollect;
        public int MinimumHouseSize;
        public int MaximumHouseSize;
        public int Id;
        public int CreatedBy;
        public String CreatedDate;
        public Object UpdatedBy;
        public String UpdateDate;
        public int ShopId;
        public int DepartmentId;
        public String Code;
        public String ExpirationDate;
        public String Title;
        public Object Note;
        public int EstateType;
        public int HouseType;
        public int MaximumBudget;
        public int MinimumBudget;
        public Object RentingPlaceType;
        public Object PreferredRentingBedroomType;
        public Object PreferredRentingGenderType;
        public boolean IsMLS;
    }

    public static class AreaArrayBean {
        public String AreaName;
        public List<?> SubArray;
    }

    public static class CustomerDemandCitiesBean {
        public String CityText;
        public int CustomerDemandId;
        public int CityId;
    }

    public static class CustomerDemandAreasBean {
        public String AreaText;
        public String ParentText;
        public int CustomerDemandId;
        public int AreaId;
    }

    public static class CustomerDemandBedroomsBean {
        public String BedroomText;
        public int BedroomId;
        public int CustomerDemandId;
    }

    public static class CustomerDemandPropertyTypesBean {
        public int PropertyTypeId;
        public String PropertyTypeText;
        public int CustomerDemandId;
    }

    public static class CustomerDemandFloorTypesBean {
        public int FloorTypeId;
        public String FloorTypeText;
        public int CustomerDemandId;
    }

    public static class CustomerDemandCardinalDirectionsBean {
        public int CardinalDirectionId;
        public String CardinalDirectionText;
        public int CustomerDemandId;
    }

    public static class CustomerDemandHouseFeaturesBean {
        public int HouseFeatureId;
        public String HouseFeatureText;
        public int CustomerDemandId;
    }
}
