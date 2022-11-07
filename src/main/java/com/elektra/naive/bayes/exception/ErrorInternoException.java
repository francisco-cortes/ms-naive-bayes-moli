package com.elektra.naive.bayes.exception;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>ErrorInternoExepcion</b>
 * @descripcion: breve descripción del contenido
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternoException extends RuntimeException {
  /*
  codigo operacion
   */
  @Schema(
    example = "JOSE"
  )
  private String nombre;
  /*
  codigo operacion
   */
  @Schema(
    example = "NOMBRE"
  )
  private String tipoNombre;
  /*
  codigo operacion
   */
  @Schema(
    example = "SQL EXEPCION"
  )
  private String mensaje;
  /*
  codigo operacion
   */
  @Schema(
    example = "1"
  )
  private int valor;

  @Schema(
    example = "1"
  )
  private double probabilidadNbNombre;

  @Schema(
    example = "1"
  )
  private double probabilidadNbApellido;

  /*
  constructor
   */
  public ErrorInternoException(String nombre, String tipoNombre, String mensaje, int valor,
                               double probabilidadNbNombre, double probabilidadNbApellido) {
    this.nombre = nombre;
    this.tipoNombre = tipoNombre;
    this.mensaje = mensaje;
    this.valor = valor;
    this.probabilidadNbNombre = probabilidadNbNombre;
    this.probabilidadNbApellido = probabilidadNbApellido;
  }

  public String getNombre(){
    return this.nombre;
  }

  public String getTipoNombre(){
    return this.tipoNombre;
  }

  public String getMensaje(){
    return this.mensaje;
  }

  public int getValor(){
    return this.valor;
  }

  public double getProbabilidadNbNombre(){
    return this.probabilidadNbNombre;
  }

  public double getProbabilidadNbApellido() {
    return this.probabilidadNbApellido;
  }
}
