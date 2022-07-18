# PC3 - PRINCIPIOS SOLID
En este archivo Readme resolveremos los ejercicios de la PC3.\
Los integrantes que participaron en el desarrollo de esta practica son:

| Integrantes            |
|------------------------|
| Pumayalli Quispe Kevin |
| Unda Miguel Carlos     |
| Aguilar Ybarra Mario   |
|  Rosales Joaquin Julio |


### Principio de Responsabilidad Unica (SRP)
Pregunta 1 \
Muestra la salida y explica los resultados en función de los métodos entregados.

Vemos que se muestra la siguiente salida:
````
Demostracion sin SRP 
Nombre del empleado: Abejita,Jessica
Este empleado tiene 7.5 años de experiencia.
El ID del empleado es: J368
Este empleado es un empleado senior`
----
Nombre del empleado: Smart,Chalito
Este empleado tiene 3.2 años de experiencia.
El ID del empleado es: C415
Este empleado es un empleado junior 
````
Para la primera parte en la clase Cliente se instancia el objeto jessica con los siguientes argumentos:
firstName: ”Jessica”, lastName: ”Abejita”, experience: 7.5 .
```java
Empleado jessica = new Empleado("Jessica", "Abejita", 7.5);
```
Todo esto haciendo uso del constructor de la clase Empleado cuyos parámetros son:
firstName, lastName, experience .
```java
public Empleado(String firstName, String lastName, double experience) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.experienceInYears = experience;
}
```
Luego mandamos a llamar al método showEmpDetail(jessica) pasándole como argumento el objeto jessica, de la clase Empleado.

```java
showEmpDetail(jessica);
```
Que ejecutará el método estático privado showEmpDetail con un objeto de la clase Empleado como parámetro
```java
private static void showEmpDetail(Empleado emp) {
    emp.displayEmpDetail();
    System.out.println("El ID del empleado es: "+ emp.generateEmpId(emp.firstName));
    System.out.println("Este empleado es un" + " empleado " +
        emp.checkSeniority(emp.experienceInYears)
    );
}
```
Luego ejecutamos el método displayEmpDetail() del objeto pasado como argumento que en éste caso sería jessica .
```java
public void displayEmpDetail(){
    System.out.println("Nombre del empleado: "+lastName+","+firstName);
    System.out.println("Este empleado tiene "+ experienceInYears+" años de experiencia.");
}
```
Lo que haría el método displayEmpDetail() sería mostrarnos el nombre ,el apellido y los años de experiencia laboral del objeto jessica de la clase Empleado.

Luego mostraremos el ID del objeto jessica a través del método generateEmpId pasándole como argumento el firstName del objeto jessica.
```java
public String generateEmpId(String empFirstName){
    int random = new Random().nextInt(1000);
    empId = empFirstName.substring(0,1)+random;
    return empId;
}
```
Lo que hará éste método generateEmpId será generar una identificación del objeto jessica mediante la concatenación de la cadena del primer carácter de su firstName, con un número entero random entre (0 y 1000) respectivamente.

Luego mostraremos la antigüedad del objeto jessica a través del método checkseniority
pasando como argumento experienceInYears (años de experiencia) del objeto jessica.
```java
public String checkSeniority(double experienceInYears){
    return  experienceInYears > 5 ?"senior":"junior";
}
```
Lo que hará este método checkseniority será determinar si el objeto jessica de la clase Empleado es senior o junior. Si los años de experiencia es mayor que 5 será un senior, caso contrario será un junior.

Por último, para la segunda parte de la salida en pantalla, se volverán a repetir los mismos pasos para el objeto chalo 
de la clase Empleado.

Pregunta 2\
¿Cuál es el problema con este diseño y las razones posibles del problema?

El principal problema que se observa es que se viola el Principio de Responsabilidad Única (SRP). 
Ya que a la clase Empleado le estamos dando 3 responsabilidades, aunque parezca que no, 
la primera sería mostrar los detalles del empleado (displayEmpDetail() ), la segunda seria checar 
los años y el nivel de antigüedad de un empleado (checkSeniority() ), y la última responsabilidad 
sería generar una identificación del empleado (generateEmpId() ). Como se puede observar estas 3 
responsabilidades son muy distintas entre sí, por lo cual no tendría sentido que estén todas juntas
en una misma clase. Además, al estar estas 3 responsabilidades en una misma clase, surgen problemas 
al momento de implementar modificaciones en algún futuro, como por ejemplo: si se quiere mostrar los 
detalles del empleado de una forma distinta, si se quiere cambiar los criterios para decidir el nivel 
de antigüedad de un empleado, si se quiere cambiar la lógica del algoritmo para generar la 
identificación del empleado. En todos estos casos se tendría que modificar la clase Empleado. Pero si 
se siguiera el SRP, tanto el método checkSeniority() y el método generateEmpId() tendrían cada uno 
su propia clase, separando así las responsabilidades, y si en caso se quisiera hacer una modificación 
de un método en el futuro, sólo se tendría que modificar su respectiva clase.

Pregunta 3\
Modifica la clase Empleado. Agrega dos clases SeniorityChecker que contiene el
método checkSeniority() y la clase GeneradorIDEmpleado contiene el método
generateEmpId(...) para generar la identificación del empleado.

Como vemos primero creamos la clase SeniorityChecker que únicamente contiene el método checkSeniority() 
que recibe como parámetro un double experienceInYears (años de experiencia), además este método tiene un 
valor de retorno string, si los años de experiencia es mayor que 5 devuelve el String “Senior”, caso 
contrario devuelve el String “Junior”.
```java
public class SeniorityChecker {
    public String checkSeniority(double experienceInYears){
        return experienceInYears>5 ? "Senior":"Junior";
    }
}
```
Ahora vemos que creamos la clase GeneradorIDEmpleado que únicamente tiene el método generateEmpId() que recibe como parámetro un String empFirstName (primer nombre), lo primero que hace es generar un numero random entero entre 0 y 1000, luego define el String empId como la concatenación del primer carácter de empFirstName con el valor del numero random obtenido anteriormente,obteniendo así como resultado el ID del empleado y por último retornando este valor.
```java
public class GeneradorIDEmpleado {
    String empId;
    public String generateEmpId(String empFirstName) {
        int random = new Random().nextInt(1000);
        empId=empFirstName.substring(0,1)+random;
        return empId;
    }
}
```
Para la primera parte vemos que dentro del main de la clase Cliente, se instancia el objeto jessica a través del constructor de la clase Empleado como vimos anteriormente, luego llamamos al método estático showEmpDetail() pasándole como argumento el objeto jessica que acabamos de crear.
```java
public class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion con SRP");

        Empleado jessica = new Empleado("Jessica", "Abejita", 7.5);
        showEmpDetail(jessica);

        System.out.println("\n*******\n");

        Empleado chalo = new Empleado ("Chalito", "Smart", 3.2);
        showEmpDetail(chalo);

    }
    /*more code*/
}
```
Lo que hará el método estático showEmpDetail() será llamar al método displayEmpDetail() de la clase Empleado a través del objeto jessica que pasamos como parámetro. Esto nos mostrará tanto el nombre, el apellido y los años de experiencia del objeto jessica. Luego llamaremos a los métodos estáticos showEmpId() y showEmpLevel() pasándoles a ambos como argumento el objeto jessica de la clase Empleado.
```java
private static void showEmpDetail(Empleado emp) {
    // Muestra detalles del empleado
    emp.displayEmpDetail();

    //Genera el ID
    showEmpId(emp);

    // Verifica el nivel laboral
    showEmpLevel(emp);
}
```
Lo que hará el método estático showEmpId() será instanciar un objeto idEmpleado de la clase GeneadorIDEmpleado que creamos anteriormente. Luego definiremos un String empId como el resultado de llamar al método generateEmpId a través del objeto idEmpleado instanciado recientemente, pasándole como argumento el firsName del objeto jessica, que recibió el método estático showEmpId() como parámetro. Por último simplemente mostraremos el String empId que sera el ID de empleado del objeto jessica.
```java
private static void showEmpId(Empleado emp){
    GeneradorIDEmpleado idEmpleado = new GeneradorIDEmpleado();
    String empId = idEmpleado.generateEmpId(emp.firstName);
    System.out.println("El ID del empleado es : "+empId);
}
```
Por otra parte lo que hará el método estático showEmpLevel() será instanciar un objeto checker de la clase SeniorityChecker que creamos anteriormente. Luego definiremos un String seniorityLevel como el resultado de llamar al método checkSeniority a través del objeto checker instanciado recientemente, pasándole como argumento el experienceInYears del objeto jessica, que recibio el metodo estático showEmpLevl() como parámetro. Por último mostraremos el String seniorityLevel que será el nivel de antigüedad del empleado del objeto jessica.
```java
private static void showEmpLevel(Empleado emp){
    SeniorityChecker checker = new SeniorityChecker();
    String seniorityLevel = checker.checkSeniority(emp.experienceInYears);
    System.out.println("Este empleado es un empleado : "+seniorityLevel);
}
```
Pregunta 4\
Realiza una demostración completa que sigue a SRP. Explica tus resultados.

Como se observa  la salida por pantalla es muy similar a cuando no estaba implementado el Principio de Responsabilidad Unica (SRP), vemos que se muestra el apellido, el nombre y los años de experiencia para Jessica. También vemos que tiene asignado un ID de empleado y debido a que tiene 7.5 años de experiencia que es mayor que 5, se le considera que es un empleado senior. Lo mismo pasa para el empleado Chalito con la diferencia que él tiene 3.2 años de experiencia que es menor que 5, por lo tanto es un empleado junior.

```
Demostracion con SRP
Nombre del empleado: Abejita,Jessica
Este empleado tiene 7.5 años de experiencia.
El ID del empleado es : J839
Este empleado es un empleado : Senior
*******
Nombre del empleado: Smart,Chalito
Este empleado tiene 3.2 años de experiencia.
El ID del empleado es : C348
Este empleado es un empleado : Junior
```
Como conclusión nos damos cuenta que el Principio de Responsabilidad Única (SRP) no nos obliga a que una clase deba tener como máximo un solo método, sino que simplemente enfatiza en la responsabilidad individual de ahí el nombre de Responsabilidad Unica. Por ejemplo en nuestro código en la clase Cliente tenemos 3 métodos showEmpDetail, showEmpId y showEmpLevel que lo que hacen es mostrar salidas de nombres, apellidos, años de experiencia, ID y nivel de antigüedad. Estos métodos están estrechamente relacionados, ya que cumplen una misma funcionalidad, la de mostrar, por lo que tiene sentido colocarlos en una misma clase.
*******
### Principio abierto/cerrado (OCP)
Pregunta 5\
¿Por que no es correcto colocar displayResult() y evaluateDistinction() en la misma clase:

Como primer punto a aclarar el método displayResult() seria el metodo toString() de la clase Estudiante. Entonces este metodo toString y el método EvaluateDistinction() no pueden estar en la misma clase ya que violaría el principio SRP ya que la clase Estudiante ejecutaría 2 responsabilidades.


Pregunta 6\
Muestra la salida y explica los resultados en función de los métodos entregados

Aquí se muestra la salida por pantalla del programa:
```
Demostracion sin OCP
Resultados:
Nombre: Irene
Numero Regex: R1
Dept:Ciencia de la Computacion.
Marks:81.5
*******
Nombre: Jessica
Numero Regex: R2
Dept:Fisica
Marks:72.0
*******
Nombre: Chalo
Numero Regex: R3
Dept:Historia
Marks:71.0
*******
Nombre: Claudio
Numero Regex: R4
Dept:Literatura
Marks:66.5
*******
Distinciones:
R1 ha recibido una distincion en ciencias.
R3 ha recibido una distincion en artes.
```
Ahora procederemos a explicar los metodos que se usaron para mostrar estar salida

Primero en la clase Cliente está List<Estudiante> enrolledStudents= enrollStudentst().

```java
public class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion sin OCP");
        List<Estudiante> enrolledStudents = enrollStudents();
    }
    /*more code*/
}
```
En donde enrollStudentst() es un método que instancia 4 objetos de la clase Estudiante. Luego crea una lista de objetos de la clase Estudiante llamada estudiantes,para luego añadir a esta lista los 4 objetos instanciados previamente, y por último retorna esta lista.
```java
private static List<Estudiante> enrollStudents() {
    Estudiante irene = new Estudiante("Irene", "R1", 81.5, "Ciencia de la Computacion.");
    Estudiante jessica= new Estudiante("Jessica", "R2", 72, "Fisica");
    Estudiante chalo = new Estudiante("Chalo", "R3", 71, "Historia");
    Estudiante claudio = new Estudiante("Claudio", "R4", 66.5, "Literatura");

    List<Estudiante> estudiantes = new ArrayList<Estudiante>();
    estudiantes.add(irene);
    estudiantes.add(jessica);
    estudiantes.add(chalo);
    estudiantes.add(claudio);
    return estudiantes;
}
```
Procederemos a mostrar los resultados mediante un for que va recorrer la lista enrollStudents imprimiendo cada objeto Estudiante de la lista.
```java
public class Cliente {
    public static void main(String[] args) {
        /*more code*/
        // Muestra todos los resultados
        System.out.println("Resultados:");
        for(Estudiante estudiante:enrolledStudents){
            System.out.println(estudiante);
        }
    }
}
```
Y para mostrar los detalles del estudiante se usa el método toString(). Donde este método muestra los datos nombre,numeroRegex,dept,puntaje de cada estudiante.
```java
public class Estudiante {
    /*more code*/
    @Override
    public String toString() {
        return ("Nombre: " + name + "\nNumero Regex: " + regNumber + "\nDept:" + department + "\nMarks:"
                + score + "\n*******");
    }
}
```
Luego en el main de la clase cliente instanciamos un objeto llamado distinctionDecider de la clase DistinctionDecider. Luego mediante un for recorremos la lista enrollStudentst() y para cada iteración llamamos al método evaluateDistinction a través del objeto distinctionDecider instanciado previamente pasandole como argumento cada objeto de la lista enrollStudentst(). 

```java
public class Cliente {
    public static void main(String[] args) {
        /**more code/
        System.out.println("Distinciones:");
        DistinctionDecider distinctionDecider = new DistinctionDecider();
        // Evalua las distinciones
        for(Estudiante estudiante:enrolledStudents){
            distinctionDecider.evaluateDistinction(estudiante);
        }
    }
```
Donde este método evaluateDistinction evalúa si el departamento al que pertenece el estudiante es de ciencias de la computación o física,para luego validar si su puntaje es mayor a 80 para recibir una distincion en este departamento. Ahora si el departamento en el que pertenece el estudiante es de historia o inglés, válida si su puntaje es mayor a 70 para recibir una distincion en este departamento.
```java
public class DistinctionDecider {
    List<String> science= Arrays.asList("Ciencia de la Computacion.","Fisica");
    List<String> arts= Arrays.asList("Historia","Literatura");
    public void evaluateDistinction(Estudiante estudiante) {
        if (science.contains(estudiante.department)) {
            if (estudiante.score > 80) {
                System.out.println(estudiante.regNumber+" ha recibido una distincion en ciencias.");
            }
        }
        if (arts.contains(estudiante.department)) {
            if (estudiante.score > 70) {
                System.out.println(estudiante.regNumber+" ha recibido una distincion en artes.");
            }
        }
    }
}
```
Pregunta 7\
¿Cuál es el problema con este diseño y las razones posibles del problema?.

Un problema seria cuando se quiere agregar una nueva línea de departamento como por ejemplo Política que incluye los departamentos de ética y economía. Entonces se necesitará hacer cambios donde se tendrá que modificar o añadir un nuevo criterio de distinction para esta nueva línea de departamento y para esto se tendría que modificar el método EvaluateDistinction() agregando nuevas instrucciones if. De esta manera se tendrá que volver a modificar todo el flujo del trabajo del código Afectando asi a la clase DistinctionDecider.
Por lo tanto se está violando el OCP ya que no estaría cerrada a modificaciones.

Pregunta 8\
Debes abordar el método de evaluación para la distinción de una mejor manera. Por lo tanto, crea 
la interfaz DistinctionDecider que contiene un método llamado EvaluationDistinction.

Creamos la interface DistinctionDecider y el método evaluateDistinction() que lo contiene para evaluar la distinción de cada estudiante de una mejor manera posible. Esta interfaz nos sirve para luego implementar diferentes clases de distinción cada uno con su propio criterio.
```java
interface DistinctionDecider{
    void evaluateDistinction(Estudiante estudiante);
}
```
Pregunta 9\
Completa el código de ArtsDistinctionDecider y ScienceDistinctionDecider que implementan esta interfaz y sobreescribe el método de evaluateDistinction(...) para especificar los criterios de evaluación según sus necesidades. De esta forma, los criterios de distinción específicos de flujo se envuelven en una unidad independiente. Debes tener en cuenta que el método de evaluateDistinction(...) acepta un parámetro Estudiante. Significa que ahora también puede pasar un objeto ArtsStudent o un objeto ScienceStudent a este método.

Como se puede ver las clases ArtsDistinctionDecider y ScienceDistinctionDecider implementan la interfaz de DistinctionDecider y utilizan el método evaluateDistinction para cumplir su propósito.

```java
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
public class ScienceDistinctionDecider implements DistinctionDecider {
    List<String> science= Arrays.asList("Ciencia de la Computacion.","Fisica");

    @Override
    public void evaluateDistinction(Estudiante estudiante) {
        if (science.contains(estudiante.department)) {
            if (estudiante.score > 80) {
                System.out.println(estudiante.regNumber+" ha recibido una distincion en ciencias.");
            }
        }
    }
}
```
Pregunta 10\
Realiza una demostración completa que sigue a OCP. Explica tus resultados

Aquí mostramos la imagen de salida en el que solamente Irene con numero de registro R1 y Chalo con numero de registro R3 recibieron una distinción de sus respectivos departamentos
```
Demostracion con OCP
Resultados:
Nombre: Irene
Numero Reg: R1
Dept:Ciencia de la Computacion.
Marks:81.5

Nombre: Jessica
Numero Reg: R2
Dept:Fisica
Marks:72.0

Nombre: Chalo
Numero Reg: R3
Dept:Historia
Marks:71.0

Nombre: Claudio
Numero Reg: R4
Dept:Literatura
Marks:66.5

Distinciones:
R1 ha recibido una distincion en ciencias.
R3 ha recibido una distincion en artes.
```
En la clase abstracta Estudiante se define su constructor con su nombre,número de registro,score y departamento,y luego tenemos el método toString() que nos muestra en una cadena los datos del estudiante
```java
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
```
La clase ArteEstudiante extiende de la clase abstracta Estudiante esto quiere decir que tiene implementados sus mismos constructores y sus mismos métodos. En esta clase únicamente instanciaremos ,mediante su propio constructor, estudiantes de la línea de departamento de artes
```java
public class ArteEstudiante extends Estudiante{
    public ArteEstudiante(String name, String regNumber, double score,String dept) {
        super(name,regNumber,score,dept);
    }
}
```
La clase CienciaEstudiante extiende de la clase abstracta Estudiante esto quiere decir que tiene implementados sus mismos constructores y sus mismos métodos. En esta clase únicamente instanciaremos ,mediante su propio constructor, estudiantes de la línea de departamento de ciencias.
```java
public class CienciaEstudiante extends Estudiante{
    public CienciaEstudiante(String name, String regNumber, double score, String dept){
        super(name,regNumber,score,dept);
    }
}
```
La interface DistinctionDecider únicamente tiene definido el método evaluateDistinction cuyo parámetro es un objeto de la clase Estudiante.
```java
interface DistinctionDecider{
    void evaluateDistinction(Estudiante estudiante);
}
```
La clase ScienceDistinctionDecider implementa la interface DistinctionDecider, dentro de esta clase vamos a definir una lista de Strings llamada science que tendrá como valores : ciencia de la computación y física. También tendrá un método llamado evaluateDistinction definida con un objeto Estudiante como parámetro lo que hará este método será corroborar si el departamento al que pertenece el estudiante está dentro de la lista String science.Si es asi compara si su puntaje es mayor a 80 para otorgarle una distinction en su departamento.
```java
public class ScienceDistinctionDecider implements DistinctionDecider {
    List<String> science= Arrays.asList("Ciencia de la Computacion.","Fisica");

    @Override
    public void evaluateDistinction(Estudiante estudiante) {
        if (science.contains(estudiante.department)) {
            if (estudiante.score > 80) {
                System.out.println(estudiante.regNumber+" ha recibido una distincion en ciencias.");
            }
        }
    }
}
```
La clase ArtsDistinctionDecider implementa la interface DistinctionDecider, dentro de esta clase vamos a definir una lista de Strings llamada arts que tendrá como valores : historia y literatura. También tendrá un método llamado evaluateDistinction definida con un objeto Estudiante como parámetro lo que hará este método será corroborar si el departamento al que pertenece el estudiante está dentro de la lista String arts.Si es asi compara si su puntaje es mayor a 70 para otorgarle una distinction en su departamento.
```java
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
```
La clase Cliente crea 2 listas con objetos de la clase Estudiante , la primera lista llamada CienciasEstudiantes que llama al método enrollScienceStudents(),lo que hace este método es que va instanciar 2 objetos estudiantes mediante el constructor CienciaEstudiante,luego creará una lista de objetos estudiantes llamada CienciasEstudiantes ,a esta lista incluirá los 2 objetos instanciados previamente y retornará esta lista. Para la segunda lista llamada ArtesEstudiantes se llamará al método enrollArtsStudents() cuyo funcionamiento es muy similar al enrollScienceStudents.

```java
public class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion con OCP");
        List<Estudiante> CienciasEstudiantes = enrollScienceStudents();
        List<Estudiante> ArtesEstudiantes = enrollArtsStudents();
        /*more code*/

    }

