package de.binosys.android.bluetooth.crossconcern.dagger;

import android.bluetooth.BluetoothAdapter;

import dagger.Module;
import dagger.Provides;



/**
 * Created by Gabriel on 21.01.16.
 * Copyright (c) 2015. All rights reserved.
 */
@Module(
        complete = false,
        library = true,
        overrides = false)
public class BluetoothModule {

    @Provides
    public BluetoothAdapter provideBluetoothAdapter() {

        return BluetoothAdapter.getDefaultAdapter();
    }
}
