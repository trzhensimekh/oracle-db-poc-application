package com.naya.oracledbpocapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naya.oracledbpocapplication.models.Product;
import com.naya.oracledbpocapplication.repositories.ProductRepository;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OracleDbPocApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    private ResultActions resultActions;

    @RepeatedTest(5)
    public void testSaveProduct() throws Exception {
    long number = new Random().nextInt(300);
    Product product = new Product((Long)number , "TestProduct"+number, null, null);
    System.out.println("---->"+asJsonString(product));
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
             resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(product)));
                latch.countDown();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
    resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    Optional<Product> savedProduct = productRepository.findById(product.getProductId());
    System.out.println("Added --->"+savedProduct);
    assertEquals(product.getProductName(), savedProduct.get().getProductName());
    assertEquals(product.getProductId(), savedProduct.get().getProductId());
}

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
