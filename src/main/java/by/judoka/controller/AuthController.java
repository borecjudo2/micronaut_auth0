package by.judoka.controller;

import by.judoka.utils.Auth0Secured;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Vladislav_Karpeka
 */
@Controller
public class AuthController {

  @Value("${auth0.token.params.roles.name}")
  private String paramsRolesName;

  @Secured(SecurityRule.IS_ANONYMOUS)
  @Get
  public String sayHello() {
    return "Hello anonymous!";
  }

  @Auth0Secured("admin")
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get("/info")
  public Map<?, ?> getInfo(@Nullable Authentication authentication) {
    if (authentication == null) {
      return Collections.singletonMap("isLoggedIn", false);
    }
    return CollectionUtils.mapOf("isLoggedIn", true, "username", authentication.getName(),
        "roles", authentication.getAttributes().get(paramsRolesName));
  }
}
