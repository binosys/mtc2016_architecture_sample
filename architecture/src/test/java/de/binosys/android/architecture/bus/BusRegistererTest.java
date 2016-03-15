/**
 Copyright Binosys GmbH(c)2015.All rights reserved.

 Licensed under the Apache License,Version2.0(the"License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,software
 distributed under the License is distributed on an"AS IS"BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package de.binosys.android.architecture.bus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Gabriel on 12.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class BusRegistererTest {

    @Mock
    IBusSystem mockBus;

    String targetPackage = "de.binosys.android";

    List<String>targetPackages;

    TestClass1 class1 = TestClass1.getInstance1();
    TestClass1 class2 = TestClass1.getInstance2();
    TestClass2 class3 = new TestClass2();

    private BusRegisterer testee;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        targetPackages = new ArrayList<>();
        targetPackages.add(targetPackage);
        testee = new BusRegisterer(targetPackages, mockBus);
    }

    @Test
    public void newInstance() throws Exception {

        // Act
        BusRegisterer busRegisterer = new BusRegisterer(targetPackages, mockBus);
        // Assert
        assertNotNull(busRegisterer);

        assertNotNull(busRegisterer.getObservers());
        assertEquals(2, busRegisterer.getObservers().size());

        assertNotNull(busRegisterer.getRegistrations());
        assertEquals(0, busRegisterer.getRegistrations().size());

    }

    @Test
    public void register() throws Exception {


        // Act
        testee.register(this);

        // Assert
        Map<Object, List<Object>> registrations = testee.getRegistrations();
        assertEquals(1, registrations.size());

        assertTrue(registrations.containsKey(this));
        List<Object> registeredObjects = registrations.get(this);

        assertEquals(3, registeredObjects.size());
        assertEquals(TestClass1.getInstance1(), registeredObjects.get(0));
        assertEquals(TestClass1.getInstance2(), registeredObjects.get(1));
        assertEquals(class3, registeredObjects.get(2));

        verify(mockBus, times(3)).register(anyObject());
        verify(mockBus).register(class1);
        verify(mockBus).register(class2);
        verify(mockBus).register(class3);
    }

    @Test
    public void unregister() throws Exception {
        // Arrange
        testee.register(this);

        // Act
        testee.unregister(this);

        // Assert
        assertEquals(0, testee.getRegistrations().size());

        verify(mockBus, times(3)).unregister(anyObject());
        verify(mockBus).unregister(class1);
        verify(mockBus).unregister(class2);
        verify(mockBus).unregister(class3);
    }
}