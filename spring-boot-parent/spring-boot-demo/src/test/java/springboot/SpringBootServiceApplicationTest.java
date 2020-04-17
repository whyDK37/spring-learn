package springboot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = "--app.test=one")
//@Import(SpringBootServiceApplication.class)
class SpringBootServiceApplicationTest {

  @BeforeAll
  static void beforeAll() {
    System.out.println("beforeAll");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("beforeEach");
  }

  @Test
  void exampleTest() {
    System.out.println("exampleTest...");
  }

  @Test
  void exampleTest2() {
    System.out.println("exampleTest 2 ...");
  }
}