package de.binosys.android.bluetooth.repo;


import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;



public class DeviceGeneratorTest {


	@Mock
	Handler             mockHandler;
	@Mock
	BleDeviceRepository mockRepo;

	DeviceGenerator testee;


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		testee = new DeviceGenerator();
		testee.handler = mockHandler;
		testee.repo = mockRepo;
	}


	@Test
	public void stopDeviceScan() throws Exception {

		// Arrange
		testee.runner.count = 2;
		// Act
		testee.stopDeviceScan();
		// Assert
		verify(mockHandler).removeCallbacks(testee.runner);
		assertEquals(0, testee.runner.count);

	}


	@Test
	public void startDeviceScan() throws Exception {

		// Act
		testee.startDeviceScan();
		// Assert
		verify(mockHandler).post(testee.runner);
	}


	@Test
	public void runner_run() {
		// Arrange
		ArgumentCaptor<BleDevice> captor = ArgumentCaptor.forClass(BleDevice.class);
		// Act
		testee.runner.run();
		// Assert
		assertEquals(1, testee.runner.count);
		verify(mockRepo).put(eq("80:44:3E:3F:A1:4A"), captor.capture());

		BleDevice value = captor.getValue();
		assertEquals("80:44:3E:3F:A1:4A", value.getAddress());
		assertEquals("Fitsyou-34", value.getName());
		assertTrue(value.getRssi() >= 0);
		assertTrue(value.getRssi() <= 100);
		assertEquals(null, value.getScanRecord());

	}
}