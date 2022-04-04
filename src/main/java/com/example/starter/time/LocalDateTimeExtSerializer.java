package com.example.starter.time;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.format.DateTimeFormatter;

public class LocalDateTimeExtSerializer extends LocalDateTimeSerializer {

  public LocalDateTimeExtSerializer( ) {
    super(DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm+08:00"));
  }
}
