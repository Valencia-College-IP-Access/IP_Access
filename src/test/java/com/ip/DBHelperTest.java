package com.ip;

import com.ip.entity.Device;
import com.ip.entity.LoanInformation;
import com.ip.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Optional: make the tests self contained (create the database values each time)
//Each test may need to have ids changed to reflect what's in the database.
//TODO: More validation concerning interaction with foreign keys in the loan table. Users and Devices referenced by Loans
// cannot be updated or deleted.
//TODO: More exception catching.
class DBHelperTest {
    //add user
    @Test
    void testAddUser() {
        User user = new User("John","Doe","real");
        assertTrue(DBHelper.add(user));
    }
    //add loan
    @Test
    void testAddLoan() {
        LoanInformation loan = new LoanInformation(3, 3, "2025-03-05", "on time");
        System.out.println(loan);
        assertTrue(DBHelper.add(loan));
        assertFalse(DBHelper.getDeviceById(loan.getDeviceId()).getAvailability());
    }
    //add device
    @Test
    void testAddDevice() {
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
    void removeUser() {
        User user = DBHelper.getUserById(4);
        assertTrue(DBHelper.remove(user));
    }

    @Test
    void removeLoan() {
        LoanInformation loan = DBHelper.getLoanById(1);
        assertTrue(DBHelper.remove(loan));
        Device loanedDevice = DBHelper.getDeviceById(loan.getDeviceId());
        assertTrue(loanedDevice.getAvailability());
        assertNull(loanedDevice.getRenterID());
    }

    @Test
    void removeDevice() {
        assertTrue(DBHelper.removeDeviceById(4));
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

    @Test
    void getUserByIdTest() {
        User user = DBHelper.getUserById(3);
        System.out.println(user);
        System.out.println(user.getFirstName());
        assertNotNull(user);
    }
}