/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.business.overview;

import android.os.Handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.mtc2016.business.overview.OverviewManager;
import de.binosys.android.mtc2016.repository.AppRepository;
import de.binosys.android.bluetooth.business.BleManager;
import de.binosys.android.bluetooth.repo.BleDeviceRepository;



public class OverviewManagerTest {


    @Mock
    IBusSystem mockBus;
    @Mock
    private BleManager mockBleManager;
    @Mock
    private BleDeviceRepository mockBleDeviceRepository;
    @Mock
    private AppRepository mockAppRepository;
    @Mock
    private Handler mockHandler;

    private OverviewManager testee;

    @Before
    public void setUp() {

        testee = new OverviewManager();

        testee.bleManager = mockBleManager;
        testee.bus = mockBus;
        testee.handler = mockHandler;
        testee.setAppRepository(mockAppRepository);
        testee.setBleDeviceRepository(mockBleDeviceRepository);
    }

    @Test
    public void testStartDeviceScan() throws Exception {

    }

    @Test
    public void testStopDeviceScan() throws Exception {

    }

    @Test
    public void testGetDeviceWith() throws Exception {

    }

    @Test
    public void testOn() throws Exception {

    }
}