    private static List<Estudiante> enrollScienceStudents() {
        Estudiante Irene = new CienciaEstudiante("Irene", "R1", 81.5,"Ciencia de la Computacion.");
        Estudiante jessica = new CienciaEstudiante("Jessica", "R2", 72,"Fisica");
        List<Estudiante> CienciasEstudiantes = new ArrayList<Estudiante>();
        CienciasEstudiantes.add(Irene);
        CienciasEstudiantes.add(jessica);
        return CienciasEstudiantes;
    }

    private static List<Estudiante> enrollArtsStudents() {
        Estudiante chalo = new ArteEstudiante("Chalo", "R3", 71,"Historia");
        Estudiante claudio = new ArteEstudiante("Claudio", "R4", 66.5,"Literatura");
        List<Estudiante> ArtesEstudiantes = new ArrayList<Estudiante>();
        ArtesEstudiantes.add(chalo);
        ArtesEstudiantes.add(claudio);
        return ArtesEstudiantes;
    }


}
```
Luego procederemos a mostrar los resultados, recorreremos la lista CienciasEstudiantes con un for y mostraremos cada objeto estudiante, el mismo proceso se repetirá para la lista ArtesEstudiantes.
```java
public class Cliente {
    public static void main(String[] args) {
        /*more code*/
        // Muestra todos los resultados.
        System.out.println("Resultados:");
        for (Estudiante estudiante : CienciasEstudiantes) {
            System.out.println(estudiante);
        }
        for (Estudiante estudiante : ArtesEstudiantes) {
            System.out.println(estudiante);
        }
        /*more code*/
    }
}
```
Por último instanciamos 2 objetos de la clase DistinctionDecider el primero llamado scienceDistinctionDecider lo instanciamos como un objeto de la clase ScienceDistinctionDecider el segundo llamado artsDistinctionDecider lo instanciamos como un objeto de la clase ArtsDistinctionDecider.Para luego recorrer mediante un for la lista CienciasEstudiantes previamente definida y para cada iteración se llamará al método evaluateDistinction del objeto scienceDistinctionDecider pasandole como argumento el objeto de la lista. Para finalizar también se recorre mediante un for la lista ArtesEstudiantes previamente definida y para cada iteración se llamará al método evaluateDistinction del objeto artsDistinctionDecider pasándole como argumento el objeto de la lista .
```java
public class Cliente {
    public static void main(String[] args) {
        /*more code*/
        // Evalua las distinciones
        DistinctionDecider scienceDistinctionDecider = new ScienceDistinctionDecider();
        DistinctionDecider artsDistinctionDecider = new ArtsDistinctionDecider();
        System.out.println("Distinciones:");
        for (Estudiante estudiante : CienciasEstudiantes) {
            scienceDistinctionDecider.evaluateDistinction(estudiante);
        }
        for (Estudiante estudiante : ArtesEstudiantes) {
            artsDistinctionDecider.evaluateDistinction(estudiante);
        }
    }
}
```
Pregunta 11\
¿Cuáles son las principales ventajas ahora?

Tanto la clase Estudiante como DistinctionDecider no se pueden modificar para ningún cambio futuro en los criterios de distinción. Osea que estas clases son cerradas para su modificación.
Ahora, si consideramos a los estudiantes de flujos diferentes, como por ejemplo la línea de departamento Política que abarca los departamentos de economía y ética, se puede agregar una nueva clase derivada por ejemplo politicaDistinctionDecider que implementa la interfaz DistinctionDecider, y puede establecer nuevos criterios de distinción para los estudiantes de Política
Usando este enfoque, evita el uso de las cadenas if-else (que se muestra en el paquete NoSolid). Esta cadena podría crecer si considera nuevas características de un estudiante. En casos como este, evitar una gran cadena if-else se considera una mejor práctica .

*****
### Principio de Sustitución de Liskov (LSP)
Pregunta 12 \
Muestra la salida y explica los resultados en función de los métodos entregados.

Para la primera parte en la clase Cliente se instancia el objeto

```java
public class Cliente {
public static void main(String[] args) {
System.out.println("Demostracion sin LSP\n");
PaymentHelper helper = new PaymentHelper();

        // Instanciando dos usuarios registrados
        GuestUserPayment pagoAbejita = new GuestUserPayment("Abejita");
        RegisteredUserPayment pagoChalito = new RegisteredUserPayment("Chalito");

        // Agregando los usuarios a los helper
        helper.addUser(pagoAbejita);
        helper.addUser(pagoChalito);

        GuestUserPayment guestUser = new GuestUserPayment("Abejita");
        helper.addUser(guestUser);

        // Procesando el pago usando la clase helper
        // Encuentras algun problema?
        helper.showPreviousPayments();
        helper.processNewPayments();

    }
}

