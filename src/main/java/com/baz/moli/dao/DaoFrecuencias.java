package com.baz.moli.dao;

import com.baz.moli.modelos.ModeloRespuestaFrecuencias;
import com.baz.moli.propiedades.Propiedades;
import com.baz.moli.util.Constantes;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
/**
        * <b>DaoFrecuencias</b>
        * @descripcion: dao para ms-frecuenciasp-nombre
        * @autor: Francisco Javier Cortes Torres, Desarrollador
        * @ultimaModificacion: 07/10/22
       */
@Singleton
public class DaoFrecuencias {

  @Inject
  private DaoFabricaConexionHttp daoFabricaConexionHttp;

  @Inject
  private Propiedades propiedades;

  public ModeloRespuestaFrecuencias obtenerFrecuencias(String nombre) throws IOException {
    String param = nombre.replace(" ", "+");
    /*
    construcción sencilla de la cadena para la url
     */
    String link = propiedades.conexionesdb().get(Constantes.C3REMESASC).urlbase() +
      propiedades.conexionesdb().get(Constantes.C3REMESASC).endpoint() + Constantes.HANTEN +
      propiedades.conexionesdb().get(Constantes.C3REMESASC).name() + Constantes.IGUAL + param;
    /*
    objetos modelo
     */
    ModeloRespuestaFrecuencias modeloRespuestaFrecuencias = new ModeloRespuestaFrecuencias();
    BufferedReader br;
    /*
    conector https
     */
    HttpURLConnection connection;
    /*
    conexión de método https
     */
    connection = daoFabricaConexionHttp.crearConexion(Constantes.GET_METHOD,link);
    /*
    headers necesarios para el consumo
     */
    connection.setRequestProperty("Accept","*/*");
    connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");

    if(connection.getResponseCode() > Constantes.OK_HTTP_LIMIT){
      br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
    }
    /*
    si no obtiene el objeto inputStream para respuesta positivas
     */
    else {
      br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    StringBuilder sb = new StringBuilder();

    String line;
    while ((line = br.readLine()) != null) {
      sb.append(line);
    }
    /*
    cierra el buferedReader
     */
    br.close();
    /*
    cerrar conexión https
     */
    connection.disconnect();
    /*
    parseo de las respuestas a json
     */
    int i = sb.indexOf("{");
    String responseString = sb.substring(i);
    JSONObject jsonResponse = new JSONObject(responseString.trim());
    /*
    obtención de respuestas json a modelo modeloRespuestaFrecuencias
     */
    modeloRespuestaFrecuencias.setFrecuenciaNombre(jsonResponse.getBigDecimal("frecuenciaNombre"));
    modeloRespuestaFrecuencias.setTotalRegistrosNombre(jsonResponse.getBigDecimal("totalRegistrosNombre"));
    modeloRespuestaFrecuencias.setFrecuenciaApellidos(jsonResponse.getBigDecimal("frecuenciaApellidos"));
    modeloRespuestaFrecuencias.setTotalRegistrosApellidos(jsonResponse.getBigDecimal("totalRegistrosApellidos"));
    modeloRespuestaFrecuencias.setMensaje(jsonResponse.getString("mensaje"));
    modeloRespuestaFrecuencias.setCodigoInterno(jsonResponse.getString("codigoInterno"));

    return modeloRespuestaFrecuencias;
  }
}
