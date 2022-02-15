package factories.sistema;

import basedatos.BD;
import basedatos.BDMySQL;
import querys.ClienteQuery;
import querys.ComprobantePagoQuery;
import querys.Query;

public class ComprobantePagoFactory implements SistemaFactory{
    
    public OrdenVentaFactory ordenVenta;
    public int idComprobante;
    public String formaPago;

    public ComprobantePagoFactory() {
    }
    
    
    
    public ComprobantePagoFactory(OrdenVentaFactory ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public OrdenVentaFactory getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVentaFactory ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    
    @Override
    public Query getQuery(BD bd) {
        return new ComprobantePagoQuery(bd);
    }
    
}
