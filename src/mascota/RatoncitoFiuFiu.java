package mascota;

public class RatoncitoFiuFiu {

    private String nombre;

    private int edad;

    private double peso;

    private byte hambre;

    private byte suciedad;

    private byte salud;

    private byte energia;

    public RatoncitoFiuFiu(String nombre, int peso, byte hambre, byte suciedad, byte salud, byte energia) {
       this.nombre = nombre;
       this.peso = peso;
       this.hambre = hambre;
       this.suciedad = suciedad;
       this.salud = salud;
       this.energia = energia;

        // Un objeto mascota.RatoncitoFiuFiu debería informar cuando nace...
    }


    public String estadisticas() {
        return null;
    }

    public void limpiar(int i) {
    }

    public int queTramoEdad() {
        return 0;
    }

    public boolean estasDormido() {
        return false;
    }

    public boolean estasEnfermo() {
        return false;
    }

    public boolean estasSucio() {
        return false;
    }

    public boolean estasMuerto() {
        return false;
    }

    public void envejecer(int i) {
    }

    public boolean tienesQuejas() {
        return false;
    }

    public void alimentar(int b) {

    }

    public void curar(int i) {
    }
}