package NoSolid.ISP;

interface Fax {
    void faxType();
}

class LanFax implements Fax{
    @Override
    public void faxType(){
        System.out.println("Enviamos el fax usando LanFax.");
    }
}
class AnalogFax implements Fax{
    @Override
    public void faxType(){
        System.out.println("Enviando el fax mediante AnalogFax.");
    }
}
class InternetFax implements Fax{
    @Override
    public void faxType(){
        System.out.println("Enviando el fax mediante InnternetFax(EFax).");
    }
}
