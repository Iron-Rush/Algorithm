package cn.czl.utils.bluetooth;

import com.intel.bluetooth.BluetoothStack;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;

/**
 * @author RedRush
 * @date 2021/7/14 11:36
 * @description:
 */
public class TestDiscoveryAgent {

    public static void main(String[] args) {
        try {
            RemoteDeviceDiscovery discovery = new RemoteDeviceDiscovery();
            System.out.println(discovery.getDevices());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
