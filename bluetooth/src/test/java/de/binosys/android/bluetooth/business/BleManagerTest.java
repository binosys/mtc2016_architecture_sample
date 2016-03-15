package de.binosys.android.bluetooth.business;


import android.app.Activity;
import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.binosys.android.bluetooth.repo.BleDeviceRepository;
import de.binosys.android.bluetooth.repo.DeviceGenerator;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;



/**
 * Created by Gabriel on 22.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class BleManagerTest {

	BleManager testee;
	@Mock
	private BleDeviceRepository mockRepo;
	@Mock
	private Activity            mockActivity;
	@Mock
	private DeviceGenerator     mockGenerator;
	@Mock
	Handler mockHandler;


	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		testee = new BleManager();
		testee.repo = mockRepo;
		testee.generator = mockGenerator;
		testee.handler = mockHandler;
	}


	@Test
	public void startDeviceScan() {

		// Act
		testee.startDeviceScan();
		// Assert
		verify(mockGenerator).startDeviceScan();
	}


	@Test
	public void stopDeviceScan() {

		// Act
		testee.stopDeviceScan();
		// Assert
		verify(mockGenerator).stopDeviceScan();
	}


	@Test
	public void connect() {

		// Act
		testee.connect("");
		// Assert
		verify(mockHandler).postDelayed(any(BleManager.PostDeviceConnected.class), eq(2000L));
	}


	@Test
	public void disconnect() {

		// Act
		testee.disconnect();
		// Assert
		verify(mockHandler).postDelayed(any(BleManager.PostDeviceDisconnected.class), eq(2000L));
	}


	@Test
	public void getGatt() {
		// Act/Assert
		assertNull(testee.getGatt());
	}

}