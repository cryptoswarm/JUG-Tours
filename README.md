# Required Steps
## Create an OIDC app in Okta
1. Sign-in redirect URIs: `http://localhost:8080/login/oauth2/code/okta`
2. Sign-out redirect URIs: `http://localhost:3000,http://localhost:8080`
## Create .env and add it to your .gitignore
Save the following in .env
```
export issuer="https://<your-auth0-domain>"
export clientId="<your-client-id>"
export clientSecret="<your-client-secret>"
```
## Create an `application.properties` file
Save the following:
```
spring.datasource.url=jdbc\:h2\:mem\:dre
spring.h2.console.enabled=true
#Swagger conigs
springdoc.api-docs.path=/v1/api-docs
springdoc.swagger-ui.path=/v1/swagger.html
#Okta config
okta.oauth2.issuer=${issuer}
okta.oauth2.client-id=${clientId}
okta.oauth2.client-secret=${clientSecret}
```

## Run the application
1. Run the command: `source .env`
2. Execute the application : `./mvnw spring-boot:run`
3. Run the React app: `npm start`