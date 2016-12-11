package com.example.jegge.exercise;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jegge on 2016-12-11.
 */
public class Exercise extends RealmObject {
    @PrimaryKey
    private long eid;
    private String name;
}
