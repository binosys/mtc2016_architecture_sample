/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.business.detail;

import android.bluetooth.BluetoothGatt;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import de.binosys.android.architecture.bus.BusObserver;
import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.mtc2016.business.DeviceManager;
import de.binosys.android.mtc2016.business.detail.event.EventBusinessDetailDeviceConnected;
import de.binosys.android.bluetooth.business.BleManager;
import de.binosys.android.bluetooth.business.event.EventBusinessBleDeviceServicesScanFinished;
import de.binosys.android.bluetooth.repo.BleDevice;


@BusObserver
public class DetailManager extends DeviceManager {

	@Inject
	BleManager bleManager;
	@Inject
	IBusSystem bus;
	@Inject
	DetailMapper mapper;

	public void connectSelectedDevice() {

		BleDevice device = getSelectedDevice();
		if (device != null) {
			bleManager.connect(device.getAddress());
		}
	}

	public void disconnectSelectedDevice() {

		bleManager.disconnect();
	}

	public DetailDTO getDetailsOfConnectedDevice() {

		BleDevice device = getSelectedDevice();
		BluetoothGatt bluetoothGatt = bleManager.getGatt();

		DetailDTO details = mapper.from(device, bluetoothGatt);

		return details;
	}

	@Subscribe
	public void on(EventBusinessBleDeviceServicesScanFinished event) {

		bus.post(new EventBusinessDetailDeviceConnected());
	}
}
