package com.prismo.prismo.Controller;

import static com.prismo.prismo.Model.OperationsTypes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prismo.prismo.DTO.TransactionsDTO;
import com.prismo.prismo.Service.TransactionsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {
  private static final String URL = "/Transactions/";
  static Long ONE = 1L;
  static Long TEN = 10L;

  @Autowired private TransactionsController transactionsController;

  @MockBean private TransactionsService transactionsService;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void accountsCreate_ThenSuccess() throws Exception {
    TransactionsDTO accountDTO =
        TransactionsDTO.builder().operationTypeId(PAYMENT).amount(TEN).accountId(ONE).build();

    mockMvc
        .perform(
            post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(accountDTO)))
        .andExpect(status().isOk());
  }

  @Test
  public void accountsCreateWithoutOperationTypeId_ThenError() throws Exception {
    TransactionsDTO accountDTO = TransactionsDTO.builder().amount(TEN).accountId(ONE).build();

    mockMvc
        .perform(
            post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(accountDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void accountsCreateWithoutAmount_ThenError() throws Exception {
    TransactionsDTO accountDTO =
        TransactionsDTO.builder().operationTypeId(PAYMENT).accountId(ONE).build();

    mockMvc
        .perform(
            post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(accountDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void accountsCreateWithoutAccountId_ThenError() throws Exception {
    TransactionsDTO accountDTO =
        TransactionsDTO.builder().operationTypeId(PAYMENT).amount(TEN).build();

    mockMvc
        .perform(
            post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(accountDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void transactionsControllerClassNeedRestControllerAnnotation_thenSuccess() {
    assertThat(TransactionsController.class.isAnnotationPresent(RestController.class))
        .isEqualTo(true);
  }
}
