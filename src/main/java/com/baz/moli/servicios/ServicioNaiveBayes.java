package com.baz.moli.servicios;

import com.baz.log.LogServicio;
import com.baz.moli.dao.FrecuenciasDao;
import com.baz.moli.dto.DtoPeticionNaiveBayes;
import com.baz.moli.dto.DtoRespuestaNaiveBayes;
import com.baz.moli.exception.ErrorInternoException;
import com.baz.moli.modelos.ModeloRespuestaFrecuencias;
import com.baz.moli.util.Constantes;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * <b>ServicioNaiveBayes</b>
 * @descripcion: Lógica principal donde se calcula la probabilidad naive bayes
 * para determinar a que tipo pertenece el nombre dado
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 09/06/22
 */
@Singleton
public class ServicioNaiveBayes {

  private static final String NOMBRE_CLASE = "CalculoNaiveBatesService";

  /*
  inyección del dao para obtener frecuencias
   */
  @Inject
  private FrecuenciasDao frecuenciasDao;

  /**
   * <b>naiveBayes</b>
   * @descripcion: Metodo principal
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @params: NaiveBayesRequest
   * @ultimaModificacion: 07/10/22
   */

  public DtoRespuestaNaiveBayes naiveBayes(DtoPeticionNaiveBayes request, String uid){
    LogServicio log = new LogServicio();
    log.iniciarTiempoMetodo(NOMBRE_CLASE,Constantes.NOMBRE_MS);
    /*
    inicio de modelo
    */
    ModeloRespuestaFrecuencias frecuencias;

    try {
      /*
      obtiene las frecuencias
       */
      frecuencias = buscarFrecuencias(request.getNombre().toUpperCase().trim());

      log.registrarMensaje(NOMBRE_CLASE, "las frecuencias para : " + request.getNombre() + " Son: \n" +
        "Frecuencia nombre: " + frecuencias.getFrecuenciaNombre() + " de un total de : " +
        frecuencias.getTotalRegistrosNombre() + " nombres \n" +
        "Frecuencia apellidos: " + frecuencias.getFrecuenciaApellidos() + " de un total de : " +
        frecuencias.getTotalRegistrosApellidos() + " apellidos \n");
    }
    catch (Exception excepcion) {
      log.registrarExcepcion(excepcion,"Error de exepcion");
      log.registrarMensaje(NOMBRE_CLASE, excepcion.getMessage());
      throw new ErrorInternoException(request.getNombre(), request.getTipoNombre(), "Error de Excepcion: "
        + excepcion.getMessage(), Constantes.VALOR_EXEPCION,Constantes.ZERO_BY_DEFAULT,Constantes.ZERO_BY_DEFAULT);
    }

    /*
    cálculo de la probabilidad de nombre
     */
    double probabilidadNombre = calculoNaiveBayes(frecuencias.getFrecuenciaNombre().doubleValue(),
      frecuencias.getTotalRegistrosNombre().doubleValue());
    log.registrarMensaje(NOMBRE_CLASE, "Probabilidad de Nombre : " + probabilidadNombre);

    /*
    cálculo de la probabilidad de apellido
     */
    double probabilidadApellido = calculoNaiveBayes(frecuencias.getFrecuenciaApellidos().doubleValue(),
      frecuencias.getTotalRegistrosApellidos().doubleValue());
    log.registrarMensaje(NOMBRE_CLASE, "Probabilidad de Apellido : " + probabilidadApellido);

    /*
    creación del objeto respuesta
     */
    log.terminarTiempoMetodo(NOMBRE_CLASE);
    log.obtenerTiempoTotal(NOMBRE_CLASE);
    return comparaNaiveBayes(request.getNombre(), request.getTipoNombre().toUpperCase().trim()
      , probabilidadNombre, probabilidadApellido, log);

  }

  /**
   * <b>buscarFrecuencias</b>
   * @descripcion: obtiene las frecuencias de un nombre a través de frecuencias dao
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @params: String
   * @ultimaModificacion: 07/10/22
   */

  private ModeloRespuestaFrecuencias buscarFrecuencias(String nombre) throws IOException {
    return frecuenciasDao.obtenerFrecuencias(nombre);
  }

  /**
   * <b>calculoNaiveBayes</b>
   * @descripcion: calculo de nb si la frecuencia es 0 la probabilidad sera de 0
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 09/06/22
   */

  private double calculoNaiveBayes (Double frec, Double total){
    double naiveBayes;
    if(frec > 0 && total > 0) {
      naiveBayes = (frec/total) * Constantes.CONSTANTE_NB;
    }
    else {
      naiveBayes = 0;
    }
    return naiveBayes;
  }

  /**
   * <b>comparaNaiveBayes</b>
   * @descripcion: realiza la comparacion de probabilidades y asigna tipoCadena y mensaje salida
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 09/06/22
   */

  private DtoRespuestaNaiveBayes comparaNaiveBayes(String nomAp, String tipoNombre,
                                                   double probNbNom, double probNbAp, LogServicio log){
    String nuevoTipoNombre;
    String mensaje;
    int valor;

    if(probNbNom > probNbAp){
      log.registrarMensaje(NOMBRE_CLASE, "Probabilidad de nombre mas alta");
      nuevoTipoNombre = Constantes.ES_NOMBRE ;

      if( tipoNombre.equals(nuevoTipoNombre) ){
        mensaje = Constantes.MENSAJE_CLASIFICACION_CORRECTA;
        valor = Constantes.VALOR_CLASIFICACION_CORRECTA;
      }
      else if (tipoNombre.equals(Constantes.ES_APELLIDO)){
        mensaje = Constantes.PROBABLE_NOMBRE;
        valor = Constantes.VALOR_CLASIFICACION_INCORRECTA;
      }
      else {
        mensaje = Constantes.MENSAJE_ERROR_CLASIFICACION;
        valor = Constantes.VALOR_EXEPCION;
      }

    }
    else if (probNbAp > probNbNom){
      log.registrarMensaje(NOMBRE_CLASE, "Probabilidad de apellido mas alta");
      nuevoTipoNombre = Constantes.ES_APELLIDO;

      if(tipoNombre.equals(nuevoTipoNombre)){
        mensaje = Constantes.MENSAJE_CLASIFICACION_CORRECTA;
        valor = Constantes.VALOR_CLASIFICACION_CORRECTA;
      }
      else if (tipoNombre.equals(Constantes.ES_NOMBRE)){
        mensaje = Constantes.PROBABLE_APELLIDO;
        valor = Constantes.VALOR_CLASIFICACION_INCORRECTA;
      }
      else{
        mensaje = Constantes.MENSAJE_ERROR_CLASIFICACION;//etiqueta erroneo
        valor = Constantes.VALOR_EXEPCION;
      }
    }
    else {
      nuevoTipoNombre = tipoNombre;
      mensaje = Constantes.MENSAJE_ERROR_CLASIFICACION;//no se encuentra
      valor = Constantes.VALOR_EXEPCION;
    }

    return new DtoRespuestaNaiveBayes(nomAp,nuevoTipoNombre,
      mensaje,valor,probNbNom,probNbAp);
  }

}