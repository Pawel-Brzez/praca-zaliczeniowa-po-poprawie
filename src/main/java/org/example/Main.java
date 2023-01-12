package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.connect();
        boolean quit = false;


        while (!quit) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Dodaj nowego użytkownika");
            System.out.println("2. Odczytaj z bazy danych");
            System.out.println("3. Zaktualizuj dane użytkownika");
            System.out.println("4. Usuń użytkownika");
            System.out.println("5. Wyjście");
            System.out.print("> ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Podaj nazwę: ");
                    String username = scanner.nextLine();
                    System.out.print("Podaj adres email: ");
                    String email = scanner.nextLine();
                    System.out.print("Podaj hasło: ");
                    String pass = scanner.nextLine();
                    System.out.print("Zatrudniony? (true/false): ");
                    boolean enabled = scanner.nextBoolean();
                    scanner.nextLine();
                    Person p = new Person(username,email, pass,enabled, -1);
                    p.save(dbc);
                    break;

                case 2:
                    System.out.print("Podaj nazwę użytkownika: ");
                    username = scanner.nextLine();
                    p = new Person(username,"", "",false, -1);
                    try {
                        p.read(dbc);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 3:
                    System.out.print("Podaj nazwę użytkownika: ");
                    String username2 = scanner.nextLine();
                    System.out.print("Podaj adres email: ");
                    String email2 = scanner.nextLine();
                    System.out.print("Podaj hasło: ");
                    String pass2 = scanner.nextLine();
                    System.out.print("Zatrudniony? (true/false): ");
                    boolean enabled2 = scanner.nextBoolean();
                    scanner.nextLine();
                    Person p2 = new Person(username2,email2, pass2,enabled2, -1);
                    p2.update(dbc);
                    break;

                case 4:

                    System.out.print("Podaj nazwę użytkownika: ");
                    username = scanner.nextLine();
                    p = new Person(username,"", "",false, -1);
                    p.delete(dbc);
                    break;

                case 5:

                    quit = true;
                    break;
            }
        }
    }
    }
