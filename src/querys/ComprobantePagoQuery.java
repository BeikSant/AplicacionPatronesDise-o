package querys;

import basedatos.BD;
import factories.sistema.ClienteFactory;
import factories.sistema.ComprobantePagoFactory;
import factories.sistema.OrdenVentaFactory;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComprobantePagoQuery implements Query {

    BD bd;

    public ComprobantePagoQuery(BD bd) {
        this.bd = bd;
    }

    @Override
    public Object obtenerElemento(String campo, String dato) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificarElemento(Object elemento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List obtenerTodosDatos(String campo, String dato) {
        List<ComprobantePagoFactory> listaComprobantes = new ArrayList<>();
        List<OrdenVentaFactory> listaOrdenes = new OrdenVentaQuery(bd).obtenerTodosDatos(campo, dato);
        try {
            bd.conectar();
            for (OrdenVentaFactory ordenVenta : listaOrdenes) {
                PreparedStatement query =  bd.getConexion().prepareStatement("SELECT * FROM comprobante WHERE idOrdenVenta  = ?");
                query.setInt(1, ordenVenta.getId());
                ResultSet rs = query.executeQuery();
                while (rs.next()) {
                    ComprobantePagoFactory cp = new ComprobantePagoFactory();
                    cp.setIdComprobante(rs.getInt("idComprobante"));
                    cp.setFormaPago(rs.getString("formaPago"));
                    cp.setOrdenVenta(ordenVenta);
                    listaComprobantes.add(cp);
                }
            }

            ClienteFactory c = new ClienteFactory();
        } catch (SQLException ex) {
            Logger.getLogger(ComprobantePagoQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
        return listaComprobantes;
    }

    @Override
    public void crearElemento(Object elemento) {
        OrdenVentaFactory ov = (OrdenVentaFactory) elemento;
        bd.conectar();
        PreparedStatement query;
        try {
            query = bd.getConexion().prepareStatement("INSERT INTO comprobante "
                    + " (idOrdenVenta, formaPago) VALUES (?,?)");
            query.setInt(1, ov.getId());
            query.setString(2, "efectivo");
            query.executeUpdate();
            System.out.println("===Comprobante Generado===");
        } catch (SQLException ex) {
            Logger.getLogger(ComprobantePagoQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        bd.cierraConexion();
    }

    public void imprimirComprobante(ComprobantePagoFactory cp) {
        OrdenVentaFactory ov = cp.getOrdenVenta();
        ClienteFactory c = ov.getCliente();
        System.out.println("\nNumero Comprobante: " + cp.getIdComprobante());
        System.out.println("Fecha: " + ov.getFecha());
        System.out.println("Nombre Cliente: " + c.getNombre());
        System.out.println("Forma Pago: " + cp.getFormaPago());
        System.out.println("Total: " + ov.getTotal());
    }

}
