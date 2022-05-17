package coupon_project.services;

import coupon_project.entities.Categories;
import coupon_project.entities.Company;
import coupon_project.entities.Coupon;
import coupon_project.exception.InvalidException;
import coupon_project.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CompanyService extends ClientService {

    /**
     * Login to system
     *
     * @param email    email required
     * @param password password required
     * @return true if you logged in
     */
    @Override
    public boolean login(String email, String password) {
        return companyRepo.existsByEmailAndPassword(email, password);
    }


    /**
     * This method allows the company to add a coupon to their list
     *
     * @param coupon need to initialize the object
     * @throws InvalidException if some values already taken
     */
    public void addCoupon(Coupon coupon) throws InvalidException {
        if (couponRepo.existsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId())) {
            throw new InvalidException("Title already exists in the same company");
        }
        if (coupon.getPrice() <= 0) {
            throw new InvalidException("Cannot apply negative or zero price to a coupon");
        }
        if (coupon.getEndDate().isBefore(coupon.getStartDate())) {
            throw new InvalidException("Cannot apply expired coupons");
        }
        couponRepo.save(coupon);
    }

    /**
     * This method allows the company to update their coupons
     *
     * @param coupon choose and update coupon by insert values
     * @throws InvalidException  if some values already taken
     * @throws NotFoundException if coupons does not exist
     */
    public void updateCoupon(Coupon coupon) throws InvalidException, NotFoundException {
        if (!couponRepo.existsById(coupon.getId())) {
            throw new NotFoundException("Coupon with id " + coupon.getId() + " does not exist");
        }
        if (coupon.getPrice() <= 0) {
            throw new InvalidException("Cannot apply negative or zero price to a coupon");
        }
        if (coupon.getEndDate().isBefore(coupon.getStartDate())) {
            throw new InvalidException("Cannot apply expired coupons");
        }
        if (coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new InvalidException("Cannot apply coupon with end date before today");
        }
        if (couponRepo.existsByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId()) && !(couponRepo.getById(coupon.getId()).getTitle().equals(coupon.getTitle()))) {
            throw new InvalidException("Title already taken");
        }
        couponRepo.save(coupon);
    }

    /**
     * This method allows the company to delete coupons
     *
     * @param couponId delete bu coupon id
     * @throws NotFoundException if id does not exist
     */
    public void deleteCoupon(int couponId) throws NotFoundException {
        if (!couponRepo.existsById(couponId)) {
            throw new NotFoundException("Coupon with id " + couponId + " does not exist");
        }
        couponRepo.deleteById(couponId);
    }

    /**
     * This method allows you to represent all company's coupons
     *
     * @param companyId choosing company by id
     * @return a list of coupons
     * @throws NotFoundException if id not exists
     */
    public List<Coupon> getAllCouponsOfTheSameCompany(int companyId) throws NotFoundException {
        if (!companyRepo.existsById(companyId)) {
            throw new NotFoundException("Company with id " + companyId + " does not exist");
        }
        return couponRepo.findAllByCompanyId(companyId);
    }

    /**
     * This method allows you to represent company's coupons by category
     *
     * @param companyId  choosing company by id
     * @param categories choosing the category
     * @return a list of coupons from one category that belongs to the same company
     * @throws NotFoundException if id not exists
     */
    public List<Coupon> getAllCouponsOfCompanyByCategory(int companyId, Categories categories) throws NotFoundException {
        if (!companyRepo.existsById(companyId)) {
            throw new NotFoundException("Company with id " + companyId + " does not exist");
        }
        return couponRepo.findAllCouponsByCategoryAndCompanyId(categories, companyId);
    }

    /**
     * This method allows you to represent company's coupon by maximum price
     *
     * @param maxPrice  choosing the max price
     * @param companyId choosing a company by id
     * @return a list of coupons from the same company that not over the max price
     * @throws NotFoundException
     */
    public List<Coupon> getAllCouponsOfCompanyByMaxPrice(double maxPrice, int companyId) throws NotFoundException {
        if (!companyRepo.existsById(companyId)) {
            throw new NotFoundException("Company with id " + companyId + " does not exist");
        }
        return couponRepo.findAllByPriceLessThanEqualAndCompanyId(maxPrice, companyId);
    }

    /**
     * This method allows you to represent company details
     *
     * @param companyId choosing company by id
     * @return an instance of object- company
     * @throws NotFoundException
     */
    public Company getCompanyDetails(int companyId) throws NotFoundException {
        if (!companyRepo.existsById(companyId)) {
            throw new NotFoundException("company with id " + companyId + " does not exist");
        }
        return companyRepo.findById(companyId).get();
    }

    /**
     * This method allows you to get company's id
     *
     * @param email get id by email
     * @return number- Integer
     */
    public int getIdOfCompany(String email) {
        return companyRepo.getCompanyIdByEmail(email);
    }
}
