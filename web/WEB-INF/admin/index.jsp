<%-- 
    Document   : index
    Created on : fecha
    Author     : Jorge Armando Rodríguez Vera
--%>

<%@page import="JavaBeans.Marca"%>
<%@page import="cad.MarcaCad"%>
<%@page import="cad.CategoriaCad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Inicio | Nuevo</title>
    <%@include file="../../WEB-INF/css.jsp" %>
</head><!--/head-->

<body>
    <!--header  -->
    
    <%@include file="../../WEB-INF/header.jsp" %>
	
    
        
        <hr/><!--confianza-->
    
        <hr/>
	
        <section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
    
				</div>
				
				<div class="col-sm-10 clearfix">
                                    
                                    <h3>Gestionar Producto</h3>
                                    <form action="ControlProducto" enctype="multipart/form-data"  method="post">
                                        <div class="form-one">
                                           Nombre:
                                           <br/>
                                           <input type="text" name="nombre" placeholder="Nombre Producto" value="" required />
                                           <hr/>                                               
                                           Precio (MXN):
                                           <br/>
                                           <input type="number" name="precio" placeholder="Precio" value="0" min="0" />
                                           <br/>
                                           Precio promo (MXN):
                                           <br/>
                                           <input type="number" name="precionuevo" placeholder="Nuevo precio" value="0" min="0" />
                                           <hr/>
                                           Precio (EUR):
                                           <br/>
                                           <input type="number" name="precioeur" placeholder="Precio" value="0" min="0" />
                                           <br/>
                                           Precio promo (EUR):
                                           <br/>
                                           <input type="number" name="precionuevoeur" placeholder="Nuevo precio" value="0" min="0" />
                                           <hr/>
                                           Precio (USD):
                                           <br/>
                                           <input type="number" name="preciousd" placeholder="Precio" value="0" min="0" />
                                           <br/>
                                           Precio promo (USD):
                                           <br/>
                                           <input type="number" name="precionuevousd" placeholder="Nuevo precio" value="0" min="0" />
                                           <hr/>
                                           Precio (CUP):
                                           <br/>
                                           <input type="number" name="preciocup" placeholder="Precio" value="0" min="0" />
                                           <br/>
                                           Precio promo (CUP):
                                           <br/>
                                           <input type="number" name="precionuevocup" placeholder="Nuevo precio" value="0" min="0" />
                                           <hr/>
                                           Stock:
                                           <br/>
                                           <input type="number" name="cantidad" placeholder="Cantidad" value="1" min="1" />
                                           <br/>
                                           Marca: <select name="marca">
                                                <option>Selecciona una Marca</option>
                                                <% for(Marca m:MarcaCad.ListarMarcas()){ %>
                                                <option value="<%= m.getCodigo()  %>"> <%= m.getNombre() %></option>
                                                <%} %>
                                            </select>
                                            Categoria: <select name="categoria">
                                                <option>Seleccionar categoría</option>
                                                <% for(Categoria codigo:CategoriaCad.listarTodoCategoria()) { %>
                                                <option value="<%= codigo.getCodigo()  %>"> <%= codigo.getNombre() %></option>
                                                <%} %>
                                                
                                            </select>
                                            Descripción: <textarea name="descripcion" rows="4" cols="20" placeholder="Descripción" required>
                                            </textarea>
                                            Nuevo?:<input type="checkbox" name="nuevo" value="ON" checked="checked" />
                                            Recomendado?:<input type="checkbox" name="recomendado" value="ON" />
                                            Visible?: <input type="checkbox" name="visible" checked="checked" value="ON" />
                                            <hr/>
                                            Seleccionar imagen a cargar:<input type="file" name="imagen" value="Seleccionar una imagen" required/>
                                            <hr/>
                                            <input class="btn btn-success" type="submit" value="Registrar" name="accion" />
                                            <input class="btn btn-default"  type="submit" value="Consular" name="accion" />
                                            <input class="btn btn-warning" type="submit" value="Actualizar" name="accion" />
                                            <input class="btn btn-danger" type="submit" value="Borrar" name="accion" />
                                            
                                        </div>
                                    </form>
				</div>
			</div>
		</div>
	</section>
	
	<%@include  file="../../WEB-INF/footer.jsp" %><!--/Footer-->
	

        <%@include  file="../../WEB-INF/js.jsp" %><!--JS-->
    
</body>
</html>