```

De la clase GuestUserPayment haciendo uso del constructor
```java
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
```

Luego se llama al método showPreviousPayments()
```java
public void showPreviousPayments() {
    for (PreviousPayment payment: previousPayments) {
        payment.previousPaymentInfo();
        System.out.println("------");
    }
}
```
que ejecutará el método publico ShowPreviousPayments con un objeto de la clase Cliente.
        
Pregunta 13 \
Ahora supongamos que tienes un nuevo requisito que dice que necesitas admitir usuarios invitados en el futuro. Puedes procesar la solicitud de pago de un usuario invitado, pero no muestra su último detalle de pago. Entonces, crea la siguiente clase que implementa la interfaz de pago de la siguiente manera:
```java
class GuestUserPayment implements Payment {
    String name;
    public GuestUserPayment() {
        this.name = "guest";
    }
    @Override
    public void previousPaymentInfo(){
        throw new UnsupportedOperationException();
    }
    @Override
    public void newPayment(){
        System.out.println("Procesando de "+name+ "pago actual
                request.");
    }
}
```
Suponemos que se necesita admitir usuarios invitados. Se entenderá que puede procesar la solicitud de pago de un usuario invitado pero veremos que no se mostrará a detalle su último pago. Luego implementaremos la clase IUser dentro de la Main() luego se crea una instancia de usuario invitado y se usará la clase auxiliar.

Pregunta 14 \
Dentro del método main(), utilizas una instancia de usuario invitado e intentas usar su clase auxiliar de la misma manera, ¿ qué tipo de excepción te encuentras?¿Cuál es la solución?

En las iteraciones al llamar al metodo LoadPreviusPaymentInfo() en el objeto IUser se genera la excepcion para la instancia GuestUser. Este tipo de solución que le dimos no funcionará ya que GuestUser viola la regla LPS.
La solución para este caso sería emplear cadenas if-else para verificar las instancias.

```java
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
```

Pregunta 15 \
Todo lo anterior Lo más importante es que viola el OCP cada vez que modifica una clase existente que usa esta cadena if-else. Entonces, busquemos una mejor solución.
Una solucion seria eliminando el metodo ProcessNewPayment() de la interfaz IUser en donde colocaremos en otra interfaz, este metodo INewPayment como resultado obtendremos dos interfaces.

```java
public void addPreviousPayment(RegisteredUserPayment previousPayment){
        boolean b;
        if (previousPayments.add((PreviousPayment) previousPayment)) b = true;
        else b = false;
        boolean add = true;
}
```
        
Pregunta 16 \
En el próximo programa, eliminaremos el método newPayment() de la interfaz de payment. Coloca este método en otra interfaz llamada NewPayment. Como resultado, ahora tienes dos interfaces con las operaciones específicas. Dado que todos los tipos de usuarios pueden generar una nueva solicitud de pago, las clases concretas de RegisteredUserPayment y GuestUserPayment implementan la interfaz NewPayment.
Pero muestra el último detalle de pago solo para los usuarios registrados. Entonces, la clase RegisteredUser implementa la interfaz payment. Dado que Payment contiene el método previousPaymentInfo(), tiene sentido elegir un nombre mejor, como PreviousPayment en lugar de Payment. Entonces, ahora verá las siguientes interfaces:
```java
interface PreviousPayment {
    void previousPaymentInfo();
}
interface NewPayment {
    void newPayment();
}
```
Ajuste estos nuevos nombres en la clase auxiliar también. En sección del código debes tener los siguientes archivos
        PreviousPayment.java \
        NewPayment.java \
        RegisteredUserPayment.java \
        GuestUserPayment.java \
        PaymentHelper.java \
        Cliente.java 

Pregunta 17 \
¿Cuáles son los cambios clave?

Vimos que los metodos ShowPreviousPayments() y ProcessNewPayments() acepto instancias de la clase IUser como argumentos. Ahora el metodo ShowPreviousPayments() acepta las instancias de IPreviousPayment y ProcessNewPayments() aceptara las instancias de INewPayment como argumentos notamos que esta nueva estructura soluciona los problemas causados al crear usuarios y mostrar sus solicitudes de pagos actuales junto con los pagos anteriores.

Pregunta 18 \
Ten que aquí el enfoque clave estaba en el principio LSP, nada más. Podrías refactorizar fácilmente el código del cliente usando algún método estático. Por ejemplo realiza una modificación donde utilizas un método estático para mostrar todas las solicitudes de pago y utilizar este método siempre que lo necesites.
```java
public class PaymentHelper {
    List<PreviousPayment> previousPayments = new ArrayList<PreviousPayment>();
    List<NewPayment> newPayments = new ArrayList<NewPayment>();
    public void addPreviousPayment(RegisteredUserPayment previousPayment){
        boolean b;
        if (previousPayments.add((PreviousPayment) previousPayment)) b = true;
        else b = false;
        boolean add = true;
    }
    public void addNewPayment(NewPayment newPaymentRequest){
        newPayments.add(newPaymentRequest);
    }
    public void showPreviousPayments() {
        for (PreviousPayment payment: previousPayments) {
            payment.previousPaymentInfo();
            System.out.println("------");
        }
    }
    public void processNewPayments() {
        for (NewPayment payment: newPayments) {
            payment.newPayment();
            System.out.println("***********");
        }
    }

