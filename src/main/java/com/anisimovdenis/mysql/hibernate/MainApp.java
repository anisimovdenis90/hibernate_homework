package com.anisimovdenis.mysql.hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;


public class MainApp {

    public static void main(String[] args) throws IOException {
        DBService.start();
//        ProductRepository.prepareDB();

        System.out.println("Доступные команды:");
        System.out.println("/getProductsByClient + id - список товаров, которые купил клиент с указанным id");
        System.out.println("/getClientsByProduct + id - список клиентов, которые купили товар с указанным id");
        System.out.println("/delProduct + Id - удалить товар из козины по Id");
        System.out.println("/delUser + Id - удалить пользователя из козины по Id");
        System.out.println("/end - завершить работу");

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String s = consoleReader.readLine();
                if (s.toLowerCase().startsWith("/getproductsbyclient")) {
                    Optional<List<Product>> optionalProducts = DBService.getProductsByClientId(Long.valueOf(s.split(" ")[1]));
                    if (optionalProducts.isPresent()) {
                        System.out.println(optionalProducts.get());
                    } else {
                        System.out.println("Указан некорректный ID " +  s.split(" ")[1]);
                    }
                } else if (s.toLowerCase().startsWith("/getclientsbyproduct")) {
                    Optional<List<User>> optionalUsers = DBService.getUsersByProductId(Long.valueOf(s.split(" ")[1]));
                    if (optionalUsers.isPresent()) {
                        System.out.println(optionalUsers.get());
                    } else {
                        System.out.println("Указан некорректный ID " +  s.split(" ")[1]);
                    }
                } else if (s.toLowerCase().startsWith("/delproduct")) {
                        DBService.deleteProductById(Long.valueOf(s.split(" ")[1]));
                } else if (s.toLowerCase().startsWith("/deluser")) {
                        DBService.deleteUserById(Long.valueOf(s.split(" ")[1]));
                } else if (s.equals("/end")) {
                    break;
                } else {
                    System.out.println("Неизвестная команда!");
                }
            }
        }
        DBService.stop();
    }
}
