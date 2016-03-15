package de.binosys.android.bluetooth.business;

import android.bluetooth.BluetoothGatt;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;
import javax.inject.Named;

import de.binosys.android.architecture.bus.BusObserver;
import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.architecture.dagger.ArchitectureModule;
import de.binosys.android.bluetooth.business.event.EventBusinessDeviceConnected;
import de.binosys.android.bluetooth.business.event.EventBusinessDeviceDisconnected;
import de.binosys.android.bluetooth.repo.BleDeviceRepository;
import de.binosys.android.bluetooth.repo.DeviceGenerator;



/**
 * Offers all functionality to handle BLE devices
 * <p/>
 * Created by Gabriel on 21.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
@BusObserver
public class BleManager {

    private static final long DELAY_IN_MS = 2000;


    @Inject
    protected BleDeviceRepository repo;
    @Inject
    protected IBusSystem bus;
    @Inject
    DeviceGenerator generator;
    @Inject
    @Named(ArchitectureModule.NAMED_MAIN_HANDLER)
    Handler handler;


    /**
     * Starts a BLE device scan. When finished an EventBleDeviceScanFinished is fired and the result
     * can be fetched with getAvailableDevices().
     */
    public void startDeviceScan() {

        generator.startDeviceScan();
    }

    /**
     * Immediatly stops the scan for ble devices.
     */
    public void stopDeviceScan() {

        generator.stopDeviceScan();

    }

    public void connect(String address) {


        handler.postDelayed(new PostDeviceConnected(), DELAY_IN_MS);
    }

    public void disconnect() {

        handler.postDelayed(new PostDeviceDisconnected(), DELAY_IN_MS);
    }

    public BluetoothGatt getGatt() {

        return null;
    }


    @VisibleForTesting
    class PostDeviceConnected implements Runnable {

        @Override
        public void run() {

            bus.post(new EventBusinessDeviceConnected());
        }
    }

    @VisibleForTesting
    class PostDeviceDisconnected implements Runnable {

        @Override
        public void run() {

            bus.post(new EventBusinessDeviceDisconnected());
        }
    }
}
