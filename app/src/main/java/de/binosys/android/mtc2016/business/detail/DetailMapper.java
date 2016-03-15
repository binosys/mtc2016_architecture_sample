/*
 * Copyright Binosys GmbH(c) 2015. All rights reserved.
 */

package de.binosys.android.mtc2016.business.detail;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import de.binosys.android.mtc2016.R;
import de.binosys.android.bluetooth.repo.BleDevice;



public class DetailMapper {


    private static Map<Integer, Integer> majorClasses = new HashMap<>();

    static {
        majorClasses.put(BluetoothClass.Device.Major.MISC, R.string.bl_major_class_misc);
        majorClasses.put(BluetoothClass.Device.Major.COMPUTER, R.string.bl_major_class_computer);
        majorClasses.put(BluetoothClass.Device.Major.PHONE, R.string.bl_major_class_phone);
        majorClasses.put(BluetoothClass.Device.Major.NETWORKING, R.string.bl_major_class_networking);
        majorClasses.put(BluetoothClass.Device.Major.AUDIO_VIDEO, R.string.bl_major_class_video);
        majorClasses.put(BluetoothClass.Device.Major.PERIPHERAL, R.string.bl_major_class_peripheral);
        majorClasses.put(BluetoothClass.Device.Major.IMAGING, R.string.bl_major_class_imaging);
        majorClasses.put(BluetoothClass.Device.Major.WEARABLE, R.string.bl_major_class_wearable);
        majorClasses.put(BluetoothClass.Device.Major.TOY, R.string.bl_major_class_toy);
        majorClasses.put(BluetoothClass.Device.Major.HEALTH, R.string.bl_major_class_health);
        majorClasses.put(BluetoothClass.Device.Major.UNCATEGORIZED, R.string.bl_major_class_uncategorized);
    }

    @Inject
    Context context;

    public DetailDTO from(BleDevice bleDevice, BluetoothGatt gatt) {

        BluetoothDevice bluetoothDevice = bleDevice.getBluetoothDevice();

        DetailDTO dto = new DetailDTO();
        dto.setRssi(bleDevice.getRssi());
        dto.setName(bleDevice.getName());
        dto.setAddress(bleDevice.getAddress());

        if (bluetoothDevice != null) {
            dto.setBondState(getBondStateOf(bluetoothDevice));
            dto.setMajorClass(getMajorClassOf(bluetoothDevice));
            dto.setMinorClass(getMinorClassOf(bluetoothDevice));
        }
        dto.setServices(getServicesOf(gatt));

        return dto;
    }

    private List<String> getServicesOf(BluetoothGatt gatt) {

        List<String> uuids = new ArrayList<>();
        if (gatt != null) {
            List<BluetoothGattService> services = gatt.getServices();

            for (BluetoothGattService service : services) {
                uuids.add(service.getUuid().toString());
            }
        }
        return uuids;
    }

    private String getMinorClassOf(BluetoothDevice bluetoothDevice) {

        return String.valueOf(bluetoothDevice.getBluetoothClass().getDeviceClass());
    }

    private String getMajorClassOf(BluetoothDevice bluetoothDevice) {

        Integer majorClass = majorClasses.get(bluetoothDevice.getBluetoothClass().getMajorDeviceClass());

        if (majorClass == null) {
            majorClass = BluetoothClass.Device.Major.UNCATEGORIZED;
        }

        return context.getString(majorClass);
    }

    private String getBondStateOf(BluetoothDevice bluetoothDevice) {

        String result = "";

        int bondState = bluetoothDevice.getBondState();
        if (bondState == BluetoothDevice.BOND_NONE) {
            result = context.getString(R.string.bond_state_none);
        }
        else if (bondState == BluetoothDevice.BOND_BONDED) {
            result = context.getString(R.string.bond_state_bonded);
        }
        else if (bondState == BluetoothDevice.BOND_BONDING) {
            result = context.getString(R.string.bond_state_bonding);
        }
        return result;
    }
}
