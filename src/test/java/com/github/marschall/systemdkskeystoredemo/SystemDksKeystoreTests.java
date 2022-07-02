package com.github.marschall.systemdkskeystoredemo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.junit.jupiter.api.Test;

class SystemDksKeystoreTests {

  @Test
  void urlConnection() throws IOException {
    String defaultTruststore = System.getProperty("javax.net.ssl.trustStore");
    assertNotNull(defaultTruststore);
    assertFalse(defaultTruststore.isEmpty());

    URLConnection connection = new URL("https://www.badssl.com/").openConnection();
    assertTrue(connection instanceof HttpsURLConnection);
    connection.connect();
    try (InputStream inputStream = connection.getInputStream()) {
      assertNotNull(inputStream);
    }
  }

}
