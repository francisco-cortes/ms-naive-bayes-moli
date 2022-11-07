package com.elektra.naive.bayes.propiedades.modelos;

import io.quarkus.runtime.annotations.ConfigGroup;

/**
 * <b>Configuraciones</b>
 * @descripcion: Interfaz que contienes las propiedades partículares de la validacion del token a ocupar en las peticiones.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */

@ConfigGroup
public interface Configuraciones {
  //ip base de datos
  String urlbase();
  //puerto base de datos
  String endpoint();
  //nombre de la base de datos
  String name();
  //primer procedimiento almacenado
  String fsp();
}
