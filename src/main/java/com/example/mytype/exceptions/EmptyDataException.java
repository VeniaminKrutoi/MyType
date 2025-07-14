package com.example.mytype.exceptions;

public class EmptyDataException extends RuntimeException {
  public EmptyDataException(String field) {

    super("В %s должна содержаться информация".formatted(field));
  }
}
