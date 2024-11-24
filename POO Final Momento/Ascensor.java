import java.util.ArrayList;
import java.util.Scanner;
// Clase Ascensor
public class Ascensor {

    private int ubicacionActual;  // Piso actual en el que se encuentra el ascensor
    private String direccion;  // Direccion del ascensor ("subiendo" o "bajando")
    private Puerta puerta;  // Objeto que controla la puerta del ascensor
    private ArrayList<BotonDelAscensor> botones;  // Botones para seleccionar el destino del ascensor
    private final double pesoMaximo = 500.0;  // Peso maximo permitido en el ascensor
    private final int maxPersonas = 9; // Máximo número de personas
    private double pesoActual;  // Peso actual dentro del ascensor

    // Constructor del ascensor con un numero de pisos.
    public Ascensor(int pisos) {
        this.ubicacionActual = 1;  // Se inicializa el ascensor en el primer piso
        this.direccion = "subiendo";  // Inicializamos la direccion como "subiendo"
        this.puerta = new Puerta();  // Crea la puerta del ascensor
        this.botones = new ArrayList<>();  // Se crea la lista de botones para cada piso
        this.pesoActual = 0.0;  // Inicializamos el peso actual como 0

        // Inicializamos los botones para los pisos (1-5)
        for (int i = 1; i <= pisos; i++) {
            botones.add(new BotonDelAscensor(i));
        }
    }

    // Manejar la solicitud desde un piso
    public void manejarSolicitud(int piso, String direccion) {

        // Valida si el piso solicitado está dentro del rango permitido
        if (piso < 1 || piso > 5) {
            System.out.println(" El piso " + piso + " no existe."); // Mensaje de error si el piso no es valido
            return;
        }

        // Indica que se recibio una solicitud desde un piso especifico
        System.out.println("Solicitud desde el piso " + piso + " para " + direccion + ".");
        moverAscensor(piso); // Mover el ascensor al piso solicitado

        // Una vez en el piso preguntar al usuario a que piso desea ir
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n El ascensor ha llegado.. ");
                System.out.println("\nMarque el piso al que desea ir (1-5) ");
        int pisoDestino = scanner.nextInt();
        seleccionarPiso(pisoDestino); // Mover el ascensor al piso de destino
    }

    // Metodo para mover el ascensor a un piso de destino que se haya indicado
    public void moverAscensor(int pisoDestino) {

        // Verificar si el ascensor ya esta en el piso solicitado
        if (ubicacionActual == pisoDestino) {
            System.out.println("El ascensor ya está en el piso " + pisoDestino);
            return;
        }

        // Simular el movimiento del ascensor entre pisos
        while (ubicacionActual != pisoDestino) {
            if (ubicacionActual < pisoDestino) {
                direccion = "subiendo"; // Si el ascensor esta en un piso inferior sube el ascensor
                ubicacionActual++; // incrementa el piso actual
            } else {
                direccion = "bajando"; // Si el ascensor esta en un piso superior baja el ascensor
                ubicacionActual--; // disminuye el piso actual
            }
            System.out.println("Ascensor en piso " + ubicacionActual);   // Imprime la ubicacion actual del ascensor
        }

        // Indicar que el ascensor ha llegado al piso destino
        System.out.println("\nAscensor ha llegado al piso " + pisoDestino);
        puerta.abrir(); // Se abre la puerta al llegar al destino

        // Intentar cerrar la puerta (manejo de obstaculos esta en Puerta)
        puerta.cerrar();
    }

    // Metodo para seleccionar un piso al que el ascensor debe ir
    public void seleccionarPiso(int piso) {
        if (piso < 1 || piso > 5) {
            System.out.println("\n El piso " + piso + " no existe.");
            return;
        }
        botones.get(piso - 1).presionar(); // Llama al boton correspondiente al piso
        if (gestionarPeso()) { // Verifica si el peso es adecuado antes de mover el ascensor
            moverAscensor(piso); // Mueve el ascensor al piso seleccionado
        }
    }

    // Metodo para gestionar el peso de las personas que ingresan al ascensor
    private boolean gestionarPeso() {
        Scanner scanner = new Scanner(System.in);
        int cantidadPersonas;

        // Validar el numero de personas que ingresan al ascensor
        while (true) {
            System.out.print("¿Cuantas personas ingresaron al ascensor?" + " (Maximo de personas permitidos " + maxPersonas + "): ");
            cantidadPersonas = scanner.nextInt();
            if (cantidadPersonas > maxPersonas) { // Si el numero de personas excede el limite
                System.out.println(" El numero de personas (" + cantidadPersonas + ") excede el limite de " + maxPersonas + ". Solo se permite 9 personas.");
            } else {
                break; // Numero de personas valido
            }
        }

        ArrayList<Double> pesosPersonas = new ArrayList<>(); // Lista para almacenar los pesos de las personas
        double pesoTotal = 0.0; // Inicializamos el peso total

        // Solicitar el peso de cada persona que ingresa al ascensor
        for (int i = 0; i < cantidadPersonas; i++) {
            System.out.print(" Ingrese el peso de la persona " + (i + 1) + " en kg: ");
            double peso = scanner.nextDouble();
            pesosPersonas.add(peso); // Agregar el peso a la lista
            pesoTotal += peso; // Sumar el peso al total
        }

        // Validar si el peso total excede el limite permitido
        if (pesoTotal > pesoMaximo) {
            System.out.println(" El peso total (" + pesoTotal + " kg) excede el límite de " + pesoMaximo + " kg.");
            while (pesoTotal > pesoMaximo) { // Repetir hasta que el peso este dentro del limite
                System.out.println("\nDebe bajar una persona para continuar");
                for (int i = 0; i < pesosPersonas.size(); i++) {
                    System.out.println((i + 1) + ". Persona con " + pesosPersonas.get(i) + " kg");
                }

                System.out.print("Seleccione el numero de la persona que bajara: ");
                int personaABajar = scanner.nextInt();

                // Validar si la seleccion es valida
                if (personaABajar < 1 || personaABajar > pesosPersonas.size()) {
                    System.out.println("Selección no valida. Intentelo de nuevo.");
                    continue;
                }

                // Eliminar el peso de la persona seleccionada y restarlo del total
                double pesoBajado = pesosPersonas.remove(personaABajar - 1);
                pesoTotal -= pesoBajado;
                System.out.println("Persona con " + pesoBajado + " kg ha bajado. Peso actual: " + pesoTotal + " kg");
            }
        }

        System.out.println("El peso esta dentro del limite");
        System.err.println("\n El ascensor esta listo para moverse...");
        return true; // El peso esta dentro del limite
    }
}
