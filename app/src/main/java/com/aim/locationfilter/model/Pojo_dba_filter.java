/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 1:00 PM
 *
 */

package com.aim.locationfilter.model;

public class Pojo_dba_filter {

    int type;
    String id;
    String name;
    String cid;
    String cidName;

    public Pojo_dba_filter(int type, String id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCidName() {
        return cidName;
    }

    public void setCidName(String cidName) {
        this.cidName = cidName;
    }
}
