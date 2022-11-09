package com.elektra.naive.bayes.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>NaiveBayesResponse</b>
 * @descripcion: clase modelo para la respuesta de salida en /modulo-naive-bayes/calcula-nb
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 09/06/22
 */
@Data
@Getter
@Setter
@JsonPropertyOrder({"nombre","tipoNombre","mensaje","valor"})
public class DtoRespuestaNaiveBayes {
  @Schema(
    example = "NOMBRE",
    description = "Classification del nombre dependiendo de Naive Bayes"
  )
  private String tipoNombre;
  @Schema(
    example = "CLASIFICACION CORRECTA",
    description = "Mensaje de operación"
  )
  private String mensaje;
  @Schema(
    example = "1",
    description = "valor correspondiente al cambio o error"
  )
  private int valor;

  @Schema(
    example = "1",
    description = "valor de la probabilidad de naive bayes para nombre"
  )
  private double probabilidadNbNombre;

  @Schema(
    example = "1",
    description = "valor de la probabilidad de naive bayes para apellido"
  )
  private double probabilidadNbApellido;

}
