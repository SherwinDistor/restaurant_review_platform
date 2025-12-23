package com.sherwin.restaurant_review_platform.utils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class RsaKeyProperties {

  private RSAPublicKey rsaPublicKey;
  private RSAPrivateKey rsaPrivateKey;

  public RsaKeyProperties() {
    KeyPair keyPair = KeyGeneratorUtility.generateRsaKey();
    this.rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    this.rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
  }
}
