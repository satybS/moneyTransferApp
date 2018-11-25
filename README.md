# Money transfer app

This is test project for Revolut.com. It is simple REST application to transfer money from one account to another.


### Installing

This application run on H2 in-memory database, so no pre-installed software is required to run this application

## Endpoints
* `GET` /api/account/&lt;accountId&gt; - return account data for this id
* `GET` /api/customer/&lt;customerId&gt; - return customer data for this id
* `GET` /api/customer/&lt;customerId&gt;/accounts - returns all accounts of the customer(id)
* `POST` /api/transfer - money transaction from one account to another
  - example request body
  
  ```json
    {
      "from_account":"1",
      "to_account":"101",
      "amount":"10"
    }
  ```
### Notes 
Concurrent access to account is managed on a database level, by keeping current version of the entity and checking it on the write operation (optimistic lock). 
If higher data integrity will be required, then locking mechanism could be switch to pessimistic lock.
 
## How to run

Application on each run will populate some test data.

```
mvn exec:java
```

Application on startup will be available by this link http://localhost:8080

## Running the tests


```
mvn clean test
```

## Built With

* [Guice](https://github.com/google/guice) - Framework for DI
* [Jersey](https://jersey.github.io/) - RESTful Web Services framework
* [Hibernate](http://hibernate.org/) - ORM framework
* [Maven](https://maven.apache.org/) - Dependency Management


