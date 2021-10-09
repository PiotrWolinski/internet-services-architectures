package com.example.lab1;

import com.example.lab1.car.service.CarService;
import com.example.lab1.car.service.EngineService;
import com.example.lab1.user.entity.User;
import com.example.lab1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private CarService carService;

    private EngineService engineService;

    private UserService userService;

    @Autowired
    public CommandLine(CarService carService, EngineService engineService, UserService userService) {
        this.carService = carService;
        this.engineService = engineService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.print("> ");
            String command = scanner.nextLine();

            switch (command) {
                case "list_cars":
                    carService.findAll().forEach(System.out::println);
                    break;
                case "list_engines":
                    engineService.findAll().forEach(System.out::println);
                    break;
                case "list_users":
                    userService.findAll().forEach(System.out::println);
                    break;
                case "add_car":
                    carService.addCar(scanner);
                    break;
                case "add_engine":
                    engineService.addEngine(scanner);
                    break;
                case "delete_car":
                    carService.deleteCar();
                    break;
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("list_cars | list_engines | list_users");
                    System.out.println("add_car | add_engine");
                    System.out.println("delete_car | quit");
            }
        }
        scanner.close();
    }
}
