
package factories.sistema;

import basedatos.BD;
import basedatos.BDMySQL;
import querys.ClienteQuery;
import querys.Query;


public class ClienteFactory implements SistemaFactory{

    public static String nombre;
    public static String cedula;
    public static String direccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public Query getQuery(BD bd) {
        return new ClienteQuery(bd);
    }

}
