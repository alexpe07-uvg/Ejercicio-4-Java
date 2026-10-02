public  class Sonido extends Equipo{
    private double powerKw; // puede incluir decimales


    public Sonido(int code, String brand, String model, boolean available, double powerKw) {
        super(code, brand, model, 300, available);
        setPowerKw(powerKw);
    }

    public Sonido(int code, String brand, String model, boolean available) {
        super(code, brand, model, 300, available);
    }

    public double getPowerKw() {
        return powerKw;
    
    }

    public boolean setPowerKw(double powerKw) {
        if (powerKw <= 0) {
            return false;
        }
        this.powerKw = powerKw;
        return true;
    }


    @Override
    protected double calculateExtraCost(int days) {
    return 100.00 * powerKw * days;
        
    }

    @Override 
    public String toString() {
        return super.toString() + ", Potencia: " + powerKw + "kW (Sonido)";
    }


    
}
