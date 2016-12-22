# dwolla-account-url-fetcher
A simple program that fetch dwolla account url given access token

Fill in the values in config.properties file

accessToken:
Navigate to the applications page to generate an account access token: https://dashboard-uat.dwolla.com/applications
If accessToken is expired, generate a new one. 
The resulting account url remains the same as long as you pass in accessToken associated with the same account.

dwollaApiClientBasePath:
Sandbox -- https://api-uat.dwolla.com
Production -- https://api.dwolla.com

