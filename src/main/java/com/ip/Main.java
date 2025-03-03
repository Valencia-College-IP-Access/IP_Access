package com.ip;

import com.ip.entity.User;
import jakarta.persistence.*;

import java.util.Scanner;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1 - Add User");
            System.out.println("2 - Remove User by ID");
            System.out.println("3 - Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addUser(em, scanner);
                    break;
                case 2:
                    System.out.print("Enter User ID to remove: ");
                    int userId = scanner.nextInt();
                    removeUser(em, userId);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    em.close();
                    emf.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addUser(EntityManager em, Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter zip code: ");
        String zipCode = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setZipCode(zipCode);
            user.setPhoneNumber(phoneNumber);

            em.persist(user);
            tx.commit();

            System.out.println("User added successfully! ID: " + user.getId());

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private static void removeUser(EntityManager em, int userId) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            User user = em.find(User.class, userId);
            if (user != null) {
                em.remove(user);
                tx.commit();
                System.out.println("User with ID " + userId + " removed successfully.");
            } else {
                System.out.println("User with ID " + userId + " not found.");
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
