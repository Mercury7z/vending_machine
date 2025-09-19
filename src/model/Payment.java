package model;

public interface Payment {


    public void addBalance();

    public int getAmount();

    public void setAmount(int amount);

    public String getState();

    String getPaymentMethodName();

}
