package de.binosys.android.architecture.mvp;

/**
 * Base interface for all presenters no matter if used for a {@link android.app.Activity}, {@link android.app.Fragment} or {@link android.view.View}
 * <p/>
 * Created by Gabriel on 05.07.16.
 */
public interface IPresenter {

	void onViewAttached();

	void onViewDetached();
}
