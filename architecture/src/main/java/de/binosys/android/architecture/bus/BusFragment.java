package de.binosys.android.architecture.bus;

import android.support.v4.app.Fragment;

import javax.inject.Inject;



/**
 * This Fragment uses a BusRegisterer to register and unregister all @BusObservser annotated classes
 * <p/>
 * Created by Gabriel on 02.03.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class BusFragment extends Fragment {

    @Inject
    BusRegisterer busRegisterer;

    @Override
    public void onResume() {

        super.onResume();
        busRegisterer.register(this);
    }


    @Override
    public void onPause() {

        super.onPause();
        busRegisterer.unregister(this);
    }
}
