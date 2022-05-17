package coupon_project.controller;

import coupon_project.entities.Categories;
import coupon_project.exception.*;
import coupon_project.login.ClientType;
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

@RestController
@RequestMapping("/Customer")
@RequiredArgsConstructor
@CrossOrigin
@ResponseBody
public class CustomerController {

    private final CompanyService companyService;
    private final CustomerService customerService;
    private final AdminService adminService;
    @Autowired
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam int clientId, @RequestParam String email, @RequestParam String password,@RequestParam ClientType clientType) throws LoginException {
        return jwtUtil.generateToken(clientId, email, password, clientType);
    }

//    @RequestMapping(value = "/coupon_purchase/{Id}/{id}", method = RequestMethod.POST)
    @PostMapping("/coupon_purchase/{customerId}/{couponId}")
    public ResponseEntity<?> addCouponPurchase(@RequestParam String token, @PathVariable int customerId, @PathVariable int couponId) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.CUSTOMER, token)){
            throw new InvalidException("Wrong client type");
        }
        customerService.addCouponPurchase(customerId, couponId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get_all_customer's_coupons{id}")
    public ResponseEntity<?> getAllCustomerCoupon(@RequestParam String token, @PathVariable int id) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.CUSTOMER, token)){
            throw new InvalidException("Wrong client type");
        }
        return new ResponseEntity<>(customerService.getAllCustomerCoupons(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_customer's_coupons_by_category{id}")
    public ResponseEntity<?> getCustomersCouponsByCategory(@RequestParam String token, @PathVariable int id, @RequestParam Categories categories) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.CUSTOMER, token)){
            throw new InvalidException("Wrong client type");
        }
        return new ResponseEntity<>(customerService.getAllCustomerCouponsByCategory(id, categories), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_customer's_coupons_by_max_price{id}")
    public ResponseEntity<?> getCustomersCouponsByMaxPrice(@RequestParam String token, @PathVariable int id, @RequestParam int maxPrice) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.CUSTOMER, token)){
            throw new InvalidException("Wrong client type");
        }
        return new ResponseEntity<>(customerService.getAllCustomerCouponsByMacPrice(id, maxPrice), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_customer's_detail{id}")
    public ResponseEntity<?> getCustomerDetail(@PathVariable int id, @RequestParam String token) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.CUSTOMER, token)){
            throw new InvalidException("Wrong client type");
        }
        return new ResponseEntity<>(customerService.getCustomerDetails(id), HttpStatus.ACCEPTED);
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
