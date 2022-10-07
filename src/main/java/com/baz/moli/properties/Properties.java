package com.baz.moli.properties;

import com.baz.moli.properties.models.Configuraciones;
import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

import java.util.Map;

/**
 * <b>Properties</b>
 * @descripcion: interface para obtener datos del properties.yml
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@StaticInitSafe
@ConfigMapping(prefix = "conexion")
public interface Properties {
  /**
   * <b>conexionesdb</b>
   * @descripcion: apunta a configuracionesdb
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * mapa de configuraciones
   * @ultimaModificacion: 04/10/22
   */

  Map<String , Configuraciones> conexionesdb();
}
