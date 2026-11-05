## Project Structure

```text
CC_CodingAssignment/
├── src/
│   ├── main/java/me/alejandrokatz/codingassignment/
│   │   ├── facades/
│   │   │   └── Ikea.java                     # Facade unifying page objects
│   │   ├── pageobjects/
│   │   │   ├── BasePage.java                 # Abstract page with reusable Selenium helpers
│   │   │   └── ikea/
│   │   │       ├── HomePage.java             # IKEA Home Page
│   │   │       ├── SearchResultsPage.java    # IKEA Search Results Page
│   │   │       └── CartPage.java             # IKEA Shopping Cart Page
│   │   └── utils/
│   │       └── NameUtility.java              # Assignment #3: Refactored initials utility
│   └── test/java/me/alejandrokatz/codingassignment/
│       ├── api/
│       │   └── RestfulApiTest.java           # Assignment #2: REST API tests
│       ├── e2e/
│       │   ├── BaseTest.java                 # E2E test setup & teardown
│       │   └── IkeaTest.java                 # Assignment #1: IKEA E2E flow
│       └── unit/
│           └── NameUtilityTest.java          # Assignment #3: Unit tests
├── pom.xml
└── README.md
```

---

## Execute all tests from the project root:

```bash
mvn test
```