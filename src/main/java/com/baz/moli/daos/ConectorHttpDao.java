package com.baz.moli.daos;

import com.baz.log.LogServicio;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Singleton
public class ConectorHttpDao {
  /**
   * crearConexion
   * Descrpcion: crea un objeto Https con el contexto de conexion para utlizar en los DAOS
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * params: metodo(String), link(String), log(LogServicio)
   * returns: HttpsUrlConnection
   **/
  public HttpURLConnection crearConexion(String metodo, String link)
    throws IOException{
    /*
    inicia objetos de conexion
     */
    HttpURLConnection connection = null;
    /*
    convierte la url string en un objeto URL
     */
    URL url = new URL(link);
    /*
    abre la conexion con el conexto y la URL
     */
    connection = (HttpURLConnection) url.openConnection();
    /*
    determina el tiempo maximo de espera en 32 segundos
     */
    connection.setConnectTimeout(3200);
    /*
    settea el metodo de peticion
     */
    connection.setRequestMethod(metodo);

    return connection;
  }
}
