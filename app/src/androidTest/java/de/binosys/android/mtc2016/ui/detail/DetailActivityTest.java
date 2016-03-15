package de.binosys.android.mtc2016.ui.detail;

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
import de.binosys.android.mtc2016.crossconcern.dagger.DetailModule;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by Gabriel on 03.03.16.
 * Copyright (c) 2015. All rights reserved.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> activityRule = new ActivityTestRule<>(
            DetailActivity.class);


    @Test
    public void intialization() throws Exception {
        // Arrange
        DetailActivity testee = activityRule.getActivity();
        // Act
        // Assert
        FragmentManager manager = testee.getSupportFragmentManager();

        assertNotNull(testee.fragment);
        Fragment fragment = manager.findFragmentById(R.id.activity_detail_container);
        assertEquals(testee.fragment, fragment);
    }


    @Test
    public void getModules() {

        // Arrange
        DetailActivity testee = activityRule.getActivity();
        // Act
        List<Object> modules = testee.getModules();
        // Assert
        assertNotNull(modules);
        assertEquals(1, modules.size());
        assertTrue(modules.get(0) instanceof DetailModule);
    }
}