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
                loanedDevice.setRenterID(loan.getUserID());
                //TODO: set loanStatus
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
    public static boolean removeUserById(int id) {
        return remove(getDeviceById(id));
    }
    public static boolean removeDeviceById(int id) {
        return remove(getDeviceById(id));
    }
    public static boolean removeLoanById(int id) {
        return remove(getLoanById(id));
    }
    //TODO: make user removal delete loans that reference the userid, or don't attempt to remove if loans reference it
    public static boolean remove(User user) {
        boolean result = false;
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.find(User.class, user.getId()));
            tx.commit();
            result = true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        return result;
    }
    public static boolean remove(Device device) {
        boolean result = false;
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.find(Device.class, device.getId()));
            tx.commit();
            result = true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        return result;
    }
    public static boolean remove(LoanInformation loan) {
        boolean result = false;
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.find(LoanInformation.class, loan.getId()));
            Device loanedDevice = getDeviceById(loan.getDeviceId());
            loanedDevice.setAvailability(true);
            loanedDevice.setRenterID(null);
            //TODO: set loanStatus
            em.persist(loanedDevice);
            tx.commit();
            result = true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        return result;
    }
    public static Device getDeviceById(int id) {
        Device result = null;
        try {
            result = em.find(Device.class, id);
        } catch (NoResultException nre) {
            nre.printStackTrace();
        }
        return result;
    }
    public static User getUserById(int id) {
        User result = null;
        try {
            result = em.find(User.class, id);
        } catch (NoResultException nre) {
            nre.printStackTrace();
        }
        return result;
    }
    public static LoanInformation getLoanById(int id) {
        LoanInformation result = null;
        try {
            result = em.find(LoanInformation.class, id);
        } catch (NoResultException nre) {
            nre.printStackTrace();
        }
        return result;
    }
}
