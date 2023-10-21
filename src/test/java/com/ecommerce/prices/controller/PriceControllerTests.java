package com.ecommerce.prices.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTests {

    @Autowired
    private MockMvc mockMvc;
  
    @Test
    public void testRequest1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/search")
                .param("applicationDate", "2020-06-14-10.00.00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50)); // Precio esperado para esta prueba
    }

   
    @Test
    public void testRequest2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/search")
                .param("applicationDate", "2020-06-14-16.00.00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45)); // Precio esperado para esta prueba
    }

    
    @Test
    public void testRequest3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/search")
                .param("applicationDate", "2020-06-14-21.00.00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50)); // Precio esperado para esta prueba
    }
 
    @Test
    public void testRequest4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/search")
                .param("applicationDate", "2020-06-15-10.00.00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50)); // Precio esperado para esta prueba
    }

    @Test
    public void testRequest5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/search")
                .param("applicationDate", "2020-06-16 21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95)); // Precio esperado para esta prueba
    }
   
}

