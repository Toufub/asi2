package com.asi2.transaction.jms;

import com.asi2.common.model.TransactionDTO;
import com.asi2.common.model.UserDTO;
import com.asi2.transaction.controller.MicroTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsConsumer {

    private MicroTransactionService microTransactionService;

    @Autowired
    public JmsConsumer(MicroTransactionService microTransactionService) {
        this.microTransactionService = microTransactionService;
    }

    @JmsListener(destination = "transaction.create")
    public void onCreateMessage(TransactionDTO transactionDTO) {
        try{
            log.info("Received Message: "+ transactionDTO.getId());
            microTransactionService.addTransaction(transactionDTO);
            // TODO : Notif
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }
    }

    @JmsListener(destination = "transaction.put")
    public void onPutMessage(Object [] message) {
        try{
            log.info("Received Message: transaction-put "+ message[0]);
            microTransactionService.modifyTransaction(Integer.parseInt(message[0].toString()), (TransactionDTO)message[1]);
            // TODO : Notif
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }
    }
}