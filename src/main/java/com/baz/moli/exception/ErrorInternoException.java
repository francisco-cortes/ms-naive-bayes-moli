package com.baz.moli.exception;

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
    example = "CLASIFICACION CORRECTA"
  )
  private String mensaje;
  /*
  codigo operacion
   */
  @Schema(
    example = "1"
  )
  private int valor;
}
