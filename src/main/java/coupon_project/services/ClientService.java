package coupon_project.services;

import coupon_project.entities.Coupon;
import coupon_project.repositories.CompanyRepo;
import coupon_project.repositories.CouponRepo;
import coupon_project.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    @Autowired
    protected CompanyRepo companyRepo;

    @Autowired
    protected CustomerRepo customerRepo;

    @Autowired
    protected CouponRepo couponRepo;

    public abstract boolean login(String email, String password);
}
