package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//      Car car1 = new Car("BMW", 2007);
//      Car car2 = new Car("Mercedes", 2018);
//      Car car3 = new Car("Audi", 2025);
//      Car car4 = new Car("Volvo", 2002);
//
//      userService.add(new User("Андрей", "Сергеевич", "user1@mail.ru", car1));
//      userService.add(new User("Сергей", "Петрович", "user2@mail.ru", car2));
//      userService.add(new User("Антон", "Яковлев", "user3@mail.ru", car3));
//      userService.add(new User("Петр", "Андреевич", "user4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            Car car = user.getCar();
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        String CarToGet = "BMW";
        User user = userService.getUserWithCar(CarToGet);
        System.out.println("\n----------------\n");
        if (user != null) {
            System.out.println("Пользователь владеет автомобилем");
            System.out.println("---");
            System.out.printf("Имя: %s\n", user.getFirstName());
            System.out.printf("Фамилия: %s\n", user.getLastName());
            System.out.printf("Email: %s\n", user.getEmail());
            System.out.printf("Автомобиль: %s %s года", user.getCar().getModel(), user.getCar().getSeries());
        } else {
            System.out.println("Ни один из пользователей не владеет таким автомобилем");
        }

        context.close();
    }
}
