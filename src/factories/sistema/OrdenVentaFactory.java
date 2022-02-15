
package factories.sistema;

import basedatos.BD;
import java.util.Date;
import querys.Query;
import querys.OrdenVentaQuery;

public class OrdenVentaFactory implements SistemaFactory {

    public ClienteFactory cliente;
    public int id;
    public Date fecha;
    public String estado;
    public double total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ClienteFactory getCliente() {
        return cliente;
    }

    public void setCliente(ClienteFactory cliente) {
        this.cliente = cliente;
    }

    
    @Override
    public Query getQuery(BD bd) {
        return new OrdenVentaQuery(bd);
    }

}
