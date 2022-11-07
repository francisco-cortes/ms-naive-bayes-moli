package com.elektra.naive.bayes.dao;

import com.elektra.naive.bayes.util.Constantes;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Singleton
public class DaoFabricaConexionHttp {
  /**
   * <b>crearConexion</b>
   * @descrpcion: crea un objeto Https con el contexto de conexión para utilizar en los DAOS
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @params: metodo(String), link(String), log(LogServicio)
   * @returns: HttpsUrlConnection
   **/
  public HttpURLConnection crearConexion(String metodo, String link)
    throws IOException{
    /*
    inicia objetos de conexión
     */
    HttpURLConnection connection = null;
    /*
    convierte la url string en un objeto URL
     */
    URL url = new URL(link);
    /*
    abre la conexión con el contexto y la URL
     */
    connection = (HttpURLConnection) url.openConnection();
    /*
    determina el tiempo máximo de espera en 32 segundos
     */
    connection.setConnectTimeout(Constantes.TIME_OUT);
    /*
    settea el método de petición
     */
    connection.setRequestMethod(metodo);

    return connection;
  }
}
