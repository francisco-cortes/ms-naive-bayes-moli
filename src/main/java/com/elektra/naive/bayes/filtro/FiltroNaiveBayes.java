package com.elektra.naive.bayes.filtro;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.UnauthorizedException;
import com.baz.log.LogServicio;
import com.elektra.naive.bayes.modelos.Encabezado;
import com.elektra.naive.bayes.modelos.Resultado;
import com.elektra.naive.bayes.util.Constantes;
import com.elektra.naive.bayes.util.UtilidadGenerarExcepcion;
import com.baz.servicios.ValidarDto;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class FiltroNaiveBayes implements ContainerRequestFilter {
  private static final UtilidadGenerarExcepcion UTILIDAD_GENERAR_EXCEPCION = new UtilidadGenerarExcepcion();

  /**
   * <b>filtro</b>
   * @descripcion: breve descripcion del metodo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: requestContex, Contexto de peticion
   * @ultimaModificacion: 14/10/22
   */

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    System.out.println("ENTRA AL FILTER");
    LogServicio log = new LogServicio();
    String nombreClaseMetodo  = "FiltroHipocoristico-filtro";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);

    String uid = requestContext.getHeaderString("uid");
    String token = requestContext.getHeaderString("token");
    ValidarDto validarDto;

    System.out.println("VALOR DE uid "+ uid);
    try {

      if (!"/datos/naive-bayes/calcula-naive-bayes".equals(requestContext.getUriInfo().getPath())) {
        return;
      }

      System.out.println("ENTRA EN EL TRY");
      Resultado resultado = new Resultado(uid, Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO);
      validarDto = new ValidarDto();
      System.out.println("entra a validar peticion ");
      validarDto.validarPeticionAes(new Encabezado(uid, token), resultado);
      System.out.println(resultado.getMensaje());
      System.out.println("sale de validar peticion");
      System.out.println(resultado.getCodigo());

      if (!resultado.getCodigo().equals(Constantes.CODIGO_EXITO)) {
        UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.CODIGO_HTTP_400, Constantes.CODIGO_SOLICITUD_INCORRECTA,
          resultado.getMensaje(), uid);
      }
      return;
    }
    catch(BadRequestException | UnauthorizedException excepcion) {
      log.registrarExcepcion(excepcion, null);
      throw excepcion;
    }
    catch(Exception excepcion){
      log.registrarExcepcion(excepcion, null);
      UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.CODIGO_HTTP_500, Constantes.CODIGO_ERROR_GENERAL,
        excepcion.getMessage(), uid);
    }
  }
}
