package factories.productos;

import basedatos.BD;
import operacionesProducto.OperacionesPaquete;
import operacionesProducto.OperacionesProducto;
import operacionesProducto.OperacionesProductoSimple;


public class ProductoSimpleFactory extends ProductoFactory{
    public int idProducto;
    public String nombre;
    public String marca;

    public ProductoSimpleFactory() {    
    }

    @Override
    public int getIdProducto() {
        return idProducto;
    }

    @Override
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    @Override
    public OperacionesProducto getOperaciones(BD bd) {
        return new OperacionesProductoSimple(bd);
    }
}
