package com.example.lab1.config;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.car.service.CarService;
import com.example.lab1.car.service.EngineService;
import com.example.lab1.digest.Sha256Utility;
import com.example.lab1.user.entity.User;
import com.example.lab1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitializedData {

    private final CarService carService;

    private final EngineService engineService;

    private final UserService userService;

    @Autowired
    public InitializedData(CarService carService, EngineService engineService, UserService userService) {
        this.carService = carService;
        this.engineService = engineService;
        this.userService = userService;
    }
    // Podziele na osobne funkcje do inicjalizacji
    @PostConstruct
    private synchronized void init() {
        User admin = User.builder()
                .login("admin")
                .name("John")
                .surname("Snow")
                .birthDate(LocalDate.of(2000, 2, 23))
                .email("john.snow@gmail.com")
                .password(Sha256Utility.hash("admin"))
                .build();

        User mat = User.builder()
                .login("CarWow")
                .name("Mat")
                .surname("Watson")
                .birthDate(LocalDate.of(1984, 6, 12))
                .email("mat@carwow.com")
                .password(Sha256Utility.hash("sTR0nG-p4ssW0rD"))
                .build();

        userService.create(admin);
        userService.create(mat);

        Engine weakPetrol = Engine.builder().name("35 TFSI").displacement(1.5).build();
        Engine strongPetrol = Engine.builder().name("55 TFSI").displacement(3.0).build();
        Engine strongestPetrol = Engine.builder().name("V8 Biturbo").displacement(4.0).build();
        Engine weakDiesel = Engine.builder().name("2.0 TDI").displacement(2.0).build();

        engineService.create(weakPetrol);
        engineService.create(strongPetrol);
        engineService.create(strongestPetrol);
        engineService.create(weakDiesel);

        Car audiA6 = Car.builder()
                .engine(strongPetrol)
                .user(mat)
                .doors(5)
                .name("Audi A6")
                .wheels(4)
                .seats(5)
                .maxSpeed(240)
                .build();

        Car audiQ8 = Car.builder()
                .engine(strongestPetrol)
                .user(admin)
                .doors(5)
                .name("Audi RSQ8")
                .wheels(4)
                .seats(5)
                .maxSpeed(280)
                .build();

        Car opel = Car.builder()
                .engine(weakDiesel)
                .user(admin)
                .doors(5)
                .name("Opel Insignia")
                .wheels(4)
                .seats(5)
                .maxSpeed(200)
                .build();

        carService.create(audiA6);
        carService.create(audiQ8);
        carService.create(opel);
    }
}
