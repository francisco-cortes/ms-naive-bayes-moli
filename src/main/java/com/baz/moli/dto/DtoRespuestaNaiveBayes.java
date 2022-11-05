package com.baz.moli.dto;

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
@JsonPropertyOrder({"nombre","tipoNombre","mensaje","valor"})
public class DtoRespuestaNaiveBayes {
  @Schema(
    example = "LEONARDO",
    description = "Nombre o apellido de una persona"
  )
  private String nombre;
  @Schema(
    example = "NOMBRE",
    description = "Clasificacion del nombre dependiendo de Naive Bayes"
  )
  private String tipoNombre;
  @Schema(
    example = "CLASIFICACION CORRECTA",
    description = "Mensaje de expecion, o de operacion"
  )
  private String mensaje;
  @Schema(
    example = "1",
    description = "valopr correspondiente al cambio o error"
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

  /*
  constructor
   */
  public DtoRespuestaNaiveBayes(String nombre, String tipoNombre, String mensaje, int valor,
                                double probabilidadNbNombre, double probabilidadNbApellido) {
    this.nombre = nombre;
    this.tipoNombre = tipoNombre;
    this.mensaje = mensaje;
    this.valor = valor;
    this.probabilidadNbNombre = probabilidadNbNombre;
    this.probabilidadNbApellido = probabilidadNbApellido;
  }

}
