package com.elektra.naive.bayes.util;

import javax.inject.Singleton;

@Singleton
public class CalculoNaiveBayes {
  public double calcularNaiveBayes(Double frec, Double total){
    double naiveBayes;
    if(frec > 0 && total > 0) {
      naiveBayes = (frec/total) * Constantes.CONSTANTE_NB;
    }
    else {
      naiveBayes = 0;
    }
    return naiveBayes;
  }
}
