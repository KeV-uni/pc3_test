package Solid.LSP;

import NoSolid.LSP.GuestUserPayment;
import NoSolid.LSP.NewPayment;
import NoSolid.LSP.PaymentHelper;
import NoSolid.LSP.RegisteredUserPayment;

import java.util.Arrays;

public class Cliente {
    public static void main(String[] args) {

        System.out.println("Demostracion LSP.\n");
        PaymentHelper helper = new PaymentHelper();

        // Instanciando dos usuarios registrados
        RegisteredUserPayment irene = new RegisteredUserPayment("Irene");
        RegisteredUserPayment claudio = new RegisteredUserPayment("Claudio");
        // Instanciando el pago de un usuario invitado
        GuestUserPayment guestUser1 = new GuestUserPayment();

        // Consolidando la informacion del pago anterior al helper
        for (RegisteredUserPayment registeredUserPayment : Arrays.asList(irene, claudio))
            helper.addPreviousPayment(registeredUserPayment);

        // Consolidando nuevas solicitudes de pago al helper
        helper.addNewPayment((NewPayment) irene);
        helper.addNewPayment((NewPayment) claudio);
        helper.addNewPayment((NewPayment) guestUser1);

        // Recupera todos los pagos anteriores de los usuarios registrados
        helper.showPreviousPayments();

        // Procesa todas las solicitudes de pago nuevos de todos los usuarios
        helper.processNewPayments();


    }
}
