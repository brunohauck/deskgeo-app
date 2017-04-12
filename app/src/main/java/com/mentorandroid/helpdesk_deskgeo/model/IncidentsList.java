package com.mentorandroid.helpdesk_deskgeo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunodelhferreira on 12/04/17.
 */

public class IncidentsList {

    @SerializedName("EventsList")
    private List<Incident> incidentList = new ArrayList<Incident>();

    public List<Incident> getIncidentList() {
        return incidentList;
    }

    public void setIncidentList(List<Incident> incidentList) {
        this.incidentList = incidentList;
    }
}
