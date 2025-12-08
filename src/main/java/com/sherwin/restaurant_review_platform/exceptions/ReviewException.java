package com.sherwin.restaurant_review_platform.exceptions;

public class ReviewException extends BaseException {
  public ReviewException() {
  }

  public ReviewException(String message) {
    super(message);
  }

  public ReviewException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReviewException(Throwable cause) {
    super(cause);
  }
}
