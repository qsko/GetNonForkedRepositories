package MyError;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class NotAcceptableException  extends RuntimeException {

    public NotAcceptableException(String message) {
        super( message);
    }

}