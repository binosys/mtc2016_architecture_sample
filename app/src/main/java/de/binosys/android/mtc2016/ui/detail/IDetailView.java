/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.ui.detail;

import de.binosys.android.mtc2016.business.detail.DetailDTO;



public interface IDetailView {


    /**
     * Updates the view with the data of the given DetailDTO.
     * @param detailDTO
     */
    void update(DetailDTO detailDTO);
}
