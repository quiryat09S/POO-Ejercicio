package excercise;

import java.util.HashMap;
import java.util.Map;

public class MaquinaExpendedora {
    private Map<String, LoteProducto> inventario;
    private double saldoUsuarioActual;
    private double ventasTotales;

    //Credenciales
    private final String USUARIO_ADMIN = "admin";
    private final String CLAVE_ADMIN = "5678";
    //Constructor
    public MaquinaExpendedora() {
        this.inventario = new HashMap<>();
        this.saldoUsuarioActual = 0.0;
        this.ventasTotales = 0.0;
    }
    //Métodos principales
    public void agregarProducto(String codigo, Dulce dulce, int cantidadInicial) {
        inventario.put(codigo.toUpperCase(), new LoteProducto(dulce, cantidadInicial));
    }

    public void insertarDinero(double monto) {
        if (monto > 0) {
            saldoUsuarioActual += monto;
            System.out.println("Saldo actual: $" + saldoUsuarioActual);
        }
    }

    public void mostrarCatalogo() {
        System.out.println("\n--- CATÁLOGO DE DULCES ---");
        for (Map.Entry<String, LoteProducto> entry : inventario.entrySet()) {
            LoteProducto lote = entry.getValue();
            System.out.printf("[%s] %s - $%.2f (Disponibles: %d)%n", 
                entry.getKey(), 
                lote.getDulce().getNombre(), 
                lote.getDulce().getPrecio(), 
                lote.getCantidadDisponible());
        }
        System.out.println("--------------------------");
    }

    public void comprarProducto(String codigo) throws SinStockException, FondosInsuficientesException {
        codigo = codigo.toUpperCase();
        if (!inventario.containsKey(codigo)) {
            throw new IllegalArgumentException("El código seleccionado no existe.");
        }

        LoteProducto lote = inventario.get(codigo);
        Dulce dulceSeleccionado = lote.getDulce();

        if (lote.getCantidadDisponible() <= 0) {
            throw new SinStockException("Lo sentimos, no hay stock de " + dulceSeleccionado.getNombre());
        }

        if (saldoUsuarioActual < dulceSeleccionado.getPrecio()) {
            throw new FondosInsuficientesException("Fondos insuficientes. Te faltan $" + 
                (dulceSeleccionado.getPrecio() - saldoUsuarioActual));
        }

        lote.reducirStock();
        lote.registrarVenta();
        saldoUsuarioActual -= dulceSeleccionado.getPrecio();
        ventasTotales += dulceSeleccionado.getPrecio();

        System.out.println("\n¡Compra exitosa! Disfruta tu " + dulceSeleccionado.getNombre());
        entregarCambio();
    }

    private void entregarCambio() {
        if (saldoUsuarioActual > 0) {
            System.out.printf("Entregando cambio: $%.2f%n", saldoUsuarioActual);
            saldoUsuarioActual = 0.0; 
        }
    }

    // LÓGICA DE VALIDACIÓN: El método ahora requiere parámetros y lanza la nueva excepción
    public void reporteAdministrador(String usuario, String contrasena) throws AccesoDenegadoException {
        // Validación temprana: Si no coincide el usuario o la clave, se lanza el error inmediatamente
        if (!USUARIO_ADMIN.equals(usuario) || !CLAVE_ADMIN.equals(contrasena)) {
            throw new AccesoDenegadoException("Credenciales inválidas. No tienes permisos de Administrador.");
        }

        // Si la validación pasa, se ejecuta el reporte de forma segura
        System.out.println("\n=== REPORTE DE VENTAS (ADMIN) ===");
        System.out.printf("Ingresos Totales: $%.2f%n", ventasTotales);
        for (Map.Entry<String, LoteProducto> entry : inventario.entrySet()) {
            LoteProducto lote = entry.getValue();
            System.out.printf("Producto: %s | Vendidos: %d | Restantes: %d%n", 
                lote.getDulce().getNombre(), 
                lote.getCantidadVendida(), 
                lote.getCantidadDisponible());
        }
        System.out.println("=================================");
    }
}