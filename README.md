# TaxLens

Minimal Java web app that returns simplified capital gains tax (CGT) info by country.

Frontend
- A small Tailwind UI is available at `src/main/resources/static` and served from the application root.

Quick run
- Requirements: Java 17, Maven
- Start the app:

```bash
mvn spring-boot:run
```

Open the UI: http://localhost:8080
API endpoint: http://localhost:8080/api/cgt?country=DE

Testing
- Run the full test suite:

```bash
mvn test
```

- Run a single test class (example):

```bash
mvn -Dtest=com.taxlens.service.CgtServiceTest test
```

- Run a single test method (example):

```bash
mvn -Dtest=com.taxlens.service.CgtServiceTest#usesExternalWhenConfigured test
```

Notes on tests
- Tests use `spring-boot-starter-test` (JUnit, Mockito, MockMvc).
- `CgtServiceTest` covers the fallback JSON and the external-provider path (mocked `RestTemplate`).
- `CgtControllerTest` uses `@WebMvcTest` and a mocked `CgtService` to verify the HTTP layer.

Configuration
- To enable an external CGT provider, set `cgt.external.url` in `src/main/resources/application.properties` or as an environment property. Example:

```properties
cgt.external.url=https://example.com/api/cgt
```

Fallback data
- The application ships a fallback JSON at `src/main/resources/cgt-fallback.json` used when no external provider is configured or when the external call fails.

Development tips
- To build the runnable JAR:

```bash
mvn -DskipTests package
```

- To run the packaged JAR:

```bash
java -jar target/taxlens-0.0.1-SNAPSHOT.jar
```

Questions or next steps
- Add CI to run tests on push (I can add a GitHub Actions workflow).
- Add code coverage reporting (Jacoco) if you want coverage metrics.

