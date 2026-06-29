package excercise;
    //Clase abstracta
public abstract class Dulce {
    private String nombre;
    private double precio;
    //Constructor 
    public Dulce(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    //Métodos get, solo lectura, inmutables
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    //Sirve para sobreescribir segun el uso
    public abstract String describir(); 
}