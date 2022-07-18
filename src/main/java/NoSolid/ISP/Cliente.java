package NoSolid.ISP;

import java.util.ArrayList;
import java.util.List;

class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion sin ISP\n");

        /*Impresora impresora = new ImpresoraAvanzada();
        impresora.printDocument();
        impresora.sendFax();

        impresora = new ImpresoraBasica();
        impresora.printDocument();*/

        List<Impresora> impresoras = new ArrayList<Impresora>();
        impresoras.add(new ImpresoraAvanzada());
        impresoras.add(new ImpresoraBasica());

        impresoras.forEach((dispositivo)->{dispositivo.printDocument();
        dispositivo.sendFax();});

        /*for(Impresora dispositivo:impresoras){
            dispositivo.printDocument();
            dispositivo.sendFax();
        }*/
    }
}