    public void addUser(RegisteredUserPayment pagoAbejita) {
    }

    public void addUser(GuestUserPayment pagoAbejita) {

    }
}
```
Aqui refactorizamos el codigo donde su utilizara metodos estaticos para mostrar las solicitudes de pago.
*****
### Principio de Segregacion de Interfaz (ISP)
Pregunta 19 \
¿Por qué un usuario necesita cambiar una clase base (o una interfaz)?

Para implementar una nueva funcionalidad en algún futuro tomando como referencia el ejemplo anterior nosotros queremos mostrar qué tipo de fax se está enviando, ya sea LanFax,EFax o AnalogFax.Esto nos genera un problema ya que previamente el método sendFax no usaba ningún parámetro como referencia, pero como ahora necesitamos mostrar que tipo de fax está mandando este método, por lo que ahora necesita recibir un parámetro.Pero esto genera un problema ya que necesitamos hacer cambios en la interfaz impresora, consecuentemente a esto tambien tendriamos que hacer cambios en la clase impresoraBasica ,que implementa la clase Impresora, para adaptarse a las modificaciones.En conclusión tendríamos que hacer varios cambios al programa y esto no es lo adecuado por lo que el Principio de Segregación de la Interfaz (ISP) se ocupara de estos problemas.\
Mostrando la jerarquía de fax con las distintas variaciones de sus metodos:
```java
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
```
Pregunta 20\
Para usar esta jerarquía de herencia, una vez que modificas el método sendFax() a
sendFax(Fax faxType) en la clase ImpresoraAvanzada, exige que cambies la interfaz de Impresora (sí, aquí también rompe el OCP).
Cuando actualices Impresora, también debes actualizar la clase impresoraBasica para adaptarse a este cambio. ¡Ahora ves el problema!. Explica el problema.

Aquí notamos que cuando hay un cambio en ImpresoraAvanzada provoca cambios en la interfaz Impresora, lo que a su vez hace que la impresoraBasica actualice su método fax. Aunque impresoraBasica no necesita este método de fax para nada, un cambio del método fax en otro cliente de Impresora obliga a impresoraBasica a que tenga un cambio y se vuelva a recompilar.

Pregunta 21\
Si has entendido correctamente el problema. El ISP te sugiere que te ocupes de este tipo de escenario. Explica tu respuesta.

El problema radica, cuando veamos una interfaz llena, tenemos que preguntarnos si esos métodos de interfaz son necesarios para cada cliente.Sino, tendríamos un problema ya que si es que se requiere un cambio en alguna de las clases que implementa la interfaz, este cambio afectaría no solo a la interfaz sino que afectaría a las demás clases que implementan la misma interfaz. Lo mismo ocurriría si se quisiera agregar otra impresora por ejemplo que pueda fotocopiar, se tendrían que hacer cambios en la interfaces y también a las demás clases. Por lo tanto, la solución que se tendría sería dividir en interfaces más pequeñas que sean relevantes para los clientes específicos.
```java
interface Impresora {
    void printDocument();

