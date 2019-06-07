
Java application for transferring money between accounts.

## Getting Started

Take the clone of the project and start the application using command mvn exec:java from command promt.
Application(jetty server) will be launched at port 8080 of localhost with some dummy data setup through H2 database.

### Prerequisites
Postman client to test the get and post calls

## Design
This app has 4 endpoints:

GET /account-management/accounts - to get all account details presnt in system.
#### Response:
[
    {
        "userName": "sachin",
        "emailId": "sac@gmail.com",
        "telphoneNo": "9013004011",
        "address": "nehru vihar",
        "accountBalance": 799,
        "currency": "EUR"
    },
    {
        "userName": "amit",
        "emailId": "amit@gmail.com",
        "telphoneNo": "1168888",
        "address": "UK",
        "accountBalance": 261,
        "currency": "EUR"
    }
]

GET /account-management/{accountId}/balance - to get the balance of a particular account ID.
#### Response: Integer number

POST /account-management/add - to add/create an account in database
#### BODY: {
        "userName": "amit",
        "emailId": "amit2@gmail.com",
        "telphoneNo": "9013004015",
        "address": "UK",
        "accountBalance": 260,
        "currency": "EUR"
    }

POST /money-transfer/transfer - to trnsfer money between two accounts.
#### BODY: {  
        "currency":"EUR",
        "transferredAmount":1,
        "sourceAccountId":1,
        "destinationAccountId":2
    }


