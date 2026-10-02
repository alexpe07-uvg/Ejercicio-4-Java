import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<Integer, Equipo> inventario;
    private double totalCost;

    public Inventario() {
        this.inventario = new HashMap<>();
        totalCost = 0.0; // se inicializa el costo total en 0
        precargarEquipos();
    }

    //verificación de existencia de código en el inventario
    public boolean existeCodigo(Integer code) {
        if (code == null) {
            return false;
        }
        return inventario.containsKey(code);
    }

    private void precargarEquipos() {
        //se agregan equipos base al inventario
        
        //camaras del inventario
        inventario.put(100, new Camara(100, "Sony", "A7III", true, 720));
        inventario.put(101, new Camara(101, "Canon", "X123", true, 1080));
        inventario.put(102, new Camara(102, "Nikon", "Z9", true, 1440));

        // proyectores del inventario
        inventario.put(200, new Proyector(200, "Epson", "PowerLite", true, 300, true));
        inventario.put(201, new Proyector(201, "BenQ", "HT2050A", true, 250, false));
        inventario.put(202, new Proyector(202, "Optoma", "HD143X", true, 400, true));

        // Sonidos del inventario
        inventario.put(300, new Sonido(300, "Bose", "SoundLink", true, 0.5));
        inventario.put(301, new Sonido(301, "JBL", "Flip 5", true, 1));
        inventario.put(302, new Sonido(302, "Sony", "SRS-XB12", true, 2));
    }

    public boolean registrarEquipo(Equipo nuevoEquipo) {
        if (nuevoEquipo == null) {//verificacion de equipo nulo
            return false;
        }
        if (inventario.containsKey(nuevoEquipo.getCode())) {//verificacion repetidos
            return false;
        }
        inventario.put(nuevoEquipo.getCode(), nuevoEquipo);
        return true;
    }

    public Equipo buscarPorCodigo(int code) {
        return inventario.get(code);
    }

    public String mostrarInventario() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========EQUIPOS REGISTRADOS=========\n");
        for (Equipo eq : inventario.values()) {
            sb.append(eq.toString()).append("\n"); //muestra la información de los equipos registrados usando el método toString() de cada clase para mostrar atributos únicos de cada tipo de equipo
        }
        return sb.toString();
    }

    public double cotizar(int code, int dias) {
        Equipo eq = buscarPorCodigo(code);
        if (eq != null) {
            return eq.calculateCost(dias);
        }
        return -1.00;
    }

    public double alquilarEquipo(int code, int dias) {
        Equipo eq = buscarPorCodigo(code);
        
        if (eq == null || !eq.isAvailable()) {
            return -1.00;
        }

        double costoTotal = eq.calculateCost(dias);
        eq.setAvailable(false); //marcar como no disponible
        this.totalCost += costoTotal;
        return costoTotal;
    }

    public boolean devolverEquipo(int code) {
        Equipo eq = buscarPorCodigo(code);

        if (eq == null) {
            return false;
        }

        if (eq.isAvailable()) {
            return false;
        }

        eq.setAvailable(true); //marcar como disponible
        return true;
    }

    public String generarReporte() {
        int totalProy = 0, dispProy = 0, alqProy = 0;
        int totalCam = 0, dispCam = 0, alqCam = 0;
        int totalSon = 0, dispSon = 0, alqSon = 0;

        for (Equipo eq : inventario.values()) {
            if (eq instanceof Proyector) { // si encuentra un equipo de tipo Proyector, incrementa el contador de proyectores y verifica su disponibilidad
                totalProy++;
                if (eq.isAvailable()) dispProy++; else alqProy++;
            } else if (eq instanceof Camara) { // si encuentra un equipo de tipo Camara, incrementa el contador de camaras y verifica su disponibilidad
                totalCam++;
                if (eq.isAvailable()) dispCam++; else alqCam++;
            } else if (eq instanceof Sonido) { // si encuentra un equipo de tipo Sonido, incrementa el contador de sonidos y verifica su disponibilidad
                totalSon++;
                if (eq.isAvailable()) dispSon++; else alqSon++;
            }
        }


        // Valores que se mostraran en el metodo de generarReporte(), al ser este llamado desde main
        StringBuilder sb = new StringBuilder();
        sb.append("1. Proyectores Total: ").append(totalProy).append(" | Disponibles: ").append(dispProy).append(" | Alquilados: ").append(alqProy).append("\n");
        sb.append("2. Cámaras     Total: ").append(totalCam).append(" | Disponibles: ").append(dispCam).append(" | Alquilados: ").append(alqCam).append("\n");
        sb.append("3. Sonido      Total: ").append(totalSon).append(" | Disponibles: ").append(dispSon).append(" | Alquilados: ").append(alqSon).append("\n");
        sb.append("---------------------------------------------------------------\n");
        sb.append("Total general de equipos: ").append(inventario.size()).append("\n");
        sb.append("Dinero acumulado por alquileres: Q").append(String.format("%.2f", totalCost)).append("\n");
        sb.append("===============================================================");
        return sb.toString();
    }
}


    

