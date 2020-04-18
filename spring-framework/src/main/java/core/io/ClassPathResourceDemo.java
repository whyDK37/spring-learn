package core.io;

import org.springframework.core.io.ClassPathResource;

public class ClassPathResourceDemo {

  public static void main(String[] args) {
    ClassPathResource resource = new ClassPathResource("META-INF/user.yaml");
    System.out.println("resource.isFile() = " + resource.isFile());
    System.out.println("resource.isReadable() = " + resource.isReadable());

    resource = new ClassPathResource(
        ClassPathResourceDemo.class.getCanonicalName().replace('.', '/') + ".class");
    System.out.println("resource.isFile() = " + resource.isFile());
    System.out.println("resource.isReadable() = " + resource.isReadable());

  }

}
