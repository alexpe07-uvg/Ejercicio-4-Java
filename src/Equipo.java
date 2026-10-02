public abstract class Equipo {
    private int code;
    private String brand;
    private String model;
    private double dailyCost;
    private boolean available;
    

 //tarifa la define uno para cada equipo

    public Equipo (int code, String brand, String model, double dailyCost, boolean available) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.dailyCost = dailyCost;
        this.available = true; // cualquier equipo nuevo comienza disponible
    }

    public int getCode() {
        return code;
    }


    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    //metodo que calcula el costo total para cada equipo (incluye tarifa diaria, días y costo extra por caracteristicas especiales)
    public double calculateCost(int days) {
        return dailyCost * days + calculateExtraCost(days);

        
    }

    protected abstract double calculateExtraCost(int days);


    @Override
    public String toString() {
        return "Código: " + code + ", Marca: " + brand + ", Modelo: " + model + ", Costo diario: Q " + String.format("%.2f",dailyCost) + ", Disponibilidad: " + available;
    } 

}
    