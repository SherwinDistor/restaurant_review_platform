package com.sherwin.restaurant_review_platform.exceptions;

public class RestaurantException extends BaseException {
  public RestaurantException() {
  }

  public RestaurantException(String message) {
    super(message);
  }

  public RestaurantException(String message, Throwable cause) {
    super(message, cause);
  }

  public RestaurantException(Throwable cause) {
    super(cause);
  }
}
