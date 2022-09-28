package services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageServiceImpTest {

  MessageServiceImp messageServiceImp;

  @BeforeEach
  void setUp() {
    messageServiceImp = new MessageServiceImp();
  }

  @Test
  void TestConvertCuriositySol() {
    assertEquals(3504, messageServiceImp.convertCuriositySol("2022-06-15"));
  }

  @Test
  void convertCuriositySolThrowsExceptionIfDateInvalidFormat() {
    assertThrows(RuntimeException.class, () -> messageServiceImp.convertCuriositySol("20-06-15"));
  }
}
