package com.taxlens.controller;

import com.taxlens.service.CgtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CgtController.class)
public class CgtControllerTest {

  @Autowired
  MockMvc mvc;

  @MockBean
  CgtService service;

  @Test
  void controllerReturnsJsonFromService() throws Exception {
    when(service.getCgt("DE")).thenReturn(Map.of("rate", 26.375, "note", "Germany"));

    mvc.perform(get("/api/cgt").param("country", "DE"))
      .andExpect(status().isOk())
      .andExpect(content().json("{\"rate\":26.375,\"note\":\"Germany\"}"));
  }
}
