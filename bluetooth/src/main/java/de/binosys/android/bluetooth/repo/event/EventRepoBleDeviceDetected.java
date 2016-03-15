package de.binosys.android.bluetooth.repo.event;


import de.binosys.android.bluetooth.repo.BleDevice;



/**
 * Fired when a device is detected during Ble device scan.
 * <p/>
 * Created by Gabriel on 09.02.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class EventRepoBleDeviceDetected {

    public final BleDevice device;

    public EventRepoBleDeviceDetected(BleDevice device) {

        this.device = device;

    }
}
