package querys;

import basedatos.BD;
import basedatos.BDMySQL;
import factories.sistema.ClienteFactory;
import factories.sistema.OrdenVentaFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import factories.productos.ProductoFactory;
import factories.productos.ProductoSimpleFactory;

public class OrdenVentaQuery implements Query {

    BD bd;

    public OrdenVentaQuery(BD bd) {
        this.bd = bd;
    }

    public void eliminarProducto(ProductoSimpleFactory p) {
        bd.conectar();
        PreparedStatement query;
        try {
            query = bd.getConexion().prepareStatement("DELETE FROM detalleordenventa "
                    + " WHERE idProducto = ?");
            query.setInt(1, p.getIdProducto());
            query.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrdenVentaQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void archivar(OrdenVentaFactory ov) {
        bd.conectar();
        if (ov.getEstado().equals("borrador")) {
            ov.setEstado("archivado");
            PreparedStatement query;
            try {
                query = bd.getConexion().prepareStatement("UPDATE ordenventa SET "
                        + "estado = ? WHERE idOrdenVenta = ?");
                query.setString(1, ov.getEstado());
                query.setInt(2, ov.getId());
                query.executeUpdate();
                System.out.println("Orden Venta Archivado");
            } catch (SQLException ex) {
                Logger.getLogger(OrdenVentaQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bd.cierraConexion();
    }

    public void aceptar(OrdenVentaFactory ov) {
        bd.conectar();
        ov.setEstado("aceptado");
        PreparedStatement query;
        try {
            query = bd.getConexion().prepareStatement("UPDATE ordenventa SET "
                    + "estado = ? WHERE idOrdenVenta = ?");
            query.setString(1, ov.getEstado());
            query.setInt(2, ov.getId());
            query.executeUpdate();
            System.out.println("==Orden Venta Aceptada==");
            new ComprobantePagoQuery(bd).crearElemento(ov);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenVentaQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
    }

    public void obtenerTotalyProductos(OrdenVentaFactory ov) {
        double total = 0;
        bd.conectar();
        try {
            PreparedStatement query = bd.getConexion().prepareStatement("SELECT * FROM detalleordenventa "
                    + " WHERE idOrdenVenta = ?");
            query.setInt(1, ov.getId());
            ResultSet rs = query.executeQuery();

            if (!rs.next()) {
                System.out.println("==No existen productos dentro de la orden de venta==");
            } else {
                System.out.println("Codigo\t" + "Nombre\t" + "Marca\t" + "Precio");
                while (rs.next()) {
                    PreparedStatement query2 = bd.getConexion().prepareStatement("SELECT "
                            + "p.idProducto, p.codigo, ps.nombre, ps.marca, p.precio "
                            + "FROM producto p, productosimple ps WHERE ps.idProducto = p.idProducto AND p.idProducto = ?");
                    query2.setInt(1, rs.getInt("idProducto"));
                    ResultSet rs2 = query2.executeQuery();
                    while (rs2.next()) {
                        System.out.println(rs2.getString("codigo") + "\t" + rs2.getString("nombre")
                                + "\t" + rs2.getString("marca") + "\t" + rs2.getDouble("precio"));
                        total = total + rs2.getDouble("precio");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenVentaQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        ov.setTotal(total);
        modificarElemento(ov);
        System.out.println("Total de la Orden Venta: " + ov.getTotal());
        bd.cierraConexion();
    }

    public void guardarOrdenVenta(OrdenVentaFactory ov, List<ProductoSimpleFactory> listaProductos) {
        bd.conectar();
        for (ProductoFactory producto : listaProductos) {
            try {
                PreparedStatement query = bd.getConexion().prepareStatement("INSERT INTO detalleordenventa "
                        + " (idOrdenVenta, idProducto) VALUES (?,?)");
                query.setInt(1, ov.getId());
                query.setInt(2, producto.getIdProducto());
                query.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OrdenVentaQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bd.cierraConexion();
    }

    @Override
    public Object obtenerElemento(String campo, String dato) {
        bd.conectar();
        OrdenVentaFactory ov = new OrdenVentaFactory();
        try {
            PreparedStatement query = bd.getConexion().prepareStatement("SELECT * FROM ordenventa WHERE " + campo + " = ?");
            query.setString(1, dato);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                ov.setId(rs.getInt("idOrdenVenta"));
                ov.setEstado(rs.getString("estado"));
                ov.setFecha(rs.getTimestamp("fecha"));
                ov.setTotal(rs.getDouble("total"));
                ClienteFactory c = (ClienteFactory) new ClienteQuery((BDMySQL) bd).obtenerElemento("idCliente", String.valueOf(rs.getInt("idCliente")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
        return ov;
    }

    @Override
    public void modificarElemento(Object elemento) {
        OrdenVentaFactory ov = (OrdenVentaFactory) elemento;
        bd.conectar();
        try {
            PreparedStatement query = bd.getConexion().prepareStatement("UPDATE ordenventa SET "
                    + "total = ? WHERE idOrdenVenta = ?");
            query.setDouble(1, ov.getTotal());
            query.setInt(2, ov.getId());
            query.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
    }

    @Override
    public List obtenerTodosDatos(String campo, String dato) {
        bd.conectar();
        int id = 0;
        if (campo == "cliente") {
            Date date = new Date();
            try {
                PreparedStatement query = bd.getConexion().prepareStatement("SELECT * FROM cliente WHERE  cedula  = ?");
                query.setString(1, dato);
                ResultSet rs = query.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("idCliente");
                }
                campo = "idCliente";
            } catch (SQLException ex) {
                Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        List<OrdenVentaFactory> listaOrdenVenta = new ArrayList<>();

        try {
            PreparedStatement query = bd.getConexion().prepareStatement("SELECT * FROM ordenventa WHERE " + campo + " = ?");
            if (campo == "idCliente") {
                query.setInt(1, id);
            } else {
                query.setString(1, dato);
            }
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                PreparedStatement query2 = bd.getConexion().prepareStatement("SELECT * FROM cliente WHERE idCliente  = ?");
                query2.setInt(1, rs.getInt("idCliente"));
                ResultSet rs2 = query2.executeQuery();
                ClienteFactory c = new ClienteFactory();
                while (rs2.next()) {
                    c.setCedula(rs2.getString("cedula"));
                    c.setNombre(rs2.getString("nombres"));
                    c.setDireccion(rs2.getString("direccion"));
                }
                OrdenVentaFactory ov = new OrdenVentaFactory();
                ov.setCliente(c);
                ov.setId(rs.getInt("idOrdenVenta"));
                Timestamp timestamp = rs.getTimestamp("fecha");
                ov.setFecha(timestamp);
                ov.setEstado(rs.getString("estado"));
                ov.setTotal(rs.getDouble("total"));                
                listaOrdenVenta.add(ov);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
        return listaOrdenVenta;
    }

    @Override
    public void crearElemento(Object elemento) {
        ClienteFactory c = (ClienteFactory) elemento;
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        int id = 0;
        bd.conectar();
        try {
            PreparedStatement query = bd.getConexion().prepareStatement("SELECT * FROM cliente WHERE  cedula  = ?");
            query.setString(1, c.getCedula());
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idCliente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement query = bd.getConexion().prepareStatement("INSERT INTO ordenVenta "
                    + " (fecha, estado, idCliente) VALUES (?,?,?)");
            query.setTimestamp(1, timestamp);
            query.setString(2, "borrador");
            query.setInt(3, id);
            query.executeUpdate();
            System.out.println("Orden Creada");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
    }

}
