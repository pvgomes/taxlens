package com.taxlens.controller;

import com.taxlens.service.CgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CgtController {

  private final CgtService service;

  @Autowired
  public CgtController(CgtService service) {
    this.service = service;
  }

  @GetMapping("/api/cgt")
  public Map<String, Object> cgt(@RequestParam("country") String country) {
    return service.getCgt(country);
  }
}
