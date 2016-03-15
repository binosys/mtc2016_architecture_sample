package de.binosys.android.mtc2016.ui.overview;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import de.binosys.android.bluetooth.repo.BleDevice;
import de.binosys.android.mtc2016.business.overview.OverviewManager;
import de.binosys.android.mtc2016.business.overview.event.EventBusinessFoundNewDevice;
import de.binosys.android.mtc2016.ui.detail.DetailActivity;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by Gabriel on 12.03.16.
 */
public class OverviewPresenterTest {

	@Mock
	OverviewManager mockOverviewManager;
	@Mock
	IOverviewView mockView;


	BleDevice bleDevice = new BleDevice("1234", "", 0, null, null);

	OverviewPresenter testee;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		testee = new OverviewPresenter();
		testee.manager = mockOverviewManager;
		testee.setView(mockView);
	}

	@Test
	public void testOn() throws Exception {
		// Arrange
		when(mockOverviewManager.getDeviceWith("1234")).thenReturn(bleDevice);
		// Act
		testee.on(new EventBusinessFoundNewDevice("1234"));
		// Assert
		verify(mockOverviewManager).getDeviceWith("1234");
		verify(mockView).addDeviceItem(bleDevice);

	}

	@Test
	public void testOnConnectButtonClick() throws Exception {
		// Act
		testee.onConnectButtonClick(bleDevice);
		// Assert
		verify(mockOverviewManager).setSelectedDevice(bleDevice);
		verify(mockView).startActivity(eq(DetailActivity.class));
	}

	@Test
	public void testOnResume() throws Exception {
		// Arrage
		List<BleDevice> devices = new ArrayList<>(0);
		when(mockOverviewManager.getAllDevices()).thenReturn(devices);
		// Act
		testee.onResume();
		// Assert
		verify(mockOverviewManager).startDeviceScan();
		verify(mockView).setDeviceItems(devices);
	}

	@Test
	public void testOnPause() throws Exception {
		// Act
		testee.onPause();
		// Assert
		verify(mockOverviewManager).stopDeviceScan();
	}
}