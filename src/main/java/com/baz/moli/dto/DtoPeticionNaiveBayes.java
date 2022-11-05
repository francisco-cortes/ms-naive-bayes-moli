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
@JsonPropertyOrder({"nombre","tipoNombre"})
public class DtoPeticionNaiveBayes {
  @Schema(
    example = "LEONARDO",
    description = "Nombre o apellido de una persona"
  )
  private String nombre;
  @Schema(
    example = "NOMBRE",
    description = "Tipo de nombre donde se clasifico"
  )
  private String tipoNombre;
}
