package coupon_project.test;

import coupon_project.entities.Categories;
import coupon_project.entities.Coupon;
import coupon_project.exception.InvalidException;
import coupon_project.exception.LoginException;
import coupon_project.exception.NotFoundException;
import coupon_project.exception.SomethingWentWrongException;
import coupon_project.login.ClientType;
import coupon_project.login.LoginManager;
import coupon_project.services.CompanyService;
import coupon_project.utils.ArtUtils;
import coupon_project.utils.TablePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static coupon_project.test.Constants.*;

@Service
public class TestCompanyService {

    @Autowired
    ApplicationContext ctx;
    private CompanyService companyService;

    public void testAll() {
        System.out.println(ArtUtils.startTestingCompany);
        testLogin();
        testAddCoupon();
        testUpdateCoupon();
        testDeleteCoupon();
        testGetAllCoupons();
        testGetAllCouponsForThatCompanyByCategory();
        testGetAllCouponsOfCompanyByMaxPrice();
        testGetCompanyDetails();
    }

    public void testLogin() {
        try {
            System.out.println("=========== test company login ===========");
            System.out.print("test login false: ");
            companyService = (CompanyService) LoginManager.login("bestbuy@gil.com", "1234", ClientType.COMPANY, ctx);
        } catch (LoginException err) {
            System.out.println(err.getMessage());
        }
        try {
            System.out.print("test login true: ");
            companyService = (CompanyService) LoginManager.login("bestbuy@gmail.com", "1234", ClientType.COMPANY, ctx);
        } catch (LoginException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testAddCoupon() {
        try {
            System.out.println("==========test successful add coupon==========");
            companyService.addCoupon(COUPON1);
            companyService.addCoupon(COUPON2);
            companyService.addCoupon(COUPON3);
            companyService.addCoupon(COUPON4);
            companyService.addCoupon(COUPON5);
            companyService.addCoupon(COUPON6);
            companyService.addCoupon(COUPON7);
            companyService.addCoupon(COUPON8);
            companyService.addCoupon(COUPON9);
            System.out.println("Coupons were added successfully");
            System.out.println("==========test failed add coupon==========");
            companyService.addCoupon(COUPON1);
        } catch (InvalidException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testUpdateCoupon() {
        try {
            printTest("successful update coupon");
            companyService.updateCoupon(new Coupon(6, COMPANY2, Categories.ELECTRICITY, "market1", "popo",
                    startOfTheYear, endOfTheYear, 4, 65, "koko"));
            System.out.println(SU);
            printTest("failed update coupon");
            companyService.updateCoupon(new Coupon(8, COMPANY1, Categories.ELECTRICITY, COUPON2.getTitle(), "popo",
                    LocalDate.now(), LocalDate.of(2022, 2, 4), 4, 65, "koko"));
        } catch (NotFoundException | InvalidException err) {
            System.out.println(err.getMessage());
        }
        try {
            companyService.updateCoupon(new Coupon(1, COMPANY1, Categories.ELECTRICITY, COUPON2.getTitle(), "popo",
                    LocalDate.now(), LocalDate.of(2022, 2, 4), 4, 65, "koko"));
        } catch (NotFoundException | InvalidException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testDeleteCoupon() {
        try {
            printTest("Successful delete coupon");
            companyService.deleteCoupon(7);
            companyService.deleteCoupon(8);
            System.out.println(SU);
            printTest("failed delete coupon");
            companyService.deleteCoupon(20);
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testGetAllCoupons() {
        try {
            printTest("get all company's coupons");
//            System.out.println(companyFacade.getAllCoupons(2));
            TablePrinter.print(companyService.getAllCouponsOfTheSameCompany(1));
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testGetAllCouponsForThatCompanyByCategory() {
        try {
//            System.out.println(companyFacade.getAllCouponsForThatCompanyByCategory(Category.FOOD, 3));
            printTest("Successful get company's coupons by category");
            TablePrinter.print(companyService.getAllCouponsOfCompanyByCategory(1, Categories.ELECTRICITY));
            printTest("failed get company's coupons by category");
            TablePrinter.print(companyService.getAllCouponsOfCompanyByCategory(20, Categories.ELECTRICITY));
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testGetAllCouponsOfCompanyByMaxPrice() {
        try {
            printTest("successful get company's coupons by max price");
//            System.out.println(companyFacade.getAllCouponsOfCompanyByMaxPrice(2, 48));
            TablePrinter.print(companyService.getAllCouponsOfCompanyByMaxPrice(22.5, 1));
            printTest("failed get company's coupons by max price");
            TablePrinter.print(companyService.getAllCouponsOfCompanyByMaxPrice(22.5, 20));
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testGetCompanyDetails() {
        try {
            printTest("successful get company detail");
//            System.out.println(companyFacade.getCompanyDetails(10));
            TablePrinter.print(companyService.getCompanyDetails(1));
            printTest("failed get company detail");
            TablePrinter.print(companyService.getCompanyDetails(20));
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }
}
