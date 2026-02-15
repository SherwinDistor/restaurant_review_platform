# TableTalk - Restaurant Review Platform

TableTalk is a full-stack restaurant review system. It allows food enthusiasts to discover new dining spots, share their experiences through reviews, and search for restaurants based on specific criteria like cuisine, name, or location.

## Key Features

- **Advanced Search:** Query restaurants dynamically by name, cuisine type, or geographic location.
- **Review System:** Authenticated users can provide feedback and ratings for restaurants.
- **Admin Management:** Dedicated administrative roles to create, update, and manage restaurant listings and associated media.
- **Cloud Media Hosting:** Integrated with Cloudinary for professional-grade image storage and delivery.
- **Security:** Implements RSA-signed JWTs for high-security stateless authentication.

## Tech Stack

- **Framework:** Spring Boot 4.0.0
- **Language:** Java 21
- **Security:** Spring Security 6+ with OAuth2 Resource Server
- **Database:** PostgreSQL (Production), H2(Testing/Dev)
- **Storage:** Cloudinary API
- **Utilities:** Project Lombok

## Security Implementation

The security architecture of TableTalk is build on a Stateless OAuth2 Resource Server model using JSON Web Tokens (JWT).

### RSA Asymmetric Encryption

Unlike standard secret-key setups, this project uses RSA Public/Private key pairs:

1. **Private Key:** Used by the server to sign the tokens during login.
2. **Public Key:** Used by the JwtDecoder to verity the token's authenticity. This ensures that even if the public key is exposed, tokens cannot be forged.

### Access Control Policy

| HTTP Method         | Path                    | Description        |
| :------------------ | :---------------------- | :----------------- |
| **GET/POST**        | `/auth/**`              | Public             |
| **GET**             | `/api/v1/restaurant/**` | Public             |
| **POST/PUT/DELETE** | `/api/v1/restaurant/**` | Admin Only         |
| **POST/DELETE**     | `/api/v1/photo/**`      | Admin Only         |
| **ALL**             | `/api/v1/reviews/**`    | Authenticated User |

## Installation & Setup

1. **Clone the repository**

```bash
git clone https://github.com/SherwinDistor/restaurant_review_platform.git
cd restaurant_review_platform
```

2. **Configure Environment Variables**

Update `src/main/resources/application.properties` with your PostgreSQL, Cloudinary, and Admin credentials:

```bash
# Database settings
spring.datasource.url=${DATABASE_URL}

# Admin settings
security.email=${SECURITY_EMAIL}
security.password=${SECURITY_PASSWORD}

# Cloudinary settings
cloudinary.cloud-name=${CLOUDINARY_NAME}
cloudinary.api-key=${CLOUDINARY_KEY}
cloudinary.api-secret=${CLOUDINARY_SECRET}

```

3. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```
