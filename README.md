# TaxLens

TaxLens is a small Java web application that shows **headline capital gains tax rates by country**.

It is designed to be:
- simple
- fast
- easy to understand

This project is **informational only** and does not provide tax advice.

## What it does
Given a country code, TaxLens returns:
- a simplified capital gains tax rate
- a short explanatory note

Example:

**GET /api/cgt?country=DE**

Response:
```json
{
  "rate": 26.375,
  "note": "Germany flat capital gains tax incl. solidarity surcharge"
}
```

What it does NOT do

It does not calculate taxes

It does not handle tax brackets

It does not consider residency or asset type

It does not replace professional advice

Tech stack

Java 17

Spring Boot 3.3.x

Maven

Embedded Tomcat

How to run

Requirements:

Java 17

Maven (via SDKMAN recommended)

Run:

mvn clean spring-boot:run


Open:

http://localhost:8080/api/cgt?country=DE