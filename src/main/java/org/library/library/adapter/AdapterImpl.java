package org.library.library.adapter;

import lombok.extern.slf4j.Slf4j;
import org.library.library.exepcion.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class AdapterImpl implements Adapter {
    @Override
    public String getBackendResponse(String param1, String param2) {
        int random = new Random().nextInt(4);
        if (random % 2 == 0) {
            throw new ResourceNotFoundException("Remote API failed");
        }
        return "Hello from remote backend!!!";
    }

    @Override
    public String getBackendResponseFallback(ResourceNotFoundException e, String param1, String param2) {
        return "Hello from fallback method!!!";
    }
}
