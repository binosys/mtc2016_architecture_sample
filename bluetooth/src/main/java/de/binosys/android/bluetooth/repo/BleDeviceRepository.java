package de.binosys.android.bluetooth.repo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.bluetooth.repo.event.EventRepoAddedNewDevice;



/**
 * Repository for all devices which are found by a ble device scan.
 * <p/>
 * Created by Gabriel on 20.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
@Singleton
public class BleDeviceRepository {


    private Map<String, BleDevice> devices = new HashMap<>();

    @Inject
    IBusSystem bus;

    @Inject
    public BleDeviceRepository(IBusSystem bus){
        this.bus = bus;
    }

    /**
     * Adds the device to the repo. If the device already exists in the repo it is overwritten.
     *
     * @param address
     * @param device
     */
    public void put(String address, BleDevice device) {

        BleDevice previous = devices.put(address, device);
        if(previous == null){
            bus.post(new EventRepoAddedNewDevice(address));
        }
    }


    /**
     * @return list of all available devices
     */
    public @NonNull List<BleDevice> getAllDevices() {

        List<BleDevice> deviceList = new ArrayList<>(devices.values());
        return deviceList;
    }

    /**
     * Resets the BleDeviceRepo and removes all scanned devices.
     */
    public void reset() {

        devices.clear();
    }

    /**
     *
     * @param address
     *
     * @return the device with the given address or null;
     */
    public @Nullable BleDevice getDeviceWith(String address) {

        return devices.get(address);
    }
}
