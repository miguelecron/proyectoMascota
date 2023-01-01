package mascota;

public class RatoncitoFiuFiu {

    private String nombre;

    private int edad;

    private double peso;        // en gramos

    private byte hambre;        //  0 (saciado) a 10 (hambriento)

    private byte suciedad;     //  0 (totalmente limpio) a 100 (absolutamnete inmundo)

    private byte salud;         // 0 (muerto) a 100 (totalmente sano)

    private double energia;       // 0 (apatico) a 100 (extremadamente activo)

    private final int INFANCIA = 0;

    private final int ADULTO = 1;

    private final int VIEJO = 2;

    private int cambiarEstado = 120; // almacena los segundos que va a tardar en cambiar el estado

    private int envejecer;      // almacena el tiempo desde el ultimo cambio de estado

    private int energiaAnterior = 50;   // almacena la energia anterior al ultimo sueño

    public RatoncitoFiuFiu(String nombre, int peso, byte hambre, byte suciedad, byte salud, byte energia) {
        this.nombre = nombre;
        edad = 0;
        this.peso = peso;
        this.hambre = hambre;
        this.suciedad = suciedad;
        this.salud = salud;
        this.energia = energia;
    }

    // Un objeto mascota.RatoncitoFiuFiu debería informar cuando nace...
    public String nacimientoMascota() {

        String mensajeNacimiento = "Ha nacido una nueva mascota llamada " + nombre;

        return mensajeNacimiento;
    }


    public String estadisticas() {

        final StringBuilder sb = new StringBuilder();
        sb.append("Nombre de la mascota: ").append(nombre);
        sb.append("\nEdad ").append(edad);
        sb.append("\nPeso ").append(peso);
        sb.append("\nSuciedad ").append(suciedad);
        sb.append("\nSalud ").append(salud);
        sb.append("\nEnergia ").append((int)energia);
        sb.append("\nHambre ").append(hambre);

        return sb.toString();
    }

    // Estipulamos la edad de la mascota
    public int queTramoEdad() {

        if (edad < 172800) {            // de 0 a 2 dias
            return INFANCIA;
        } else if (edad < 345600) {   // de 2 a 4 dias
            return ADULTO;
        } else if (edad < 518400) {  // de 4 a 6 dias
            return VIEJO;
        }

        return 0;
    }

    //Comprobamos si esta dormida
    public boolean estasDormido() {

        int variacionEnergia = (int) (energiaAnterior - energia);
        boolean estaDormido = false;

        if (energia <= 50) {
            estaDormido = true;
        }

        if (energia >= 75) {
            estaDormido = false;
        }

        if (variacionEnergia > 20) {
            estaDormido = true;
            energiaAnterior = (int) energia;
        }

        if (variacionEnergia < -20) {
            estaDormido = false;
            energiaAnterior = (int) energia;
        }

        return estaDormido;
    }

    public boolean estasEnfermo() {

        if (salud <= 60) {
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

        if (salud <= 0 || edad > 518400) {
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

        if (peso > 100) {
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

    public boolean jugar(float cantidadDiversion){
        return false;
    }

    public void envejecer(int segundos) {

        envejecer += segundos;
        edad += segundos;


        if (envejecer == cambiarEstado) {
            int accionRealizar = (int) (Math.random() * 5);

            switch (accionRealizar) {
                case 0:
                    aumentarHambre();
                    break;
                case 1:
                    ensuciar();
                    break;
                case 2:
                    perderSalud();
                    break;
                case 3:
                    perderPeso();
                    break;
            }

            envejecer = 0;
            cambiarEstado = (int) (Math.random() * 1200) + 150;
        }
        perderEnergia();

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
        peso += (cantidadAlimento / 4);             // aumenta un cuarto del alimento cada vez que come
    }

    private void perderPeso() {

        if (hambre > 4) {
            if (peso >= 3) {
                peso -= 3;
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

    private void aumentarEnergia(double cantidadEnergia) {
        double topeAumento = energia + (cantidadEnergia * 5);

        if (topeAumento > 100) {
            energia = 100;
        } else {
            energia += cantidadEnergia * 5;
        }

    }

    private void aumentarSalud(float cantidadSalud) {

        if (salud < 100) {
            salud++;
        }
    }

    private void perderEnergia() {

        switch (hambre) {
            case 0:
                if (energia >= 70) {
                    energia -= 0.001;
                }
                break;
            case 1:
                if (energia >= 65){
                    energia -= 0.002;
                }
            case 3, 4:
                if (energia >= 60) {
                    energia -= 0.003;
                }
                break;
            case 5:
                if (energia >= 55){
                    energia -= 0.004;
                }
            case 6:
                if (energia >= 45) {
                    energia -= 0.005;
                }
                break;
            case 7, 8:
                if (energia >= 30) {
                    energia -= 0.006;
                }
                break;
            case 9:
                if (energia >= 15)
                    energia -= 0.007;
                break;

            case 10:
                if (energia >= 1){
                    energia -= 0.008;
                }
        }
    }

    private void aumentarHambre() {
        int aumentarHambre_aleatorio = (int) (Math.random() * 4 + 1);
        int topeHambre = hambre + aumentarHambre_aleatorio;

        if (topeHambre < 10) {
            hambre += aumentarHambre_aleatorio;
        } else {
            hambre = 10;
        }
    }

    private void ensuciar() {
        int ensuciarAleatorio = (int) (Math.random() * 24 + 1);

        if (suciedad + ensuciarAleatorio < 100) {
            suciedad += ensuciarAleatorio;
        } else {
            suciedad = 100;
        }
    }

    private void perderSalud() {
        int perderSalud_aleatorio = (int) (Math.random() * 24 + 1);

        if (salud > perderSalud_aleatorio) {
            salud -= perderSalud_aleatorio;
        } else {
            salud = 0;
        }
    }
}