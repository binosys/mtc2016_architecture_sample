/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.overview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import de.binosys.android.mtc2016.R;
import de.binosys.android.bluetooth.repo.BleDevice;



public class OverviewListAdapter extends ArrayAdapter<BleDevice> {

    private final IOverviewPresenter presenter;

    @Inject
    public OverviewListAdapter(Context context, ArrayList<BleDevice> devices, IOverviewPresenter presenter) {

        super(context, 0, devices);
        this.presenter = presenter;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BleDevice device = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.overview_list_item, parent, false);
        }

        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.overview_list_item_name);
        TextView address = (TextView) convertView.findViewById(R.id.overview_list_item_address);
        Button btnConnect = (Button) convertView.findViewById(R.id.overview_list_item_button);

        name.setText(device.getName());
        address.setText(device.getAddress());
        btnConnect.setOnClickListener(new ButtonClickListener(device));

        return convertView;
    }

    private class ButtonClickListener implements View.OnClickListener {

        private final BleDevice device;

        public ButtonClickListener(BleDevice device) {
            this.device = device;

        }

        @Override
        public void onClick(View v) {
            presenter.onConnectButtonClick(device);
        }
    }
}
