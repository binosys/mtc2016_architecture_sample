/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.overview;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.List;

import de.binosys.android.bluetooth.repo.BleDevice;
import de.binosys.android.mtc2016.ui.detail.DetailActivity;


public interface IOverviewView {

    void addDeviceItem(BleDevice device);

    void setDeviceItems(@NonNull List<BleDevice> devices);

    void startActivity(Class<DetailActivity> detailActivityClass);
}
