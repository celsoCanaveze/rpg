package br.com.fiap.rpg.exception;

public class TransacaoException extends RuntimeException {
    public TransacaoException(String message) {
        super(message);
    }
}