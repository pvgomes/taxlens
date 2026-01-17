package com.taxlens.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class CgtService {

  private final Map<String, Map<String, Object>> fallback;
  private final RestTemplate rest = new RestTemplate();
  private final String externalUrl;
  private final ObjectMapper mapper = new ObjectMapper();

  public CgtService(@Value("${cgt.external.url:}") String externalUrl) {
    this.externalUrl = externalUrl == null ? "" : externalUrl.trim();
    this.fallback = loadFallback();
  }

  private Map<String, Map<String, Object>> loadFallback() {
    try {
      ClassPathResource res = new ClassPathResource("cgt-fallback.json");
      return mapper.readValue(res.getInputStream(), new TypeReference<>() {});
    } catch (IOException e) {
      return Collections.emptyMap();
    }
  }

  @SuppressWarnings("unchecked")
  public Map<String, Object> getCgt(String country) {
    if (country == null) return Map.of("error", "Country required");
    String c = country.toUpperCase();

    // Try external provider when configured
    if (!externalUrl.isBlank()) {
      try {
        String url = externalUrl + (externalUrl.contains("?") ? "&" : "?") + "country=" + c;
        Map<String, Object> resp = rest.getForObject(url, Map.class);
        if (resp != null && !resp.containsKey("error") && !resp.isEmpty()) return resp;
      } catch (Exception ignored) {
        // ignore and fall back
      }
    }

    return fallback.getOrDefault(c, Map.of("error", "Country not supported"));
  }
}
