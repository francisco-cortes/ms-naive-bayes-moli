package com.baz.moli.dtos;

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
public class NaiveBayesResponseDto {
  @Schema(
    example = "LEONARDO",
    description = "Nombre o apellido de una persona"
  )
  String nombre;
  @Schema(
    example = "NOMBRE",
    description = "Clasificacion del nombre dependiendo de Naive Bayes"
  )
  String tipoNombre;
  @Schema(
    example = "sql expecion",
    description = "Mensaje de expecion, o de operacion"
  )
  String mensaje;
  @Schema(
    example = "1",
    description = "valopr correspondiente al cambio o error"
  )
  int valor;

  @Schema(
    example = "1",
    description = "valor de la probabilidad de naive bayes para nombre"
  )
  double probabilidadNbNombre;

  @Schema(
    example = "1",
    description = "valor de la probabilidad de naive bayes para apellido"
  )
  double probabilidadNbApellido;

  /*
  constructor
   */
  public NaiveBayesResponseDto(String nombre, String tipoNombre, String mensaje, int valor,
                               double probabilidadNbNombre, double probabilidadNbApellido) {
    this.nombre = nombre;
    this.tipoNombre = tipoNombre;
    this.mensaje = mensaje;
    this.valor = valor;
    this.probabilidadNbNombre = probabilidadNbNombre;
    this.probabilidadNbApellido = probabilidadNbApellido;
  }

}
