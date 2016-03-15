/*
 * Copyright Binosys GmbH(c)2015.All rights reserved.
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,software
 * distributed under the License is distributed on an"AS IS"BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.binosys.android.architecture.bus;


import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.ArrayList;
import java.util.List;

import de.binosys.android.architecture.utils.MorePreconditions;



/**
 * Copyright Binosys GmbH (c) 2015. All rights reserved.
 */
public class BusSystemAnyThread implements de.binosys.android.architecture.bus.IBusSystem {

    protected Bus bus;
    private final List<Object> registrations = new ArrayList<Object>();

    protected final static String TAG = BusSystemAnyThread.class.getSimpleName();
    private static final Object MONITOR = new Object();


    public BusSystemAnyThread() {

        bus = new Bus(ThreadEnforcer.ANY, BusSystemAnyThread.class.getSimpleName());
    }


    @Override
    public final boolean isRegistered(Object obj) {

        MorePreconditions.checkNotNull(obj);
        synchronized (MONITOR) {
            return registrations.contains(obj);
        }
    }


    @Override
    public void post(Object event) {

        MorePreconditions.checkNotNull(event);
        Log.i(TAG, "post(): " + event.getClass().getSimpleName());
        bus.post(event);
    }


    @Override
    public void register(Object obj) {

        MorePreconditions.checkNotNull(obj);

        synchronized (MONITOR) {
            if (isRegistered(obj)) {
                return;
            }
            registrations.add(obj);
            bus.register(obj);
            Log.d(TAG, "register(" + obj.getClass().getSimpleName() + ")");
        }
    }


    @Override
    public void unregister(Object obj) {

        MorePreconditions.checkNotNull(obj);

        synchronized (MONITOR) {
            if (!isRegistered(obj)) {
                Log.w(TAG, "Cannot unregister " + obj.getClass().getSimpleName() + " ==> it is NOT registered");
                return;
            }

            registrations.remove(obj);
            bus.unregister(obj);
            Log.d(TAG, "unregister(" + obj.getClass().getSimpleName() + ")");
        }
    }

}
