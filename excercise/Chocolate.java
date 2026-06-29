package excercise;
//Clase hija, hereda de Dulce
public class Chocolate extends Dulce {
    public Chocolate(String nombre, double precio) { 
        super(nombre, precio); 
    }
    
    @Override
    public String describir() { 
        return "Delicioso chocolate derretible: " + getNombre(); 
    }
}