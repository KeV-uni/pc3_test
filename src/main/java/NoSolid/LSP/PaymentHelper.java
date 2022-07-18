package NoSolid.LSP;

import java.util.ArrayList;
import java.util.List;

public class PaymentHelper {
    List<PreviousPayment> previousPayments = new ArrayList<PreviousPayment>();
    List<NewPayment> newPayments = new ArrayList<NewPayment>();
    public void addPreviousPayment(RegisteredUserPayment previousPayment){
        boolean b;
        if (previousPayments.add((PreviousPayment) previousPayment)) b = true;
        else b = false;
        boolean add = true;
    }
    public void addNewPayment(NewPayment newPaymentRequest){
        newPayments.add(newPaymentRequest);
    }
    public void showPreviousPayments() {
        for (PreviousPayment payment: previousPayments) {
            payment.previousPaymentInfo();
            System.out.println("------");
        }
    }
    public void processNewPayments() {
        for (NewPayment payment: newPayments) {
            payment.newPayment();
            System.out.println("***********");
        }
    }

    public void addUser(RegisteredUserPayment pagoAbejita) {
    }

    public void addUser(GuestUserPayment pagoAbejita) {

    }
}