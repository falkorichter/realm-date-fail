package com.sensorberg.android.realmdatecomparison;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.fest.assertions.api.Assertions;

import java.util.Date;

import io.realm.Realm;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test_realm_date_comparison() throws Exception {
        Realm realm = Realm.getInstance(getContext(), String.valueOf(System.currentTimeMillis()));
        Date firstDate = new Date(0);
        Date date2 = new Date(999);

        Assertions.assertThat(realm.where(RealmObjectWithDate.class).findAll()).hasSize(0);

        realm.beginTransaction();
        RealmObjectWithDate object1 = realm.createObject(RealmObjectWithDate.class);

        object1.setAnyDate(firstDate);
        RealmObjectWithDate object2 = realm.createObject(RealmObjectWithDate.class);

        object2.setAnyDate(date2);
        realm.commitTransaction();

        Assertions.assertThat(realm.where(RealmObjectWithDate.class).findAll()).hasSize(2);

        //these two dates are not equal!
        Assertions.assertThat(firstDate.compareTo(date2)).isNotEqualTo(0);

        //should only find object2
        Assertions.assertThat(realm.where(RealmObjectWithDate.class)
                .greaterThan("anyDate", firstDate)
                .findAll()).hasSize(1);

    }
}