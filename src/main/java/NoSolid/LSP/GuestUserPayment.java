package NoSolid.LSP;

public class GuestUserPayment implements Payment {
    String name;
    public GuestUserPayment(String abejita) {
        this.name = "guest";
    }

    public GuestUserPayment() {

    }

    @Override
    public void previousPaymentInfo(){
        throw new UnsupportedOperationException();
    }
    @Override
    public void newPayment(){
        System.out.println("Processing "+name+"'s current payment request.");

    }
}