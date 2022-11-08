package com.elektra.naive.bayes.util;

import com.elektra.naive.bayes.dto.DtoRespuestaNaiveBayes;

import javax.inject.Singleton;

@Singleton
public class ComparaNaiveBayes {
  public DtoRespuestaNaiveBayes compararResultadosNaiveBayes(String nomAp, String tipoNombre, double probNbNom,
                                                             double probNbAp){

    DtoRespuestaNaiveBayes respuesta = new DtoRespuestaNaiveBayes();

    if(probNbNom > probNbAp){
      respuesta.setTipoNombre(Constantes.ES_NOMBRE);

      if(tipoNombre.equals(Constantes.ES_NOMBRE)){
        respuesta.setMensaje(Constantes.MENSAJE_CLASIFICACION_CORRECTA);
        respuesta.setValor(Constantes.VALOR_CLASIFICACION_CORRECTA);
      }
      else if (tipoNombre.equals(Constantes.ES_APELLIDO)){
        respuesta.setMensaje( Constantes.PROBABLE_NOMBRE);
        respuesta.setValor( Constantes.VALOR_CLASIFICACION_INCORRECTA);
      }
      else {
        respuesta.setMensaje( Constantes.MENSAJE_ERROR_CLASIFICACION);
        respuesta.setValor( Constantes.VALOR_EXEPCION);
      }
    }
    else if (probNbAp > probNbNom){
      respuesta.setTipoNombre(Constantes.ES_APELLIDO);

      if(tipoNombre.equals(Constantes.ES_APELLIDO)){
        respuesta.setMensaje( Constantes.MENSAJE_CLASIFICACION_CORRECTA);
        respuesta.setValor(Constantes.VALOR_CLASIFICACION_CORRECTA);
      }
      else if (tipoNombre.equals(Constantes.ES_NOMBRE)){
        respuesta.setMensaje(Constantes.PROBABLE_APELLIDO);
        respuesta.setValor(Constantes.VALOR_CLASIFICACION_INCORRECTA);
      }
      else{
        respuesta.setMensaje( Constantes.MENSAJE_ERROR_CLASIFICACION);
        respuesta.setValor( Constantes.VALOR_EXEPCION);
      }
    }
    else {
      respuesta.setMensaje(tipoNombre);
      respuesta.setMensaje( Constantes.MENSAJE_ERROR_CLASIFICACION);//no se encuentra
      respuesta.setValor( Constantes.VALOR_EXEPCION);
    }

    return respuesta;
  }
}
