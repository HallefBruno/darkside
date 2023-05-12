
package com.darkside.org.service;

import com.cloudinary.utils.ObjectUtils;
import com.darkside.org.config.CloudinaryConfig;
import com.darkside.org.model.Video;
import com.darkside.org.repository.VideoRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {
  
  @Autowired
  private VideoRepository videoRepository;
  
  @Transactional
  public void salvar(MultipartFile multipartFile, String descricao, String categoria) {
    try {
      String extensao = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
      String nomeVideoComExtensao = multipartFile.getOriginalFilename().replaceAll(" ", "-");
      String nomeVideoSemExtensao = nomeVideoComExtensao.substring(0, nomeVideoComExtensao.lastIndexOf("."));
      String urlVideo = "darkside/"+categoria+"/".concat(nomeVideoSemExtensao);
      Video video = new Video();
      video.setNome(nomeVideoComExtensao);
      video.setCategoria(categoria);
      video.setDescricao(descricao);
      video.setUrl(urlVideo+"."+extensao);
      video.setDataPublicacao(LocalDate.now());
      if(!exists(video)) {
        CloudinaryConfig.cloudinary().uploader().uploadLarge(multipartFile.getBytes(), ObjectUtils.asMap( "resource_type", "video", "public_id",urlVideo));
        videoRepository.save(video);
        return;
      }
      throw new RuntimeException("Esse Vídeo ja foi cadastrado!");
    } catch (IOException ex) {
      throw new RuntimeException(ex.getLocalizedMessage());
    }
  }
  
  public List<Video> videos() {
    Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("dataPublicacao")));
    return videoRepository.findAll(pageable).getContent();
  }
  
  public boolean exists(Video video) {
    ExampleMatcher MATCHER = ExampleMatcher.matching()
      .withIgnorePaths("dataPublicacao")
      .withIgnorePaths("url")
      .withIgnorePaths("descricao")
      .withMatcher("nome", GenericPropertyMatchers.ignoreCase())
      .withMatcher("categoria", GenericPropertyMatchers.ignoreCase());
    Example<Video> example = Example.of(video, MATCHER);
    boolean exists = videoRepository.exists(example);
    //videoRepository.existsByNomeContainingIgnoreCaseAndCategoriaContainingIgnoreCase(video.getNome(), video.getCategoria());
    return exists;
  }
  
  public List<Video> filtrados(String descricao) {
    Video video = new Video();
    video.setDescricao(descricao.toLowerCase());
    Example<Video> example = Example.of(video, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.ENDING));
    Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("dataPublicacao")));
    List<Video> list = videoRepository.findAll(example, pageable).getContent();
    return list;
  }
  
  public List<Video> findByDescricaoContainingIgnoreCase(String descricao) {
    Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("dataPublicacao")));
    return videoRepository.findByDescricaoContainingIgnoreCase(descricao, pageable);
  }
  
  public List<Video> findByCategoriaContainingIgnoreCase(String categoria) {
    Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Order.desc("dataPublicacao")));
    return videoRepository.findByCategoriaContainingIgnoreCase(categoria, pageable);
  }
  
}
