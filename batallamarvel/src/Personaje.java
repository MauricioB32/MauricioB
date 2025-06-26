import java.util.Random;

public abstract class Personaje {
    
    protected String nombre;
    protected int vida;
    protected int danoMin;
    protected int danoMax;
    protected double probabilidadEvasion;
    protected boolean saltarSiguienteTurno; // Flag para la regeneración

    // Constructor de la clase base
    public Personaje(String nombre, int vidaInicial, int danoMin, int danoMax, double probabilidadEvasion) {
        this.nombre = nombre;
        this.vida = vidaInicial;
        this.danoMin = danoMin;
        this.danoMax = danoMax;
        this.probabilidadEvasion = probabilidadEvasion;
        this.saltarSiguienteTurno = false;
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    /**
     * Calcula un daño aleatorio y devuelve si fue el máximo.
     * @return Un array con el daño infligido y un indicador (1 si es máximo, 0 si no).
     */
    public int[] atacar() {
        Random random = new Random();
        int danoInfligido = random.nextInt(danoMax - danoMin + 1) + danoMin;
        int esDanoMaximo = (danoInfligido == danoMax) ? 1 : 0;
        
        return new int[]{danoInfligido, esDanoMaximo};
    }

    /**
     * Aplica el daño recibido, con probabilidad de evadir.
     * @param danoRecibido El daño que se va a recibir.
     * @return true si el ataque fue evadido, false si no.
     */
    public boolean recibirDano(int danoRecibido) {
        Random random = new Random();
        if (random.nextDouble() < this.probabilidadEvasion) {
            System.out.println("    - ¡" + this.nombre + " ha esquivado el ataque!");
            return true; // Se evadió
        } else {
            this.vida -= danoRecibido;
            if (this.vida < 0) {
                this.vida = 0; // Asegura que la vida no sea negativa
            }
            System.out.println("    - ¡" + this.nombre + " recibe " + danoRecibido + " de daño!");
            return false; // No se evadió
        }
    }

    // Getters y setters para acceder a las propiedades desde otras clases
    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public boolean getSaltarSiguienteTurno() {
        return saltarSiguienteTurno;
    }
    
    public void setSaltarSiguienteTurno(boolean valor) {
        this.saltarSiguienteTurno = valor;
    }

    @Override
    public String toString() {
        return this.nombre + " (Vida: " + this.vida + ")";
    }
}