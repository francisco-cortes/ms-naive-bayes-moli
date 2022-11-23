package com.elektra.naive.bayes.servicios;

import com.baz.log.LogServicio;
import com.elektra.naive.bayes.dao.DaoFrecuencias;
import com.elektra.naive.bayes.dto.DtoPeticionNaiveBayes;
import com.elektra.naive.bayes.dto.DtoRespuestaNaiveBayes;
import com.elektra.naive.bayes.modelos.ModeloRespuestaFrecuencias;
import com.elektra.naive.bayes.modelos.Resultado;
import com.elektra.naive.bayes.util.CalculoNaiveBayes;
import com.elektra.naive.bayes.util.ComparaNaiveBayes;
import com.elektra.naive.bayes.util.Constantes;
import com.elektra.naive.bayes.util.UtilidadGenerarExcepcion;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * <b>ServicioNaiveBayes</b>
 * @descripcion: Lógica principal donde se calcula la probabilidad naive bayes
 * para determinar a que tipo pertenece el nombre dado
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 09/06/22
 */
@Singleton
public class ServicioNaiveBayes {
  /*
  inyección del dao para obtener frecuencias
   */
  @Inject
  private DaoFrecuencias daoFrecuencias;

  @Inject
  private CalculoNaiveBayes calculoNaiveBayes;

  @Inject
  private ComparaNaiveBayes comparaNaiveBayes;

  @Inject
  private UtilidadGenerarExcepcion utilidadGenerarExcepcion;
  /**
   * <b>naiveBayes</b>
   * @descripcion: Método principal
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @params: NaiveBayesRequest
   * @ultimaModificacion: 07/10/22
   */

  public DtoRespuestaNaiveBayes naiveBayes(DtoPeticionNaiveBayes peticion, String uid, String token){
    String nombreClaseMetodo = "ServicioNaiveBayes-naiveBayes";
    LogServicio log = new LogServicio();
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);
    Resultado resultado = new Resultado(uid, Constantes.CODIGO_ERROR_GENERAL,
      Constantes.MENSAJE_CODIGO_500);
    /*
    inicio de modelo
    */
    ModeloRespuestaFrecuencias frecuencias;
    DtoRespuestaNaiveBayes respuesta = new DtoRespuestaNaiveBayes();
    double probabilidadNombre = 0;
    double probabilidadApellido = 0;
    try {
      /*
      obtiene las frecuencias
      */
      frecuencias = daoFrecuencias.obtenerFrecuencias(peticion.getNombre(), log, uid, token);

      log.registrarMensaje(nombreClaseMetodo, "las frecuencias para : " + peticion.getNombre() + " Son: \n" +
        "Frecuencia nombre: " + frecuencias.getFrecuenciaNombre() + " de un total de : " +
        frecuencias.getTotalRegistrosNombre() + " nombres \n" +
        "Frecuencia apellidos: " + frecuencias.getFrecuenciaApellidos() + " de un total de : " +
        frecuencias.getTotalRegistrosApellidos() + " apellidos \n");

      /*
      cálculo de la probabilidad de nombre
      */
      probabilidadNombre = calculoNaiveBayes.calcularNaiveBayes(frecuencias.getFrecuenciaNombre().doubleValue(),
        frecuencias.getTotalRegistrosNombre().doubleValue());
      log.registrarMensaje(nombreClaseMetodo, "Probabilidad de Nombre : " + probabilidadNombre);

      /*
      cálculo de la probabilidad de apellido
      */
      probabilidadApellido = calculoNaiveBayes.calcularNaiveBayes(frecuencias.getFrecuenciaApellidos().doubleValue(),
        frecuencias.getTotalRegistrosApellidos().doubleValue());
      log.registrarMensaje(nombreClaseMetodo, "Probabilidad de Apellido : " + probabilidadApellido);

      respuesta = comparaNaiveBayes.compararResultadosNaiveBayes(peticion.getTipoNombre().toUpperCase().trim(),
        probabilidadNombre, probabilidadApellido);
    }
    catch (Exception excepcion) {
      log.registrarExcepcion(excepcion,"Error de exepcion");
      log.registrarMensaje(nombreClaseMetodo, excepcion.getMessage());
      utilidadGenerarExcepcion.generarExcepcion(Constantes.CODIGO_HTTP_500, Constantes.CODIGO_HTTP_500,
        Constantes.MENSAJE_CODIGO_500 + " " + excepcion.getMessage() ,uid);
    }
    finally {
      log.terminarTiempoMetodo(nombreClaseMetodo);
    }
    return respuesta;
  }
}
