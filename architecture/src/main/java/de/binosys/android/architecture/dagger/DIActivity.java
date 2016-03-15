package de.binosys.android.architecture.dagger;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;



/**
 * Created by Gabriel on 04.03.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class DIActivity extends FragmentActivity {


        private ObjectGraph activityGraph;

        @Override protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            DIApplication application = (DIApplication) getApplication();
            List<Object> modules = getModules();
            activityGraph = application.getApplicationGraph().plus(modules.toArray());
            activityGraph.inject(this);
        }

        @Override protected void onDestroy() {
            activityGraph = null;
            super.onDestroy();
        }

        /**
         * A list of modules to use for the individual activity graph. Subclasses can override this
         * method to provide additional modules provided they call and include the modules returned by
         * calling {@code super.getModules()}.
         */
        protected List<Object> getModules() {
            return new ArrayList<>();
        }

        /** Inject the supplied {@code object} using the activity-specific graph. */
        public void inject(Object object) {
            activityGraph.inject(object);
        }
}
