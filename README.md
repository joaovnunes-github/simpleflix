# SimpleFlix

**Author:** João Vítor Nunes

## Description

SimpleFlix is a straightforward media streaming back-end application designed for simplicity and ease of use. It incorporates a combination of technologies for effective media and metadata persistence, leveraging Spring IoC (Inversion of Control), object-oriented principles, strategies, and unit testing to ensure robust functionality.

## Implementation Details

- **S3 for Media Persistence:** SimpleFlix utilizes Amazon S3 for efficient and scalable storage of media content.

- **JPA H2 for Metadata Persistence:** Metadata related to the media is stored using JPA (Java Persistence API) with H2, providing a lightweight and embedded database solution.

- **Spring IoC:** The project employs the Spring Framework's Inversion of Control to manage and wire components, enhancing modularity and maintainability.

- **Object-Oriented Design:** SimpleFlix adheres to object-oriented principles to create a clear and extensible codebase.

- **Strategy Pattern:** The application leverages the Strategy Pattern to dynamically select the streaming strategy based on runtime conditions, offering flexibility and maintainability.

- **Unit Testing:** Robust unit tests are implemented to ensure the reliability and correctness of the application's functionalities.

## Getting Started

To get started with SimpleFlix, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/joaokanta/simpleflix.git
