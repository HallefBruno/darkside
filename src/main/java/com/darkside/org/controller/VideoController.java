package com.darkside.org.controller;

import com.darkside.org.enuns.Categorias;
import com.darkside.org.service.VideoService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;
  
  @GetMapping("/page")
  public ModelAndView page() {
    ModelAndView modelAndView = new ModelAndView("/ImportarVideo");
    modelAndView.addObject("categorias", Categorias.values());
    return modelAndView;
  }

  @PostMapping("/salvar")
  public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("descricao") String descricao, @RequestParam("categoria") String categoria, RedirectAttributes redirectAttributes) throws IOException {
    try {
      videoService.salvar(file, descricao, categoria);
    } catch(RuntimeException ex) {
      redirectAttributes.addFlashAttribute("error",ex.getLocalizedMessage());
      return "redirect:/video/page";
    }
    redirectAttributes.addFlashAttribute("message","Você carregou com sucesso " + file.getOriginalFilename() + "!");
    return "redirect:/video/page";
  }

}
