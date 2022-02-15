package modelo;

public class DetalleOrdenVenta {
    public static int idOrdenVenta;
    public static int idProducto;

    public DetalleOrdenVenta(int idOrdenVenta, int idProducto) {
        this.idOrdenVenta = idOrdenVenta;
        this.idProducto = idProducto;
    }    

    public int getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(int idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
}
