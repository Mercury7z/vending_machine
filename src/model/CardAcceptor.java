package model;

import java.util.Random;
import java.util.Scanner;

public class CardAcceptor implements Payment{
    private int moneyAmount;

    public CardAcceptor (int amount) {
        this.moneyAmount = amount;
    }

    public CardAcceptor() {
        this.moneyAmount = 0;
    }

    public int getAmount() {
        return moneyAmount;
    }

    public void setAmount(int amount) {
        this.moneyAmount = amount;
    }

    @Override
    public void addBalance() {
        Scanner sc = new Scanner(System.in);
        java.util.Random r = new Random();
        System.out.println("Можно пополнить баланс на 500 картой");
        System.out.println("Введите номер карточки");
        int userCard = sc.nextInt();
        System.out.println("Вам на карту номер "  + userCard + " отправлен 4 значный пин");
        int tempPin = r.nextInt(1000,10000);
        sendPinToUser(tempPin);
        System.out.println("Введите пин");
        int userInputPin = sc.nextInt();
        if(userInputPin == tempPin) {
            if(checkUserBalance()) {
                System.out.println("Платеж успешно прошел");
                this.moneyAmount += 500;
                return;
            }
            else {
                System.out.println("На карте не хватает денег");
            }
        }
        else {
            System.out.println("Ошибка введен не правильный пин");
        }
    }

    public void sendPinToUser(int pin) {
        System.out.println("Ну тут нельзя подругому имитировать было так что в голове у себя представьте что  вам на телфон или куда то там пин прилетел " + pin);
    }

    public boolean checkUserBalance() {
        return true;
    }

    public String getPaymentMethodName() {
        return "Приемник карт";
    }
}
