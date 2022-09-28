package resource;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import services.MessageService;
import services.MessageServiceImp;

@RunWith(MockitoJUnitRunner.class)
class NasaResourceTest {
  @Mock
  private MessageService messageServiceMock;
  private MessageService msgServiceImp;

  @InjectMocks
  NasaResource nasaResource = new NasaResource();

  @BeforeEach
  void setUp() throws Exception{
    MockitoAnnotations.initMocks(this);
    msgServiceImp = new MessageServiceImp();
  }

  @Test
  @DisplayName("CONVERT EARTH DATE TO CURIOSITY SOL DATE")
  void testConvertCuriositySol(){
//    Response response = Response.
//        status(Response.Status.OK)
//        .entity(msgServiceImpl.convertCuriositySol("2022-06-15"))
//        .build();
    when(messageServiceMock.convertCuriositySol("2022-06-15")).thenReturn(3504);
    Response response = nasaResource.convertCuriositySol("2022-06-15");
    assertEquals(response.getStatus(), 200);
    assertEquals(response.getEntity(), 3504 );
  }

  @Test
  void TestConvertCuriositySolIfDateNull() {
    String date = msgServiceImp.getTodayDate();
    int actual = msgServiceImp.convertCuriositySol(date);

    when(messageServiceMock.getTodayDate()).thenReturn(date);
    when(messageServiceMock.convertCuriositySol(date)).thenReturn(actual);

    Response response = nasaResource.convertCuriositySol(null);
    assertEquals(response.getStatus(), 200);
    assertEquals(response.getEntity(), actual);
  }
  @Test
  void TestConvertCuriositySolThrowsException() {
    String date ="2022-";
    when(messageServiceMock.convertCuriositySol(date)).thenThrow(new RuntimeException());
    Response response = nasaResource.convertCuriositySol(date);
    assertEquals(response.getStatus(), 400);
    assertEquals(response.getEntity(), null );
  }
  private static URI getBaseURI(String path) {
    return UriBuilder.fromUri("http://localhost:8080/"+path).build();
  }
}
