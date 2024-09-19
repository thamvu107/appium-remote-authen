package entity.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginCred {
  protected String email;
  protected String password;


  @Override
  public String toString() {
    return "UserCred{username='" + email + "', password='[****]'}";
  }
}
