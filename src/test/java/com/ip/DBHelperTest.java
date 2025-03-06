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
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("a real one");
        assertTrue(DBHelper.add(user));
    }
    //add loan
    @Test
    void testAdd() {
        LoanInformation loan = new LoanInformation();
        loan.setDeviceId(1);
        loan.setStartDate("2025-03-05");
        loan.setLoanStatus("on time");
        loan.setUserID(3);
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