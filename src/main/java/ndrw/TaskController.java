package ndrw;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by andrew on 27/10/2016.
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity post(@Valid @RequestBody Task data) throws IOException {
      return ResponseEntity.ok(data);
    }

  @ExceptionHandler
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ValidationError handleException(MethodArgumentNotValidException exception) {
    return createValidationError(exception);
  }

  private ValidationError createValidationError(MethodArgumentNotValidException e) {
    return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
  }



}
