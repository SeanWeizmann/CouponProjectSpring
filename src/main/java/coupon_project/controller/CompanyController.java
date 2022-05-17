package coupon_project.controller;

import coupon_project.entities.Categories;
import coupon_project.entities.Coupon;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Company")
@RequiredArgsConstructor
@ResponseBody
@CrossOrigin
public class CompanyController {

    private final CompanyService companyService;
    private final CustomerService customerService;
    private final AdminService adminService;
    @Autowired
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam int clientId, @RequestParam String email, @RequestParam String password, @RequestParam ClientType clientType) throws LoginException {
        return jwtUtil.generateToken(clientId, email, password, clientType);
    }

    @PostMapping("/add_coupon")
    public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestParam String token) throws TokenExpException, InvalidException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.COMPANY, token)){
            throw new InvalidException("Wrong client type");
        }
        companyService.addCoupon(coupon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update_coupon")
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestParam String token) throws InvalidException, TokenExpException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.COMPANY, token)){
            throw new InvalidException("Wrong client type");
        }
        companyService.updateCoupon(coupon);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete_coupon_by_id/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable int id, @RequestParam String token) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.COMPANY, token)){
            throw new InvalidException("Wrong client type");
        }
        companyService.deleteCoupon(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_company's_coupons_by_id/{id}")
    public ResponseEntity<?> getAllCouponsOfCompany(@PathVariable int id, @RequestParam String token) throws TokenExpException, InvalidException, NotFoundException {
        if (!jwtUtil.isTokenExpired(token)){
            throw new TokenExpException("token expired");
        }
        if (!jwtUtil.checkClientType(ClientType.COMPANY, token)){
            throw new InvalidException("Wrong client type");
        }
        return new ResponseEntity<>(companyService.getAllCouponsOfTheSameCompany(id), HttpStatus.OK);
    }

    @GetMapping("/get_company's_coupon_by_category/{id}")
    public ResponseEntity<?> getAllCouponsOfCompanyByCategory(@PathVariable int id, @RequestParam Categories categories) throws NotFoundException {
        return new ResponseEntity<>(companyService.getAllCouponsOfCompanyByCategory(id, categories), HttpStatus.OK);
    }

//    getAllCouponsOfCompanyByMaxPrice
    @GetMapping("/get_company's_coupons_by_max_price/{id}")
    public ResponseEntity<?> getAllCouponsOfCompanyByMaxPrice(@PathVariable int id, @RequestParam int maxPrice) throws NotFoundException {
        return new ResponseEntity<>(companyService.getAllCouponsOfCompanyByMaxPrice(id, maxPrice), HttpStatus.OK);
    }

    @GetMapping("/get_company_details/{id}")
    public ResponseEntity<?> getCompanyDetails(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(companyService.getCompanyDetails(id), HttpStatus.OK);
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
