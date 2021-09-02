package by.judoka.interceptor;

import by.judoka.utils.Auth0Secured;
import com.nimbusds.jose.shaded.json.JSONArray;
import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.Authentication;

import javax.inject.Singleton;

@Singleton
@InterceptorBean(Auth0Secured.class)
public class Auth0SecuredInterceptor implements MethodInterceptor<Object, Object> {

  @Value("${auth0.token.params.roles.name}")
  private String paramsRolesName;

  @Nullable
  @Override
  public Object intercept(MethodInvocationContext<Object, Object> context) {
    String exceptedPermission = (String) context.getValue(Auth0Secured.class).orElseThrow(RuntimeException::new);
    Authentication authentication = (Authentication) context.getParameterValueMap().get("authentication");
    JSONArray actualPermissions = (JSONArray) authentication.getAttributes().get(paramsRolesName);

    if (isHaveExceptedPermission(exceptedPermission, actualPermissions)) {
      return context.proceed();
    }

    String message = String.format("User with id '%s' haven't role '%s'", authentication.getName(), exceptedPermission);
    throw new RuntimeException(message);
  }

  private boolean isHaveExceptedPermission(String exceptedPermission, JSONArray actualPermissions) {
    return actualPermissions.stream()
        .anyMatch(actualPermission -> exceptedPermission.equalsIgnoreCase((String) actualPermission));
  }
}