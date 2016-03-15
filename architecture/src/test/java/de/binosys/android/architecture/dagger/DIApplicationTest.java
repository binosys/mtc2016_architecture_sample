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

package de.binosys.android.architecture.dagger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.ObjectGraph;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;



/**
 * Base application base class for dependency injection.
 *
 * Created by Gabriel on 13.11.15.
 */
public class DIApplicationTest {

    @Mock
    private ObjectGraph mockObjectGraph;

    private DIApplication testee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testee = new DIApplication();
    }


    @Test
    public void inject_withNull() {
        // Arrange
        testee.onCreate();
        // Act
        testee.inject(null);
        // Assert
        // no exception is thrown
        assertTrue(true);
    }

    @Test
    public void inject_realObject() {
        Object module = new Object();
        testee.applicationGraph = mockObjectGraph;
        // Act
        testee.inject(module);
        // Assert
        verify(mockObjectGraph).inject(module);
    }

    @Test
    public void getApplicationGraph_not_null_after_onCreate() {
        // Act
        testee.onCreate();
        // Assert
        assertNotNull(testee.getApplicationGraph());
    }
}