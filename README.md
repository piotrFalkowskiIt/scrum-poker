# Start postgress: 
Only local setup is supported. Application is expecting postgres DB at localhost:5432. User and password can be found at src/jvmMain/resources/application.properties.

You can also run postgres with docker. Proper setup is done in postgress.yml - Just run:
`docker stack deploy -c postgress.yml postgres`


# Running application
Just use Intellij spring configuration with module classpath: od-chlopa.jvmMain.
Java 11 is required.