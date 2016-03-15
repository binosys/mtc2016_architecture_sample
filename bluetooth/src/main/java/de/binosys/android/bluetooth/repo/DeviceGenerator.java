package de.binosys.android.bluetooth.repo;


import android.os.Handler;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;
import javax.inject.Named;

import de.binosys.android.architecture.dagger.ArchitectureModule;



/**
 * Created by Gabriel on 10.03.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class DeviceGenerator {


	private static final long DELAY_IN_MS = 1000;

	private String[] addresses = {
			"80:44:3E:3F:A1:4A",
			"10:86:E5:E6:C1:4A",
			"32:47:23:EF:B1:4A",
			"45:43:11:FF:E1:4A",
			"86:76:1E:45:A1:4A",
			"13:87:B4:E1:A1:4A",
			"10:03:BF:76:D1:4A",
			"56:92:BE:33:D1:4A",
			"E3:01:BAE:35:31:4A",
			"AA:00:AE:76:41:4A",
	};

	private String[] names = {
			"Fitsyou-34",
			"Wristband",
			"Goggle",
			"Xasd34",
			"Skoda-1",
			"MAC_GoGo",
			"ASDGT_234",
			"123_DHSD",
			"FHSD",
			"MalcolmX",
	};

	@Inject
	BleDeviceRepository repo;

	@Inject
	@Named(ArchitectureModule.NAMED_MAIN_HANDLER)
	Handler handler;


	GeneratorRunner runner = new GeneratorRunner();


	public void startDeviceScan() {

		handler.post(runner);
	}


	public void stopDeviceScan() {

		handler.removeCallbacks(runner);
		runner.count = 0;
	}


	@VisibleForTesting
	class GeneratorRunner implements Runnable {

		int count = 0;


		private GeneratorRunner() {
		}


		@Override
		public void run() {

			BleDevice device = createRandomDevice();
			repo.put(String.valueOf(device.getAddress()), device);

			count++;

			handler.postDelayed(this, DELAY_IN_MS);
		}


		private BleDevice createRandomDevice() {

			String address = addresses[count % addresses.length];
			String name = names[count % names.length];
			int rssi = (int) (Math.random() * 100);
			BleDevice bleDevice = new BleDevice(address, name, rssi, null, null);

			return bleDevice;
		}
	}
}
