package ph.cary;

import java.text.NumberFormat;
import java.util.Scanner;

public class Mortgage {
    private final byte MONTHS_IN_YEAR = 12;
    private final byte PERCENT = 100;

    private int principal;
    private double interest;
    private int years;

    public void principal(double min, double max) {
        String prompt = "Principal($1K to $1M)";
        this.principal = (int)this.inputNumber(prompt, min, max);
    }

    public void interest(double min, double max) {
        String prompt = "Annual Interest(1% to 30%)";
        this.interest = this.inputNumber(prompt, min, max);
    }

    public void years(double min, double max) {
        String prompt = "Years(1yr to 30yrs)";
        this.years = (int)this.inputNumber(prompt, min, max);
    }

    public void printMortgage() {
        double mortgage = this.calculate(this.principal, this.interest, this.years);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    public void printPaymentSchedule() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (int month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double balance = this.balance(this.principal, this.interest, this.years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    private double balance(int principal, double interest, int years, int month) {
        double monthlyInterest = interest / PERCENT / MONTHS_IN_YEAR;
        double numberOfPayments = years * MONTHS_IN_YEAR;

        return principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, month))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }

    private double calculate(int principal, double interest, int years) {
        double monthlyInterest = interest / PERCENT / MONTHS_IN_YEAR;
        double numberOfPayments = years * MONTHS_IN_YEAR;

        return principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }

    private double inputNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;

        while(true) {
            System.out.print(prompt + ": ");
            value = scanner.nextDouble();

            if(value < min || value > max)
                System.out.println("Enter between " + (int)min + " and " + (int)max);
            else
                break;
        }
        return value;
    }
}
