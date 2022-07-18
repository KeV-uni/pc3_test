package Solid.OCP;

abstract class Estudiante {
    String name;
    String regNumber;
    double score;
    String department;

    public Estudiante(String name, String regNumber, double score, String department) {
        this.name = name;
        this.regNumber = regNumber;
        this.score = score;
        this.department=department;
    }

    public String toString() {
        return ("Nombre: " + name + "\nNumero Reg: " + regNumber + "\nDept:" + department + "\nMarks:"
                + score + "\n");
    }

}