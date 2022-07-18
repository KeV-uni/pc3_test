package Solid.ISP;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion con ISP");

        Impresora impresora = new ImpresoraBasica();
        impresora.printDocument();
        impresora = new ImpresoraAvanzada();
        impresora.printDocument();

        DispositivoFax fax = new ImpresoraAvanzada();
        fax.sendFax();
    }
}
