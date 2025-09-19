package model;

public class CoinAcceptor implements Payment{
    private int moneyAmount;

    public int getAmount() {
        return moneyAmount;
    }

    public CoinAcceptor (int amount) {
        this.moneyAmount = amount;
    }

    @Override
    public void setAmount(int amount) {
        this.moneyAmount = amount;
    }

    @Override
    public void addBalance() {
        System.out.println("Баланс обновлен");
        this.moneyAmount += 10;
    }

    public String getPaymentMethodName() {
        return "приемник монет ";
    }
}
