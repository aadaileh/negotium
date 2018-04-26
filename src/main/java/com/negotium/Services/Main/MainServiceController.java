package com.negotium.Services.Main;

import com.negotium.Clients.FeignClient;
import com.negotium.DTOs.*;
import com.negotium.Factory.CommonFactoryAbstract;
import com.negotium.Services.Main.impl.MainServiceImplentations;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <h1>Main Service for the Coursework: NEGOTIUM</h1>
 * <p>
 * It is always the first point to get requests and distribute them to different other services.
 * The whole communication is done based on the NetFlix Feign Client. This service negotiate with: Authentication-service
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   16.04.2018
 */
@RestController
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableSwagger2
public class MainServiceController extends CommonFactoryAbstract implements MainServiceInterface {

    private static final Logger LOG = LoggerFactory.getLogger(MainServiceController.class);

    @Autowired
    private MainServiceImplentations mainServiceImplentations;

    /**
     * Register new job-seekers to NEGOTIUM. This method saves entered data to
     * both tables: credentials, users, contact_information. It does generate a UUID token
     * that will be used in the confirmation process.
     *
     * @return user user's data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Register job-seekers to NEGOTIUM")
    @RequestMapping(value = "/negotium/api/register",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public String registerUser(@RequestBody User user) throws SQLException {

        return mainServiceImplentations.registerUser(user);
    }

    /**
     * Confirm the new job-seekers registered to NEGOTIUM. This method checks the code
     * taken from link and compare it to the one in db, if matches, returns ok, otherwise
     * false.
     *
     * @return boolean true if matches, false if not
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Confirm job-seekers registration to NEGOTIUM")
    @RequestMapping(value = "/negotium/api/confirm/{code}",
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public Boolean confirm(@PathVariable String code) throws SQLException {

        Boolean credentialsId = mainServiceImplentations.confirm(code);
        return credentialsId;
    }

    /**
     * Verify the credentials by checking the database table credentials.
     *
     * @return User if credentials are coorect, or NULL if not
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Verify the credentials of job-seekers and recruitment agencies")
    @RequestMapping(value = "/negotium/api/login/",
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    @Override
    public User login(@RequestBody Credentials credentials) throws SQLException {
        return mainServiceImplentations.verifyCredentials(credentials);
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    /**
     * Method to retrieve all transactions related to account based on the client-id. All
     * kind of transaction returned sorted according timestamp. If no results found, empty
     * Account object is returned.
     *
     * @param clientId contains client-id
     * @return Account transactions data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Retrieves all account details and transactions related to the given client-id")
    @RequestMapping(value = "/api/main-service/account/{clientId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public ArrayList<Transaction> getAccountDetails(@PathVariable String clientId) {

        FeignClient feignClient = getFeignClient("/api/account-service/account/");

        ArrayList<Transaction> accountDetails = feignClient.getAccountDetailsFromClient(clientId);
        return accountDetails;
    }

    /**
     * Method to perform a fund transfer process. Usually the fund-transfer process consists from the following steps:
     * 1) get the available balance, 2) get transfer details 3) check transfer details by (comparing balance to request)
     * and (verifying benificiary details). 4) Update Account. 5) Add record to the main transfer table 6) Print receipt.
     * All these methods will be available in both transaction-service and account-service. These will be called from here.
     *
     * @param fundTransferRequest transfer's all related fields
     * @return Account transactions data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Perform all necessary actions to perform a fund transfer")
    @RequestMapping(value = "/api/main-service/transfer-fund",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public FundTransferResponse transferFunds(@RequestBody FundTransferRequest fundTransferRequest) {

        //GET  /api/account-service/balance/{clientId}
        FeignClient feignClientAccountServiceBalance = getFeignClient("/api/account-service/balance/");
        double accountBalance = feignClientAccountServiceBalance.getAccountBalance(fundTransferRequest.getClientId());

        //POST /api/transaction-service/check
        FeignClient feignClientTransactionServiceCheck = getFeignClient("/api/transaction-service/check");
        fundTransferRequest.setAvailableBalance(accountBalance);
        FundTransferResponse fundTransferResponse = feignClientTransactionServiceCheck.verifyTransfer(fundTransferRequest);

        //POST /api/transaction-service/transaction/add
        FeignClient feignClientTransactionServiceAdd = getFeignClient("/api/transaction-service/transaction/add");
        Boolean performTransfer = feignClientTransactionServiceAdd.performTransfer(fundTransferRequest);
        fundTransferResponse.setPerformTransferStatus(true);

        //PUT /api/account-service/update
        if(performTransfer != null && performTransfer) {
            FeignClient feignClientAccountServiceUpdate = getFeignClient("/api/account-service/update/");
            feignClientAccountServiceUpdate.updateAccountTable(fundTransferRequest);
            fundTransferResponse.setUpdateAccountStatus(true);
        }

        fundTransferResponse.setBalance(accountBalance);
        return fundTransferResponse;
    }

    /**
     * Method to deposit funds to the account. Usually the deposit process consists from the following steps:
     * 1) Initiate the mechanical process to get and count the money. 2) Update Account records. 3) Print receipt.
     * All these methods will be available in both transaction-service and account-service. These will be called from here.
     *
     * @param fundTransferRequest transfer's all related fields
     * @return Account transactions data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Deposits money to own account via ATM")
    @RequestMapping(value = "/api/main-service/deposit",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
    public FundTransferResponse deposit(@RequestBody FundTransferRequest fundTransferRequest) {

        //1) Initaing the mechanical process to get and count the money
        FeignClient feignClientAccountServiceGetAndCount = getFeignClient("");
        boolean getAndCountResult = feignClientAccountServiceGetAndCount.getAndCount();

        //2) Update Account records.
        FeignClient feignClientAccountServiceUpdate = getFeignClient("/api/account-service/update/");
        feignClientAccountServiceUpdate.updateAccountTable(fundTransferRequest);

        FundTransferResponse fundTransferResponse = new FundTransferResponse();
        fundTransferResponse.setUpdateAccountStatus(true);
        return fundTransferResponse;
    }

    /**
     * Method to withdraw money from ATM. Usually the withdrawal process consists from the following steps:
     * 1) get the available balance, 2) check transfer details by (comparing balance to request) 3) Update
     * Account. 4) Initiate mechanical process to deliver money 5) Print receipt.
     * All these methods will be available in both transaction-service and account-service. These will be called from here.
     *
     * @param fundTransferRequest transfer's all related fields
     * @return Account transactions data (if success), or null in case of failure
     *
     * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    @ApiOperation("Withdraws money from own account via ATM")
    @RequestMapping(value = "/api/main-service/withdraw",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.")})
        public FundTransferResponse withdraw(@RequestBody FundTransferRequest fundTransferRequest) {

        //1) get the available balance
        FeignClient feignClientAccountServiceBalance = getFeignClient("/api/account-service/balance/");
        double accountBalance = feignClientAccountServiceBalance.getAccountBalance(fundTransferRequest.getClientId());

        //2) check transfer details by (comparing balance to request)
        FeignClient feignClientTransactionServiceCheck = getFeignClient("/api/transaction-service/check");
        fundTransferRequest.setAvailableBalance(accountBalance);
        FundTransferResponse fundTransferResponse = feignClientTransactionServiceCheck.verifyTransfer(fundTransferRequest);

        //3) Update Account.
        if(fundTransferResponse.isResults()) {
            FeignClient feignClientAccountServiceUpdate = getFeignClient("/api/account-service/update/");
            feignClientAccountServiceUpdate.updateAccountTable(fundTransferRequest);
            fundTransferResponse.setUpdateAccountStatus(true);
        }

        //4) Initiate mechanical process to deliver money
        FeignClient feignClientTransactionServiceDeliverCash = getFeignClient("");
        fundTransferRequest.setAvailableBalance(accountBalance);
        feignClientTransactionServiceDeliverCash.deliverCash();

        fundTransferResponse.setBalance(accountBalance);
        return fundTransferResponse;
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
