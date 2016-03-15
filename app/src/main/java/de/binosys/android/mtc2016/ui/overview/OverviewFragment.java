/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.overview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import de.binosys.android.architecture.bus.BusObserver;
import de.binosys.android.architecture.bus.BusRegisterer;
import de.binosys.android.mtc2016.R;
import de.binosys.android.bluetooth.repo.BleDevice;
import de.binosys.android.mtc2016.ui.detail.DetailActivity;


@BusObserver
public class OverviewFragment extends Fragment implements IOverviewView {


    @Inject
    BusRegisterer registerer;
    @Inject
    OverviewPresenter presenter;
    @Inject
    OverviewListAdapter listAdapter;

    private View root;
    private ListView deviceList;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        presenter.setView(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.overview_fragment, null);

        deviceList = (ListView) root.findViewById(R.id.overview_device_list);
        deviceList.setAdapter(listAdapter);
        return root;
    }

    @Override
    public void onResume() {

        super.onResume();
        registerer.register(this);
        presenter.onResume();
    }

    @Override
    public void onPause() {

        presenter.onPause();
        registerer.unregister(this);
        super.onPause();
    }


    @Override
    public void addDeviceItem(BleDevice device) {

        listAdapter.add(device);
        listAdapter.notifyDataSetChanged();
    }


    @Override
    public void setDeviceItems(@NonNull List<BleDevice> devices) {

        if (devices != null) {
            listAdapter.clear();
            listAdapter.addAll(devices);
            listAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void startActivity(Class<DetailActivity> activityClass) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        startActivity(intent);
    }
}
