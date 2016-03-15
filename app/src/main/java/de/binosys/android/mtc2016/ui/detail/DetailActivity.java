/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import javax.inject.Inject;

import de.binosys.android.architecture.dagger.DIActivity;
import de.binosys.android.mtc2016.R;
import de.binosys.android.mtc2016.crossconcern.dagger.DetailModule;



public class DetailActivity extends DIActivity {

    @Inject
    DetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_activity);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_detail_container, fragment);
        transaction.commit();
    }


    @Override
    protected List<Object> getModules() {

        List<Object> modules = super.getModules();
        modules.add(new DetailModule(this));
        return modules;
    }
}
