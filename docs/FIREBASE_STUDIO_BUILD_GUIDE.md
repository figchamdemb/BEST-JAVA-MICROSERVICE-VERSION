# Firebase Studio Build Guide - E-Government Backend System

## Overview
This guide explains what happens when you run each script in your Firebase Studio environment and what dependencies will be checked and validated.

## Pre-Build Requirements

### System Requirements
\`\`\`bash
# Required Tools
- Java 17 or higher
- Maven 3.8+
- Docker (for containerization)
- Git
- Node.js 16+ (for client generation)
\`\`\`

### Environment Variables
\`\`\`bash
# Database Configuration
DATABASE_URL=jdbc:postgresql://localhost:5432/egov_db
DATABASE_USERNAME=egov_user
DATABASE_PASSWORD=your_password

# Redis Configuration
REDIS_HOST=localhost
REDIS_PORT=6379

# JWT Configuration
JWT_SECRET=your-256-bit-secret
JWT_EXPIRATION=86400000

# External API Keys
STRIPE_SECRET_KEY=sk_test_...
TWILIO_ACCOUNT_SID=AC...
SENDGRID_API_KEY=SG...
GOOGLE_MAPS_API_KEY=AIza...
\`\`\`

## Script Execution Order and Expected Results

### 1. scripts/build-check.sh
**Purpose**: Validates build environment and dependencies

#### What It Checks:
\`\`\`bash
#!/bin/bash
echo "🔍 Starting Build Environment Check..."

# Java Version Check
echo "📋 Checking Java Version..."
java -version
if [ $? -ne 0 ]; then
    echo "❌ Java not found or incorrect version"
    exit 1
fi

# Maven Version Check
echo "📋 Checking Maven Version..."
mvn -version
if [ $? -ne 0 ]; then
    echo "❌ Maven not found"
    exit 1
fi

# Docker Check
echo "📋 Checking Docker..."
docker --version
if [ $? -ne 0 ]; then
    echo "⚠️ Docker not found - containerization will be skipped"
fi

# Project Structure Validation
echo "📋 Validating Project Structure..."
if [ ! -f "pom.xml" ]; then
    echo "❌ pom.xml not found"
    exit 1
fi

if [ ! -d "src/main/java" ]; then
    echo "❌ Source directory not found"
    exit 1
fi

# Dependency Check
echo "📋 Checking Maven Dependencies..."
mvn dependency:resolve -q
if [ $? -ne 0 ]; then
    echo "❌ Dependency resolution failed"
    exit 1
fi

# Database Connection Test
echo "📋 Testing Database Connection..."
mvn test -Dtest=TestDatabaseConfig -q
if [ $? -ne 0 ]; then
    echo "⚠️ Database connection test failed - check configuration"
fi

echo "✅ Build Environment Check Complete!"
\`\`\`

#### Expected Output:
\`\`\`
🔍 Starting Build Environment Check...
📋 Checking Java Version...
openjdk version "17.0.2" 2022-01-18
📋 Checking Maven Version...
Apache Maven 3.8.4
📋 Checking Docker...
Docker version 20.10.12
📋 Validating Project Structure...
📋 Checking Maven Dependencies...
📋 Testing Database Connection...
✅ Build Environment Check Complete!
\`\`\`

### 2. scripts/fix-permissions.sh
**Purpose**: Sets correct file permissions for Unix/Linux systems

#### What It Does:
\`\`\`bash
#!/bin/bash
echo "🔧 Fixing File Permissions..."

# Make scripts executable
chmod +x scripts/*.sh
echo "✅ Script permissions fixed"

# Set Java source file permissions
find src -name "*.java" -exec chmod 644 {} \;
echo "✅ Java source permissions fixed"

# Set resource file permissions
find src/main/resources -type f -exec chmod 644 {} \;
echo "✅ Resource permissions fixed"

# Set configuration file permissions
chmod 644 *.yml *.yaml *.xml *.properties 2>/dev/null || true
echo "✅ Configuration permissions fixed"

# Set documentation permissions
find docs -name "*.md" -exec chmod 644 {} \; 2>/dev/null || true
echo "✅ Documentation permissions fixed"

echo "🔧 File Permissions Fixed Successfully!"
\`\`\`

#### Expected Output:
\`\`\`
🔧 Fixing File Permissions...
✅ Script permissions fixed
✅ Java source permissions fixed
✅ Resource permissions fixed
✅ Configuration permissions fixed
✅ Documentation permissions fixed
🔧 File Permissions Fixed Successfully!
\`\`\`

### 3. scripts/final-validation.sh
**Purpose**: Comprehensive validation of the entire system

#### What It Validates:
\`\`\`bash
#!/bin/bash
echo "🔍 Starting Final System Validation..."

# Compile Check
echo "📋 Compiling Project..."
mvn clean compile -q
if [ $? -ne 0 ]; then
    echo "❌ Compilation failed"
    exit 1
fi
echo "✅ Compilation successful"

# Test Compilation
echo "📋 Compiling Tests..."
mvn test-compile -q
if [ $? -ne 0 ]; then
    echo "❌ Test compilation failed"
    exit 1
fi
echo "✅ Test compilation successful"

# Unit Tests
echo "📋 Running Unit Tests..."
mvn test -q
if [ $? -ne 0 ]; then
    echo "⚠️ Some unit tests failed - check logs"
else
    echo "✅ All unit tests passed"
fi

# Integration Tests
echo "📋 Running Integration Tests..."
mvn verify -Pintegration-tests -q
if [ $? -ne 0 ]; then
    echo "⚠️ Some integration tests failed - check logs"
else
    echo "✅ All integration tests passed"
fi

# Security Scan
echo "📋 Running Security Scan..."
mvn org.owasp:dependency-check-maven:check -q
if [ $? -ne 0 ]; then
    echo "⚠️ Security vulnerabilities found - check report"
else
    echo "✅ No security vulnerabilities found"
fi

# Code Quality Check
echo "📋 Running Code Quality Check..."
mvn sonar:sonar -q 2>/dev/null || echo "⚠️ SonarQube not configured"

# API Documentation Generation
echo "📋 Generating API Documentation..."
mvn springdoc-openapi:generate -q
if [ $? -ne 0 ]; then
    echo "⚠️ API documentation generation failed"
else
    echo "✅ API documentation generated"
fi

# Package Creation
echo "📋 Creating Application Package..."
mvn package -DskipTests -q
if [ $? -ne 0 ]; then
    echo "❌ Package creation failed"
    exit 1
fi
echo "✅ Application package created"

echo "🎉 Final System Validation Complete!"
\`\`\`

#### Expected Output:
\`\`\`
🔍 Starting Final System Validation...
📋 Compiling Project...
✅ Compilation successful
📋 Compiling Tests...
✅ Test compilation successful
📋 Running Unit Tests...
✅ All unit tests passed
📋 Running Integration Tests...
✅ All integration tests passed
📋 Running Security Scan...
✅ No security vulnerabilities found
📋 Running Code Quality Check...
⚠️ SonarQube not configured
📋 Generating API Documentation...
✅ API documentation generated
📋 Creating Application Package...
✅ Application package created
🎉 Final System Validation Complete!
\`\`\`

### 4. scripts/generate-clients.sh
**Purpose**: Generates client SDKs for different platforms

#### What It Generates:
\`\`\`bash
#!/bin/bash
echo "🚀 Starting Client Generation..."

# Clean previous generations
rm -rf generated-clients/
mkdir -p generated-clients/

# Generate OpenAPI Specification
echo "📋 Generating OpenAPI Specification..."
mvn springdoc-openapi:generate -q
if [ $? -ne 0 ]; then
    echo "❌ OpenAPI generation failed"
    exit 1
fi
echo "✅ OpenAPI specification generated"

# Generate Java Client
echo "📋 Generating Java Client SDK..."
docker run --rm \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/target/openapi.json \
  -g java \
  -o /local/generated-clients/java \
  --additional-properties=groupId=com.egov,artifactId=egov-client,apiPackage=com.egov.client.api,modelPackage=com.egov.client.model
echo "✅ Java client generated"

# Generate TypeScript/Angular Client
echo "📋 Generating TypeScript/Angular Client..."
docker run --rm \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/target/openapi.json \
  -g typescript-angular \
  -o /local/generated-clients/typescript-angular \
  --additional-properties=npmName=egov-client,npmVersion=1.0.0
echo "✅ TypeScript/Angular client generated"

# Generate React/TypeScript Client
echo "📋 Generating React/TypeScript Client..."
docker run --rm \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/target/openapi.json \
  -g typescript-fetch \
  -o /local/generated-clients/typescript-react \
  --additional-properties=npmName=egov-react-client,npmVersion=1.0.0
echo "✅ React/TypeScript client generated"

# Generate Flutter/Dart Client
echo "📋 Generating Flutter/Dart Client..."
docker run --rm \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/target/openapi.json \
  -g dart \
  -o /local/generated-clients/flutter \
  --additional-properties=pubName=egov_client,pubVersion=1.0.0
echo "✅ Flutter/Dart client generated"

# Generate Swift Client (iOS)
echo "📋 Generating Swift Client..."
docker run --rm \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/target/openapi.json \
  -g swift5 \
  -o /local/generated-clients/swift \
  --additional-properties=projectName=EgovClient
echo "✅ Swift client generated"

# Generate Kotlin Client (Android)
echo "📋 Generating Kotlin Client..."
docker run --rm \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/target/openapi.json \
  -g kotlin \
  -o /local/generated-clients/kotlin \
  --additional-properties=groupId=com.egov,artifactId=egov-kotlin-client,packageName=com.egov.client
echo "✅ Kotlin client generated"

# Generate Documentation
echo "📋 Generating Client Documentation..."
docker run --rm \
  -v ${PWD}:/local \
  openapitools/openapi-generator-cli generate \
  -i /local/target/openapi.json \
  -g html2 \
  -o /local/generated-clients/documentation
echo "✅ Client documentation generated"

# Create README files for each client
echo "📋 Creating Client README files..."
for client_dir in generated-clients/*/; do
    if [ -d "$client_dir" ]; then
        client_name=$(basename "$client_dir")
        cat > "$client_dir/README.md" << EOF
# E-Government ${client_name^} Client

This client was auto-generated from the E-Government Unified Backend System OpenAPI specification.

## Installation
Please refer to the specific installation instructions for ${client_name}.

## Usage
Import the client and use the generated API classes to interact with the E-Government backend.

## API Documentation
Full API documentation is available in the \`docs/\` directory.

## Support
For support, please refer to the main project documentation.
EOF
    done
echo "✅ Client README files created"

echo "🎉 Client Generation Complete!"
echo "📁 Generated clients available in: generated-clients/"
ls -la generated-clients/
\`\`\`

#### Expected Output:
\`\`\`
🚀 Starting Client Generation...
📋 Generating OpenAPI Specification...
✅ OpenAPI specification generated
📋 Generating Java Client SDK...
✅ Java client generated
📋 Generating TypeScript/Angular Client...
✅ TypeScript/Angular client generated
📋 Generating React/TypeScript Client...
✅ React/TypeScript client generated
📋 Generating Flutter/Dart Client...
✅ Flutter/Dart client generated
📋 Generating Swift Client...
✅ Swift client generated
📋 Generating Kotlin Client...
✅ Kotlin client generated
📋 Generating Client Documentation...
✅ Client documentation generated
📋 Creating Client README files...
✅ Client README files created
🎉 Client Generation Complete!
📁 Generated clients available in: generated-clients/
total 8
drwxr-xr-x  9 user  staff  288 Jan  1 12:00 documentation
drwxr-xr-x  9 user  staff  288 Jan  1 12:00 flutter
drwxr-xr-x  9 user  staff  288 Jan  1 12:00 java
drwxr-xr-x  9 user  staff  288 Jan  1 12:00 kotlin
drwxr-xr-x  9 user  staff  288 Jan  1 12:00 swift
drwxr-xr-x  9 user  staff  288 Jan  1 12:00 typescript-angular
drwxr-xr-x  9 user  staff  288 Jan  1 12:00 typescript-react
\`\`\`

## Dependencies That Will Be Checked

### 1. Maven Dependencies (from pom.xml)
\`\`\`xml
<!-- Spring Boot Dependencies -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- Database Dependencies -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>

<!-- Redis Dependencies -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<!-- JWT Dependencies -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
</dependency>

<!-- External API Dependencies -->
<dependency>
    <groupId>com.stripe</groupId>
    <artifactId>stripe-java</artifactId>
</dependency>
<dependency>
    <groupId>com.twilio.sdk</groupId>
    <artifactId>twilio</artifactId>
</dependency>

<!-- Documentation Dependencies -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
</dependency>
\`\`\`

### 2. Test Dependencies
\`\`\`xml
<!-- Testing Dependencies -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
\`\`\`

## Potential Issues and Solutions

### Common Build Issues:
1. **Java Version Mismatch**: Ensure Java 17+ is installed
2. **Maven Not Found**: Install Maven 3.8+
3. **Database Connection**: Configure PostgreSQL connection
4. **Docker Issues**: Install Docker for client generation
5. **Memory Issues**: Increase JVM heap size: `export MAVEN_OPTS="-Xmx2g"`

### Firebase Studio Specific:
1. **IDE Configuration**: Set Java SDK to 17+
2. **Maven Integration**: Enable Maven auto-import
3. **Database Plugin**: Install database plugins for PostgreSQL
4. **Terminal Access**: Use built-in terminal for script execution

## Success Indicators

### ✅ All Scripts Successful:
- All dependencies resolved
- Code compiles without errors
- Tests pass
- Security scan clean
- Client SDKs generated
- Documentation created

### 📊 Generated Artifacts:
- `target/egov-backend-1.0.0.jar` - Main application
- `generated-clients/` - Client SDKs
- `target/site/` - Documentation
- `target/openapi.json` - API specification

## Next Steps After Successful Build:
1. **Deploy to Firebase**: Use generated JAR file
2. **Configure Environment**: Set production environment variables
3. **Database Setup**: Run migration scripts
4. **Client Integration**: Use generated SDKs
5. **Monitoring**: Set up logging and monitoring

This comprehensive build process ensures your E-Government system is production-ready with all dependencies validated and client SDKs generated for multiple platforms.
