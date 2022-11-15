package com.elektra.naive.bayes.dao;

import com.baz.log.LogServicio;
import com.elektra.naive.bayes.modelos.ModeloRespuestaFrecuencias;
import com.elektra.naive.bayes.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PruebaDaoFrecuencias {
  @Inject
  private DaoFrecuencias daoFrecuencias;

  @DisplayName("Frecuencias no encontradas")
  @Test
  public void probarNoEncontradoObtenerFrecuecias() throws InvalidAlgorithmParameterException, NoSuchPaddingException,
    IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

    LogServicio logServicio = new LogServicio();
    ModeloRespuestaFrecuencias frecuencias = daoFrecuencias.obtenerFrecuencias(
      "",logServicio);
    assertEquals(frecuencias.getMensaje(), "No se encontro el nombre en la Base de datos");
  }

  @DisplayName("Frecuencias")
  @Test
  public void probarObtenerFrecuecias() throws InvalidAlgorithmParameterException, NoSuchPaddingException,
    IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

    LogServicio logServicio = new LogServicio();
    ModeloRespuestaFrecuencias frecuencias = daoFrecuencias.obtenerFrecuencias(
      "LEONARDO",logServicio);
    assertEquals(frecuencias.getMensaje(), Constantes.MENSAJE_EXITO);
  }

  @DisplayName("Frecuencias null")
  @Test
  public void probarObtenerFrecueciasAAA() throws InvalidAlgorithmParameterException, NoSuchPaddingException,
    IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

    LogServicio logServicio = new LogServicio();
    ModeloRespuestaFrecuencias frecuencias = daoFrecuencias.obtenerFrecuencias(
      null,logServicio);
    assertEquals(frecuencias.getMensaje(), Constantes.CODIGO_SIN_FRECUENCIAS + " " + Constantes.MENSAJE_ERROR_CLASIFICACION);
  }

}
