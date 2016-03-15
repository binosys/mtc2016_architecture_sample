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
package de.binosys.android.architecture.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

import de.binosys.android.architecture.bus.IBusSystem;
import de.binosys.android.architecture.permission.event.EventPermissionRequestResult;



/**
 * Created by Gabriel on 08.02.16.
 * Copyright (c) 2015. All rights reserved.
 */
public class PermissionHelper {


    private static final int PERMISSION_REQUEST_CODE = 0x00000010;

    @Inject
    IBusSystem bus;

    public void requestPermission(Activity activity, String permission) {

        if (!hasPermission(activity, permission)) {

            ActivityCompat.requestPermissions(activity,
                                              new String[]{permission},
                                              PERMISSION_REQUEST_CODE);
        }
    }

    public boolean hasPermission(Context context, String permission) {

        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        bus.post(new EventPermissionRequestResult(permissions[0], grantResults[0]));
    }
}
