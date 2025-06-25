# Database Design

## Overview
The database is designed to support multi-tenant architecture with proper data isolation and audit trails.

## Key Design Principles
- **Multi-tenant**: All tables include tenant_id for data isolation
- **Auditable**: All tables include audit fields (created_at, updated_at, created_by, updated_by)
- **Scalable**: Proper indexing and partitioning strategies
- **Secure**: Row-level security for sensitive data

## Core Tables
- **tenants**: Tenant management
- **users**: User accounts and profiles
- **roles**: Role-based access control
- **incidents**: Emergency incident management
- **vehicles**: Vehicle registration and tracking
- **addresses**: Address verification and management

## Relationships
- Users belong to tenants
- Incidents are assigned to officers
- Vehicles are owned by users
- Addresses are verified by agents
