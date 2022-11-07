package com.elektra.naive.bayes.dto;

import com.baz.anotaciones.DesCifrarValorAes;
import com.baz.anotaciones.Validacion;
import com.elektra.naive.bayes.util.Constantes;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>NaiveBayesResponse</b>
 * @descripcion: clase modelo para la respuesta de salida en /modulo-naive-bayes/calcula-nb
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 09/06/22
 */
@Data
@JsonPropertyOrder({"nombre","tipoNombre"})
public class DtoPeticionNaiveBayes {
  @Validacion(
    tipoDato = Validacion.tiposDato.VARCHAR,
    requerido = Constantes.ES_REQUERIDO,
    caracteresValidos = Validacion.LETRAS,
    longitudMin = Constantes.LONGITUD_MIN_ENTRADA,
    longitudMax = Constantes.LONGITUD_MAX_ENTRADA
  )
  @DesCifrarValorAes
  @Schema(
    example = "LEONARDO",
    required = Constantes.ES_REQUERIDO,
    minLength = Constantes.LONGITUD_MIN_ENTRADA,
    maxLength = Constantes.LONGITUD_MAX_ENTRADA,
    description = "Nombre o apellido de una persona"
  )
  private String nombre;

  @Validacion(
    tipoDato = Validacion.tiposDato.VARCHAR,
    requerido = Constantes.ES_REQUERIDO,
    caracteresValidos = Validacion.LETRAS,
    longitudMin = Constantes.LONGITUD_MIN_ENTRADA,
    longitudMax = Constantes.LONGITUD_MAX_ENTRADA
  )
  @Schema(
    example = "NOMBRE",
    required = Constantes.ES_REQUERIDO,
    minLength = Constantes.LONGITUD_MIN_ENTRADA,
    maxLength = Constantes.LONGITUD_MAX_ENTRADA,
    description = "Tipo de nombre donde se clasifico"
  )
  private String tipoNombre;
}
