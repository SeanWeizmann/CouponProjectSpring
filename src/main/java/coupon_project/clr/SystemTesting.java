package coupon_project.clr;

import coupon_project.couponExterminator.DailyJob;
import coupon_project.test.TestAdminService;
import coupon_project.test.TestCompanyService;
import coupon_project.test.TestCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class SystemTesting implements CommandLineRunner {

    @Autowired
    private TestAdminService testAdminService;
    @Autowired
    private TestCompanyService testCompanyService;
    @Autowired
    private TestCustomerService testCustomerService;
    @Autowired
    private DailyJob dailyJob;

    @Override
    public void run(String... args) throws Exception {
        dailyJob.start();
        testAdminService.testAll();
        testCompanyService.testAll();
        testCustomerService.testAll();
        DailyJob.sleep(1000);
        dailyJob.interrupt();
    }
}
