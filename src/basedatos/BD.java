package basedatos;

import java.sql.Connection;

public interface BD {
    public void conectar();
    public Connection getConexion();
    public void cierraConexion();
}
