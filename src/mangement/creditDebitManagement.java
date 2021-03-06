package mangement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import card.*;

public class creditDebitManagement {
    private creditList debit ;
    public creditDebitManagement(){
        this.debit = new creditList();
    }
    public creditDebitManagement(creditList debt){
        this.debit = debt;
    }

    // Given a card find out how long it will take to payoff that card with a given amount
    //The total amount of months it would take for a card be payoff with the given amount
    public int payoffCardTime(String name, double amount) {
        Card curr = debit.getCard(name);
        double interest = curr.getMonthlyApr();
        double balance = curr.getBalance();
        int months = 0;
        while (balance > 0) {
            balance -= amount;
            balance = balance + curr.monthlyInterest(balance , interest);
            months++;
//             System.out.println( " "+balance + "  "+ months);
        }

        return months;
    }
    // How much interst will u save with a payment
    public double interestSaved(String name, double payment){
        Card myCard = debit.getCard(name);
        double balance = myCard.getBalance();
        balance -= payment;
        myCard.setBalance(balance);

        return myCard.monthlyInterest();
    }
    //Finds the card that has the highest interest
    public Card highInterestCard(){
        Card ret = null;
        double max = 0;

        for ( int i = 0 ; i < debit.size() ; i++)
        {
//                double balance = debit.getCard(i).getBalance();
                double interest = debit.getCard(i).monthlyInterest();
//                double total = balance+interest;
                if( interest > max){
                    ret = debit.getCard(i);
                    max=interest;
                }

        }
        return ret;
    }
    //Find the card with the highest balance
    public Card highestBalanceCard(){
        Card ret = null;
        double max = 0;

        for ( int i = 0 ; i < debit.size() ; i++)
        {
                double balance = debit.getCard(i).getBalance();
//            double interest = debit.getCard(i).monthlyInterest();
//                double total = balance+interest;
            if( balance > max){
                ret = debit.getCard(i);
                max=balance;
            }

        }
        return ret;
    }
    // Returns the total amount of interest for all the cards toegther
    public double totalInterest(){
        double ret = 0;
        for ( int i = 0 ; i < debit.size() ; i ++ )
        {
            ret += debit.getCard(i).monthlyInterest();
        }
        return ret;
    }
    // Writes the current information to a text file
    //Format will be just the info of card written down
    public void writeToText(String path) {
        String fileName = path+".txt";
        File write = new File(fileName);

        //Create the file if it does not exist
        try {
            if (write.createNewFile()) {
                System.out.println("File created: " + write.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(fileName);

            for (int i = 0; i < debit.size(); i++) {

                Card writeout = debit.getCard(i);

                myWriter.write(writeout.getName() + "\n");
                myWriter.write("" + writeout.getBalance() + "\n");
                myWriter.write("" + writeout.getApr() + "\n");



            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFile(String path) {
        try {
            String fileName = path+ ".txt";
            File read = new File(fileName);
            Scanner myReader = new Scanner(read);
            int counter = 1;
            creditList newDebit = new creditList();
            Card add = new Card();
            while (myReader.hasNext()) {

                String name = myReader.nextLine();
                double balance = Double.parseDouble(myReader.nextLine());
                double apr = Double.parseDouble(myReader.nextLine());

                Card newC = new Card(name,balance, apr);
                newDebit.addNewCard(newC);
            }
            setDebit(newDebit);
            myReader.close();
        } catch(FileNotFoundException e) {
                System.out.println("File does not Exist!");
    }

}


    public void setDebit(creditList newCard) {
        this.debit = newCard;
    }

    public creditList getDebit() {
        return debit;
    }

    @Override
        public String toString() {
            return "creditDebitMangement{" +
                    "debit=" + debit +
                    '}';
        }


}
