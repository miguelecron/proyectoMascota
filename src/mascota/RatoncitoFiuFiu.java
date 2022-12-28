package mascota;

public class RatoncitoFiuFiu {

    private String nombre;

    private int edad;

    private double peso;

    private byte hambre;        //  0 (saciado) a 10 (hambriento)

    private byte suciedad;     //  0 (totalmente limpio) a 100 (absolutamnete inmundo)

    private byte salud;         // 0 (muerto) a 100 (totalmente sano)

    private byte energia;       // 0 (apatico) a 100 (extremadamente activo)

    private final int INFANCIA = 0;

    private final int ADULTO = 1;

    private final int VIEJO = 2;

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

        final StringBuilder sb = new StringBuilder();
        sb.append("Nombre de la mascota ").append(nombre);
        sb.append("Edad ").append(edad);
        sb.append("Peso ").append(peso);
        sb.append("Suciedad ").append(suciedad);
        sb.append("Salud ").append(salud);
        sb.append("Energia ").append(energia);

        return sb.toString();
    }

    public void limpiar(float esfuerzoHigienico) {
        suciedad -= esfuerzoHigienico;
    }

    public int queTramoEdad() {

        if (edad <259200){
            return INFANCIA;
        } else if (edad < 777600) {
            return ADULTO;
        } else if (edad > 777600){
            return VIEJO;
        }

        return 0;
    }

    public boolean estasDormido() {

        int energiaAnerior = 50;
        int variacionEnergia = energiaAnerior - energia;
        boolean estaDormido = false;

        if (variacionEnergia > 20){
            estaDormido = true;
        }
        if (variacionEnergia < -20){
            estaDormido = false;
        }

        energiaAnerior = energia;

        return estaDormido;

    }

    public boolean estasEnfermo() {

        if (salud <= 70) {
            return true;
        }
        return false;
    }

    public boolean estasSucio() {

        if (suciedad >= 40) {
            return true;
        }
        return false;
    }

    public boolean estasMuerto() {

        if (salud == 0) {
            return true;
        }
        return false;
    }

    public boolean tienesHambre() {

        if (hambre < 5) {
            return true;
        }
        return false;
    }

    private boolean estaGordo(){

        if (peso > 60){
            return true;
        }
        return false;
    }

    public boolean estasFeliz() {

        if (hambre < 3 && salud > 80 && suciedad < 30) {
            return true;
        }
        return false;
    }

    public void envejecer(int segundos) {
        edad += segundos;

        if (segundos < 600) {
            hambre++;
            suciedad += 10;
            salud -= 10;
            energia -= 10;
            perderPeso();
            perderEnergia();
        }

    }

    public boolean tienesQuejas() {

        if (estasSucio() || tienesHambre() || estasEnfermo()|| estaGordo()  ) {
            return true;
        }
        return false;
    }

    public void alimentar(float cantidadAlimento) {
        hambre -= cantidadAlimento;
        ganarPeso(cantidadAlimento);
        aumentarEnergia(cantidadAlimento);
        aumentarSalud(cantidadAlimento);

    }

    public void curar(float cantidadMedicina) {
        salud += cantidadMedicina;

    }

    private void ganarPeso(float cantidadAlimento) {
        peso++;
    }

    private void perderPeso() {

        if (hambre < 4) {
            peso -= 10;
        }
        if (salud < 50) {
            peso -= 10;
        }
    }

    private void aumentarEnergia(float cantidadEnergia) {
        energia += cantidadEnergia * 2;
    }

    private void aumentarSalud(float cantidadSalud) {
        salud++;
    }

    private void perderEnergia() {

        if (hambre < 3){
            energia -=40;
        }
        if (hambre < 5){
            energia -= 25;
        }
        if (hambre < 7){
            energia -=5;
        }
    }
}