package coupon_project;

import coupon_project.couponExterminator.DailyJob;
import coupon_project.entities.Categories;
import coupon_project.entities.Company;
import coupon_project.entities.Coupon;
import coupon_project.entities.Customer;
import coupon_project.exception.InvalidException;
import coupon_project.exception.NotFoundException;
import coupon_project.exception.SomethingWentWrongException;
import coupon_project.login.ClientType;
import coupon_project.login.LoginManager;
import coupon_project.repositories.CompanyRepo;
import coupon_project.repositories.CouponRepo;
import coupon_project.services.AdminService;
import coupon_project.services.CompanyService;
import coupon_project.services.CustomerService;
import coupon_project.test.Tester;
import coupon_project.token_verify_login_master.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;

import static coupon_project.test.Constants.endOfTheYear;
import static coupon_project.test.Constants.startOfTheYear;

@SpringBootApplication
//@EnableSwagger2
public class CouponProjectPhase2Application {

    public static void main(String[] args) {

        SpringApplication.run(CouponProjectPhase2Application.class, args);
		/*
		try (ConfigurableApplicationContext ctx = SpringApplication.run(CouponProjectPhase2Application.class, args)) {
			DailyJob dailyJob = ctx.getBean(DailyJob.class);
			dailyJob.start();
			AdminService adminService = ctx.getBean(AdminService.class);
			CompanyService companyService = ctx.getBean(CompanyService.class);
			CustomerService customerService = ctx.getBean(CustomerService.class);
			Tester.testAll();



			adminService.addCompany(new Company("sean", "sean@sean.com", "1234"));
			adminService.deleteCompany(1);

		} catch (NotFoundException e) {
			e.printStackTrace();
		}

			System.out.println(adminService.getAllCompanies());
			System.out.println(adminService.getOneCompany(1));

			adminService.addCustomer(new Customer("sean", "weizmann", "rtrtn@gmail.com", "123456"));
			adminService.deleteCustomer(3);
			System.out.println(adminService.getAllCustomers());
			System.out.println(adminService.getOneCustomer(4));
			System.out.println(companyService.login("sean@sean.com" , "1234"));
			companyService.addCoupon(new Coupon(adminService.getOneCompany(2), Categories.RESTAURANT, "good soup", "not an usual soup",
					LocalDate.now(), LocalDate.now(), 12, 15, "image"));

			companyService.deleteCoupon(5);

			System.out.println(companyService.getAllCouponsOfTheSameCompany(2));
			System.out.println(companyService.getCompanyDetails(2));
			System.out.println(companyService.getAllCouponsOfCompanyByCategory(2, Categories.ELECTRICITY));
			companyService.updateCoupon(new Coupon(6, new Company(2), Categories.ELECTRICITY, "1+1Iphone",
					"you buy one Iphone and get the other one for free",
					startOfTheYear, endOfTheYear, 4, 40, "image"));

			companyService.updateCoupon(new Coupon(6, new Company(2), Categories.ELECTRICITY, "1+1Iphone",
					"you buy one Iphone and get the other one for free",
					LocalDate.of(1939, 2, 15), LocalDate.of(1940, 2, 17), 4, 40, "image"));
			System.out.println(companyService.getAllCouponsOfCompanyByMaxPrice(50, 2));

			System.out.println(customerService.login("rtrtn@gmail.com", "123456"));
			customerService.addCouponPurchase(4, 6);
			System.out.println(customerService.getAllCustomerCoupons(4));
			System.out.println(customerService.getAllCustomerCouponsByCategory(4, Categories.ELECTRICITY));
			System.out.println(customerService.getAllCustomerCouponsByMacPrice(4, 40));

			LoginManager loginManager = ctx.getBean(LoginManager.class);
			CustomerService customerService1 = (CustomerService) loginManager.login("rtrtn@gmail.com", "123456", ClientType.CUSTOMER);
			customerService1.addCouponPurchase(4, 6);

			CompanyRepo companyRepo = ctx.getBean(CompanyRepo.class);
			System.out.println(Arrays.toString(companyRepo.findAllCouponName()));

			Thread.sleep(1000);
			dailyJob.interrupt();
			JwtUtil jwtUtil = ctx.getBean(JwtUtil.class);
			System.out.println(jwtUtil.codedKey.length);
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}

		 */
    }

}