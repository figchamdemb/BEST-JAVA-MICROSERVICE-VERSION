# E-Government API Client Generation Guide

This guide explains how to generate client libraries for the E-Government Unified Backend API using OpenAPI specifications.

## 🚀 Quick Start

### Prerequisites
- Node.js 16+ (for npm-based generation)
- Java 17+ (for Maven-based generation)
- Docker (for containerized generation)

### Method 1: Using NPM Script (Recommended)

\`\`\`bash
# Install OpenAPI Generator CLI
npm install -g @openapitools/openapi-generator-cli

# Generate all client libraries
npm run generate-clients

# Generate specific language
npm run generate-js      # JavaScript/TypeScript
npm run generate-python  # Python
npm run generate-java    # Java
\`\`\`

### Method 2: Using Bash Script

\`\`\`bash
# Make script executable
chmod +x scripts/generate-clients.sh

# Run generation script
./scripts/generate-clients.sh
\`\`\`

### Method 3: Using Maven

\`\`\`bash
# Generate Java client only
mvn clean generate-sources -f pom-client-generation.xml

# Generate all clients using profile
mvn clean generate-sources -f pom-client-generation.xml -Pgenerate-all-clients
\`\`\`

### Method 4: Using Docker

\`\`\`bash
# Build the generator image
docker build -f Dockerfile.client-generation -t egov-client-generator .

# Run client generation
docker run -v $(pwd)/generated-clients:/workspace/generated-clients egov-client-generator

# Or use Docker Compose
docker-compose -f docker-compose.client-generation.yml up
\`\`\`

## 📦 Generated Client Libraries

### JavaScript/TypeScript
- **Package**: `@egov/api-client`
- **Location**: `generated-clients/javascript/`
- **Installation**: `npm install @egov/api-client`

\`\`\`javascript
import { Configuration, AuthenticationApi } from '@egov/api-client';

const config = new Configuration({
  basePath: 'https://api.emergency.gov/v2',
  accessToken: 'your-jwt-token'
});

const authApi = new AuthenticationApi(config);
\`\`\`

### Python
- **Package**: `egov-api-client`
- **Location**: `generated-clients/python/`
- **Installation**: `pip install egov-api-client`

\`\`\`python
import egov_api_client
from egov_api_client.api import authentication_api

configuration = egov_api_client.Configuration(
    host="https://api.emergency.gov/v2",
    access_token="your-jwt-token"
)

with egov_api_client.ApiClient(configuration) as api_client:
    auth_api = authentication_api.AuthenticationApi(api_client)
