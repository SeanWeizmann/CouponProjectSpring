package coupon_project.test;

import coupon_project.entities.Categories;
import coupon_project.entities.Company;
import coupon_project.entities.Coupon;
import coupon_project.entities.Customer;

import java.time.LocalDate;

public class Constants {

    public static final String SU = "Successful";
    public static int thisYear = LocalDate.now().getYear();
    public static LocalDate endOfTheYear = LocalDate.of(thisYear, 12, 31);
    public static LocalDate startOfTheYear = LocalDate.of(thisYear, 1, 1);


    public static final Company COMPANY1 = new Company("BestBuy", "bestbuy@gmail.com", "1234");
    public static final Company COMPANY2 = new Company("McDonald's", "mcdonlad@gmail.com", "1234");
    public static final Company COMPANY3 = new Company("Booking", "booking@gmail.com", "1234");
    public static final Company COMPANY4 = new Company("roho", "loho@gmail.com", "1234");

    public static final Customer CUSTOMER1 = new Customer("Bil", "Gates", "bilgates@gmail.com", "1234");
    public static final Customer CUSTOMER2 = new Customer("Alicia", "Keys", "aliciakeys@gmail.com", "1234");
    public static final Customer CUSTOMER3 = new Customer("Kanye", "West", "kanyewest@gmail.com", "1234");

    public static final Coupon COUPON1 = new Coupon(COMPANY1, Categories.ELECTRICITY, "1+1McBook",
            "you buy one mc and get the other one for free",
            startOfTheYear, endOfTheYear, 4, 20, "image");

    public static final Coupon COUPON2 = new Coupon(COMPANY1, Categories.ELECTRICITY, "1+1Iphone",
            "you buy one Iphone and get the other one for free",
            startOfTheYear, endOfTheYear, 4, 40, "image");

    public static final Coupon COUPON3 = new Coupon(COMPANY3, Categories.VACATION, "20% discount on vacation",
            "get 20% discount on your next vacation",
            startOfTheYear, endOfTheYear, 4, 20, "image");

    public static final Coupon COUPON4 = new Coupon(COMPANY3, Categories.VACATION, "jeep trip",
            "with the luxury jeep",
            startOfTheYear, endOfTheYear, 4, 40, "image");

    public static final Coupon COUPON5 = new Coupon(COMPANY2, Categories.FOOD, "vegan meal",
            "why come to Mcdonald's if you're vegan",
            startOfTheYear, endOfTheYear, 4, 40, "image");


    public static final Coupon COUPON6 = new Coupon(COMPANY2, Categories.FOOD, "kids meal",
            "with Tom and Jerry toys",
            startOfTheYear, endOfTheYear, 4, 40, "image");

    public static final Coupon COUPON7 = new Coupon(COMPANY2, Categories.RESTAURANT, "MCoffee",
            "2 masala chai for 40 rupees :)",
            startOfTheYear, endOfTheYear, 4, 40, "image");

    public static final Coupon COUPON8 = new Coupon(COMPANY2, Categories.RESTAURANT, "indian street food",
            "samosa or special tali with ginger lemon honey tea for 120 rupees :)",
            startOfTheYear, endOfTheYear, 4, 40, "image");

    public static final Coupon COUPON9 = new Coupon(COMPANY2, Categories.FOOD, "sushi",
            "why come to Mcdonald's if you're vegan",
            startOfTheYear, endOfTheYear, 0, 40, "image");

    public static final void printTest(String s) {
        System.out.println("==========\ttest " + s + "\t==========");
    }
}
