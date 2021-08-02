## Micronaut and Auth0

### Auth0

- [Login in Auth0 and create new application](https://auth0.com)

### Settings your auth0 app

 - Allowed Callback URLs -> http://YOUR_HOST:YOUR_PORT/oauth/callback/auth0 -> Example: http://localhost:8080/oauth/callback/auth0
 - Allowed Logout URLs -> http://YOUR_HOST:YOUR_PORT/logout -> Example: http://localhost:8080/logout

 ![App urls](App urls.png)

### application.yml

- Init application.yml (OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_DOMAIN)

---

## Link
 - [SECURE A MICRONAUT APP WITH OKTA BY MICRONAUT](https://guides.micronaut.io/latest/micronaut-oauth2-okta-maven-java.html)

