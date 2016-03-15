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
package de.binosys.android.architecture.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import de.binosys.android.architecture.bus.BusRegisterer;
import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.architecture.dagger.ApplicationGraphUtils;



/**
 * Base class for Services which want to use a handler for posting their work on the handlers thread.
 * <p/>
 * Created by Gabriel on 13.11.15.
 */
public class HandlerService extends Service {

    protected Handler handler;

    @Inject
    protected IBusSystem bus;

    @Inject
    BusRegisterer busRegisterer;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // no binding available
        return null;
    }

    @Override
    public void onCreate() {

        ApplicationGraphUtils.inject(this, this);

        busRegisterer.register(this);
        setupHandler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
        return 0;
    }

    @Override
    public void onDestroy() {

        busRegisterer.unregister(this);
    }

    private void setupHandler() {

        if (handler == null) {
            HandlerThread thread = new HandlerThread(getServiceName(), android.os.Process.THREAD_PRIORITY_FOREGROUND);
            thread.start();

            Looper looper = thread.getLooper();
            handler = new Handler(looper);
        }

    }


    protected String getServiceName() {

        return HandlerService.class.getSimpleName();
    }

    @VisibleForTesting
    public void setBus(IBusSystem bus) {

        this.bus = bus;
    }
}
