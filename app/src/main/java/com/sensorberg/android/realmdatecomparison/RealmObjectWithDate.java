package com.sensorberg.android.realmdatecomparison;

import java.util.Date;

import io.realm.RealmObject;

public class RealmObjectWithDate extends RealmObject {

    private Date anyDate;

    public Date getAnyDate() {
        return anyDate;
    }

    public void setAnyDate(Date anyDate) {
        this.anyDate = anyDate;
    }
}
