package com.ip;

import com.ip.entity.LoanInformation;
import com.ip.entity.User;
import com.ip.entity.Device;
import jakarta.persistence.*;

public class DBHelper {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private static final EntityManager em = emf.createEntityManager();

    public static boolean add(User user) {
        boolean result = false;
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(user);
            tx.commit();
            result = true;
            System.out.println("User " + user.getFirstName() + " added successfully");

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public static boolean add(LoanInformation loan) {
        boolean result = false;
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Device loanedDevice = getDeviceById(loan.getDeviceId());
            if (loanedDevice != null) {
                loanedDevice.setAvailability(false);
            } else {
                System.out.println("Loaned device not found!");
                return false;
            }
            em.persist(loan);
            em.persist(loanedDevice);
            tx.commit();
            result = true;
            System.out.println("Loan added successfully! ID: " + loan.getId());

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    public static boolean add(Device device) {
        boolean result = false;
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(device);
            tx.commit();
            result = true;
            System.out.println("Device" + device.getDeviceName() + "added successfully!");

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    public static Device getDeviceById(int id) {
        Device result = null;
        try {
            result = em.createQuery("select d from Device d where d.id = " + id, Device.class).getSingleResult();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        }
        return result;
    }
    public static User getUserById(int id) {
        User result = null;
        try {
            result = em.createQuery("select u from User u where u.id = " + id, User.class).getSingleResult();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        }
        return result;
    }
}
