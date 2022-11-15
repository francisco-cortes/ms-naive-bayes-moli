package com.elektra.naive.bayes.dao;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PruebaDaoFrecuenciasDaoFabricaConexionHttp {

  @Inject
  private DaoFabricaConexionHttp daoFabricaConexionHttp;

  @DisplayName("crea conexion http")
  @Test
  public void probarCrearConexion() throws IOException {
    HttpURLConnection conexion = daoFabricaConexionHttp.crearConexion("GET",
      "http://localhost:8080/datos/frecuencias/obtener-frecuencias?nombre=LEONARDO");

    assertNotNull(conexion);
  }
}
