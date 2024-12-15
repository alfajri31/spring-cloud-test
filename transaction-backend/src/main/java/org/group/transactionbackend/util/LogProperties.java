package org.group.transactionbackend.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class LogProperties {
    @Value("${log.transaksi}")
    private String postLog;
    public String postLog() {
        return postLog;
    }
}
