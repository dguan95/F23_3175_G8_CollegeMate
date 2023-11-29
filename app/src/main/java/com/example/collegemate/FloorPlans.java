package com.example.collegemate;

public class FloorPlans {
    private int FloorPlanId;
    private String FloorPlanName;
    private int FloorPlanPic;

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
}
