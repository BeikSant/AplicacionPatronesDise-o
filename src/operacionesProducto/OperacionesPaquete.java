package operacionesProducto;

import basedatos.BD;
import java.util.List;

public class OperacionesPaquete implements OperacionesProducto{

    BD bd;
    
    public OperacionesPaquete(BD bd) {
        this.bd = bd;
    }
    
    @Override
    public List obtenertodosProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
