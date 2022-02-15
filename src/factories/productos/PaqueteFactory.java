package factories.productos;

import basedatos.BD;
import operacionesProducto.OperacionesPaquete;
import operacionesProducto.OperacionesProducto;

public class PaqueteFactory extends ProductoFactory{
    
    public int idProducto;

    public PaqueteFactory(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int getIdProducto() {
        return idProducto;
    }

    @Override
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    @Override
    public OperacionesProducto getOperaciones(BD bd){
        return new OperacionesPaquete(bd);
    }
}
