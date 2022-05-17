package coupon_project.test;

import coupon_project.CouponProjectPhase2Application;
import coupon_project.couponExterminator.DailyJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Tester {

    @Autowired
    TestAdminService testAdminService;
    @Autowired
    TestCompanyService testCompanyService;
    @Autowired
    TestCustomerService testCustomerService;



    public void testAll() {
        try {

            testAdminService.testAll();
            testCompanyService.testAll();
            testCustomerService.testAll();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