    void sendFax();
}
```
Pregunta 22\
¿Es conveniente usar e inicializar el siguiente código?
interface Impresora {
void printDocument();
void sendFax();
}

No ya que en esta interfaz sólo está considerando los métodos printDocument() y sendFax() para impresoras avanzadas que puedan imprimir y enviar fax. Pero  en el caso donde se requiera llamar a una impresora básica que contiene sólo al método  printDocument() estaría usando métodos que no debería tener la impresora básica que sería sendFax().Por lo tanto no sería muy conveniente usar este código para buenas prácticas.

Pregunta 23 \
Si comienzas tu codificación considerando las impresoras avanzadas que pueden imprimir y enviar un fax, está bien. Pero en una etapa posterior, si tu programa también necesita admitir impresoras básicas,¿ qué código puedes escribir?.

Como antes el código violaba el principio de OCP, en lo que respecta a la interfaz Impresora ya que no estaba cerrada para su modificación, ya que si se necesitaba hacer alguna modificación en alguna de las clases que implementa, la interfaz Impresora tenia que cambiar, y por consecuencia también sus otras clases que la implementan, violando tambien asi el LSP. Para solucionar este problema haremos uso del Principio de Segregación de la Interfaz (ISP),para esto primero separamos la interfaz Impresora de noSolid en : la interfaz Impresora que contiene únicamente el método printDocument() que nos muestra que imprime un documento  y la otra interfaz sería DispositivoFax que contiene el método sendFax() que nos muestra que envía un fax. Esto se hace con el fin de que cada nueva impresora que se quiera añadir al programa, tenga que crear únicamente una clase implementando las interfaces que sean necesarias para así obtener los métodos que necesita esta clase. Por ejemplo la clase ImpresoraBasica() que únicamente requiere del método printDocument() solo tendrá que implementar la interface Impresora, en cambio la clase ImpresoraAvanzada() utiliza los métodos printDocument() y sendFax(), por lo que tendrá que implementar la interface Impresora e DispositivoFax.

Pregunta 24 \
Comprueba tus respuestas añadiendo dentro de main(), el siguiente código
polimórfico:\
Impresora impresora = new ImpresoraAvanzada();\
impresora.printDocument();\
impresora.sendFax();\
impresora = new ImpresoraBasica();\
impresora.printDocument();\
impresora .sendFax();

En la clase Cliente que contiene al método main nos muestra la creación del objeto impresora de la clase ImpresoraAvanzada que a su vez ésta implementa la interfaz Impresora.
```java
class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion sin ISP");
        Impresora impresora = new ImpresoraAvanzada();
        impresora.printDocument();
        impresora.sendFax();
        /*more code*/
   }
}
```
Por lo cual el objeto impresora de la clase ImpresoraAvanzada manda a llamar los métodos printDocument() y sendFax() de la interfaz Impresora.
```java
public class ImpresoraAvanzada implements Impresora{
    @Override
    public void printDocument() {
        System.out.println("La impresora avanzada imprime un documento.");
    }

