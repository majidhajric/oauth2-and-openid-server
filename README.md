# OAuth2 and OpenID Connect Server with Social Logins

## Overview

This project is a custom implementation of a Spring OAuth2 server with support for social logins and various cool
features. It provides authentication and authorization for your applications with an emphasis on security and user
convenience.

## Features

- [ ] OAuth2 Authentication
- [ ] Social Logins (e.g., Google, Facebook, GitHub)
- [ ] JWT Token Support
- [ ] User Profile Management
- [ ] Role-Based Access Control
- [ ] Two-Factor Authentication
- [ ] Password Reset Functionality
- [ ] OAuth2 Clients Management
- [ ] Customizable Scopes
- [ ] User Registration and Email Verification
- [ ] Access Revocation

## Getting Started

To run this project locally, follow these steps:

1. Clone the repository.
2. Configure settings in the application.yaml file.
3. Build and run the project.
4. Access the project via [http://localhost:9000](http://localhost:9000).

## Configuration

Make sure to update the following configuration files:

- `application.yaml` - Configure OAuth2 and social login settings.

## Usage

1. Users can register or log in using their preferred social accounts or by using email/password.
2. OAuth2 clients can request tokens. Management client is TODO.
3. Define roles and permissions for user access control in DB.
4. Customize scopes and user profiles as needed.
5. Implement password reset and two-factor authentication if required.

## Contributions

Contributions and suggestions are welcome. Feel free to submit issues and pull requests.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- Spring Security
- OAuth2 Framework

## Contact

For questions or assistance, please contact [Majid Hajric](mailto:majidhajric@gmail.com).
