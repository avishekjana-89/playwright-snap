# Playwright Test Automation Framework

A robust test automation framework built using Playwright with Java, TestNG, and Extent Reports for testing the OrangeHRM application. This framework implements the Page Object Model design pattern and includes features like parallel execution, Docker support, and comprehensive reporting.

## 🛠 Tech Stack

- **Java**: Core programming language
- **Playwright**: Modern web testing and automation framework
- **TestNG**: Testing framework for test organization and parallel execution
- **Maven**: Build and dependency management
- **Extent Reports**: HTML report generation
- **Docker**: Containerized execution support
- **Lombok**: Reduces boilerplate code
- **Easy Random**: Test data generation

## 🏗 Project Structure

```
src/
├── main/java/com/
│   ├── extent/reports/       # Reporting configuration
│   ├── orangehrm/
│   │   ├── components/       # Reusable UI components
│   │   ├── models/          # Data models
│   │   ├── pages/           # Page Objects
│   │   └── rules/           # Business rules
│   ├── playwright/factory/   # Playwright configuration
│   └── testng/listener/     # TestNG listeners
└── test/
    ├── java/com/orangehrm/
    │   ├── common/          # Base test setup
    │   └── tests/           # Test classes
    └── resources/           # Test resources
```

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Docker (optional, for containerized execution)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/avishekjana-89/playwright-snap.git
   cd playwright-snap
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

### Running Tests

1. Run all tests:
   ```bash
   mvn clean test
   ```

2. Run specific test groups:
   ```bash
   mvn clean test -Dgroups=smoke
   mvn clean test -Dgroups=regression
   ```

### Docker Execution

1. Start the Docker containers:
   ```bash
   docker-compose up -d
   ```

2. Run tests:
   ```bash
   mvn clean test
   ```

3. Stop containers:
   ```bash
   docker-compose down
   ```

## 📊 Test Reports

- Extent Reports are generated after each test run in the `test-output` directory
- Reports include:
  - Test execution summary
  - Step-by-step test details
  - Screenshots for failed tests
  - Test execution time and status

## 🔑 Key Features

- Page Object Model implementation
- Data-driven testing support
- Parallel test execution
- Automatic test data generation using Easy Random
- Comprehensive HTML reports
- Docker support for containerized execution
- Reusable UI components
- Custom TestNG listeners for enhanced reporting

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add YourFeature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Create a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details
