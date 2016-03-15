package de.binosys.android.bluetooth.repo.event;

/**
 * Created by Gabriel on 21.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class EventRepoBleDisconnected {

    public final int gattStatus;

    public EventRepoBleDisconnected(int gattStatus) {

        this.gattStatus = gattStatus;

    }
}
