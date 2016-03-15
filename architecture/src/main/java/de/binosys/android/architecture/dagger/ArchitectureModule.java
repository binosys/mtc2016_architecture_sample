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
package de.binosys.android.architecture.dagger;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.binosys.android.architecture.bus.BusRegisterer;
import de.binosys.android.architecture.bus.BusSystemAnyThread;
import de.binosys.android.architecture.bus.IBusSystem;



/**
 * Dagger Context main module {@link Module}
 */
@Module(
        complete = false,
        library = true)
public class ArchitectureModule {

    public static final String NAMED_MAIN_HANDLER = "main_handler";
    private static final String TAG = ArchitectureModule.class.getSimpleName();
    public static final String BINOSYS_BASE_PACKAGE = "de.binosys.android";

    private final Context context;


    public ArchitectureModule(Context context) {

        this.context = context;
    }


    @Provides
    @Singleton
    Context provideContext() {

        return context;
    }


    @Provides
    @Named(NAMED_MAIN_HANDLER)
    public Handler provideHandler() {

        return new Handler(context.getMainLooper());
    }


    @Provides
    @Singleton
    public IBusSystem provideUiBusSystem() {

        return new BusSystemAnyThread();
    }


    @Provides
    @Singleton
    public BusRegisterer provideBusRegisterer(IBusSystem bus) {

        List<String>packages = new ArrayList<>();
        packages.add(getPackageName());
        packages.add(BINOSYS_BASE_PACKAGE);

        return new BusRegisterer(packages, bus);
    }

    private String getPackageName() {

        PackageManager pm = context.getPackageManager();
        String packageString = "";
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            packageString = packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Could not determine package name");
        }
        return packageString;
    }

}
