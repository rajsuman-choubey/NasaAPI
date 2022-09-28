package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


public class MessageServiceImp implements MessageService {

  private static final DateTimeFormatter isoFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final LocalDate curiosityLandingDate = LocalDate.parse("2012-08-06",
      isoFormat);

  @Override
  public String getHello() {
    return "Hello World Jersey and HK2 ";
  }

  @Override
  public int convertCuriositySol(String date) {

    try {
      LocalDate earthDate = LocalDate.parse(date, isoFormat);
      float diffInDays = ChronoUnit.DAYS.between(curiosityLandingDate, earthDate);
      return (int) Math.round(diffInDays * 86400 / 88775.245);
    } catch (DateTimeParseException e) {
      throw new RuntimeException("Date is not in ISO 8601 format", e);
    }
  }

  public String getTodayDate() {
    return isoFormat.format(LocalDateTime.now());
  }
}

