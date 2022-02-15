package factories.productos;

import basedatos.BD;
import operacionesProducto.OperacionesProducto;

public abstract class ProductoFactory {
    
    public int idProducto;
    public String codigo;
    public double precio;
    
    public ProductoFactory() { 
       
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public abstract OperacionesProducto getOperaciones(BD bd);
}
