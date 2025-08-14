package org.library.library.adapter;

import org.library.library.exepcion.ResourceNotFoundException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface Adapter {

    @Retryable(retryFor = {ResourceNotFoundException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public String getBackendResponse(String param1, String param2);

    @Recover
    public String getBackendResponseFallback(ResourceNotFoundException e, String param1, String param2);
}