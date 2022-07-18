package Solid.ISP;

public class ImpresoraAvanzada implements Impresora, DispositivoFax{
        public void printDocument(){
            System.out.println("La impresora avanzada imprime un documento.");
        }
        public void sendFax(){
            System.out.println("La impresora avanzada envia un fax.");
        }
}
