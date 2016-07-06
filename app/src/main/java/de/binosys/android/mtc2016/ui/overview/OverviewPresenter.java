/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.overview;

import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.binosys.android.architecture.bus.BusObserver;
import de.binosys.android.bluetooth.repo.BleDevice;
import de.binosys.android.mtc2016.business.overview.OverviewManager;
import de.binosys.android.mtc2016.business.overview.event.EventBusinessFoundNewDevice;
import de.binosys.android.mtc2016.ui.detail.DetailActivity;


@Singleton
@BusObserver
public class OverviewPresenter implements IOverviewPresenter {

	@Inject
	OverviewManager manager;
	private IOverviewView view;

	@Subscribe
	public void on(EventBusinessFoundNewDevice event) {

		BleDevice device = manager.getDeviceWith(event.address);
		view.addDeviceItem(device);
	}

	@Override
	public void onConnectButtonClick(BleDevice device) {

		manager.setSelectedDevice(device);
		view.startActivity(DetailActivity.class);
	}

	@Override
	public void onViewAttached() {

		manager.startDeviceScan();
		initList();
	}

	@Override
	public void onViewDetached() {

		manager.stopDeviceScan();
	}

	@Override
	public void setView(IOverviewView view) {

		this.view = view;
	}

	private void initList() {

		List<BleDevice> devices = manager.getAllDevices();
		view.setDeviceItems(devices);
	}
}
