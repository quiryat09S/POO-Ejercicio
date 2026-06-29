package excercise;
//Clase hija, hereda de Dulce
public class Gomita extends Dulce {
    public Gomita(String nombre, double precio) { 
        super(nombre, precio); 
    }
    
    @Override
    public String describir() { 
        return "Gomita dulce y masticable: " + getNombre(); 
    }
}