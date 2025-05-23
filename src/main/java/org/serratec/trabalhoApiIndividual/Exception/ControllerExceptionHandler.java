package org.serratec.trabalhoApiIndividual.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroResposta> handleValidationExceptions(MethodArgumentNotValidException ex) {
		String detalhes = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.joining("; "));

		ErroResposta erro = new ErroResposta("Erro de validação", detalhes);
		return ResponseEntity.badRequest().body(erro);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroResposta> handleException(Exception ex) {
		ErroResposta erro = new ErroResposta("Erro interno", "Ocorreu um erro interno. Tente novamente mais tarde!");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
}