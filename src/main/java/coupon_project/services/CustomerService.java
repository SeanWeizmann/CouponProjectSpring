package coupon_project.services;

import coupon_project.entities.Categories;
import coupon_project.entities.Coupon;
import coupon_project.entities.Customer;
import coupon_project.exception.InvalidException;
import coupon_project.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CustomerService extends ClientService {

    /**
     * Login to system
     *
     * @param email    email required
     * @param password password required
     * @return true if you logged in
     */
    @Override
    public boolean login(String email, String password) {
        return customerRepo.existsByEmailAndPassword(email, password);
    }

    /**
     * This method allows the customer to buy coupons
     * @param customerId choosing customer by customer id
     * @param couponId choosing the coupon by coupon id
     * @throws NotFoundException if coupon/customer id is not recognize
     * @throws InvalidException if coupon already purchase
     */
    public void addCouponPurchase(int customerId, int couponId) throws NotFoundException, InvalidException {
        if (couponRepo.existsByCustomersIdAndId(customerId, couponId)) {
            throw new NotFoundException("This coupon has been purchased already");
        }
        Coupon couponDetail = couponRepo.getById(couponId);
        if (!(couponDetail.getAmount() >= 1)) {
            throw new InvalidException("coupon amount is 0");
        }
        if (couponDetail.getEndDate().isBefore(LocalDate.now())) {
            throw new InvalidException("coupons has expired");
        }
        couponDetail.setAmount(couponDetail.getAmount() - 1);
        couponDetail.addCustomer(customerRepo.getById(customerId));
        couponRepo.save(couponDetail);
    }

    /**
     * This method allows the customers to view all of their coupons
     * @param customerId choosing customer by id
     * @return a list if coupons
     * @throws NotFoundException if customer id not exists
     */
    public List<Coupon> getAllCustomerCoupons(int customerId)throws NotFoundException{
        return couponRepo.findAllByCustomersId(customerId);
    }

    /**
     * This method allows the customers to view their coupons of the same category
     * @param customerId choosing customer by customer id
     * @param categories choosing category
     * @return a list of customer's coupons of the same category
     * @throws NotFoundException if customer id does not exist
     */
    public List<Coupon> getAllCustomerCouponsByCategory(int customerId, Categories categories)throws NotFoundException{
        return couponRepo.findAllByCustomersIdAndCategory(customerId, categories);
    }

    /**
     * This method allows the customers to view their coupons by maximum price
     * @param customerId choosing customer by customer id
     * @param maxPrice choosing the max price
     * @return a list of customer's coupons that not over the max price
     * @throws NotFoundException if customer id not exists
     */
    public List<Coupon> getAllCustomerCouponsByMacPrice(int customerId, double maxPrice)throws NotFoundException{
        return couponRepo.findAllByCustomersIdAndPriceLessThanEqual(customerId, maxPrice);
    }

    /**
     * This method allows you to get customer details
     * @param customerId choosing customer by customer id
     * @return an instance of object- Customer
     * @throws NotFoundException if customer id not exists
     */
    public Customer getCustomerDetails(int customerId) throws NotFoundException {
        if (!customerRepo.existsById(customerId)){
            throw new NotFoundException("customer with id " + customerId + " is not exist");
        }
        return customerRepo.findById(customerId).get();
    }

    /**
     * This method allows you to get customer id by email
     * @param email get id by email
     * @return id number- Integer
     */
    public int getCustomerIdByEmail(String email){
        return customerRepo.getCustomerIdByEmail(email);
    }

}
