# account-aggregator-client
An Account Aggregator Client capable of interacting with Onemoney AA Sandbox and Sahmati's Encryption/Decryption service aka Rahasya.

Onemoney AA API documentation: https://documenter.getpostman.com/view/12076903/TVYAfLCh
Rahasya Service Docker Image + Repo Info: https://github.com/Sahamati/rahasya

The service submits the Consent Request received from the UI to Onemoney's Backend, which makes the consent request appear on User's Onemoney AA Screen (Web/Mobile). 
Once the Consent is approved, the following logical flow takes place.

![aa-svc](https://github.com/GoluKumar4024/account-aggregator-client/assets/52768804/4f2c6c62-5f9e-4eb8-b010-3ee380529a43)

