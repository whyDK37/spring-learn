package core.io;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

public class FileSystemResourceLoaderDemo {

  public static void main(String[] args) {
    String userDir = System.getProperty("user.dir");
    System.out.println(userDir);
    FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
    Resource resource = fileSystemResourceLoader.getResource(userDir + "/pom.xml");
    System.out.println("resource.getClass() = " + resource.getClass());
    System.out.println("resource.isFile() = " + resource.isFile());
  }

}
