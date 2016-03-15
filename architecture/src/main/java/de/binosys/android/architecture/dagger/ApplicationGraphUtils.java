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


import android.app.Activity;
import android.app.Service;
import android.content.Context;

import javax.annotation.Nullable;

import dagger.ObjectGraph;
import de.binosys.android.architecture.utils.MorePreconditions;


public class ApplicationGraphUtils {

	/**
	 * Creates the object graph for the given activity and injects the activity.
	 *
	 * @param activity       ATTENTION: activity.getApplication() must return a subclass of {@link DIApplication}
	 * @param activityModule
	 */
	public static void buildGraph(Activity activity, @Nullable Object activityModule) {
		MorePreconditions.checkNotNull(activity);

		DIApplication application = (DIApplication) activity.getApplication();
		buildGraph(activity, activityModule, application);
	}

	/**
	 * Creates the object graph for the given activity and injects the activity.
	 *
	 * @param service        ATTENTION: service.getApplicationContext() must return a subclass of {@link DIApplication}
	 * @param activityModule
	 */
	public static void buildGraph(Service service, @Nullable Object activityModule) {
		MorePreconditions.checkNotNull(service);

		DIApplication application = (DIApplication) service.getApplicationContext();
		buildGraph(service, activityModule, application);
	}


	private static void buildGraph(Context context, Object activityModule, DIApplication application) {
		ObjectGraph applicationGraph = application.getApplicationGraph();

		if (activityModule == null) {
			applicationGraph.inject(context);
		}
		else {
			applicationGraph.plus(activityModule).inject(context);
		}
	}


	/**
	 * Injects the object into the applicationGraph.
	 * Can be used for e.g. Fragments:
	 * -
	 * - return new Fragment from newInstance()
	 * - call this method in yourFragment.onActivityCreated() to inject dependencies.
	 * -
	 * - ATTENTION: don't forget to add the Fragment to your Module.injects section
	 *
	 * @param activity ATTENTION: activity.getApplication() must return a subclass of {@link DIApplication}
	 * @param object   object to inject
	 */
	public static void inject(Activity activity, Object object) {
		inject((DIApplication) activity.getApplication(), object);

	}


	/**
	 * Injects the object into the applicationGraph.
	 * Can be used for e.g. Fragments:
	 * -
	 * - return new Fragment from newInstance()
	 * - call this method in yourFragment.onActivityCreated() to inject dependencies.
	 * -
	 * - ATTENTION: don't forget to add the Fragment to your Module.injects section
	 *
	 * @param service ATTENTION: service.getApplication() must return a subclass of {@link DIApplication}
	 * @param object  object to inject
	 */
	public static void inject(Service service, Object object) {
		inject((DIApplication) service.getApplication(), object);
	}


	/**
	 * Injects the object into the applicationGraph.
	 * Can be used for e.g. Fragments:
	 * -
	 * - return new Fragment from newInstance()
	 * - call this method in yourFragment.onActivityCreated() to inject dependencies.
	 * -
	 * - ATTENTION: don't forget to add the Fragment to your Module.injects section
	 *
	 * @param application
	 * @param object      object to inject
	 */
	public static void inject(DIApplication application, Object object) {
		application.inject(object);
	}
}
