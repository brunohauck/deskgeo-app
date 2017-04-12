package com.mentorandroid.helpdesk_deskgeo.model;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunodelhferreira on 25/03/17.
 */

public class DosList {

    @SerializedName("DosList")
    private List<Dos> dosList = new ArrayList<Dos>();

    public List<Dos> getDosList() {
        return dosList;
    }

    public void setDosList(List<Dos> dosList) {
        this.dosList = dosList;
    }
}
