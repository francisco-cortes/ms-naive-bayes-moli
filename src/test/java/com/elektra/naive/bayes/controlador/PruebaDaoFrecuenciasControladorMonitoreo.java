package com.elektra.naive.bayes.controlador;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaDaoFrecuenciasControladorMonitoreo {
  @DisplayName("Preuba desde el endpoint principal clasificación probable nombre")
  @Test
  public void PruebaNaiveBayesIncorrectaNombre(){
    given()
      .when().get("/datos/naive-bayes/status")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo("OK"));
  }
}
