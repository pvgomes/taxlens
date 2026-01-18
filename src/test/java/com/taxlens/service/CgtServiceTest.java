package com.taxlens.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CgtServiceTest {

  @Test
  void returnsFallbackWhenExternalNotConfigured() {
    RestTemplate rest = Mockito.mock(RestTemplate.class);
    CgtService svc = new CgtService(rest, "");

    Map<String, Object> de = svc.getCgt("DE");
    assertThat(de).containsKey("rate");
    assertThat(de.get("rate")).isEqualTo(26.375);
    assertThat(de.get("note")).asString().contains("Germany");
  }

  @Test
  void returnsErrorForUnknownCountry() {
    RestTemplate rest = Mockito.mock(RestTemplate.class);
    CgtService svc = new CgtService(rest, "");

    Map<String, Object> xx = svc.getCgt("XX");
    assertThat(xx).containsKey("error");
  }

  @Test
  void usesExternalWhenConfigured() {
    RestTemplate rest = Mockito.mock(RestTemplate.class);
    String external = "https://example.com/api/cgt";
    // simulate external response
    Map<String, Object> extResp = Map.of("rate", 1.23, "note", "external provider");
    Mockito.when(rest.getForObject(Mockito.startsWith(external), Mockito.eq(Map.class))).thenReturn(extResp);

    CgtService svc = new CgtService(rest, external);
    Map<String, Object> out = svc.getCgt("DE");
    assertThat(out.get("rate")).isEqualTo(1.23);
    assertThat(out.get("note")).isEqualTo("external provider");
  }
}
