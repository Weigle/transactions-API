package com.pismo.pismo.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.pismo.pismo.DTO.TransactionsDTO;
import com.pismo.pismo.exception.AccountNotFound;
import com.pismo.pismo.service.TransactionsService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionsController {

  private static final String producesType = "application/json";

  private final TransactionsService transactionsService;

  @RequestMapping(value = "/", method = POST, produces = producesType)
  public void createTransactions(@Valid @RequestBody TransactionsDTO transactionsDTO)
      throws AccountNotFound {
    transactionsService.saveTransactions(transactionsDTO);
  }
}
