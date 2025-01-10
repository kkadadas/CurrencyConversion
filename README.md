# CurrencyConversion
Currency Conversion in real time
This is a simple Spring Boot application that provides real-time currency conversion functionality.
I have already setup the API and the API access key which is used to get the real time data, I have used FREE FOREX API for the conversion rates

## Prerequisites

- Java 17 or higher
- Maven

1.  Clone this repository:


2. Build the application:

    ```
    mvn clean install
    ```

3. Run the application:

    ```
    mvn spring-boot:run
    or
    Right click on the CurrencyConverterApplivation.java and run as java application to run on the local host
    ```

4. The application will be available at `http://localhost:8080`.

5. Tested using POSTMAN.

## API Endpoints

### 1. GET /api/rates?base=USD

Fetch the exchange rates for a given base currency. Default is `USD`.

### 2. POST /api/convert

Convert an amount from one currency to another:

**Request:**

```json
{
    "from": "USD",
    "to": "EUR",
    "amount": 100
}




