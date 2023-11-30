package com.example.collegemate;

public class FloorPlans {
    private int FloorPlanId;
    private String FloorPlanName;
    private int FloorPlanPic;
    private String FloorPlanDetails;

    private int FloorPlanPrice;

    public int getFloorPlanPrice() {
        return FloorPlanPrice;
    }

    public void setFloorPlanPrice(int floorPlanPrice) {
        FloorPlanPrice = floorPlanPrice;
    }

    public String getFloorPlanDetails() {
        return FloorPlanDetails;
    }

    public void setFloorPlanDetails(String floorPlanDetails) {
        FloorPlanDetails = floorPlanDetails;
    }

    public int getFloorPlanId() {
        return FloorPlanId;
    }

    public void setFloorPlanId(int floorPlanId) {
        FloorPlanId = floorPlanId;
    }

    public String getFloorPlanName() {
        return FloorPlanName;
    }

    public void setFloorPlanName(String floorPlanName) {
        FloorPlanName = floorPlanName;
    }

    public int getFloorPlanPic() {
        return FloorPlanPic;
    }

    public void setFloorPlanPic(int floorPlanPic) {
        FloorPlanPic = floorPlanPic;
    }

    public FloorPlans(int floorPlanId, String floorPlanName, int floorPlanPic) {
        FloorPlanId = floorPlanId;
        FloorPlanName = floorPlanName;
        FloorPlanPic = floorPlanPic;
    }

    public FloorPlans(int floorPlanId, String floorPlanName, int floorPlanPic, String floorPlanDetails) {
        FloorPlanId = floorPlanId;
        FloorPlanName = floorPlanName;
        FloorPlanPic = floorPlanPic;
        FloorPlanDetails = floorPlanDetails;
    }

    public FloorPlans(int floorPlanId, String floorPlanName, int floorPlanPic, String floorPlanDetails, int floorPlanPrice) {
        FloorPlanId = floorPlanId;
        FloorPlanName = floorPlanName;
        FloorPlanPic = floorPlanPic;
        FloorPlanDetails = floorPlanDetails;
        FloorPlanPrice = floorPlanPrice;
    }
}
