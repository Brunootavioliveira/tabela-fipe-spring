package br.com.brunootavio.tabela_fipe.exception;

public class FipeApiException extends RuntimeException {
    public FipeApiException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
