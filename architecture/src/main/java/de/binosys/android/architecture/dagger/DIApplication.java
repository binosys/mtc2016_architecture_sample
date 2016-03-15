/*
 * Copyright Binosys GmbH(c)2015.All rights reserved.
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,software
 * distributed under the License is distributed on an"AS IS"BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.binosys.android.architecture.dagger;


import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;



/**
 * This is a base application class which provides dependency injection capabilities via Dagger.
 */
public class DIApplication extends Application {

    private static final String TAG = DIApplication.class.getSimpleName();

    ObjectGraph applicationGraph;
    final ArrayList<Object> modules = new ArrayList<Object>();


    @Override
    public void onCreate() {

        super.onCreate();
        applicationGraph = ObjectGraph.create(getModules().toArray());
    }

    /**
     * A list of modules to use for the application graph. Subclasses can override this method to
     * provide additional modules provided they call {@code super.getModules()}.
     */
    protected List<Object> getModules() {

        modules.add(new ArchitectureModule(this));
        return modules;
    }


    /**
     * Inject the given object into the applicationGraph
     *
     * @param object
     */
    public void inject(@Nullable Object object) {

        if(object != null){
            applicationGraph.inject(object);
        }else {
            Log.w(TAG, "inject(Object): parameter object is null");
        }
    }

    public ObjectGraph getApplicationGraph() {

        return applicationGraph;
    }
}
