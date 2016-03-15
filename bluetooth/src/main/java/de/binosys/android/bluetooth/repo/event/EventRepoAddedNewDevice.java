package de.binosys.android.bluetooth.repo.event;

/**
 * Event that indicates that a new devices has been added to the repo
 *
 * Created by Gabriel on 01.03.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class EventRepoAddedNewDevice {

    public final String address;

    public EventRepoAddedNewDevice(String address) {

        this.address = address;
    }
}
