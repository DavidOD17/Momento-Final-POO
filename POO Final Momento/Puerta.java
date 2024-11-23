import java.util.Scanner;
// Clase Puerta
public class Puerta {

    private boolean abierta; // Indica si la puerta esta abierta o cerrada

    // Constructor que inicializa la puerta cerrada
    public Puerta() {
        this.abierta = false;
    }

    // Metodo para abrir la puerta
    public void abrir() {
        if (!abierta) {
            abierta = true;
            System.out.println("Puerta abierta. Luz verde encendida.");
        }
    }

    // Metodo para cerrar la puerta
    public void cerrar() {
        if (abierta) {
            if (hayObstaculo()) {
                manejarObstaculo(); // Manejar el obstáculo si es detectado
            } else {
                abierta = false;
                System.out.println("Puerta cerrada. Luz roja encendida.");
            }
        }
    }
    // Metodo que devuelve si la puerta esta abierta
    public boolean estaAbierta() {
        return abierta;
    }

    // Metodo para detectar si hay un obstaculo en la puerta
    public boolean hayObstaculo() {
        // Simulacion de sensor de obstaculos hay una probabilidad de que haya un obstaculo
        return Math.random() < 0.1; 
    }
    // Método privado para manejar un obstaculo detectado
    private void manejarObstaculo() {

        // Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Obstaculo detectado. Manteniendo puertas abiertas.");// Mensaje de advertencia

        // Bucle infinito hasta que el usuario confirme que el obstaculo ha sido retirado
        while (true) {
            System.out.print("¿Ya se retiro el obstaculo de la puerta? (sí/no): ");
            String respuesta = scanner.nextLine().trim().toLowerCase(); // Leer y normalizar la respuesta del usuario

            // Si el usuario confirma que retiro el obstaculo
            if (respuesta.equals("sí") || respuesta.equals("si")) {
                System.out.println("Obstaculo retirado. Continuando funcionamiento...");
                abierta = false; // Cambiar el estado de la puerta a cerrada
                System.out.println("Puerta cerrada. Luz roja encendida.");
                break; // Salir del bucle

            // Si el usuario indica que no ha retirado el obstaculo
            } else if (respuesta.equals("no")) {
                System.out.println(" Por favor, retire el obstaculo para continuar."); // Recordar al usuario que retire el obstaculo

            // Manejo de respuestas invalidas
            } else {
                System.out.println("Respuesta no valida. Escriba 'sí' o 'no'.");
            }
        }
    }
}