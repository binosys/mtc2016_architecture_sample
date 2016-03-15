package de.binosys.android.mtc2016.ui.overview;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import de.binosys.android.mtc2016.R;
import de.binosys.android.mtc2016.crossconcern.dagger.OverviewModule;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by Gabriel on 07.03.16.
 * Copyright (c) 2015. All rights reserved.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class OverviewActivityTest {

	@Rule
	public ActivityTestRule<OverviewActivity> activityRule = new ActivityTestRule<>(
			OverviewActivity.class);

	@Test
	public void initialization() {
		// Arrange
		OverviewActivity testee = activityRule.getActivity();
		// Act
		FragmentManager manager = testee.getSupportFragmentManager();
		// Assert
		assertNotNull(testee.fragment);
		Fragment fragment = manager.findFragmentById(R.id.activity_overview_container);
		assertEquals(testee.fragment, fragment);
	}

	@Test
	public void getModules() {

		// Arrange
		OverviewActivity testee = activityRule.getActivity();
		// Act
		List<Object> modules = testee.getModules();
		// Assert
		assertNotNull(modules);
		assertEquals(1, modules.size());
		assertTrue(modules.get(0) instanceof OverviewModule);
	}
}
