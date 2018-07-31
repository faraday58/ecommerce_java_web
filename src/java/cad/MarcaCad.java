/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cad;

import JavaBeans.Marca;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Armando
 */
public class MarcaCad {
    public static ArrayList<Marca> ListarMarcas()
    {
        try {
        String sql="{CALL sp_listarTodoMarca() }";
        Connection c= Conexion.conectar();
        CallableStatement sentencia;        
        sentencia = c.prepareCall(sql);
        ArrayList<Marca> marcas = new ArrayList<>();
        ResultSet resultado = sentencia.executeQuery();
            while(resultado.next())
            {
                Marca marca= new Marca();
                marca.setCodigo(resultado.getInt("codigo"));
                marca.setNombre(resultado.getString("nombre"));
                marcas.add(marca);                
            }
            return marcas;
    
        } catch (SQLException ex) {
            Logger.getLogger(MarcaCad.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
        
        
    }
    
    
}
