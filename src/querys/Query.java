package querys;

import java.util.List;

public interface Query {
    Object obtenerElemento(String campo, String dato);
    void modificarElemento(Object elemento);
    List obtenerTodosDatos(String campo, String dato);
    void crearElemento(Object elemento);
}
