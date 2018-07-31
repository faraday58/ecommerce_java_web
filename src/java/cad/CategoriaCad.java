/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cad;

import JavaBeans.Categoria;
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
 * 
 * Clase de Acceso a Datos
 */
public class CategoriaCad {
       
    
    public static ArrayList<Categoria> listarcategorias(){
       
        try {
            String sql="{CALL sp_listarCategoriaSuperior() }";
            Connection c= Conexion.conectar();
            CallableStatement sentencia=(CallableStatement) c.prepareCall(sql);
            ResultSet resultado=  sentencia.executeQuery();
            ArrayList<Categoria> categorias = new ArrayList();
            while(resultado.next())
            {
                Categoria categoria= new Categoria();
                categoria.setCodigo(resultado.getInt("codigo"));
                categoria.setNombre(resultado.getString("nombre"));
                categorias.add(categoria);
            }
            return categorias;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCad.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }       
    
    }
    
    public static ArrayList<Categoria> listarTodoCategoria()
    {
        
        try {
            String  sql="{ CALL sp_listarTodoCategoria()  }";            
            Connection c = Conexion.conectar();
            CallableStatement sentencia = c.prepareCall(sql);
            ResultSet resultado = sentencia.executeQuery();
            ArrayList<Categoria> categorias = new ArrayList<>();
            while(resultado.next())
            {
                Categoria categoria= new Categoria();
                categoria.setCodigo(resultado.getInt("codigo"));
                categoria.setNombre(resultado.getString("nombre"));
                categorias.add(categoria);                
            }
            return categorias;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCad.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
    public static ArrayList<Categoria> listarSubcategorias(int subCategoria){
       
        try {
             String sql="{CALL sp_listarSubCategoria(?) }";
            Connection c= Conexion.conectar();
            CallableStatement sentencia=(CallableStatement) c.prepareCall(sql);
            sentencia.setInt(1, subCategoria);
            ResultSet resultado=  sentencia.executeQuery();
            ArrayList<Categoria> categorias = new ArrayList();
            while(resultado.next())
            {
                Categoria categoria= new Categoria();
                categoria.setCodigo(resultado.getInt("codigo"));
                categoria.setNombre(resultado.getString("nombre"));
                categorias.add(categoria);
            }
            return categorias;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCad.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public static boolean esSuperior(int cat)
    {
        try
        {
             String sql="{CALL sp_contarSubCategoria(?) }";
             Connection c = Conexion.conectar();
             CallableStatement sentencia = c.prepareCall(sql);
             sentencia.setInt(1, cat);
             ResultSet resultado = sentencia.executeQuery();
             resultado.next();
             return resultado.getInt("cantidad") > 0 ;             
            
        }
        catch(SQLException ex)
        {
              return false;
        }       
        
    }
    
    
    
    
}
    

