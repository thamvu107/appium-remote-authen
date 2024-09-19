package entity.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RemoteServerAuthentication {
  private String username;

  private String password;

  @Override
  public String toString() {
    return "RemoteServerCred{username='" + username + "', password='[****]'}";
  }
}
