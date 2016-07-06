/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.crossconcern.dagger;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import de.binosys.android.bluetooth.repo.BleDevice;
import de.binosys.android.mtc2016.ui.overview.IOverviewPresenter;
import de.binosys.android.mtc2016.ui.overview.OverviewActivity;
import de.binosys.android.mtc2016.ui.overview.OverviewFragment;
import de.binosys.android.mtc2016.ui.overview.OverviewListAdapter;
import de.binosys.android.mtc2016.ui.overview.OverviewPresenter;


@Module(
        injects = {
                OverviewActivity.class,
                OverviewFragment.class},
        complete = false,
        addsTo = AppModule.class
     )
public class OverviewModule {


    OverviewActivity activity;

    public OverviewModule(OverviewActivity activity) {

        this.activity = activity;
    }

    @Provides
    public OverviewListAdapter provideOverviewListAdapter(IOverviewPresenter presenter) {

        return new OverviewListAdapter(activity, new ArrayList<BleDevice>(), presenter);
    }



    @Provides
    public IOverviewPresenter provideIOverviewPresenter(OverviewPresenter presenter) {

        return presenter;
    }


}
