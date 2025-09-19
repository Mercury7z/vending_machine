import enums.ActionLetter;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {
    private Scanner sc = new Scanner(System.in);
    private final UniversalArray<Product> products = new UniversalArrayImpl<>();

    private Payment payAcceptor;

    private static boolean isExit = false;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new Soda(ActionLetter.C, 30),
                new CocaCola(ActionLetter.D, 50),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        payAcceptor = new CoinAcceptor(0);
    }

    public static void run() {
        AppRunner app = new AppRunner();
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);

        print("Монет на сумму: " + payAcceptor.getAmount());

        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);

    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (payAcceptor.getAmount() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        print(" a - пополнить баланс");
        showActions(products);
        print(" h - Выйти");
        String action = "";
        try {
            action = fromConsole().substring(0, 1);
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("Нужно выбрать действие");
        }
        if ("a".equalsIgnoreCase(action)) {
            System.out.println("Выберите удобный способ оплаты");
            System.out.println("Способ номер 1: приемник монет");
            System.out.println("Способ номер 2: приемник карт");
            int userInput = sc.nextInt();
            int balance = 0;
            switch (userInput) {
                case 1:
                    balance = payAcceptor.getAmount();
                    payAcceptor = new CoinAcceptor(balance);
                    payAcceptor.addBalance();
                    break;
                case 2:
                    balance = payAcceptor.getAmount();
                    payAcceptor = new CardAcceptor(balance);
                    payAcceptor.addBalance();
                    break;
                    }
                    payAcceptor.addBalance();

        }
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    payAcceptor.setAmount(payAcceptor.getAmount() - products.get(i).getPrice());
                    print("Вы купили " + products.get(i).getName());
                    break;
                } else if ("h".equalsIgnoreCase(action)) {
                    isExit = true;
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            print("Недопустимая буква. Попрбуйте еще раз.");
            chooseAction(products);
        }


    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
