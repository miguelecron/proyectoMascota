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
        edad = 0;
        this.peso = peso;
        this.hambre = hambre;
        this.suciedad = suciedad;
        this.salud = salud;
        this.energia = energia;
        // Un objeto mascota.RatoncitoFiuFiu debería informar cuando nace...
    }


    public String estadisticas() {

        final StringBuilder sb = new StringBuilder();
        sb.append("Nombre de la mascota: ").append(nombre);
        sb.append("\nEdad ").append(edad);
        sb.append("\nPeso ").append(peso);
        sb.append("\nSuciedad ").append(suciedad);
        sb.append("\nSalud ").append(salud);
        sb.append("\nEnergia ").append(energia);

        return sb.toString();
    }

    public int queTramoEdad() {

        if (edad < 2880) {            // de 0 a 2 dias
            return INFANCIA;
        } else if (edad < 5760) {   // de 2 a 4 dias
            return ADULTO;
        } else if (edad < 11520) {  // de 4 a 6 dias
            return VIEJO;
        }

        return 0;
    }

    public boolean estasDormido() {

        int energiaAnerior = 50;
        int variacionEnergia = energiaAnerior - energia;
        boolean estaDormido = false;

        if (variacionEnergia > 20) {
            estaDormido = true;
        }
        if (variacionEnergia < -20) {
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

        if (salud == 0 || edad > 11520) {
            return true;
        }

        return false;
    }

    public boolean tienesHambre() {

        if (hambre > 5) {
            return true;
        }
        return false;
    }

    private boolean estaGordo() {

        if (peso > 60) {
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

    public boolean tienesQuejas() {

        if (estasSucio() || tienesHambre() || estasEnfermo() || estaGordo()) {
            return true;
        }
        return false;
    }

    public void envejecer(int segundos) {
        edad += segundos;


        if (segundos > 600) {
            aumentarHambre();
            ensuciar();
            perderSalud();
            perderPeso();
            perderEnergia();
        }

    }

    public void alimentar(float cantidadAlimento) {
        float topeAlimento = hambre - cantidadAlimento;

        if (topeAlimento < 0) {
            hambre = 0;
        } else {
            hambre -= cantidadAlimento;
        }
        ganarPeso(cantidadAlimento);
        aumentarEnergia(cantidadAlimento);
        aumentarSalud(cantidadAlimento);

    }

    public void limpiar(float esfuerzoHigienico) {

        float topeLimpieza = suciedad - esfuerzoHigienico;  // variable para calcular que cantidad de limpieza se puede aplicar

        if (topeLimpieza < 0) {
            suciedad = 0;
        } else {
            suciedad -= esfuerzoHigienico;
        }
    }

    public void curar(float cantidadMedicina) {
        float topeCura = salud + cantidadMedicina;

        if (topeCura > 100) {
            salud = 100;
        } else {
            salud += cantidadMedicina;
        }

    }

    private void ganarPeso(float cantidadAlimento) {
        peso++;             // aumenta una unidad de peso cada vez que come
    }

    private void perderPeso() {

        if (hambre < 4) {
            if (peso >= 10) {
                peso -= 10;
            } else {
                peso = 0;
            }
        }

        if (salud < 50) {
            if (peso >= 5) {
                peso -= 5;
            } else {
                peso = 0;
            }
        }
    }

    private void aumentarEnergia(float cantidadEnergia) {
        float topeAumento = energia + (cantidadEnergia * 5);

        if (topeAumento > 100) {
            energia = 100;
        } else {
            energia += cantidadEnergia * 5;
        }

    }

    private void aumentarSalud(float cantidadSalud) {
        salud++;
    }

    private void perderEnergia() {

        switch (hambre) {
            case 1, 2:
                if (energia >= 10) {
                    energia -= 10;
                } else {
                    energia = 0;
                }
                break;
            case 3, 4:
                if (energia >= 5) {
                    energia -= 5;
                } else {
                    energia = 0;
                }
                break;
            case 5, 6:
                if (energia >= 3) {
                    energia -= 3;
                } else {
                    energia = 0;
                }
                break;
            case 7, 8:
                if (energia >= 1) {
                    energia -= 1;
                } else {
                    energia = 0;
                }
                break;
            case 9, 10:
                break;
        }
    }

    private void aumentarHambre() {

        if (hambre < 10) {
            hambre++;
        }
    }

    private void ensuciar() {

        if (suciedad < 100) {
            suciedad += 3;
        }
    }

    private void perderSalud(){

        if (salud > 3){
            salud -= 3;
        }
    }
}