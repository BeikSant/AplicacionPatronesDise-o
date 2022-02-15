package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class BDMySQL implements BD{

    private static final String DB = "sistemapuntoventa";
    private static final String DBuser = "root";
    private static final String DBpwd = "";
    private static final String DBurl = "jdbc:mysql://localhost:3306/" + DB;

    Connection Conector;

    @Override
    public void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conector = DriverManager.getConnection(DBurl, DBuser, DBpwd);
        } catch (ClassNotFoundException classNotFoundException) {
            JOptionPane.showMessageDialog(null, "Error con el driver JDBC");
        } catch (SQLException exception) {
            try {
                JOptionPane.showMessageDialog(null, "Error en la conexion");
                Conector.close();
            } catch (SQLException ex) {
                Logger.getLogger(BDMySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public Connection getConexion() {
        return Conector;
    }

    @Override
    public void cierraConexion() {
    try {
        Conector.close();
    } catch (SQLException exception) {
        // TODO: handle exception
        System.out.println("Error cerrando la conexion");
    }

    }
}
