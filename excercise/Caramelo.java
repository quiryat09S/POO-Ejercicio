package excercise;
//Clase hija, hereda de Dulce
public class Caramelo extends Dulce {

    public Caramelo(String nombre, double precio) {
        super(nombre, precio);

    }

    @Override
    public String describir() {
        return "Caramelo suave y acidito: " + getNombre();
    }
       
}