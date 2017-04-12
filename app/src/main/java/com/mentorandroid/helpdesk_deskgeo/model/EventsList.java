package com.mentorandroid.helpdesk_deskgeo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunodelhferreira on 02/04/17.
 */

public class EventsList {

    @SerializedName("EventsList")
    private List<Events> eventsList = new ArrayList<Events>();

    public List<Events> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Events> eventsList) {
        this.eventsList = eventsList;
    }
}
