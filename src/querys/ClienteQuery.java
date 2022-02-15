package querys;

import basedatos.BD;
import factories.sistema.ClienteFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteQuery implements Query {

    BD bd;

    public ClienteQuery() {
    }

    public ClienteQuery(BD bd) {
        this.bd = bd;
    }
    
    @Override
    public Object obtenerElemento(String campo, String dato) {
        bd.conectar();
        ClienteFactory c = new ClienteFactory();
        try {
            PreparedStatement query = bd.getConexion().prepareStatement("SELECT * FROM cliente WHERE " + campo + " = ?");
            if (campo == "idCliente"){
                query.setInt(1, Integer.parseInt(dato));
            } else {
                query.setString(1, dato);
            }
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                c.setNombre(rs.getString("nombres"));
                c.setDireccion(rs.getString("direccion"));
                c.setCedula(rs.getString("cedula"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
        return c;
    }


    @Override
    public void modificarElemento(Object elemento) {
        ClienteFactory c = (ClienteFactory) elemento;
        bd.conectar();
        try {
            PreparedStatement query = bd.getConexion().prepareStatement("UPDATE cliente SET "
                + "nombres = ?, direccion = ? WHERE cedula = ?");
            query.setString(1, c.getNombre());
            query.setString(2, c.getDireccion());
            query.setString(3, c.getCedula());
            query.executeUpdate();
            System.out.println("Informacion del cliente actualizada");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
    }

    @Override
    public void crearElemento(Object elemento) {
        ClienteFactory c = (ClienteFactory) elemento;
        bd.conectar();
        try {
            PreparedStatement query = bd.getConexion().prepareStatement("INSERT INTO cliente "
                    + " (nombres, cedula, direccion) VALUES (?,?,?)");
            query.setString(1, c.getNombre());
            query.setString(2, c.getCedula());
            query.setString(3, c.getDireccion());
            query.executeUpdate();
            System.out.println("Cliente registrado");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        bd.cierraConexion();
    }

    @Override
    public List obtenerTodosDatos(String campo, String dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
