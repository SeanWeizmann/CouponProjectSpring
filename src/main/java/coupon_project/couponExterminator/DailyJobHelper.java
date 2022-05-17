package coupon_project.couponExterminator;

import coupon_project.repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class DailyJobHelper {

    @Autowired
    CouponRepo couponRepo;

    public void deleteXCoupons(LocalDate today){
        couponRepo.deleteCouponByEndDateBefore(today);
    }
}
