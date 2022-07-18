package Solid.OCP;

import java.util.Arrays;
import java.util.List;

public class ArtsDistinctionDecider implements DistinctionDecider{
    List<String> arts= Arrays.asList("Historia","Literatura");

    @Override
    public void evaluateDistinction(Estudiante estudiante) {
        if (arts.contains(estudiante.department)) {
            if (estudiante.score > 70) {
                System.out.println(estudiante.regNumber+" ha recibido una distincion en artes.");
            }
        }
    }
}