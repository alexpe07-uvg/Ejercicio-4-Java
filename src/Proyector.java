public class Proyector extends Equipo {
    private int lumens;
    private boolean wireless;

    
    public Proyector(int code, String brand, String model, boolean available, int lumens, boolean wireless) {
        super(code, brand, model, 250, available);
        setLumens(lumens);
        this.wireless = wireless;
    }

    public Proyector(int code, String brand, String model, boolean available) {
        super(code, brand, model, 250, available);
    }

    public int getLumens() {
        return lumens;
    }

    public boolean setLumens(int lumens) {
        if (lumens <= 0) {
            return false;
        }
        this.lumens = lumens;
        return true;
    }

    public boolean isWireless() {
        return wireless;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }
    
    @Override
    protected double calculateExtraCost(int days) {
        if (wireless) {
            return 50.00 * days;
        }

        return 0.00;
    }

    @Override 
    public String toString() {
        return super.toString() + ", Lúmenes: " + lumens + ", Inalámbrico: " + wireless + "(Proyector)";
    }
    
}
