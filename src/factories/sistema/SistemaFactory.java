package factories.sistema;

import basedatos.BD;
import querys.Query;

public interface SistemaFactory {
    Query getQuery(BD bd);
}
