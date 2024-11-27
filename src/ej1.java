import java.util.ArrayList;
public class ej1 {
    public static void main(String[] args) {

        VueloRegular vueloRegular = new VueloRegular("RR001", "Madrid", "Barcelona", "2024-12-15", 100, 150.0);
        VueloCharter vueloCharter = new VueloCharter("CH001", "Paris", "Roma", "2024-12-20", 3000.0);

        Reservas reservas = new Reservas();

        // sgregar vuelos al sist de reservas
        reservas.agregarVuelo(vueloRegular);
        reservas.agregarVuelo(vueloCharter);

        System.out.println("Información de los vuelos:");
        reservas.mostrarInformacionVuelos();

        double precioTotal = reservas.calcularPrecioTotal();
        System.out.println("Precio total sin promociones: " + precioTotal);

        System.out.println("\nAplicando promociones:");
        reservas.aplicarPromociones();
    }

}

abstract class Vuelo {
    protected String numeroVuelo;
    protected String origen;
    protected String destino;
    protected String fecha;

    public Vuelo(String numeroVuelo, String origen, String destino, String fecha) {//constructor
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }
    public abstract double calcularPrecio();

    //ahora pongo los getters
    public String getNumeroVuelo() {
        return numeroVuelo;
    }
    public String getOrigen() {
        return origen;
    }
    public String getDestino() {
        return destino;
    }
    public String getFecha() {
        return fecha;
    }
}

class VueloRegular extends Vuelo implements Promocionable {
    private int numeroAsientos;
    private double precioBasePorAsiento;

    public VueloRegular(String numeroVuelo, String origen, String destino, String fecha, int numeroAsientos, double precioBasePorAsiento) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
        this.precioBasePorAsiento = precioBasePorAsiento;
    }

    @Override
    public double calcularPrecio() {
        return numeroAsientos * precioBasePorAsiento;
    }

    @Override
    public void aplicarPromocion() {
        // aca es donde se aplica el descuento
        double descuento = 0.10;
        double precioTotal = calcularPrecio();
        precioTotal = precioTotal - (precioTotal * descuento);
        System.out.println("Precio con promoción para el vuelo regular " + numeroVuelo + ": " + precioTotal);
    }

    // Métodos getters
    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public double getPrecioBasePorAsiento() {
        return precioBasePorAsiento;
    }
}

class VueloCharter extends Vuelo implements Promocionable {
    private double precioTotal;

    public VueloCharter(String numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    public double calcularPrecio() {
        return precioTotal;
    }

    @Override
    public void aplicarPromocion() {
        // Aplicar un descuento, por ejemplo un 15% de descuento
        double descuento = 0.15;
        double precioConDescuento = precioTotal - (precioTotal * descuento);
        System.out.println("Precio con promoción para el vuelo charter " + numeroVuelo + ": " + precioConDescuento);
    }

    // Métodos getters
    public double getPrecioTotal() {
        return precioTotal;
    }
}
interface Promocionable {
    void aplicarPromocion();
}



class Reservas {
    private ArrayList<Vuelo> vuelos;

    public Reservas() {
        vuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public double calcularPrecioTotal() {
        double precioTotal = 0;
        for (Vuelo vuelo : vuelos) {
            precioTotal += vuelo.calcularPrecio();
        }
        return precioTotal;
    }

    // aplicar promociones
    public void aplicarPromociones() {
        for (Vuelo vuelo : vuelos) {
            if (vuelo instanceof Promocionable) {
                ((Promocionable) vuelo).aplicarPromocion();
            }
        }
    }

    // mostrar la info de los vuelos
    public void mostrarInformacionVuelos() {
        for (Vuelo vuelo : vuelos) {
            System.out.println("Vuelo: " + vuelo.getNumeroVuelo() +
                    ", Origen: " + vuelo.getOrigen() +
                    ", Destino: " + vuelo.getDestino() +
                    ", Fecha: " + vuelo.getFecha() +
                    ", Precio: " + vuelo.calcularPrecio());
        }
    }
}

