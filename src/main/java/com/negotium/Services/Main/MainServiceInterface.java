package com.negotium.Services.Main;

import com.negotium.DTOs.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <h1>Main Service Interface for the Coursework: ABC Banking Group</h1>
 *
 * <p>
 * Here is the declaration and definition of all methods implemented in the
 * related controller. It is always the first point to get requests and
 * distribute them to different other services. The whole communication is
 * done based on the NetFlix Feign Client. This service negotiate with:
 * Authentication-service, Account-service and Transaction-service.
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
public interface MainServiceInterface {
//
//        /**
//         * Method to verify the given credentials. Credentials can be either coming from ATM (card-id, pin) or
//         * Online banking (username, password). This method makes a use of the login-service via Feign client.
//         * It returns either the logged in user's data, in case of success. Or NULL, in case of failure.
//         *
//         * @param credentials contains user's credentials
//         * @return user user's data (if success), or null in case of failure
//         *
//         * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
//         */
//        public User login(@RequestBody Credentials credentials) throws SQLException;
//
//        /**
//         * Return the logged in user's data upon need. It returns either the requested user's data,
//         * in case it is found, Or empty object, in case of failure.
//         *
//         * @param username contains user's username
//         * @return user user's data (if success), or null in case of failure
//         *
//         * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
//         */
//        public User getUser(@PathVariable String username);
//
//        /**
//         * Method to retrieve all transactions related to account based on the client-id. All
//         * kind of transaction returned sorted according timestamp. If no results found, empty
//         * Account object is returned.
//         *
//         * @param clientId contains client-id
//         * @return Account transactions data (if success), or null in case of failure
//         *
//         * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
//         */
//        public ArrayList<Transaction> getAccountDetails(@PathVariable String clientId);
//
//        /**
//         * Method to fullfill a fund transfer process. Usually the fund-transfer process consists from the following steps:
//         * 1) get the available balance, 2) get transfer details 3) check transfer details by (comparing balance to request)
//         * and (verifying benificiary details). 4) Update Account. 5) Add record to the main transfer table 6) Print receipt.
//         * All these methods will be available in both transaction-service and account-service. These will be called from here.
//         *
//         * @param fundTransferRequest transfer's all related fields
//         * @return Account transactions data (if success), or null in case of failure
//         *
//         * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
//         */
//        public Response transferFunds(@RequestBody FundTransferRequest fundTransferRequest);
//
//        /**
//         * Method to deposit funds to the account. Usually the deposit process consists from the following steps:
//         * 1) Initiate the mechanical process to get and count the money. 2) Update Account records. 3) Print receipt.
//         * All these methods will be available in both transaction-service and account-service. These will be called from here.
//         *
//         * @param fundTransferRequest transfer's all related fields
//         * @return Account transactions data (if success), or null in case of failure
//         *
//         * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
//         */
//        public Response deposit(@RequestBody FundTransferRequest fundTransferRequest);
//
//        /**
//         * Method to withdraw money from ATM. Usually the withdrawal process consists from the following steps:
//         * 1) get the available balance, 2) check transfer details by (comparing balance to request) 3) Update
//         * Account. 4) Initiate mechanical process to deliver money 5) Print receipt.
//         * All these methods will be available in both transaction-service and account-service. These will be called from here.
//         *
//         * @param fundTransferRequest transfer's all related fields
//         * @return Account transactions data (if success), or null in case of failure
//         *
//         * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
//         */
//        public Response withdraw(@RequestBody FundTransferRequest fundTransferRequest);
}