    @Override
    public void sendFax() {
        System.out.println("La impresora avanzada envía un fax.");
    }
}
```
Luego se crea el objeto impresora de la clase ImpresoraBasica que a su vez también implementa la interfaz Impresora.
```java
class Cliente {
    public static void main(String[] args) {
        /*more code*/
        impresora = new ImpresoraBasica();
        impresora.printDocument();
        impresora.sendFax();
   }
}
```
Luego el objeto impresora de la clase ImpresoraBasica manda a llamar a los métodos printDocument() y sendFax() de la interfaz Impresora como se muestra.
```java
class ImpresoraBasica implements Impresora{
    @Override
    public void printDocument() {
        System.out.println("La impresora basica imprime un documento.");
    }
    @Override
    public void sendFax() {
        throw new UnsupportedOperationException();
    }
}
```
Por lo cual éste último muestra un error en pantalla al intentar usar el método sendFax(), como se logra ver :
```
Demostracion sin ISP
La impresora avanzada imprime un documento.
La impresora avanzada envía un fax.
La impresora basica imprime un documento.
Exception in thread "main" java.lang.UnsupportedOperationException
	at NoSolid.ISP.ImpresoraBasica.sendFax(ImpresoraBasica.java:11)
	at NoSolid.ISP.Cliente.main(Cliente.java:17)
```
Además, no puedes escribir algo como
```java
List<Impresora> impresoras = new ArrayList<Impresora>();
impresoras.add(new ImpresoraAvanzada());
impresoras.add(new ImpresoraBasica());
for (Impresora dispositivo : impresoras) {
    dispositivo.printDocument();
    dispositivo.sendFax();
}
```
En ambos casos, verás excepciones de tiempo de ejecución.

Lo que hace este código dentro del main de la clase Cliente, es que crea una lista de objetos de la interfaz Impresora.
```java
class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion sin ISP\n");
        
        List<Impresora> impresoras = new ArrayList<Impresora>();
        /*more code*/
    }
}
```
Nota: Esta interfaz esta definida como se muestra :
```java
interface Impresora {
    void printDocument();

    void sendFax();
}
```
A esta lista que acabamos de crear la llamaremos impresoras, luego añade a esta lista nuevos objetos de la clase ImpresoraAvanzada que implementa la interface Impresora, también añade a la lista nuevos objetos de la clase ImpresoraBasica.
```java
class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion sin ISP\n");

        List<Impresora> impresoras = new ArrayList<Impresora>();
        impresoras.add(new ImpresoraAvanzada());
        impresoras.add(new ImpresoraBasica());
    }
}
```
Por último recorre mediante un for la lista impresoras creada anteriormente, y para cada objeto recorrido de la lista, llama a los métodos printDocument() y sendFax() cada una de su respectiva clase a la que pertenece el objeto recorrido. 
```java
class Cliente {
    public static void main(String[] args) {
        /*more code*/
        for(Impresora dispositivo:impresoras){
            dispositivo.printDocument();
            dispositivo.sendFax();
        }
    }
}
```
Y cómo se logra observar es la misma salida que el código explicado anteriormente, con el mismo error que se lanza cuando se llama al método senFax() de la clase ImpresoraBasica.
```
La impresora avanzada imprime un documento.
La impresora avanzada envía un fax.
La impresora basica imprime un documento.
Exception in thread "main" java.lang.UnsupportedOperationException
    at NoSolid.ISP.ImpresoraBasica.sendFax(ImpresoraBasica.java:11)
    at NoSolid.ISP.Cliente.main(Cliente.java:23)
