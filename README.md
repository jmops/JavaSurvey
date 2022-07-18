# JavaSurvey
The backend of a simple survey application.

@TODO frontend, run as containers (db and server).
## Requirements
- Install postgres.
- Create a database and user.
- Add src/main/resources/schema-postgres.sql and 
src/main/resources/session-postgres.sql into the database.
- Set the following environment variables: dbname, dbuser and dbpassword.

## To run
- Run the following commands from the same directory as this README:
```console
./gradlew bootJar # Compile

java -jar build/survey-0.0.1-SNAPSHOT.jar \
--spring.datasource.url=jdbc:postgresql://localhost:5432/$dbname \
--spring.datasource.username=$dbuser \
--spring.datasource.password=$dbpassword
```