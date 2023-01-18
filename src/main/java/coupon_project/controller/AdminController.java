package coupon_project.controller;

import coupon_project.entities.Company;
import coupon_project.entities.Customer;
import coupon_project.exception.*;
import coupon_project.login.ClientType;
import coupon_project.repositories.CompanyRepo;
import coupon_project.repositories.CouponRepo;
import coupon_project.repositories.CustomerRepo;
import coupon_project.services.AdminService;
import coupon_project.services.CompanyService;
import coupon_project.services.CustomerService;
import coupon_project.token_verify_login_master.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
@RequestMapping("/Administrator")
@RequiredArgsConstructor
@ResponseBody
@CrossOrigin
public class AdminController {

    private final CompanyService companyService;
    private final CustomerService customerService;
    private final AdminService adminService;
    @Autowired
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public String login(@RequestParam int clientId, @RequestParam String email, @RequestParam String password,@RequestParam ClientType clientType) throws LoginException {
        return jwtUtil.generateToken(clientId, email, password, clientType);
    }

    @PostMapping("/add_company")
    public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestParam String token) throws InvalidException, TokenExpException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
        adminService.addCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update_company")
    public ResponseEntity<?> updateCompany(@RequestBody Company company, @RequestParam String token) throws InvalidException, NotFoundException, TokenExpException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
        adminService.updateCompany(company);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete_company_by_id/{id}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable int id, @RequestParam String token) throws NotFoundException, InvalidException, TokenExpException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
        adminService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_all_companies")
    public ResponseEntity<?> getAllCompanies(){
        return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_company_by_id/{id}")
    public ResponseEntity<?> getOneCompany(@PathVariable int id,@RequestParam String token) throws InvalidException, TokenExpException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
//        adminService.getOneCompany(id);
        return new ResponseEntity<>(adminService.getOneCompany(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/add_customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestParam String token) throws InvalidException, TokenExpException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
        adminService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update_customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestParam String token) throws InvalidException, TokenExpException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
        adminService.updateCustomer(customer);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete_customer_by_id/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id, @RequestParam String token) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
        adminService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_all_customers")
    public ResponseEntity<?> getAllCustomers(@RequestParam String token) throws TokenExpException, InvalidException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
        return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_customer_by_id/{id}")
    public ResponseEntity<?> getOneCustomer(@PathVariable int id, @RequestParam String token) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.ADMINISTRATOR, token)){
            throw new InvalidException("Wrong client type");
        }
        return new ResponseEntity<>(adminService.getOneCustomer(id), HttpStatus.ACCEPTED);
    }


    @ExceptionHandler
    public String invalidExceptionHandler(InvalidException err){
        return err.getMessage();
    }

    @ExceptionHandler
    public String loginExceptionHandler(LoginException err){
        return err.getMessage();
    }

    @ExceptionHandler
    public String notFoundExceptionHandler(NotFoundException err){
        return err.getMessage();
    }

    @ExceptionHandler
    public String somethingWentWrongHandler(SomethingWentWrongException err){
        return err.getMessage();
    }

    @ExceptionHandler
    public String tokenExpExceptionHandler(TokenExpException err){
        return err.getMessage();
    }
}
