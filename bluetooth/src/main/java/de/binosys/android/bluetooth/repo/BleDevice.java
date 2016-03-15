package de.binosys.android.bluetooth.repo;

import android.bluetooth.BluetoothDevice;



/**
 * Represents a Bluetooth LE device.
 *
 * Created by Gabriel on 19.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class BleDevice {
    private final String address;
    private final String name;
    private final int rssi;
    private final byte[] scanRecord;
    private BluetoothDevice bluetoothDevice;


    public BleDevice(String address, String name, int rssi, byte[] scanRecord, BluetoothDevice device) {
        this.address = address;
        this.name = name;
        this.rssi = rssi;
        this.scanRecord = scanRecord;
        this.bluetoothDevice = device;
    }


    public String getAddress() {

        return address;
    }

    public String getName() {

        return name;
    }

    public int getRssi() {

        return rssi;
    }

    public byte[] getScanRecord() {

        return scanRecord;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BleDevice bleDevice = (BleDevice) o;

        return address != null ? address.equals(bleDevice.address) : bleDevice.address == null;

    }

    @Override
    public int hashCode() {

        return address != null ? address.hashCode() : 0;
    }


    public BluetoothDevice getBluetoothDevice() {

        return bluetoothDevice;
    }

}
