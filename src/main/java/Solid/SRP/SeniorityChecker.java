package Solid.SRP;

public class SeniorityChecker {
    public String checkSeniority(double experienceInYears){
        return experienceInYears>5 ? "Senior":"Junior";
    }
}