```
Pregunta 25 \
Reemplaza el segmento de código\
for (Impresora dispositivo : impresoras) { \
    dispositivo.printDocument();\
    dispositivo.sendFax();\
}\
con una expresión lambda adecuada. Tú eliges cuál quieres usar.

En este caso la expresion lambda que decidi utilizar fue la siguiente:
```java
class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion sin ISP\n");
        /*more code*/
        impresoras.forEach((dispositivo)->{dispositivo.printDocument();
            dispositivo.sendFax();});
        
        /*for(Impresora dispositivo:impresoras){
            dispositivo.printDocument();
            dispositivo.sendFax();
        }*/
    }
}
```
Como se puede ver, la expresion lambda recorre la lista impresoras, y trabaja con cada uno de los objetos que la conforman, llamando para cada objeto el metodo printDocument() y el metodo senFax().

Pregunta 26\
Muestra la salida y explica los resultados en función de los métodos entregados.

Luego como se puede ver en la imagen de la salida, es igual que cuando recorriamos la lista mediante el for.
Ya que el funcionamiento completo del codigo no cambia, unicamente cambia como es que imprimimos en pantalla
cada metodo de cada objeto que conforma la lista.
```
La impresora avanzada imprime un documento.
La impresora avanzada envía un fax.
La impresora basica imprime un documento.
Exception in thread "main" java.lang.UnsupportedOperationException
    at NoSolid.ISP.ImpresoraBasica.sendFax(ImpresoraBasica.java:11)
    at NoSolid.ISP.Cliente.lambda$main$0(Cliente.java:22)
    at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    at NoSolid.ISP.Cliente.main(Cliente.java:21)
