package org.library.library.exepcion;

import java.time.ZonedDateTime;
import java.util.List;

public record ApiError(String message, int status, String path, ZonedDateTime timestamp, List<String> errors) {
}
