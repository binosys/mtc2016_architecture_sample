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
package de.binosys.android.architecture.permission.event;

import android.content.pm.PackageManager;



/**
 * Fired when the result of a permission request is available.
 *
 * Created by Gabriel on 08.02.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class EventPermissionRequestResult {

    public final boolean isGranted;
    public final String permission;

    public EventPermissionRequestResult(String permission, int grantResult) {

        this.permission = permission;
        this.isGranted = (grantResult == PackageManager.PERMISSION_GRANTED);
    }
}

