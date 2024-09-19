package entity.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpCred extends LoginCred {
  private String repeatPassword;

  @Override
  public String toString() {
    return "UserCred{username='" + email + "', password='[****]'" + ", repeatPassword='[****]'" + "}";
  }

}
