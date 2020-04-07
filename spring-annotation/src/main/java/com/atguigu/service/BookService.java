package com.atguigu.service;


import com.atguigu.dao.BookDao;
import javax.inject.Inject;
import org.springframework.stereotype.Service;


@Service
public class BookService {

  //@Qualifier("bookDao")
  //@Autowired(required=false)
  //@Resource(name="bookDao2")
  @Inject
  private BookDao bookDao;

  public void print() {
    System.out.println(bookDao);
  }

  @Override
  public String toString() {
    return "BookService [bookDao=" + bookDao + "]";
  }


}
