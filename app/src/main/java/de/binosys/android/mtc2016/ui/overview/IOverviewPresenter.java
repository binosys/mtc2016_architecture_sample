package de.binosys.android.mtc2016.ui.overview;

import de.binosys.android.architecture.mvp.IPresenter;
import de.binosys.android.bluetooth.repo.BleDevice;


/**
 * Created by Gabriel on 05.07.16.
 */
public interface IOverviewPresenter extends IPresenter {

	void onConnectButtonClick(BleDevice device);

	void setView(IOverviewView view);
}
