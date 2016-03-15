/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.overview;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import javax.inject.Inject;

import de.binosys.android.architecture.dagger.DIActivity;
import de.binosys.android.mtc2016.R;
import de.binosys.android.mtc2016.crossconcern.dagger.OverviewModule;



public class OverviewActivity extends DIActivity {

    @Inject
    OverviewFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.overview_activity);

        showOverviewFragment();
    }

    private void showOverviewFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_overview_container, fragment);
        transaction.commit();
    }

    @Override
    protected List<Object> getModules() {

        List<Object> modules = super.getModules();
        modules.add(new OverviewModule(this));
        return modules;
    }
}
