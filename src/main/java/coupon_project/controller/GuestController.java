package coupon_project.controller;

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
@RequestMapping("/Guest")
@RequiredArgsConstructor
@CrossOrigin
@ResponseBody
public class GuestController {


    private final CompanyService companyService;
    private final CustomerService customerService;
    private final AdminService adminService;
    @Autowired
    private final JwtUtil jwtUtil;


    @GetMapping("/get_all_companies")
    public ResponseEntity<?> getAllCompanies(){
        return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_all_coupons")
    public ResponseEntity<?> getAllCoupons(){
        return new ResponseEntity<>(adminService.getAllCoupons(), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    public String somethingWentWrongHandler(SomethingWentWrongException err){
        return err.getMessage();
    }

}
