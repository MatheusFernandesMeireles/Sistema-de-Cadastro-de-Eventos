import controller.EventController;
import model.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao sistema de eventos!");

        System.out.print("Digite seu nome: ");
        String name = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua cidade: ");
        String city = scanner.nextLine();

        User user = new User(name, email, city);
        EventController controller = new EventController(user);
        controller.start();
    }
}
