package com.elektra.naive.bayes.util;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;

@Singleton
public class LectorRespuestaHttp {
  public String leerRespuestaHttp(BufferedReader respuesta) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = respuesta.readLine()) != null) {
      sb.append(line);
    }
    return sb.toString();
  }
}
