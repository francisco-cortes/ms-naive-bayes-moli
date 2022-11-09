package com.elektra.naive.bayes.util;

import com.elektra.naive.bayes.dto.DtoRespuestaNaiveBayes;

import javax.inject.Singleton;

@Singleton
public class ComparaNaiveBayes {
  public DtoRespuestaNaiveBayes compararResultadosNaiveBayes(String tipoNombre, double probabilidadNombre,
                                                             double probabilidadApellidos){

    DtoRespuestaNaiveBayes respuesta = new DtoRespuestaNaiveBayes();

    if(probabilidadNombre > probabilidadApellidos){
      respuesta.setTipoNombre(Constantes.ES_NOMBRE);

      if(tipoNombre.equals(Constantes.ES_NOMBRE)){
        respuesta.setMensaje(Constantes.CODIGO_CLASIFICACION_CORRECTA + " " +
          Constantes.MENSAJE_CLASIFICACION_CORRECTA);
        respuesta.setValor(Constantes.VALOR_CLASIFICACION_CORRECTA);
      }
      else if (tipoNombre.equals(Constantes.ES_APELLIDO)){
        respuesta.setMensaje(Constantes.CODIGO_PROBABLE_NOMBRE + " " +
          Constantes.PROBABLE_NOMBRE);
        respuesta.setValor( Constantes.VALOR_CLASIFICACION_INCORRECTA);
      }
      else {
        respuesta.setMensaje(Constantes.CODIGO_TIPO_INCORRECTO + " " +
          Constantes.MENSAJE_ERROR_CLASIFICACION);
        respuesta.setValor( Constantes.VALOR_EXEPCION);
      }
    }
    else if (probabilidadApellidos > probabilidadNombre){
      respuesta.setTipoNombre(Constantes.ES_APELLIDO);

      if(tipoNombre.equals(Constantes.ES_APELLIDO)){
        respuesta.setMensaje(Constantes.CODIGO_CLASIFICACION_CORRECTA + " " +
          Constantes.MENSAJE_CLASIFICACION_CORRECTA);
        respuesta.setValor(Constantes.VALOR_CLASIFICACION_CORRECTA);
      }
      else if (tipoNombre.equals(Constantes.ES_NOMBRE)){
        respuesta.setMensaje(Constantes.CODIGO_PROBABLE_APELLIDO + " " +
          Constantes.PROBABLE_APELLIDO);
        respuesta.setValor(Constantes.VALOR_CLASIFICACION_INCORRECTA);
      }
      else{
        respuesta.setMensaje(Constantes.CODIGO_TIPO_INCORRECTO + " " +
          Constantes.MENSAJE_ERROR_CLASIFICACION);
        respuesta.setValor( Constantes.VALOR_EXEPCION);
      }
    }
    else {
      respuesta.setTipoNombre(tipoNombre);
      respuesta.setMensaje(Constantes.CODIGO_SIN_FRECUENCIAS + " " +
        Constantes.MENSAJE_ERROR_CLASIFICACION);//no se encuentra
      respuesta.setValor( Constantes.VALOR_EXEPCION);
    }

    return respuesta;
  }
}
