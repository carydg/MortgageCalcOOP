package ph.cary;

public class Main {
    public static void main(String[] args) {
        var mortgage = new Mortgage();

        mortgage.principal(1_000, 1_000_000);
        mortgage.interest(1, 30);
        mortgage.years(1, 30);

        mortgage.printMortgage();
        mortgage.printPaymentSchedule();
    }
}