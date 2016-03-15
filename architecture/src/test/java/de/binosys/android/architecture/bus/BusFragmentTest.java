package de.binosys.android.architecture.bus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;



/**
 * Created by Gabriel on 02.03.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class BusFragmentTest {

    @Mock
    BusRegisterer mockRegisterer;

    BusFragment testee;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        testee = new BusFragment();
        testee.busRegisterer = mockRegisterer;
    }

    @Test
    public void testOnResume() throws Exception {
        // Act
        testee.onResume();
        // Assert
        verify(mockRegisterer).register(testee);
    }


    @Test
    public void testOnPause() throws Exception {
        // Act
        testee.onPause();
        // Assert
        verify(mockRegisterer).unregister(testee);
    }
}