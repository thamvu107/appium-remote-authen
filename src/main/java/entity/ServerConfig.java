package entity;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.ClientConfig;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@Setter
public class ServerConfig {

  private String ip;

  private int port;

  private boolean isAuthenticationRequired;



  public ServerConfig(String ip, int port, boolean authen) {

    validateIP(ip);
    validatePort(port);

    this.ip = ip;
    this.port = port;
    this.isAuthenticationRequired = authen;
  }

  public void setIp(String ip) {
    validateIP(ip);
    this.ip = ip;
  }

  public void setPort(int port) {
    validatePort(port);
    this.port = port;
  }

  private void validatePort(int port) {
    if (port <= 0 || port > 65535) {
      throw new IllegalArgumentException("Port must be between 1 and 65535");
    }
  }

  private void validateIP(String serverIP) {
    if (serverIP == null || serverIP.isEmpty()) {
      throw new IllegalArgumentException("Server IP cannot be null or empty");
    }
  }

  private void validateProtocol(String protocol) {
    if (protocol == null || protocol.isEmpty()) {
      throw new IllegalArgumentException("Protocol cannot be null or empty");
    }
  }

  public URL getServerURL() {
    try {
      return new URL(
        "http",
        this.getIp(),
        this.getPort(),
        ""
      );
    } catch (MalformedURLException e) {
      throw new RuntimeException("Failed to construct server URL", e);
    }
  }


  @Override
  public String toString() {
    return String.format("ServerConfig{protocol='%s', serverIP='%s', port=%d,  authen=%b}", "http", ip, port, isAuthenticationRequired);
  }
}
