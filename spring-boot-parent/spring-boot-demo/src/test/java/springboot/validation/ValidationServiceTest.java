package springboot.validation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import springboot.SpringBootServiceApplication;


@SpringBootTest(args = "--app.test=one")
@Import(SpringBootServiceApplication.class)
class ValidationServiceTest {

  @Autowired
  private ValidationService validationService;

  @Test
  void findByCodeAndAuthor() {
    Exception exception = null;
    try {
      validationService.findByCodeAndAuthor("", null);
    } catch (Exception e) {
      exception = e;
      e.printStackTrace();
    }
    assertNotNull(exception);
  }
}