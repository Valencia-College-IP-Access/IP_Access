/*

//TODO: Optional: make the tests self contained (create the database values each time)
//Each test may need to have ids changed to reflect what's in the database.
//TODO: More validation concerning interaction with foreign keys in the loan table. Users and Devices referenced by Loans
// cannot be updated or deleted.
//TODO: More exception catching.

 */

package com.ip;

import com.ip.entity.Device;
import com.ip.entity.LoanInformation;
import com.ip.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DBHelperTest {

    @AfterEach
    void tearDown() {
        EntityManager em = DBHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            ((EntityTransaction) tx).begin();
            em.createQuery("DELETE FROM LoanInformation").executeUpdate();
            em.createQuery("DELETE FROM Device").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    @Test
    void testAddUser() {
        User user = new User("John", "Doe", "real@example.com");
        assertTrue(DBHelper.add(user));
    }

    @Test
    void testAddLoan() {
        User user = new User("Jane", "Smith", "jane@example.com");
        assertTrue(DBHelper.add(user));

        Device device = new Device("Laptop", true);
        assertTrue(DBHelper.add(device));

        LoanInformation loan = new LoanInformation(user.getId(), device.getId(), "2025-03-05", "on time");
        assertTrue(DBHelper.add(loan));
        assertFalse(DBHelper.getDeviceById(loan.getDeviceId()).getAvailability());
    }

    @Test
    void testAddDevice() {
        Device device = new Device("iPhone 13", true);
        assertTrue(DBHelper.add(device));
    }

    @Test
    void testRemoveUser() {
        User user = new User("Jack", "Black", "jack@example.com");
        DBHelper.add(user);
        assertTrue(DBHelper.remove(user));
    }

    @Test
    void testGetDeviceById() {
        Device device = new Device("iPad", true);
        DBHelper.add(device);
        assertNotNull(DBHelper.getDeviceById(device.getId()));
    }

    @Test
    void testGetUserById() {
        User user = new User("Alex", "Johnson", "alex@example.com");
        assertTrue(DBHelper.add(user));

        User fetchedUser = DBHelper.getUserById(user.getId());
        if (fetchedUser != null) {
            System.out.println("User found: " + fetchedUser.getFirstName());
            assertNotNull(fetchedUser);
        } else {
            System.out.println("User not found");
            fail("User was not added correctly");
        }
    }
}

