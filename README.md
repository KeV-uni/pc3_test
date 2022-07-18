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

```java

```
```javA

```
```java

```

*****
### Principio de Segregacion de Interfaz (ISP)
Pregunta 19 \
¿Por qué un usuario necesita cambiar una clase base (o una interfaz)?

```java

```
```java

```
```java

```
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


`$ npm install marked`




```
```
```
```
```
```
```
