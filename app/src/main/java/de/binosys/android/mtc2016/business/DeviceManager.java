/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.business;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import javax.inject.Inject;

import de.binosys.android.mtc2016.repository.AppRepository;
import de.binosys.android.bluetooth.repo.BleDevice;
import de.binosys.android.bluetooth.repo.BleDeviceRepository;


public class DeviceManager {

    @Inject
    protected BleDeviceRepository bleRepository;
    @Inject
    protected AppRepository appRepository;

    public BleDevice getDeviceWith(String address) {

        return bleRepository.getDeviceWith(address);
    }

    public
    @NonNull
    List<BleDevice> getAllDevices() {

        return bleRepository.getAllDevices();
    }

    public void setSelectedDevice(BleDevice selectedDevice){
        appRepository.setSelectedDeviceAddress(selectedDevice.getAddress());
    }

    public BleDevice getSelectedDevice(){
        String address = appRepository.getSelectedDeviceAddress();
        return bleRepository.getDeviceWith(address);
    }


    @VisibleForTesting
    public void setBleDeviceRepository(BleDeviceRepository repo) {

        bleRepository = repo;
    }


    @VisibleForTesting
    public void setAppRepository(AppRepository repo) {

        appRepository = repo;
    }
}
