package de.binosys.android.bluetooth.repo.event;


/**
 * Created by Gabriel on 21.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class EventRepoBleConnecting {

    public final int gattStatus;

    public EventRepoBleConnecting(int gattStatus) {

        this.gattStatus = gattStatus;

    }
}
