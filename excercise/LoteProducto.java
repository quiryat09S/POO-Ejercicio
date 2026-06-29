package excercise;
//Inventario
public class LoteProducto {
    private Dulce dulce;
    private int cantidadDisponible;//contador actual/stock
    private int cantidadVendida;//Contador acumulativo
    //Constructor
    public LoteProducto(Dulce dulce, int cantidadInicial) {
        this.dulce = dulce;
        this.cantidadDisponible = cantidadInicial;
        this.cantidadVendida = 0;
    }
    //Métodos de acceso
    public Dulce getDulce() { return dulce; }
    public int getCantidadDisponible() { return cantidadDisponible; }
    public int getCantidadVendida() { return cantidadVendida; }
    //Métodos de modificación
    public void reducirStock() { this.cantidadDisponible--; }
    public void registrarVenta() { this.cantidadVendida++; }
}