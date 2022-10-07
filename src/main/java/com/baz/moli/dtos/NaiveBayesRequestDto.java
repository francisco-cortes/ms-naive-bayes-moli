package com.baz.moli.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
/**
 * <b>NaiveBayesResponse</b>
 * @descripcion: clase modelo para la respuesta de salida en /modulo-naive-bayes/calcula-nb
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 09/06/22
 */
@Data
@JsonPropertyOrder({"nombre","tipoNombre"})
public class NaiveBayesRequestDto {
  String nombre;
  String tipoNombre;
}
