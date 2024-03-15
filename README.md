# SimpleFlix

**Author:** João Vítor Nunes

## Description

SimpleFlix is a straightforward media streaming back-end application designed for simplicity and ease of use. It
incorporates a combination of technologies for effective media and metadata persistence, leveraging Spring IoC (
Inversion of Control), object-oriented principles, strategies, and unit testing to ensure robust functionality.

## Implementation Details

1. **Java Spring Backend:** Developed using Java Spring, emphasizing best practices, design patterns, and the IoC
   paradigm.
2. **Database Management:** PostgreSQL handles metadata storage, with JPA facilitating seamless database connectivity.
3. **Media File Storage:** Amazon S3 serves as the repository for media files, ensuring scalable and efficient storage.
4. **Database Migrations:** Flyway is employed for organized and version-controlled database migrations.

## Getting Started

To get started with SimpleFlix, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/joaokanta/simpleflix.git

2. Seed and start databases:
    ```bash
   ./seed-databases.sh

3. Start application:
    ```bash
   mvn spring-boot:run

## Planned features


1. Remove hardcoded project configurations and start consuming them from a Spring Cloud Config instance.
2. User authentication