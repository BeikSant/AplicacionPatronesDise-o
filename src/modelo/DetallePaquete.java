package modelo;

public class DetallePaquete {
    public static int idPaquete;
    public static int idProducto;
    public static int idDetallePaquete;

    public DetallePaquete(int idPaquete, int idProducto, int idDetallePaquete) {
        this.idPaquete = idPaquete;
        this.idProducto = idProducto;
        this.idDetallePaquete = idDetallePaquete;
    }
    
    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdDetallePaquete() {
        return idDetallePaquete;
    }

    public void setIdDetallePaquete(int idDetallePaquete) {
        this.idDetallePaquete = idDetallePaquete;
    }
    
    
  
}