```

Pregunta 27\
Hay dos actividades diferentes: una es imprimir unos documentos y la otra es enviar un fax.
Crea dos interfaces llamada Impresora y DispositivoFax. Impresora contiene el método
printDocument() y DispositivoFax contiene el método SendFax(). No olvides explicar tus resultados.

Como se observa, la interfaz Impresora que separamos anteriormente en 2 interfaces, una de ellas es la interfaz Impresora que únicamente posee el método printDocument().
```java
interface Impresora {
    void printDocument();
}
```
Luego tenemos a la interfaz DispositivoFax es la segunda interfaz creada, que únicamente posee el método sendFax().
```java
interface DispositivoFax {
    void sendFax();
}
```
Como se observa en la clase ImpresoraBasica que a su vez implementa la interfaz Impresora que únicamente contiene el método printDocument()
```java
public class ImpresoraBasica implements Impresora{
    public void printDocument(){
        System.out.println("Impresora basica imprime un documento.");
    }
}
```
Aquí podemos observar la clase ImpresoraAvanzada que implementa las interfaces Impresora y DispositivoFax. Esta clase tiene la tarea de definir los métodos printDocument() y sendFax() que se encontraban cada una dentro de la interfaz Impresora y DispositivoFax respectivamente.
```java
public class ImpresoraAvanzada implements Impresora, DispositivoFax{
        public void printDocument(){
            System.out.println("La impresora avanzada imprime un documento.");
        }
        public void sendFax(){
            System.out.println("La impresora avanzada envia un fax.");
        }
}
```
Ahora en la clase Cliente que contiene al método main nos muestra la creación del objeto impresora de la clase ImpresoraBasica que a su vez ésta implementa la interfaz Impresora por lo cual el objeto impresora manda a llamar al método printDocument().Luego se crea el objeto impresora de la clase ImpresoraAvanzada que a su vez también se implementa de la interfaz Impresora que manda a llamar al método printDocument() de la interfaz Impresora.Así también se crea un objeto fax de la clase ImpresoraAvanzada que a su vez ésta implementa la interfaz DispositivoFax por lo cual el objeto fax manda a llamar al método sendFax() de la interfaz DispositivoFax.
```java
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
```
Por último veremos lo que se muestra en la salida por pantalla, como se vio en la explicación del código anterior de la clase Cliente, se define un objeto impresora de la interface Impresora como un objeto de la clase ImpresoraBasica y se manda a llamar a su único método printDocument(). Luego se sobreescribe sobre el objeto impresora creado anteriormente como si fuese un objeto de la clase ImpresoraAvanzada , para luego mostrar su método printDocument(). Para terminar se define un objeto fax de la interface DispositivoFax como un objeto de la clase ImpresoraAvanzada y mandamos a llamar a su método sendFax().
El resultado de todo esto se muestra en la pantalla :
```
Demostracion con ISP
Impresora basica imprime un documento.
La impresora avanzada imprime un documento.
La impresora avanzada envia un fax.
```
Pregunta 28\
¿Qué sucede si usa un método predeterminado dentro de la interfaz?.

Si usamos un método predeterminado en la interfaz, entonces la clase que implementa la interfaz no tendría que molestarse por la funcionalidad de esta. Pero aquí ocurre un gran problema ya que si en la interfaz agregamos un método, este nuevo método debe de estar disponible para su uso, en las clases que implementan la interfaz, por lo que estas clases también tendrían que estar cambiando constantemente. Por lo tanto se estaría violando el principio OCP y el principio de sustitución de Liskov (LSP), provocando así que sea difícil la reutilización de código para posibles mejoras en el futuro.

Pregunta 29\
¿Qué sucede si proporcionas un método de fax predeterminado en una interfaz?.

En el caso de que se agregue un nuevo método predeterminado sendFax() que está en la interfaz DispositivoFax que es usado por la clase ImpresoraBasica y esto violaría los principios de OCP y LSP por lo que a su vez también podría provocar problemas de mantenimiento y reutilizaciones difíciles. Por lo tanto la  clase ImpresoraBasica tendría que mandar una exception cuando se llame este método. La exception que se tendria que mostrar en el codigo seria algo como esto:
```java
public void sendFax() { throw new UnsupportedOperationException(); }
```
Pregunta 30\
¿Qué sucede si usa un método vacío, en lugar de lanzar la excepción?

Se puede considerar que el uso de métodos vacíos no sigue el principio de ISP  ,ya que estarían violando el principio de :   "las clases que implementen una interfaz o una clase abstracta no deberían estar obligadas a utilizar partes que no van a utilizar".   porque ese método vacío no tendría funcionalidad ya que cuando se lanza una excepción esto ayudaría a mejorar el código smell ,ya que esta excepción nos indicaría que algo estaría funcionando mal .
*****
### Principio de Inversión de Dependencia (DIP)
Pregunta 31 \
Muestra la salida y explica los resultados en función de los métodos entregados
```
A demo without IDP
El id: E001 es guardado en la base de datos Oracle.
```
En la clase InterfaceUsuario se declara el atributo oracleDatabase como privado,también se genera un método InterfazUsuario() que genera un objeto oracleDatabase del tipo de clase OracleDatabase, inmediatamente lo está integrando como el atributo de la clase interfazUsuario. Asi tambien el metodo saveEmployeeId() recibe como argumento empId y este ID se guarda en la base de datos OracleDatabase, haciendo uso del metodo saveEmpIdInDatabase().
```java
public class InterfazUsuario {
    private OracleDatabase oracleDatabase;
    public InterfazUsuario() {
        this.oracleDatabase = new OracleDatabase();
    }
    public void saveEmployeeId(String empId) {
        oracleDatabase.saveEmpIdInDatabase(empId);
    }
}
```
En esta clase OracleDataBase contiene al método saveEmpIdInDatabase() que recibe como argumento empId en el cual imprime un mensaje donde indica que el ID del empleado ingresado es guardado en la base de datos Oracle

```java
public class OracleDatabase {
    public void saveEmpIdInDatabase(String empId) {
        System.out.println("El id: " + empId + " es guardado en la base de datos Oracle.");
    }
}
```
Por último en la clase Cliente contiene un main el cual imprime un mensaje que nos dice que no se está usando DIP .Así también se crea un objeto llamado usuario del tipo de la clase InterfazUsuario y el objeto usuario usando el método saveEmployeeId() guarda el ID en el objeto usuario.

Pregunta 32 \
El programa es simple, pero ¿qué tipo de problemas presenta?

El problema que se encuentra de las clases presentadas es lo siguiente:
Haciendo un análisis de la clase InterfazUsuario, podemos resaltar que se observa un alto grado de modularidad debido a la gran dependencia existente de la clase InterfazUsuario al depender de la clase de nivel inferior OracleDatabase que se encuentra en zona de argumento, cuando se ingresa el atributo OracleDatabase de la clase InterfazUsuario siendo esta de nivel superior. Esta estrecha relación entre estas clases indica un gran acoplamiento entre ellas, lo que quiere decir que si en un futuro se desea hacer cambios en la clase OracleDatabase también se observarán  cambios en la clase InterfazUsuario

Pregunta 33 \
En el programa de la carpeta SOLID, para el caso DIP verás la siguiente jerarquía:
BaseDatos.java \
OracleDatabase.java

ya que la interface BaseDatos tienes menos funcionalidad que la clase OracleDatabase

```java
interface BaseDatos {
  void SaveEmpIdInDatabase(String empId);
}
```
```java
class OracleDatabase implements BaseDatos {
    public void SaveEmpIdInDatabase(String empId)
 {
     System.out.println("El id: " + empId + " es guardado en la base de datos Oracle.");

 }
}
```
En la clase OracleDatabase  esta implementando de la interfaz BaseDatos que es abstracta que contiene al metodo SaveEmpIdInDatabase que guarda el Id del empleado en la base de datos y lo muestra.
```java
class OracleDatabase implements BaseDatos {
    public void SaveEmpIdInDatabase(String empId)
 {
     System.out.println("El id: " + empId + " es guardado en la base de datos Oracle.");

 }
}
```
En esta clase InterfazUsuario genera un atributo database de tipo BaseDatos y contiene al constructor InterfazUsuario y asu vez tambien contiene al metodo saveEmployeeID que guarda el ID del empleado en database.

```java
class InterfazUsuario {
  private BaseDatos database;
  public InterfazUsuario(BaseDatos database)
  {
      this.database=database;
  }
    public void saveEmployeeId(String empId) {
        database.SaveEmpIdInDatabase(empId);
    }
}
```
Esta clase MySQLDatabase implementa de la interfaz BaseDatos abstracta que contiene al metodo SaveEmpIdInDatabase en lo cual imprime el ID del y lo guarda en la base datos MySQL

```java
class MySQLDatabase implements BaseDatos {
    public void SaveEmpIdInDatabase(String empId)
    {
        System.out.println("El id: " + empId + " es guardado en MySQL de la base de datos.");
    }
}
```
Pregunta 34 \
Completa todos los archivos siguientes de la sección SOLID
InterfazUsuario.java
BaseDatos.java
OracleDataBase.java
MySQLDatabase.java
Cliente.java

Explica los resultados.¿ El programa resuelve todos los posibles problemas del programa que no usa DIP.
En resumen, en OOP, te sugiero seguir la cita de Robert C. Martin:
Los módulos de alto nivel simplemente no deberían depender de los módulos de bajo nivel de ninguna manera.
Entonces, cuando tienes una clase base y una clase derivada, tu clase base no debe conocer ninguna de sus clases derivadas.

InterfazUsuario.java\
En esta clase InterfazUsuario genera un atributo database de tipo BaseDatos y contiene al constructor InterfazUsuario y asu vez tambien contiene al metodo saveEmployeeID que guarda el ID del empleado en database.
```java
class InterfazUsuario {
  private BaseDatos database;
  public InterfazUsuario(BaseDatos database)
  {
      this.database=database;
  }
    public void saveEmployeeId(String empId) {
        database.SaveEmpIdInDatabase(empId);
    }
}
```
BaseDatos.java\
Esta interfaz BaseDatos contiene al metodo SaveEmpIdInDatabase que a su vez es usada tambien por las clases MySQLDatabase y OracleDatabase
```java
interface BaseDatos {
  void SaveEmpIdInDatabase(String empId);
}
```
OracleDataBase.java\
En la clase OracleDatabase  esta implementando de la interfaz BaseDatos que es abstracta que contiene al metodo SaveEmpIdInDatabase que guarda el Id del empleado en la base de datos y lo muestra.
```java
class OracleDatabase implements BaseDatos {
    public void SaveEmpIdInDatabase(String empId)
 {
     System.out.println("El id: " + empId + " es guardado en la base de datos Oracle.");

 }
}
```
MySQLDatabase.java \
-Esta clase MySQLDatabase implementa de la interfaz BaseDatos abstracta que contiene al metodo SaveEmpIdInDatabase en lo cual imprime el ID del y lo guarda en la base datos MySQL
```java
class MySQLDatabase implements BaseDatos {
    public void SaveEmpIdInDatabase(String empId)
    {
        System.out.println("El id: " + empId + " es guardado en MySQL de la base de datos.");
    }
}
```
Cliente.java\
En esta clase Cliente tiene una main .Usando Oracle genera un objeto database del tipo de la clase OracleDatabase ,tambien genera un objeto llamado usuario de tipo InterfazUsuario a la vez esta usando como argumento el objeto de la linea anterior
el objeto usuario usando al metodo saveEmployeeID guarda el ID del usuario.Crea al objeto Mysql usa al objeto database de la clase MySQLDatabase, luego crea al objeto usuario de tipo interfazusuario, y asi el objeto usuario usando el metodo saveEmployeeID guarda el ID del usuario.
```java
public class Cliente {
    public static void main(String[] args) {
        System.out.println("Demostracion con DIP");
        // Usando Oracle
        BaseDatos database = new OracleDatabase();
        InterfazUsuario usuario = new InterfazUsuario(database);
        usuario.saveEmployeeId("E001");//SaveEmployeeId("E001");
        // Usando Mysql
        database = new MySQLDatabase();
        usuario = new InterfazUsuario(database);
        usuario.saveEmployeeId("E002");
    }
}
```
Pregunta 35\
Encuentra alguna excepción a esta sugerencia.

Una de las excepciones sería considerar el caso en el que su clase base necesita restringir el recuento de instancias de clases derivadas en un punto determinado. Luego tambien se podria notar cuando el constructor de la clase InterfazUsuario acepta un parámetro database.Asi tambien puede proporcionar una instalación adicional al usuario cuando usa tanto el constructor como la propiedad dentro de esta clase.

Pregunta 36\
El constructor de la clase InterfazUsuario acepta un parámetro de base de datos.
Proporciona una instalación adicional a un usuario cuando utiliza tanto el constructor como el
método setter (setDatabase) dentro de esta clase. ¿Cuál es el beneficio?.

El beneficio sería que ahora puede crear instancias de una base de datos mientras crea instancias de la clase InterfazUsuario y cambiarla más tarde usando BaseDatos. Un ejemplo sería agregar y asi probar
```java
BaseDatos.database = new OracleDatabase();
usuario.saveEmployeeId("E003");
```
