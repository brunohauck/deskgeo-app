package com.mentorandroid.helpdesk_deskgeo.model;

/**
 * Created by brunodelhferreira on 02/04/17.
 */

public class Events {

    private String title;

    private String description;

    private String diagnostic;

    private Status status;

    private Slas slas;

    private EventType eventType;

    public Slas getSlas() {
        return slas;
    }

    public void setSlas(Slas slas) {
        this.slas = slas;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
