package Solid.SRP;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion con SRP");

        Empleado jessica = new Empleado("Jessica", "Abejita", 7.5);
        showEmpDetail(jessica);

        System.out.println("\n*******\n");

        Empleado chalo = new Empleado ("Chalito", "Smart", 3.2);
        showEmpDetail(chalo);

    }

    private static void showEmpDetail(Empleado emp) {
        // Muestra detalles del empleado
        emp.displayEmpDetail();

        //Genera el ID
        showEmpId(emp);

        // Verifica el nivel laboral
        showEmpLevel(emp);
    }
    private static void showEmpId(Empleado emp){
        GeneradorIDEmpleado idEmpleado = new GeneradorIDEmpleado();
        String empId = idEmpleado.generateEmpId(emp.firstName);
        System.out.println("El ID del empleado es : "+empId);
    }
    private static void showEmpLevel(Empleado emp){
        SeniorityChecker checker = new SeniorityChecker();
        String seniorityLevel = checker.checkSeniority(emp.experienceInYears);
        System.out.println("Este empleado es un empleado : "+seniorityLevel);
    }
}