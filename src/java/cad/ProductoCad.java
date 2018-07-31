/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cad;

import JavaBeans.Producto;
import JavaBeans.ProductoMoneda;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Armando
 */
public class ProductoCad {
    
    
    public static boolean registrarProducto(Producto producto, ProductoMoneda prodeur, ProductoMoneda produsd, ProductoMoneda procup ){
      
        try {
             String sql="{CALL sp_registrar_producto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            Connection c= Conexion.conectar();
            CallableStatement sentencia=(CallableStatement) c.prepareCall(sql);
            sentencia.setString(1,producto.getNombre());
            sentencia.setFloat(2,producto.getPrecio() );
            sentencia.setFloat(3,producto.getPrecio_nuevo());
            sentencia.setInt(4,producto.getStock() );
            sentencia.setBoolean(5,producto.isNuevo() );
            sentencia.setBoolean(6,producto.isRecomendado() );
            sentencia.setString(7,producto.getDescripcion());
            sentencia.setBoolean(8,producto.isVisible() );
            sentencia.setInt(9,producto.getCodigo_marca() );
            sentencia.setInt(10,producto.getCodigo_categoria() );
            sentencia.setString(11,producto.getImg());
            
            sentencia.setString(12,prodeur.getMoneda() );
            sentencia.setFloat(13,prodeur.getPrecio());
            sentencia.setFloat(14,prodeur.getPrecionuevo());
            
            sentencia.setString(15,produsd.getMoneda() );
            sentencia.setFloat(16,produsd.getPrecio());
            sentencia.setFloat(17,produsd.getPrecionuevo());
            
            sentencia.setString(18,procup.getMoneda() );
            sentencia.setFloat(19,procup.getPrecio());
            sentencia.setFloat(20,procup.getPrecionuevo());
            
            return sentencia.executeUpdate() > 0;
           } catch (SQLException ex) {
            Logger.getLogger(CategoriaCad.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
}
