package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main класс, с которого spring начинает работу находится (здесь - в пакете main)
 * и по умолчанию, spring сканирует все что в этом же и все что в нижних пакетах, относительно этого пакетах
 *
 * scanBasePackages - приписаны пакеты, которые он должен сканировать помимо "текущего"
 */

@SpringBootApplication(scanBasePackages = {"controller", "service", "utils"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
