package de.binosys.android.bluetooth.repo.event;

import android.bluetooth.BluetoothGatt;



/**
 * Created by Gabriel on 21.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class EventRepoBleServicesDiscovered {

    public final int status;

    public EventRepoBleServicesDiscovered(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EventBleServicesDiscovered.status: " + getStatusString();
    }

    public String getStatusString() {

        String statusString = "";
        if (status == BluetoothGatt.GATT_SUCCESS) {
            statusString = "GATT_SUCCESS";
        } else if (status == BluetoothGatt.GATT_READ_NOT_PERMITTED) {
            statusString = "GATT_READ_NOT_PERMITTED";
        } else if (status == BluetoothGatt.GATT_WRITE_NOT_PERMITTED) {
            statusString = "GATT_WRITE_NOT_PERMITTED";
        } else if (status == BluetoothGatt.GATT_INSUFFICIENT_AUTHENTICATION) {
            statusString = "GATT_INSUFFICIENT_AUTHENTICATION";
        } else if (status == BluetoothGatt.GATT_REQUEST_NOT_SUPPORTED) {
            statusString = "GATT_REQUEST_NOT_SUPPORTED";
        } else if (status == BluetoothGatt.GATT_INSUFFICIENT_ENCRYPTION) {
            statusString = "GATT_INSUFFICIENT_ENCRYPTION";
        } else if (status == BluetoothGatt.GATT_INVALID_OFFSET) {
            statusString = "GATT_INVALID_OFFSET";
        } else if (status == BluetoothGatt.GATT_INVALID_ATTRIBUTE_LENGTH) {
            statusString = "GATT_INVALID_ATTRIBUTE_LENGTH";
        } else if (status == BluetoothGatt.GATT_CONNECTION_CONGESTED) {
            statusString = "GATT_CONNECTION_CONGESTED";
        } else if (status == BluetoothGatt.GATT_FAILURE) {
            statusString = "GATT_FAILURE";
        }

        return statusString;
    }
}
