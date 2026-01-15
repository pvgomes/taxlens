package com.taxlens.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CgtController {

  private static final Map<String, Map<String, Object>> CGT = Map.of(
    "DE", Map.of("rate", 26.375, "note", "Germany flat capital gains tax incl. solidarity surcharge"),
    "PL", Map.of("rate", 19.0, "note", "Poland flat capital gains tax"),
    "AE", Map.of("rate", 0.0, "note", "UAE has no personal capital gains tax")
  );

  @GetMapping("/api/cgt")
  public Map<String, Object> cgt(@RequestParam("country") String country) {
    return CGT.getOrDefault(
      country.toUpperCase(),
      Map.of("error", "Country not supported")
    );
  }
}


