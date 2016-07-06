/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.overview;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import de.binosys.android.architecture.bus.BusRegisterer;
import de.binosys.android.bluetooth.repo.BleDevice;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;



public class OverviewFragmentTest {


    @Mock
    private BusRegisterer mockRegisterer;
    @Mock
    private OverviewPresenter mockPresenter;
    @Mock
    private OverviewListAdapter mockListAdapter;

    private OverviewFragment testee;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        testee = new OverviewFragment();
        testee.presenter = mockPresenter;
        testee.registerer = mockRegisterer;
        testee.listAdapter = mockListAdapter;
    }




    @Test
    public void setDeviceItems() throws Exception {
        // Arrange
        List<BleDevice> devices = new ArrayList<>(1);
        devices.add(mock(BleDevice.class));
        // Act
        testee.setDeviceItems(devices);
        // Assert
        verify(mockListAdapter).clear();
        verify(mockListAdapter).addAll(devices);
        verify(mockListAdapter).notifyDataSetChanged();
    }

    @Test
    public void setDeviceItems_null_parameter() throws Exception {
        // Act
        testee.setDeviceItems(null);
        // Assert
        verifyZeroInteractions(mockListAdapter);
    }

    @Test
    public void onCreate() throws Exception {
        // Act
        testee.onCreate(null);
        // Assert
        verify(mockPresenter).setView(testee);
    }

    @Test
    public void onResume() throws Exception {
        // Act
        testee.onResume();
        // Assert
        verify(mockRegisterer).register(testee);
    }

    @Test
    public void onPause() throws Exception {
        // Act
        testee.onPause();
        // Assert
        verify(mockRegisterer).unregister(testee);
        verify(mockPresenter).onViewDetached();
    }
}