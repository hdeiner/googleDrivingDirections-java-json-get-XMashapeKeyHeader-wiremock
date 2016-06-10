package main.java;


public class GoogleDrivingDirectionsLeg {

    private int    leg;
    private String distance;
    private String duration;
    private String direction;

    public GoogleDrivingDirectionsLeg(int leg, String distance, String duration, String direction) {
        this.leg       = leg;
        this.distance  = distance;
        this.duration  = duration;
        this.direction = direction;
    }

    public int getLeg() {
        return leg;
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration(){
        return duration;
    }

    public String getDirection(){
        String directionToReturn = direction;
        directionToReturn = directionToReturn.replaceAll("<div.*?>"," ");
        directionToReturn = directionToReturn.replaceAll("<.*?>","");
        return directionToReturn;
    }

}
