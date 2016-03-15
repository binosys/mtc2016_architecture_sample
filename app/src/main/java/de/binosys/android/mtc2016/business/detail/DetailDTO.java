/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.business.detail;

import android.os.ParcelUuid;

import java.util.ArrayList;
import java.util.List;



public class DetailDTO {

    private int rssi;
    private String majorClass;
    private String minorClass;
    private String name;
    private String address;
    private String bondState;
    private ParcelUuid[] uuids = new ParcelUuid[0];
    private List<String> services = new ArrayList<>();
    private String connectionState;

    public int getRssi() {

        return rssi;
    }

    void setRssi(int rssi) {

        this.rssi = rssi;
    }

    public String getMajorClass() {

        return majorClass;
    }

    void setMajorClass(String majorClass) {

        this.majorClass = majorClass;
    }

    public String getMinorClass() {

        return minorClass;
    }

    void setMinorClass(String minorClass) {

        this.minorClass = minorClass;
    }

    public String getName() {

        return name;
    }

    void setName(String name) {

        this.name = name;
    }

    public String getAddress() {

        return address;
    }

    void setAddress(String address) {

        this.address = address;
    }

    public String getBondState() {

        return bondState;
    }

    void setBondState(String bondState) {

        this.bondState = bondState;
    }

    public ParcelUuid[] getUuids() {

        return uuids;
    }

    void setUuids(ParcelUuid[] uuids) {

        this.uuids = uuids;
    }

    public void setServices(List<String> services) {

        this.services = services;
    }

    public List<String> getServices() {

        return services;
    }

    public String getConnectionState() {

        return connectionState;
    }

    void setConnectionState(String connectionState) {

        this.connectionState = connectionState;
    }
}

