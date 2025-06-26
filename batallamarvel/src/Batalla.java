import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Batalla { // <-- Asegúrate de que el nombre de la clase sea Batalla

    private Personaje heroe1;
    private Personaje heroe2;
    private int turnoActual;

    public Batalla(Personaje heroe1, Personaje heroe2) {
        this.heroe1 = heroe1;
        this.heroe2 = heroe2;
        this.turnoActual = 1;
    }

    public void simularBatalla() throws InterruptedException {
        System.out.println("--- ¡COMIENZA LA BATALLA ÉPICA! ---");
        TimeUnit.SECONDS.sleep(1);

        while (heroe1.estaVivo() && heroe2.estaVivo()) {
            System.out.println("\n==============================");
            System.out.println("** TURNO " + turnoActual + " **");
            System.out.println("==============================");
            TimeUnit.SECONDS.sleep(1);

            // --- Turno de Deadpool (heroe1) ---
            realizarTurno(heroe1, heroe2);
            if (!heroe2.estaVivo()) {
                break;
            }
            TimeUnit.SECONDS.sleep(1);

            // --- Turno de Wolverine (heroe2) ---
            realizarTurno(heroe2, heroe1);

            System.out.println("\n--- Estado de la batalla ---");
            System.out.println("  > " + heroe1);
            System.out.println("  > " + heroe2);

            turnoActual++;
            TimeUnit.SECONDS.sleep(2);
        }

        mostrarResultadoFinal();
    }

    private void realizarTurno(Personaje atacante, Personaje defensor) throws InterruptedException {
        System.out.println("\n--- Le toca a " + atacante.getNombre() + " ---");

        if (atacante.getSaltarSiguienteTurno()) {
            System.out.println("    - ¡" + atacante.getNombre() + " está regenerándose y pierde su turno!");
            atacante.setSaltarSiguienteTurno(false);
            TimeUnit.SECONDS.sleep(1);
            return;
        }

        int[] danoInfo = atacante.atacar();
        int danoInfligido = danoInfo[0];
        boolean esDanoMaximo = (danoInfo[1] == 1);

        System.out.println("    - " + atacante.getNombre() + " prepara un ataque de " + danoInfligido + " de daño...");
        TimeUnit.SECONDS.sleep(1);

        boolean defensorEvadio = defensor.recibirDano(danoInfligido);

        if (esDanoMaximo && !defensorEvadio) {
            System.out.println("    - ¡" + defensor.getNombre() + " recibe el ataque MÁXIMO! Perderá su próximo turno para recuperarse.");
            defensor.setSaltarSiguienteTurno(true);
        }
        TimeUnit.SECONDS.sleep(1);
    }

    private void mostrarResultadoFinal() {
        System.out.println("\n########################################");
        System.out.println("  !!! FIN DE LA BATALLA ÉPICA !!!");
        System.out.println("########################################");

        if (!heroe1.estaVivo()) {
            System.out.println("¡El ganador es... " + heroe2.getNombre() + "!");
        } else if (!heroe2.estaVivo()) {
            System.out.println("¡El ganador es... " + heroe1.getNombre() + "!");
        } else {
            System.out.println("¡La batalla termina en un empate!"); 
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int vidaDeadpool = 0;
        int vidaWolverine = 0;

        System.out.println("===========================================");
        System.out.println(" SIMULADOR DE COMBATE: DEADPOOL vs WOLVERINE");
        System.out.println("===========================================");

        while (true) {
            System.out.print("Introduce la vida inicial para Deadpool: ");
            try {
                vidaDeadpool = scanner.nextInt();
                if (vidaDeadpool > 0) {
                    break;
                } else {
                    System.out.println("La vida debe ser un número positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Introduce la vida inicial para Wolverine: ");
            try {
                vidaWolverine = scanner.nextInt();
                if (vidaWolverine > 0) {
                    break;
                } else {
                    System.out.println("La vida debe ser un número positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next();
            }
        }

        Deadpool deadpool = new Deadpool(vidaDeadpool);
        Wolverine wolverine = new Wolverine(vidaWolverine);
        Batalla batalla = new Batalla(deadpool, wolverine);

        batalla.simularBatalla();

        scanner.close();
    }
}