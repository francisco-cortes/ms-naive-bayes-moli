package com.elektra.naive.bayes.dao;

import com.baz.log.LogServicio;
import com.baz.servicios.CifradorAes;
import com.elektra.naive.bayes.modelos.ModeloRespuestaFrecuencias;
import com.elektra.naive.bayes.propiedades.Propiedades;
import com.elektra.naive.bayes.util.Constantes;
import com.elektra.naive.bayes.util.LectorRespuestaHttp;
import org.json.JSONObject;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <b>DaoFrecuencias</b>
 * @descripcion: dao para ms-frecuencia-nombre
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 07/10/22
 */
@Singleton
public class DaoFrecuencias {

  @Inject
  private DaoFabricaConexionHttp daoFabricaConexionHttp;

  @Inject
  private Propiedades propiedades;

  @Inject
  private LectorRespuestaHttp lectorRespuestaHttp;

  private CifradorAes cifradorAes;

  public ModeloRespuestaFrecuencias obtenerFrecuencias(String nombre, LogServicio log) throws IOException,
    InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
    NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    String nombreClaseMetodo = "DaoFrecuencias-obtenerFrecuencias";
    log.iniciarTiempoMetodo(nombreClaseMetodo,Constantes.ES_NOMBRE);
    cifradorAes = new CifradorAes(false);
    String param = nombre.replace(" ", "+");
    /*
    construcción sencilla de la cadena para la url
     */
    String link = cifradorAes.desencriptarDato(propiedades.conexionesdb().get(Constantes.C3REMESASC).urlbase()) +
      cifradorAes.desencriptarDato( propiedades.conexionesdb().get(Constantes.C3REMESASC).endpoint() )+
      Constantes.HANTEN +
      cifradorAes.desencriptarDato(propiedades.conexionesdb().get(Constantes.C3REMESASC).name()) +
      Constantes.IGUAL +
      param;
    /*
    objetos modelo
     */
    ModeloRespuestaFrecuencias modeloRespuestaFrecuencias = new ModeloRespuestaFrecuencias();
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
      BufferedReader respuestaBuffered = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      String respuesta = lectorRespuestaHttp.leerRespuestaHttp(respuestaBuffered);
      log.registrarMensaje(nombreClaseMetodo, "Error al obtener frecuencias: " +
        respuesta);
      /*
      cierra el buferedReader
      */
      respuestaBuffered.close();
      modeloRespuestaFrecuencias.setFrecuenciaNombre(Constantes.BIG_ZERO_BY_DEFAULT);
      modeloRespuestaFrecuencias.setTotalRegistrosNombre(Constantes.BIG_ZERO_BY_DEFAULT);
      modeloRespuestaFrecuencias.setFrecuenciaApellidos(Constantes.BIG_ZERO_BY_DEFAULT);
      modeloRespuestaFrecuencias.setTotalRegistrosApellidos(Constantes.BIG_ZERO_BY_DEFAULT);
      modeloRespuestaFrecuencias.setMensaje(Constantes.CODIGO_SIN_FRECUENCIAS + " " + Constantes.MENSAJE_ERROR_CLASIFICACION);
    }
    /*
    si no obtiene el objeto inputStream para respuesta positivas
     */
    else {
      BufferedReader respuestaBuffered = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String respuesta = lectorRespuestaHttp.leerRespuestaHttp(respuestaBuffered);
      /*
      cierra el buferedReader
      */
      respuestaBuffered.close();
      /*
      parseo de las respuestas a json
      */
      JSONObject jsonResponse = new JSONObject(respuesta.trim());
      /*
      obtención de respuestas json a modelo modeloRespuestaFrecuencias
      */
      modeloRespuestaFrecuencias.setFrecuenciaNombre(jsonResponse.getBigDecimal("frecuenciaNombre"));
      modeloRespuestaFrecuencias.setTotalRegistrosNombre(jsonResponse.getBigDecimal("totalRegistrosNombre"));
      modeloRespuestaFrecuencias.setFrecuenciaApellidos(jsonResponse.getBigDecimal("frecuenciaApellidos"));
      modeloRespuestaFrecuencias.setTotalRegistrosApellidos(jsonResponse.getBigDecimal("totalRegistrosApellidos"));
      modeloRespuestaFrecuencias.setMensaje(jsonResponse.getString("mensaje"));
    }
    connection.disconnect();
    log.terminarTiempoMetodo(nombreClaseMetodo);
    return modeloRespuestaFrecuencias;
  }
}
