package com.baz.moli.daos;

import com.baz.moli.models.FrecuenciasResponseModel;
import com.baz.moli.utilis.Constantes;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
/**
        * <b>FrecuenciasDao</b>
        * @descripcion: dao para ms-frecuenciasp-nombre
        * @autor: Francisco Javier Cortes Torres, Desarrollador
        * @ultimaModificacion: 07/10/22
       */
@Singleton
public class FrecuenciasDao {

  @Inject
  private ConectorHttpDao conectorHttpDao;

  public FrecuenciasResponseModel obtenerFrecuencias(String nombre) throws IOException {
    /*
    construccion sencilla de la cadena para la url
     */
    String link = "";
    /*
    obejtos modelo
     */
    FrecuenciasResponseModel frecuenciasResponseModel = new FrecuenciasResponseModel();
    BufferedReader br;
    /*
    conector https
     */
    HttpURLConnection connection;
    /*
    conexion de metodo https
     */
    connection = conectorHttpDao.crearConexion(Constantes.GET_METHOD,"http://localhost:8083/remesas/frecuencias/obtener-frecuencias?nombre=jose");
    /*
    headers nesesarios para el consumo
     */
    connection.setRequestProperty("Accept","*/*");
    connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
    /*

     */
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
    cerrar conexion https
     */
    connection.disconnect();

    /*
    parseo de las respuesta a json
     */
    int i = sb.indexOf("{");
    String responseString = sb.substring(i);
    System.out.println(responseString);
    JSONObject jsonResponse = new JSONObject(responseString.trim());
    /*
    obtencion de respuestas json a modelo frecuenciasResponseModel
     */
    frecuenciasResponseModel.setFrecuenciaNombre(jsonResponse.getBigDecimal("frecuenciaNombre"));
    frecuenciasResponseModel.setTotalRegistrosNombre(jsonResponse.getBigDecimal("totalRegistrosNombre"));
    frecuenciasResponseModel.setFrecuenciaApellidos(jsonResponse.getBigDecimal("frecuenciaApellidos"));
    frecuenciasResponseModel.setTotalRegistrosApellidos(jsonResponse.getBigDecimal("totalRegistrosApellidos"));
    frecuenciasResponseModel.setMensaje(jsonResponse.getString("mensaje"));
    frecuenciasResponseModel.setCodigoInterno(jsonResponse.getString("codigoInterno"));

    return frecuenciasResponseModel;
  }
}
