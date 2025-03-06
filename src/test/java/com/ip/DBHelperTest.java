package com.ip;

import com.ip.entity.Device;
import com.ip.entity.LoanInformation;
import com.ip.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DBHelperTest {
    //add user
    @Test
    void add() {
        User user = new User("John","Doe","real");
        assertTrue(DBHelper.add(user));
    }
    //add loan
    @Test
    void testAdd() {
        LoanInformation loan = new LoanInformation(1, 3, "2025-03-05", "on time");
        System.out.println(loan);
        assertTrue(DBHelper.add(loan));
        assertFalse(DBHelper.getDeviceById(loan.getDeviceId()).getAvailability());
    }
    //add device
    @Test
    void testAdd1() {
        Device device = new Device();
        device.setAvailability(false);
        assertTrue(DBHelper.add(device));
    }

    //Use add with a Device/User/Loan that already has an ID to update
    @Test
    void update() {
        Device device = DBHelper.getDeviceById(1);
        if(device.getAvailability()) {
            device.setAvailability(false);
        } else
            device.setAvailability(true);
        assertTrue(DBHelper.add(device));

    }

    @Test
    void getDeviceByIdTest() {
        Device device = DBHelper.getDeviceById(1);
        System.out.println(device);
        //test if Device methods work
        System.out.println(device.getDeviceName());
        assertNotNull(device);
        //what happens when it looks for a device with an id that doesn't exist
        //(check database and change ids accordingly)
        Device nonexistingDevice = DBHelper.getDeviceById(5);
        assertNull(nonexistingDevice);
    }
}