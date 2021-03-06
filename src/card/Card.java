package card;

/**
    @name The Credit Card name. This name Should be unquie and serves as an id for the card
    @apr The annual percentage rate for the card
    @balance The current Balance of the card
    @paymentPromise A variable designated to be the  amount paid to that card each month.**/
public class Card {
    private String name;
    private double apr;
    private double balance;
    private double paymentPromise = 0 ;

    public Card(){
        this.name = "";
        this.balance =0;
        this.apr = 0;
    }
    public Card( String name){
        this.name = name;
        this.balance =0;
        this.apr =0;


    }
    public Card(String name, double balance){
        this.name = name;
        this.balance = balance;
        this.apr = 0;
    }
    public Card(String name , double balance , double apr){
        this.name = name;
        this.balance = balance;
        this.apr = apr;
    }

    // Getter and setter methods

    public void setName(String name) {
        this.name = name;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getApr() {
        return apr;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
    //Special Info
    public double getMonthlyApr(){
        return apr/12.0;
    }
    public double monthlyInterest()
    {
        return getMonthlyApr()/100 * balance;
    }
    public double monthlyInterest(double princple , double interest){ return interest/100 * balance;}
    @Override
    public String toString() {
        return  "Name : " + name + '\n' +
                "APR: " + apr + "\n" +
                "Balance: " + balance + "\n\n";
    }
}
