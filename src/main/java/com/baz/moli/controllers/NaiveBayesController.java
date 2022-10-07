package com.baz.moli.controllers;

import com.baz.excepciones.DtoExcepcion;
import com.baz.moli.daos.FrecuenciasDao;
import com.baz.moli.dtos.EstadoResponseDto;
import com.baz.moli.dtos.NaiveBayesRequestDto;
import com.baz.moli.dtos.NaiveBayesResponseDto;
import com.baz.moli.services.MonitoreoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * <b>ModuloNaiveBayesController</b>
 * @descripcion: clase controller que aloja los endpoints;
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 09/06/22
 */

@Path("/remesas/modulo-naive-bayes")
public class NaiveBayesController {

  /*
  instacia del servicio monitoreo a tra ves de inyeccion
   */
  @Inject
  private MonitoreoService monitoreoService;

  @Inject
  private FrecuenciasDao frecuenciasDao;

  /**
   * <b>naiveBayes</b>
   * @descripcion: endpoint principal.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 09/06/22
   */
  @POST
  @Path("/calcula-nb")
  @Operation(operationId = "1",
    summary = "Metodo que retorna un posible tipo de nombre utilizando probabilidades naiveBayes")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response naiveBayes(@RequestBody NaiveBayesRequestDto request) throws IOException {
    //NaiveBayesResponseDto respuestaNb = calculoNaiveBayes.buscarFrecuencias(request);
    return Response.ok().entity(frecuenciasDao.obtenerFrecuencias("jose")).build();
  }

  /**
   * <b>status</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 20/06/22
   */
  @GET
  @Path("/estado")
  @Operation(
    summary = "Metodo de consulta al estado y Uid del microservicio",
    description = "description")
  @Produces(MediaType.APPLICATION_JSON)
  public Response status(){
    /*
    modelo con con los datos de salida
     */
    EstadoResponseDto estadoResponseDto = monitoreoService.generarUid();
    /*
    retorna el objeto como entidad para el parceo como json
     */
    return Response.ok().entity(estadoResponseDto).build();
  }
}
