
package com.darkside.org.controller;

import com.darkside.org.enuns.Categorias;
import com.darkside.org.model.Video;
import com.darkside.org.service.VideoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
  
  @Autowired
  private VideoService videoService;
  
  @GetMapping("/")
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView("Index");
    modelAndView.addObject("categorias", Categorias.values());
    return modelAndView;
  }
  
  @GetMapping("/todos/videos")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public List<Video> videos() {
    return videoService.videos();
  }
  
  @GetMapping("/videos/filtrodos/{descricao}")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public List<Video> filtrados(@PathVariable(required = true) String descricao) {
    return videoService.findByDescricaoContainingIgnoreCase(descricao);
  }
  
  @GetMapping("/videos/categoria/{categoria}")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public List<Video> categoria(@PathVariable(required = true) String categoria) {
    return videoService.findByCategoriaContainingIgnoreCase(categoria);
  }
  
}
