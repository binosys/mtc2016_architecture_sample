/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.detail;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.binosys.android.architecture.bus.BusObserver;
import de.binosys.android.mtc2016.business.detail.DetailDTO;
import de.binosys.android.mtc2016.business.detail.DetailManager;
import de.binosys.android.mtc2016.business.detail.event.EventBusinessDetailDeviceConnected;

@Singleton
@BusObserver
public class DetailPresenter implements IDetailPresenter {

    @Inject
    DetailManager manager;

    private IDetailView view;

    @Override
    public void setView(IDetailView view) {

        this.view = view;
    }

    public void onViewAttached(){
        manager.connectSelectedDevice();
        updateView();
    }

    public void onViewDetached(){
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
