import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "/n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            // レンタルポイントを加算
            frequentRenterPoints += each.getFrequentRenterPoints();

            // この貸し出しに関する数値の表示
            result += "/t" + each.getMovie().getTitle() + "/t" +
                    String.valueOf(each.getCharge()) + "/n";
        }
        // 降った部分の追加
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "/n";
        result += "You earnet " + String.valueOf(getTotalFrequentRenterPoints()) +
                "frequent renter points";
        return result;
    }

    public String htmlStatement() {
        Enumeration rentals = _rentals.elements();
        String result = "<H1>Rentals for <EM>" + getName() + "</EM></h1>/n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //この貸し出しに関する数値の表示
            result += each.getMovie().getTitle() + ": " +
                    String.valueOf(each.getCharge()) + "<BR>/n";
        }
        //フッタ部分の追加
        result += "<p>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM></p>/n";
        result += "On this rental you earned <EM>" +
                String.valueOf(getTotalFrequentRenterPoints()) +
                "</EM> frequent renter points<P>";
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}
