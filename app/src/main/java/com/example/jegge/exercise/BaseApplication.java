package com.example.jegge.exercise;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Jegge on 2016-12-11.
 */
public class BaseApplication extends Application {
    @Override public void onCreate(){
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
