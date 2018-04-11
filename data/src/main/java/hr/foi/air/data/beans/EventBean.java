package hr.foi.air.data.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gabriel on 30.3.2018..
 */

public class EventBean implements Parcelable {
    private int currentCapacity;
    private int maxCapacity;
    private boolean openEvent;
    private String creatorUserName;
    private String title;
    private String startTime;
    private String endTime;
    private String description;
    private String address;
    private String sport;
    private String location;

    public EventBean() {
    }

    public EventBean(int currentCapacity, int maxCapacity, boolean openEvent, String creatorUserName, String title, String startTime, String endTime, String description, String address, String sport, String location) {
        this.currentCapacity = currentCapacity;
        this.maxCapacity = maxCapacity;
        this.openEvent = openEvent;
        this.creatorUserName = creatorUserName;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.address = address;
        this.sport = sport;
        this.location = location;
    }

    protected EventBean(Parcel in) {
        currentCapacity = in.readInt();
        maxCapacity = in.readInt();
        openEvent = in.readByte() != 0;
        creatorUserName = in.readString();
        title = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        description = in.readString();
        address = in.readString();
        sport = in.readString();
        location = in.readString();
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean isOpenEvent() {
        return openEvent;
    }

    public void setOpenEvent(boolean openEvent) {
        this.openEvent = openEvent;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static final Creator<EventBean> CREATOR = new Creator<EventBean>() {
        @Override
        public EventBean createFromParcel(Parcel in) {
            return new EventBean(in);
        }

        @Override
        public EventBean[] newArray(int size) {
            return new EventBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentCapacity);
        dest.writeInt(maxCapacity);
        dest.writeByte((byte) (openEvent ? 1 : 0));
        dest.writeString(creatorUserName);
        dest.writeString(title);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(sport);
        dest.writeString(location);
    }
}
