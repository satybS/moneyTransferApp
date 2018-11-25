package com.revolut.moneytransfer;

public interface ApplicationConstants {

    interface Persistence {
        String VALUE = "models";
    }

    interface Controllers {

        interface AccountController {
            String PATH = "/account";

            interface Account {
                String PATH = "{accountId}";
            }
        }

        interface CustomerController {
            String PATH = "/customer";

            interface Customer {
                String PATH = "{customerId}";
            }
            interface Accounts {
                String PATH = "{customerId}/accounts";
            }
        }
        interface TransferController {
            String PATH = "/transfer";
        }
    }
}
