package de.binosys.android.bluetooth.repo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.bluetooth.repo.event.EventRepoAddedNewDevice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



/**
 * Created by Gabriel on 04.02.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class BleDeviceRepositoryTest {


    private BleDeviceRepository testee;
    @Mock
    private IBusSystem mockBus;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        testee = new BleDeviceRepository(mockBus);
    }

    @Test
    public void put_new_device() throws Exception {

        // Arrange
        BleDevice device = new BleDevice("address", "name", 0, new byte[0], null);
        ArgumentCaptor<EventRepoAddedNewDevice> captor = ArgumentCaptor.forClass(EventRepoAddedNewDevice.class);
        // Act
        testee.put("123", device);
        // Assert
        assertEquals(1, testee.getAllDevices().size());
        assertEquals(device, testee.getAllDevices().get(0));

        verify(mockBus, times(1)).post(captor.capture());
        assertNotNull(captor.getValue());
        assertEquals("123", captor.getValue().address);
    }

    @Test
    public void put_existing_device() throws Exception {

        // Arrange
        BleDevice device = new BleDevice("address", "name", 0, new byte[0], null);
        ArgumentCaptor<EventRepoAddedNewDevice> captor = ArgumentCaptor.forClass(EventRepoAddedNewDevice.class);
        testee.put("123", device);
        // Act
        testee.put("123", device);
        // Assert
        assertEquals(1, testee.getAllDevices().size());
        assertEquals(device, testee.getAllDevices().get(0));

        verify(mockBus, times(1)).post(captor.capture());
    }

    @Test
    public void put_different_devices_for_same_address() throws Exception {

        // Arrange
        BleDevice device1 = new BleDevice("123", "name", 0, new byte[0], null);
        BleDevice device2 = new BleDevice("123", "name", 0, new byte[0], null);
        // Act
        testee.put("123", device1);
        testee.put("123", device2);
        // Assert
        assertEquals(1, testee.getAllDevices().size());
        assertEquals(device2, testee.getAllDevices().get(0));
    }

    @Test
    public void put_null() throws Exception {

        // Arrange
        // Act
        testee.put("123", null);
        // Assert
        assertEquals(1, testee.getAllDevices().size());
        assertNull(testee.getAllDevices().get(0));
    }


    @Test
    public void getAllDevices_no_devices() throws Exception {
        // Act
        List<BleDevice> devices = testee.getAllDevices();
        // Assert
        assertNotNull(devices);
        assertEquals(0, devices.size());
    }


    @Test
    public void reset() {
        // Arrange
        BleDevice device1 = new BleDevice("123", "name", 0, new byte[0], null);
        BleDevice device2 = new BleDevice("123", "name", 0, new byte[0], null);
        testee.put("1", device1);
        testee.put("2", device2);
        // Act
        testee.reset();
        // Assert
        assertEquals(0, testee.getAllDevices().size());
    }

    @Test
    public void getDeviceWith_when_empty() {
        // Act
        BleDevice device = testee.getDeviceWith("");
        // Assert
        assertNull(device);
    }

    @Test
    public void getDeviceWith_address_null() {
        // Act
        BleDevice device = testee.getDeviceWith(null);
        // Assert
        assertNull(device);
    }

    @Test
    public void getDeviceWith_address_not_available() {
        // Act
        BleDevice device = testee.getDeviceWith("1234");
        // Assert
        assertNull(device);
    }

    @Test
    public void getDeviceWith_address_available() {
        // Arrange
        BleDevice testDevice = new BleDevice("123", "name", 0, new byte[0], null);
        testee.put("1234", testDevice);
        // Act
        BleDevice device = testee.getDeviceWith("1234");
        // Assert
        assertEquals(testDevice, device);
    }
}