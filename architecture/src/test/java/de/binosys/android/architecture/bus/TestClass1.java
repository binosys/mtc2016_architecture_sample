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

/**
 * This class is used to test singleton processing (self cycles) of the BusRegisterer.
 * <p/>
 * Created by Gabriel on 12.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
@BusObserver
public class TestClass1 {


    private static final TestClass1 INSTANCE_1 = new TestClass1();
    private static TestClass1 instance2 = new TestClass1();

    private TestClass1() {

    }

    public static TestClass1 getInstance1() {
        return INSTANCE_1;
    }

    public static TestClass1 getInstance2() {
        if (instance2 == null) {
            instance2 = new TestClass1();
        }

        return instance2;
    }

}
