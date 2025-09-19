package model;

public interface Payment {


    public void addBalance();

    public int getAmount();

    public void setAmount(int amount);

    String getPaymentMethodName();

}
