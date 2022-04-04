package com.example.starter.time;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.format.DateTimeFormatter;

public class LocalDateTimeExtDeserializer extends LocalDateTimeDeserializer {

  public LocalDateTimeExtDeserializer( ) {
    super(DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm+08:00"));
  }
}
