package com.example.util;

/**
 * Created by nurmemet on 2016/5/17.
 */
public class EventBusEv {

    public EventBusEv(String event, Object data) {
        this.event = event;
        this.data = data;
    }

    public String event;
    public Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            EventBusEv ev = (EventBusEv) o;
            if (event.equals(ev.getEvent())) {
                return true;
            }
        }
        return false;
    }

    public static boolean is(EventBusEv ev, String target) {
        if (ev != null && target != null) {
            if (target.equals(ev.getEvent())) {
                return true;
            }
        }
        return false;
    }
}
