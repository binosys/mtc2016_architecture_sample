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


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Inject;
import javax.inject.Singleton;



/**
 * IBusSystem implementation that posts all events at the Main Thread.
 */
@Singleton
public final class BusSystemMainThread extends BusSystemAnyThread {

    private static final Object MONITOR = new Object();
    private Handler handler = null;
    private EventPostRunnable postRunnable = new EventPostRunnable();

    @Inject
    public BusSystemMainThread(Context context) {

        bus = new Bus(ThreadEnforcer.MAIN, BusSystemMainThread.class.getSimpleName());
        handler = new Handler(context.getMainLooper());
    }


    @Override
    public void post(final Object event) {

        Log.d(TAG, "post(" + event.getClass().getSimpleName() + ")");

        synchronized (MONITOR) {
            postRunnable.event = event;
            handler.post(postRunnable);
        }
    }


    private class EventPostRunnable implements Runnable {

        Object event;

        @Override
        public void run() {

            bus.post(event);
        }
    }
}
