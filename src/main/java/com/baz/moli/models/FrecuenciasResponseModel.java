package com.baz.moli.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.math.BigDecimal;

/**
 * <b>FrecuenciasResponseDto</b>
 * @descripcion: breve descripción del contenido
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 06/10/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder({"frecuenciaNombre","totalregistrosNombre","frecuenciaApellidos","totalRegistrosApellidos"})
public class FrecuenciasResponseModel {
  /*
  cuantas veces se encontro la cadena como nombre
   */
  private BigDecimal frecuenciaNombre;
  /*
  total de todos lo nombres en la base de datos
   */
  private BigDecimal totalRegistrosNombre;
  /*
  total de veces que se encontro la cadena como apellido
   */

  private BigDecimal frecuenciaApellidos;
  /*
  total de todos los apellidos en la base de datos
   */
  private BigDecimal totalRegistrosApellidos;
  /*
  mensaje de operacion interna
   */

  private String mensaje;
  /*
  codigo de operacion interna
   */
  private String codigoInterno;
}
