/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.crossconcern.dagger;

import dagger.Module;
import dagger.Provides;
import de.binosys.android.mtc2016.ui.detail.DetailActivity;
import de.binosys.android.mtc2016.ui.detail.DetailFragment;
import de.binosys.android.mtc2016.ui.detail.DetailPresenter;
import de.binosys.android.mtc2016.ui.detail.IDetailPresenter;
import de.binosys.android.mtc2016.ui.detail.IDetailView;


@Module(
		complete = false,
		library = true,
		overrides = true,
		injects = {
				DetailActivity.class})
public class DetailModule {


	DetailActivity activity;

	public DetailModule(DetailActivity activity) {

		this.activity = activity;
	}

	@Provides
	public IDetailView provideDetailView(DetailFragment fragment) {

		return fragment;
	}

	@Provides
	public IDetailPresenter provideDetailPresenter(DetailPresenter presenter) {

		return presenter;
	}
}
