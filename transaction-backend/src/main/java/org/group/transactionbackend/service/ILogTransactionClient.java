package org.group.transactionbackend.service;

import org.group.transactionbackend.model.dto.LogDto;

import java.io.IOException;

public interface ILogTransactionClient {
    void postLogTransaction(LogDto logDto) throws IOException;
}
