package com.example.springis.response;

public class ResponseJSON {
  private String message;
  private Boolean status;

  public ResponseJSON() {}

  public ResponseJSON(String message, Boolean status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {return message;}
  public Boolean getStatus() {return status;}

  public void setMessage(String message) {this.message = message;}
  public void setStatus(Boolean status) {this.status = status;}

}
