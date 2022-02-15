package aplicacionpatronesdiseño;

import basedatos.BD;
import basedatos.BDMySQL;
import factories.productos.ProductoFactory;
import factories.productos.ProductoSimpleFactory;
import factories.sistema.ClienteFactory;
import factories.sistema.ComprobantePagoFactory;
import factories.sistema.OrdenVentaFactory;
import java.util.List;
import querys.ClienteQuery;
import querys.Query;
import querys.OrdenVentaQuery;
import factories.sistema.SistemaFactory;
import java.util.ArrayList;
import operacionesProducto.OperacionesProducto;
import querys.ComprobantePagoQuery;

public class AplicacionPatronesDiseño {

    public static void main(String[] args) {
        testServidor(new BDMySQL());

    }

    public static void testServidor(BD bd) {

        /*ProductoFactory pf = new ProductoSimpleFactory();
        OperacionesProducto operacionesProducto = pf.getOperaciones(bd);
        List<ProductoSimpleFactory> listaProductoSimple = operacionesProducto.obtenertodosProductos();
        System.out.println("Lista de Productos Simple");
        for (ProductoSimpleFactory ps : listaProductoSimple) {
            System.out.println("\nCodigo: " + ps.getCodigo());
            System.out.println("Nombre: " + ps.getNombre());
            System.out.println("Marca: " + ps.getMarca());
            System.out.println("Precio: " + ps.getPrecio());
        }

        List<ProductoSimpleFactory> listaProductoSimple2 = new ArrayList<>();
        listaProductoSimple2.add(listaProductoSimple.get(3));
        listaProductoSimple2.add(listaProductoSimple.get(5));
        
        SistemaFactory s = new OrdenVentaFactory();
        Query ordenVentaQuery = s.getQuery(bd);
        List<OrdenVentaFactory> listOrdenVentas = ordenVentaQuery.obtenerTodosDatos("cliente", "1950031375");

        OrdenVentaQuery ov = new OrdenVentaQuery(bd);
        
        for (OrdenVentaFactory ordenVenta: listOrdenVentas) {
            System.out.println("\nOrden de Venta: " + ordenVenta.getId());
            System.out.println("Hora y Fecha: " + ordenVenta.getFecha());
            System.out.println("Estado: " + ordenVenta.getEstado());
            System.out.println("Productos");
            new OrdenVentaQuery(bd).obtenerTotalyProductos(ordenVenta);
            ov.eliminarProducto(listaProductoSimple2.get(1));
        }

        List<OrdenVentaFactory> listOrdenVentas2 = ordenVentaQuery.obtenerTodosDatos("cliente", "1950031375");

        for (OrdenVentaFactory ordenVenta: listOrdenVentas2) {
            ov.archivar(ordenVenta);
            System.out.println("\nOrden de Venta: " + ordenVenta.getId());
            System.out.println("Hora y Fecha: " + ordenVenta.getFecha());
            System.out.println("Estado: " + ordenVenta.getEstado());
            System.out.println("Productos");
            new OrdenVentaQuery(bd).obtenerTotalyProductos(ordenVenta);
        }*/
 /* System.out.println("Nombre: " + c.getNombre());
        System.out.println("Cedula: " + c.getCedula());
        System.out.println("Direccion: " + c.getDireccion());
        
        c = (ClienteFactory) clienteQuery.obtenerElemento("nombres", "Santiago Roman");
        System.out.println("\nNombre: " + c.getNombre());
        System.out.println("Cedula: " + c.getCedula());
        System.out.println("Direccion: " + c.getDireccion());
        
        SistemaFactory s = new OrdenVentaFactory();
        Query clienteQuery = new ClienteQuery(bd);
        ClienteFactory c = (ClienteFactory) clienteQuery.obtenerElemento("cedula", "1950031375");
        
        Query ordenVQuery = new OrdenVentaQuery(bd);
        List<OrdenVentaFactory> listaOrdenes =  (List<OrdenVentaFactory>) 
                ordenVQuery.obtenerTodosDatos("cliente", c.getCedula());
        for (OrdenVentaFactory ordenes : listaOrdenes) {
            System.out.println("Numero Orden: " + ordenes.getId());
            System.out.println("Estado: " + ordenes.getEstado());
            System.out.println("Total: " + ordenes.getTotal());
        }
        
        List<OrdenVentaFactory> listaOrdenes2 =  (List<OrdenVentaFactory>) 
                ordenVQuery.obtenerTodosDatos("estado", "borrador");
        for (OrdenVentaFactory ordenes : listaOrdenes2) {
            System.out.println("\nNumero Orden: " + ordenes.getId());
            System.out.println("Estado: " + ordenes.getEstado());
            System.out.println("Total: " + ordenes.getTotal());
        } */
 /*SistemaFactory s = new OrdenVentaFactory();
        Query ordenVentaQuery = s.getQuery(bd);
        List<OrdenVentaFactory> listOrdenVentas = ordenVentaQuery.obtenerTodosDatos("cliente", "1950031375");

        OrdenVentaQuery ov = new OrdenVentaQuery(bd);
        
        for (OrdenVentaFactory ordenVenta: listOrdenVentas) {
            System.out.println("\nOrden de Venta: " + ordenVenta.getId());
            System.out.println("Hora y Fecha: " + ordenVenta.getFecha());
            System.out.println("Estado: " + ordenVenta.getEstado());
            System.out.println("Productos");
            new OrdenVentaQuery(bd).obtenerTotalyProductos(ordenVenta);
            System.out.println("Aceptando Orden de Venta");
            ov.aceptar(ordenVenta);
        }*/
        SistemaFactory s = new ComprobantePagoFactory();
        Query comprobanteQuery = s.getQuery(bd);

        List<ComprobantePagoFactory> listaComprobantes = comprobanteQuery.obtenerTodosDatos("cliente", "1950031375");
        System.out.println("==Impresion de Comprobantes==");
        for (ComprobantePagoFactory comprobante : listaComprobantes) {
            ComprobantePagoQuery cq = new ComprobantePagoQuery(bd);
            cq.imprimirComprobante(comprobante);
        }
    }
}
