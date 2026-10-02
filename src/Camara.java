public class Camara extends Equipo {
    private int resolution; // en pixeles verticales, (720p,1080p, 2160p)

    public Camara(int code, String brand, String model, boolean available, int resolution) {
        super(code, brand, model, 100, available);
        setResolution(resolution);
    }

    public Camara(int code, String brand, String model, boolean available) {
        super(code, brand, model, 100, available);
    }

    public int getResolution() {
        return resolution;
    }

    public boolean setResolution(int resolution) {
        if (resolution <= 0) {
            return false;
        }
        this.resolution = resolution;
        return true;
    }

    @Override
    protected double calculateExtraCost(int days) {
        if (resolution > 1080) {
            return 75.00;
        }
        return 0.00;
    }

     @Override
    public String toString() {
        return super.toString() + ", Resolución: " + resolution + "p (Cámara)";
    }
    
}
