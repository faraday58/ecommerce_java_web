/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import JavaBeans.Producto;
import JavaBeans.ProductoMoneda;
import cad.ProductoCad;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Armando
 */
public class ControlProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlProducto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlProducto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        recibirDatos(request);
        
        String url =request.getAttribute("imagen").toString();
        
        String nombre= request.getAttribute("nombre").toString();
        float precio=Float.parseFloat(request.getAttribute("precio").toString());
        float precionuevo= Float.parseFloat(request.getAttribute("precionuevo").toString());
        
        float precioeur= Float.parseFloat(request.getAttribute("precioeur").toString());
        float precionuevoeur= Float.parseFloat(request.getAttribute("precionuevoeur").toString());
        
        float preciousd= Float.parseFloat(request.getAttribute("preciousd").toString());
        float precionuevousd= Float.parseFloat(request.getAttribute("precionuevousd").toString());
        
        float preciocup= Float.parseFloat(request.getAttribute("preciocup").toString());
        float precionuevocup= Float.parseFloat(request.getAttribute("precionuevocup").toString());
        
        int cantidad= Integer.parseInt(request.getAttribute("cantidad").toString());
        
        int marca = Integer.parseInt(request.getAttribute("marca").toString());
        int categoria =Integer.parseInt(request.getAttribute("categoria").toString());
        
        String descripcion = request.getAttribute("descripcion").toString();
        
        boolean nuevo =request.getAttribute("nuevo").toString().equalsIgnoreCase("ON");
        boolean recomendado = request.getAttribute("recomendado").toString().equalsIgnoreCase("ON");
        boolean visible = request.getAttribute("visible").toString().equalsIgnoreCase("ON");
        
        
        String accion = request.getAttribute("accion").toString();
        
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setPrecio_nuevo(precionuevo);
        producto.setCodigo_categoria(categoria);
        producto.setCodigo_marca(marca);
        producto.setDescripcion(descripcion);
        producto.setImg(url);
        producto.setNuevo(nuevo);
        producto.setRecomendado(recomendado);
        producto.setStock(cantidad);
        producto.setVisible(visible);
        
        ProductoMoneda prodmonedaeur = new ProductoMoneda();
        prodmonedaeur.setMoneda("EUR");
        prodmonedaeur.setPrecio(precioeur);
        prodmonedaeur.setPrecionuevo(precionuevoeur);
        
        ProductoMoneda prodmonedausd = new ProductoMoneda();
        prodmonedaeur.setMoneda("USD");
        prodmonedaeur.setPrecio(preciousd);
        prodmonedaeur.setPrecionuevo(precionuevousd);
        
        ProductoMoneda prodmonedacup = new ProductoMoneda();
        prodmonedaeur.setMoneda("CUP");
        prodmonedaeur.setPrecio(preciocup);
        prodmonedaeur.setPrecionuevo(precionuevocup);
        
        
        if(accion.equalsIgnoreCase("Registrar") ){
            if( ProductoCad.registrarProducto(producto, prodmonedaeur, prodmonedausd, prodmonedacup))
            {
                request.setAttribute("mensaje", "<p style='color:green'>  Producto registrado </p>");
                
            }else{
                request.setAttribute("mensaje", "<p style='color:red'>  Producto no registrado </p>");
            }                  
            
        }else{
            request.setAttribute("mensaje", "<p style='color:yellow'>  Acción desconocida </p>");
            
        }
        request.getRequestDispatcher("Admin").forward(request,response);
        //response.sendRedirect("imagenes/" +url);
        
        
    }
    
    private void recibirDatos(HttpServletRequest request) 
    {
        
        try {
            FileItemFactory fileitemFactory= new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileitemFactory);
            List items= servletFileUpload.parseRequest(request);
            String nombre;
            for( int i=0; i< items.size(); i++)
            {
                FileItem item= (FileItem)items.get(i);
                if(!item.isFormField())
                {
                    String ruta= request.getServletContext().getRealPath("/")+"imagenes/";
                    SimpleDateFormat sdf= new SimpleDateFormat("ddMyyyyhhmmss");
                    String fecha= sdf.format(new Date());
                    //Permite subir el mismo archivo pero genera un nombre aleatorio para evitar
                    //Excepciones
                    nombre= fecha + new Random().nextInt()+item.getName();
                    String nuevonombre= ruta+ nombre  ;
                                       
                    File folder = new File(ruta);
                    if(!folder.exists())
                    {
                        folder.mkdirs();
                    }
                    File imagen = new File(nuevonombre);
                    if(item.getContentType().contains("image"))
                    {
                        item.write(imagen);
                        request.setAttribute(item.getFieldName(), nombre );                        
                    }
                }else
                {
                    request.setAttribute(item.getFieldName(), item.getString());
                }
                
            }
            
        } catch (FileUploadException ex) {
            Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("subida", false);
             
        } catch (Exception ex) {
            request.setAttribute("subida", false);
            Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
