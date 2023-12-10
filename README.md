# ConsentiFI - An Account Aggregator Client

This repository contains an Account Aggregator (AA) Client designed to interact seamlessly with the Onemoney AA Sandbox and Sahmati's Encryption/Decryption service, also known as Rahasya.

To ensure proper functionality, it's essential to have the Rahasya service (rahasya-svc) running locally and FCM(Firebase Cloud Messaging) Project settings (Check src/main/resources folder).

## API Documentation 

Swagger UI: http://localhost:9092/aa-svc/swagger-ui.html

## Important References

- **Onemoney AA Postman Documentation:** Explore the detailed Postman documentation [here](https://documenter.getpostman.com/view/12076903/TVYAfLCh) to understand the API interactions.

- **Rahasya Service Docker Image + Repository Information:** Access the Rahasya service Docker image and repository information on GitHub [here](https://github.com/Sahamati/rahasya).

## Working

1. **Consent Request Submission:**
   The client facilitates the submission of Consent Requests received from the user interface (UI) to Onemoney's backend. Consent request appears on the user's Onemoney AA Screen (Web/Mobile).

![image](https://github.com/GoluKumar4024/account-aggregator-client/assets/52768804/6df3b3e3-5d00-45f0-93c4-8f641678f113)

2. **Consent Approval Flow:**
   Once the consent is approved, the following logical flow takes place.

![aa-svc](https://github.com/GoluKumar4024/account-aggregator-client/assets/52768804/4f2c6c62-5f9e-4eb8-b010-3ee380529a43)
