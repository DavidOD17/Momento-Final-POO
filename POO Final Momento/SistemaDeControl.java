import java.util.Scanner;

// Clase SistemaDeControl
public class SistemaDeControl {
    private Ascensor ascensor; // Objeto que controla el ascensor
    private Piso[] pisos; // Arreglo de los pisos

    // Constructor que inicializa el ascensor y los pisos
    public SistemaDeControl(int numeroDePisos) {
        ascensor = new Ascensor(numeroDePisos);  // Crea un ascensor con el numero de pisos dado
        pisos = new Piso[numeroDePisos];
        for (int i = 1; i <= numeroDePisos; i++) {
            pisos[i - 1] = new Piso(i);  // Crea un piso por cada piso en el edificio
        }
    }

    // Metodo que inicia el sistema y gestiona las opciones del usuario
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Interfaz de usuario para controlar el ascensor
            System.out.println("\n    ************************************************");
            System.out.println("    *              Panel de control                *");
            System.out.println("    ************************************************");
            System.out.println("    *1. Solicitar ascensor (Subida/Bajada)         *");
            System.out.println("    *2. Salir                                      *");
            System.out.println("    ************************************************");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt(); // Lee la opcion seleccionada por el usuario

            // Manejo de las opciones del menu principal
            switch (opcion) {
                case 1:
                // Solicitar el ascensor
                    System.out.print("Desde que piso solicita el ascensor (1-5): ");
                    int piso = scanner.nextInt(); // Leer el piso desde donde se solicita el ascensor

                    // Validar que el piso solicitado este dentro del rango permitido
                    if (piso < 1 || piso > 5) {
                        System.out.println(" El piso " + piso + " no existe."); // Mensaje de error si el piso es invalido
                    } else {

                        // Preguntar si el usuario desea subir o bajar
                        System.out.print("¿Subida (1) o Bajada (2)? ");
                        // Leer la direccion (1 para subir, 2 para bajar)
                        int direccion = scanner.nextInt();

                         // Convertir la direccion numerica a texto ("subida" o "bajada")
                        String dirTexto = (direccion == 1) ? "subida" : "bajada";

                        // Llamar al metodo manejarSolicitud del ascensor para procesar la solicitud
                        ascensor.manejarSolicitud(piso, dirTexto);
                    }
                    break;

                case 2:
                // Salir del sistema
                    System.out.println("Saliendo...");
                    scanner.close(); // Cierra el escaner cuando se sale del sistema
                    return;

                default:
                // Mostrar mensaje de error para entradas invalidas
                    System.out.println("Opcion no valida.");
            }
        }
}
}