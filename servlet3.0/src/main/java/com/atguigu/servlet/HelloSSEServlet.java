package com.atguigu.servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/sse", asyncSupported = true)
public class HelloSSEServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    resp.setContentType("text/event-stream");
    resp.setCharacterEncoding("utf-8");

    // 可多次返回数据
    for (int i = 0; i < 10; i++) {
      // 指定事件类型
      resp.getWriter().write("event:me\n");
      // 格式：data+ 数据 +两个回车
      resp.getWriter().write("data:" + i + "\n\n");
      resp.getWriter().flush();
    }
  }

}
