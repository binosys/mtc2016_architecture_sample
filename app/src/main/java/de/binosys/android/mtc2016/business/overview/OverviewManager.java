/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.business.overview;

import android.os.Handler;
import android.support.annotation.VisibleForTesting;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;
import javax.inject.Named;

import de.binosys.android.architecture.bus.BusObserver;
import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.mtc2016.business.DeviceManager;
import de.binosys.android.mtc2016.business.overview.event.EventBusinessFoundNewDevice;
import de.binosys.android.bluetooth.business.BleManager;
import de.binosys.android.bluetooth.repo.event.EventRepoAddedNewDevice;

import static de.binosys.android.architecture.dagger.ArchitectureModule.NAMED_MAIN_HANDLER;



@BusObserver
public class OverviewManager extends DeviceManager{


    private static final long SCAN_INTERVAL_IN_MS = 15000;

    @Inject
    BleManager bleManager;
    @Inject
    @Named(NAMED_MAIN_HANDLER)
    Handler handler;
    @Inject
    IBusSystem bus;

    StopScanRunnable stopScanRunnable = new StopScanRunnable();


    public void startDeviceScan() {

        bleManager.startDeviceScan();
        handler.postDelayed(stopScanRunnable, SCAN_INTERVAL_IN_MS);
    }

    public void stopDeviceScan() {

        bleManager.stopDeviceScan();
    }


    @VisibleForTesting
    class StopScanRunnable implements Runnable {

        @Override
        public void run() {

            bleManager.stopDeviceScan();
        }
    }

    @Subscribe
    public void on(EventRepoAddedNewDevice event){
        bus.post(new EventBusinessFoundNewDevice(event.address));
    }
}
