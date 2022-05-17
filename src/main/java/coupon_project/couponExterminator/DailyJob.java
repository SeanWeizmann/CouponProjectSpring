package coupon_project.couponExterminator;

import coupon_project.repositories.CompanyRepo;
import coupon_project.repositories.CouponRepo;
import coupon_project.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class DailyJob extends Thread{

    @Autowired
    DailyJobHelper dailyJobHelper;
    private final long DAY = 1000 * 60 * 60 * 24;


    @Override
    public void run() {
        System.out.println("Daily Job has started");
//        CouponRepo couponRepo = ctx.getBean(CouponRepo.class);
        while (true) {
            try {
                LocalDate today = LocalDate.now();
                dailyJobHelper.deleteXCoupons(today);
                Thread.sleep(DAY);
            } catch (InterruptedException err) {
                System.out.println("Daily Job exterminator shut down");
                break;
            }
        }

    }
}
