/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.crossconcern.dagger;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.binosys.android.architecture.bus.BusRegisterer;
import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.architecture.dagger.ArchitectureModule;
import de.binosys.android.architecture.utils.PackageUtil;
import de.binosys.android.mtc2016.repository.AppRepository;
import de.binosys.android.bluetooth.repo.BleDeviceRepository;


@Module(
        complete = false,
        library = true,
        overrides = true)
public class AppModule {

    private final Context context;

    public AppModule(Context context) {

        this.context = context;
    }

    @Provides
    @Singleton
    public BusRegisterer provideBusRegisterer(IBusSystem bus) {

        List<String> packages = new ArrayList<>();
        packages.add(PackageUtil.getPackageName(context));
        packages.add(ArchitectureModule.BINOSYS_BASE_PACKAGE);

        return new BusRegisterer(packages, bus);
    }


    // we need to explicitly provide these singletons to make them global singletons as we use
    // activity scoped graphs.

    @Provides
    @Singleton
    public AppRepository provideAppRepository() {

        return new AppRepository();
    }

    @Provides
    @Singleton
    public BleDeviceRepository provideBleDeviceRepository(IBusSystem bus) {

        return new BleDeviceRepository(bus);
    }
}
