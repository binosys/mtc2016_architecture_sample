/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.repository;

import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
public class AppRepository {

    private String selectedDeviceAddress;

    @Inject
    public AppRepository() {

    }


    public void setSelectedDeviceAddress(String address) {

        this.selectedDeviceAddress = address;
    }

    public String getSelectedDeviceAddress() {

        return selectedDeviceAddress;
    }
}
