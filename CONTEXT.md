# TaxLens – Project Context (Authoritative)

## What this project is
TaxLens is a small Java 17 + Spring Boot application whose sole purpose is to
display **headline capital gains tax rates by country**.

This project is informational only.
It is not a tax calculator and does not aim for legal accuracy.

## Current scope (MVP)
- Java 17
- Spring Boot 3.3.x
- One REST endpoint:
  - `GET /api/cgt?country=XX`
- Returns JSON:
  - `rate` (number, percentage)
  - `note` (short explanatory string)
- Data is hardcoded in memory
- No persistence
- No authentication
- No external APIs

Supported countries (initial):
- DE (Germany)
- PL (Poland)
- AE (United Arab Emirates)

## What is explicitly out of scope
- Progressive tax brackets
- Asset-type differentiation
- Holding period rules
- Residency logic
- Deductions, exemptions, surcharges
- Databases
- User accounts
- External tax APIs

## Technical constraints
- Java version: 17
- Maven compiler:
  - `release=17`
  - `-parameters` enabled
- Spring Boot embedded Tomcat
- Keep configuration explicit and boring

## Current structure
