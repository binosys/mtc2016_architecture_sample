/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.detail;

import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import de.binosys.android.architecture.bus.BusRegisterer;
import de.binosys.android.mtc2016.R;
import de.binosys.android.mtc2016.business.detail.DetailDTO;


public class DetailFragment extends Fragment implements IDetailView {

    @Inject
    DetailFragmentPresenter presenter;
    @Inject
    BusRegisterer busRegisterer;
    private View root;

    private TextView connectionState;
    private TextView bondState;
    private TextView name;
    private TextView address;
    private TextView majorClass;
    private TextView minorClass;
    private TextView serviceUuid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.detail_fragment, null);

        connectionState = (TextView) root.findViewById(R.id.detail_fragment_status_value);
        bondState = (TextView) root.findViewById(R.id.detail_fragment_bondend_value);
        name = (TextView) root.findViewById(R.id.detail_fragment_device_name);
        address = (TextView) root.findViewById(R.id.detail_fragment_device_address);
        majorClass = (TextView) root.findViewById(R.id.detail_fragment_major_class);
        minorClass = (TextView) root.findViewById(R.id.detail_fragment_minor_class);
        serviceUuid = (TextView) root.findViewById(R.id.detail_fragment_service_uuid);

        return root;
    }

    @Override
    public void onResume() {

        super.onResume();
        busRegisterer.register(this);
        presenter.onResume();
    }


    @Override
    public void onPause() {

        super.onPause();
        presenter.onPause();
        busRegisterer.unregister(this);
    }

    @Override
    public void update(DetailDTO detailDTO) {
        connectionState.setText(detailDTO.getConnectionState());
        bondState.setText(detailDTO.getBondState());
        name.setText(detailDTO.getName());
        address.setText(detailDTO.getAddress());
        majorClass.setText(detailDTO.getMajorClass());
        minorClass.setText(detailDTO.getMinorClass());
        serviceUuid.setText(createUuidString(detailDTO));
    }

    @NonNull
    private String createUuidString(DetailDTO detailDTO) {

        StringBuilder builder = new StringBuilder("");
        for(ParcelUuid uuid : detailDTO.getUuids()){
            builder.append(uuid);
            builder.append("\n");
        }
        return builder.toString();
    }
}
