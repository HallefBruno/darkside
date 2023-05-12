
package com.darkside.org.repository;

import com.darkside.org.model.Video;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
  boolean existsByNomeContainingIgnoreCaseAndCategoriaContainingIgnoreCase(String nome, String categoria);
  List<Video> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
  List<Video> findByCategoriaContainingIgnoreCase(String categoria, Pageable pageable);
  
}
