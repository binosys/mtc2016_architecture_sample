/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016;

import java.util.List;

import de.binosys.android.architecture.dagger.DIApplication;
import de.binosys.android.mtc2016.crossconcern.dagger.AppModule;
import de.binosys.android.bluetooth.crossconcern.dagger.BluetoothModule;



public class MtcApplication extends DIApplication {

    @Override
    protected List<Object> getModules() {

        List<Object> modules =  super.getModules();
        modules.add(new BluetoothModule());
        modules.add(new AppModule(this));
        return modules;
    }

}
