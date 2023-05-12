
package com.darkside.org.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Video implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, unique = true)
  private String nome;
  
  @Column(nullable = false)
  private String categoria;
  
  @Column(nullable = false, unique = true)
  private String url;
  
  @Column(nullable = false)
  private String descricao;
  
  @Column(nullable = false)
  private LocalDate dataPublicacao; 
  
}
