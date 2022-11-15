package com.elektra.naive.bayes.controlador;

import com.elektra.naive.bayes.dto.DtoPeticionNaiveBayes;
import com.elektra.naive.bayes.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaDaoFrecuenciasControladorMonitoreo {
  @DisplayName("Preuba desde el endpoint principal calcificacion probable nombre")
  @Test
  public void PruebaNaiveBayesIncorrectaNombre(){
    given()
      .when().get("/datos/naive-bayes/status")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo("OK"));
  }
}
