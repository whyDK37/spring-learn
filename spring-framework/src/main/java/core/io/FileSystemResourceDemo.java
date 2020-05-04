package core.io;

import org.springframework.core.io.FileSystemResource;

public class FileSystemResourceDemo {

  public static void main(String[] args) {
    String userDir = System.getProperty("user.dir");
    System.out.println(userDir);
    FileSystemResource pom = new FileSystemResource(userDir + "/pom.xml");
    System.out.println("pom.isFile() = " + pom.isFile());
  }

}
