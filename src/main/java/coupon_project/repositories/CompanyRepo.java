package coupon_project.repositories;

import coupon_project.entities.Company;
import coupon_project.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import javax.persistence.OrderBy;
import java.time.LocalDate;
import java.util.List;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

    /**
     * This method allows you to check if company exists by name and email
     * @param name check in the DB if this name exists
     * @param email check in the DB if this email exists
     * @return
     */
    boolean existsByNameOrEmail(String name, String email);

    /**
     * This method allows you to get id of company by email
     * @param email get id by email
     * @return id- Integer
     */
    int getCompanyIdByEmail(String email);

    /**
     * This method allows you to check if email exists in the DB
     * @param email checking by email
     * @return true/false- Boolean
     */
    boolean existsByEmail(String email);

    /**
     * This method allows you to check if name exists in the DB
     * @param name checking by name
     * @return true/false- Boolean
     */
    boolean existsByName(String name);

    /**
     * This method allows you to represent a company by email and password
     * @param email company's email from the DB
     * @param password company's password from the DB
     * @return an instance of object Company
     */
    Company findByEmailAndPassword(String email, String password);

    /**
     * This method allows you to check if company exists by email and password
     * @param email checking by email
     * @param password checking by password
     * @return true/false- Boolean
     */
    boolean existsByEmailAndPassword(String email, String password);


    @OrderBy(
            "name ASC"
    )
    @Query(
        value = "select name from company",
        nativeQuery = true
    )
    String[] findAllCompanyName();

    @OrderBy("name ASC")
    List<Company> findAll();

    @Query(
            value = "select title from coupon",
            nativeQuery = true
    )
    String[] findAllCouponName();







}
