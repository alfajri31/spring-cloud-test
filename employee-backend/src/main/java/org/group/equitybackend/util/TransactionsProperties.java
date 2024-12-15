package org.group.equitybackend.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class TransactionsProperties {

    TransactionsProperties() {
    }
    @Value("${transaction.upload}")
    private String upload;
    public String postUpload() {
        return upload;
    }
}
