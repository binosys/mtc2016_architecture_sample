/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.business.detail;

import android.bluetooth.BluetoothGatt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.mtc2016.business.detail.DetailDTO;
import de.binosys.android.mtc2016.business.detail.DetailManager;
import de.binosys.android.mtc2016.business.detail.DetailMapper;
import de.binosys.android.mtc2016.business.detail.event.EventBusinessDetailDeviceConnected;
import de.binosys.android.mtc2016.repository.AppRepository;
import de.binosys.android.bluetooth.business.BleManager;
import de.binosys.android.bluetooth.repo.BleDevice;
import de.binosys.android.bluetooth.repo.BleDeviceRepository;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gabriel on 11.03.16.
 */
public class DetailManagerTest {


    private class DetailManagerTestee extends DetailManager {

        public DetailManagerTestee(BleDeviceRepository mockDeviceRepo, AppRepository mockAppRepo,
                                   BleManager mockBleManager, IBusSystem mockBus, DetailMapper mockMapper) {
            bleRepository = mockDeviceRepo;
            appRepository = mockAppRepo;
            bus = mockBus;
            bleManager = mockBleManager;
            mapper = mockMapper;
        }
    }

    DetailManagerTestee testee;


    @Mock
    private IBusSystem mockBus;
    @Mock
    private BleManager mockBleManager;
    @Mock
    private BleDeviceRepository mockDeviceRepo;
    @Mock
    private AppRepository mockAppRepo;
    @Mock
    private DetailMapper mockMapper;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        testee = new DetailManagerTestee(mockDeviceRepo, mockAppRepo, mockBleManager, mockBus, mockMapper);
    }

    @Test
    public void connectSelectedDevice() throws Exception {

        // Arrange
        BleDevice device = new BleDevice("123", "name", 0, null, null);
        when(mockAppRepo.getSelectedDeviceAddress()).thenReturn("123");
        when(mockDeviceRepo.getDeviceWith("123")).thenReturn(device);
        // Act
        testee.connectSelectedDevice();
        // Assert
        verify(mockBleManager).connect("123");
    }

    @Test
    public void disconnectSelectedDevice() throws Exception {

        // Act
        testee.disconnectSelectedDevice();
        // Assert
        verify(mockBleManager).disconnect();
    }

    @Test
    public void getDetailsOfConnectedDevice() throws Exception {
        // Arrange
        BleDevice device = new BleDevice("123", "name", 0, null, null);
        BluetoothGatt mockGatt = mock(BluetoothGatt.class);
        DetailDTO detailDto = new DetailDTO();
        when(mockAppRepo.getSelectedDeviceAddress()).thenReturn("123");
        when(mockDeviceRepo.getDeviceWith("123")).thenReturn(device);
        when(mockBleManager.getGatt()).thenReturn(mockGatt);
        when(mockMapper.from(device, mockGatt)).thenReturn(detailDto);

        // Act
        DetailDTO result = testee.getDetailsOfConnectedDevice();
        // Assert
        assertEquals(detailDto, result);
    }

    @Test
    public void on() throws Exception {
        // Act
        testee.on(null);
        // Assert
        verify(mockBus).post(any(EventBusinessDetailDeviceConnected.class));
    }
}