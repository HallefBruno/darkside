
package com.darkside.org.enuns;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public enum Categorias {
  
  MUSICAL("Musical"),
  RELIGIOSO("Religioso"),
  EDUCATIVO("Educativo"),
  POLEMICO("Polêmico"),
  OUTROS("Outros");
  
  private final String value;

  private Categorias(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
  
  public List<Categorias> getCategorias() {
    List<Categorias> enumValues = new ArrayList<>(EnumSet.allOf(Categorias.class));
    return enumValues;
  }
  
}
