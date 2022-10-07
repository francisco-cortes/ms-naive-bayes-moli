package com.baz.moli.daos;

import com.baz.moli.models.FrecuenciasResponseModel;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Singleton
public class FrecuenciasDao {

  @Inject
  private ConectorHttpDao conectorHttpDao;

  public FrecuenciasResponseModel obtenerFrecuencias(String nombre) throws IOException {
    FrecuenciasResponseModel frecuenciasResponseModel = new FrecuenciasResponseModel();
    BufferedReader br;
    /*
    conector https
     */
    HttpURLConnection connection;

    connection = conectorHttpDao.crearConexion("GET","http://localhost:8083/remesas/frecuencias/obtener-frecuencias?nombre=jose");
    connection.setRequestProperty("Accept","*/*");
    connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");

    if(connection.getResponseCode() > 299){
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

    connection.disconnect();

    int i = sb.indexOf("{");
    String responseString = sb.substring(i);
    System.out.println(responseString);
    JSONObject jsonResponse = new JSONObject(responseString.trim());
    frecuenciasResponseModel.setFrecuenciaNombre(jsonResponse.getBigDecimal("frecuenciaNombre"));
    frecuenciasResponseModel.setTotalRegistrosNombre(jsonResponse.getBigDecimal("totalRegistrosNombre"));
    frecuenciasResponseModel.setFrecuenciaApellidos(jsonResponse.getBigDecimal("frecuenciaApellidos"));
    frecuenciasResponseModel.setTotalRegistrosApellidos(jsonResponse.getBigDecimal("totalRegistrosApellidos"));
    frecuenciasResponseModel.setMensaje(jsonResponse.getString("mensaje"));
    frecuenciasResponseModel.setCodigoInterno(jsonResponse.getString("codigoInterno"));

    return frecuenciasResponseModel;
  }
}
