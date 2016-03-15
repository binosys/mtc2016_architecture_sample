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
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * This module represents objects which exist only for the scope of a single activity. We can
 * safely create singletons using the activity instance because ths entire object graph will only
 * ever exist inside of that activity.
 */
@Module(
		complete = false,
		library = true)
public class ActivityModule
{

	private final Activity	activity;


	public ActivityModule(Activity activity)
	{
		this.activity = activity;
	}


	/**
	 * Allow the activity context to be injected but require that it be annotated with {@link ForActivity @ForActivity}
	 * to explicitly differentiate it from application context.
	 */
	@Provides
	@Singleton
	@ForActivity
	Context provideActivityContext()
	{
		return activity;
	}

}
