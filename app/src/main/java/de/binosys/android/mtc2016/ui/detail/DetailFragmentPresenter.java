/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.detail;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import de.binosys.android.architecture.bus.BusObserver;
import de.binosys.android.mtc2016.business.detail.DetailDTO;
import de.binosys.android.mtc2016.business.detail.DetailManager;
import de.binosys.android.mtc2016.business.detail.event.EventBusinessDetailDeviceConnected;


@BusObserver
public class DetailFragmentPresenter {

    @Inject
    DetailManager manager;

    private IDetailView view;

    public void setView(IDetailView view) {

        this.view = view;
    }

    public void onResume(){
        manager.connectSelectedDevice();
        updateView();
    }

    public void onPause(){
        manager.disconnectSelectedDevice();
    }


    @Subscribe
    public void on(EventBusinessDetailDeviceConnected event){
        updateView();
    }

    private void updateView() {

        DetailDTO details = manager.getDetailsOfConnectedDevice();
        view.update(details);
    }

}
