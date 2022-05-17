package coupon_project.repositories;

import coupon_project.entities.Categories;
import coupon_project.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CouponRepo extends JpaRepository <Coupon, Integer> {

    /**
     * This method allows you to check if coupon exists in DB
     * @param title checking by title of coupon
     * @param companyId checking by id
     * @return true/false- Boolean
     */
    boolean existsByTitleAndCompanyId(String title, int companyId);

    /**
     * This method allows you to represent all Company's coupons
     * @param companyId choosing company by id
     * @return a list of coupons
     */
    List<Coupon> findAllByCompanyId(int companyId);

    /**
     * This method allows you to represent a list of coupons by category of single company
     * @param categories choosing the category
     * @param companyId choosing a company by id
     * @return a list of coupons
     */
    List<Coupon> findAllCouponsByCategoryAndCompanyId(Categories categories, int companyId);

    /**
     * This method allows you to represent a list of coupons by max price
     * @param maxPrice choosing a maximum price
     * @param companyId choosing the company by id
     * @return a list of coupons
     */
    List<Coupon> findAllByPriceLessThanEqualAndCompanyId(double maxPrice, int companyId);

    /**
     * This method allows you to check if customer bought this coupon already
     * @param customerId checking by customer id
     * @param couponId checking by coupon id
     * @return true/false- Boolean
     */
    boolean existsByCustomersIdAndId(int customerId, int couponId);

    /**
     * This method allows you to represent all customer's coupons
     * @param customerId choosing customer by id
     * @return a list of coupons
     */
    List<Coupon> findAllByCustomersId(int customerId);

    /**
     * This method allows you to represent all customer's coupon by one category
     * @param customerId choosing the customer by id
     * @param categories choosing the category
     * @return a list of coupons by category
     */
    List<Coupon> findAllByCustomersIdAndCategory(int customerId, Categories categories);

    /**
     * This method allows you to represent all customer's coupon by maximum price
     * @param customerId choosing the customer by id
     * @param maxPrice choose the max price
     * @return a list of coupons that not over the max price
     */
    List<Coupon> findAllByCustomersIdAndPriceLessThanEqual(int customerId, double maxPrice);

    /**
     * This method allows you to delete expired coupons
     * @param localDate delete itself by threading if coupon has expired
     */
    void deleteCouponByEndDateBefore(LocalDate localDate);
}
