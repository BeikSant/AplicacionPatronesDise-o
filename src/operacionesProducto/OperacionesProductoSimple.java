package operacionesProducto;

import basedatos.BD;
import factories.productos.ProductoSimpleFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperacionesProductoSimple implements OperacionesProducto {

    BD bd;

    public OperacionesProductoSimple(BD bd) {
        this.bd = bd;
    }

    @Override
    public List obtenertodosProductos() {
        bd.conectar();
        List<ProductoSimpleFactory> listaProductosSimple = new ArrayList<>();
        PreparedStatement query;
        try {
            query = bd.getConexion().prepareStatement("SELECT "
                    + "p.idProducto, p.codigo, ps.nombre, ps.marca, p.precio "
                    + "FROM producto p, productosimple ps WHERE ps.idProducto = p.idProducto");
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                ProductoSimpleFactory ps = new ProductoSimpleFactory();
                ps.setCodigo(rs.getString("codigo"));
                ps.setNombre(rs.getString("nombre"));
                ps.setIdProducto(rs.getInt("idProducto"));
                ps.setMarca(rs.getString("marca"));
                ps.setPrecio(rs.getDouble("precio"));
                listaProductosSimple.add(ps);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesProductoSimple.class.getName()).log(Level.SEVERE, null, ex);
        }

        bd.cierraConexion();
        return listaProductosSimple;

    }

    @Override
    public void actualizarProducto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminarProducto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
