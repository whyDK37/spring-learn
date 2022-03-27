package io.reflectoring.validation.result;

public class Result<T> {
  public String code;
  public String msg;

  T data;

  public String getCode() {
    return code;
  }

  public Result<T> setCode(String code) {
    this.code = code;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  public Result<T> setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public T getData() {
    return data;
  }

  public Result<T> setData(T data) {
    this.data = data;
    return this;
  }
}
