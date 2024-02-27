package javahk2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;
	private String memberType;
	private boolean member;
	private List<Visit> visits;
	
	public Customer(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public boolean isMember() {
		return member;
	}
 public void setMember(boolean member) {
		this.member = member;
	}
public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
	public void addVisit(Visit visit) {
        if (visits == null) {
            visits = new ArrayList<>();
        }
        visits.add(visit);
    }

    public List<Visit> getVisits() {
        return visits;
    }
	@Override
	 public String toString() {
		 return "Customer [name=" + name + ", memberType=" + memberType + ", member=" + member + "]";
	 }
	}

class DiscountRate {
    
    public static final double PREMIUM_DISCOUNT_RATE = 0.2;
    public static final double GOLD_DISCOUNT_RATE = 0.15;
    public static final double SILVER_DISCOUNT_RATE = 0.1;

    public static final double PRODUCT_DISCOUNT_RATE = 0.1;

    
    public static double getServiceDiscountRate(String memberType) {
        if(memberType.toLowerCase().equalsIgnoreCase("premium")) {
                return PREMIUM_DISCOUNT_RATE;
        }else if(memberType.toLowerCase().equalsIgnoreCase("gold")) {
            return GOLD_DISCOUNT_RATE;
        }else if(memberType.toLowerCase().equalsIgnoreCase("silver")) {
            return SILVER_DISCOUNT_RATE;
        }else {
		return 0;
        }
        }
    
    public static double getProductDiscountRate() {
        return PRODUCT_DISCOUNT_RATE;
    }
}
class Visit extends Customer {
    private Customer customer;
    private LocalDate date;
    private double serviceExpense;
    private double productExpense;
    
    public Visit(String name, LocalDate date) {
    	super(name);
    	this.date=date;
    }
    public double getServiceExpense() {
		return serviceExpense;
	}
	public void setServiceExpense(double ex) {
		this.serviceExpense = ex;
	}
	public double getProductExpense() {
		return productExpense;
	}
	public void setProductExpense(double ex) {
		this.productExpense = ex;
	}

	public double getTotalExpense() {
        double serviceDiscountRate = DiscountRate.getServiceDiscountRate(customer.getMemberType());
        double productDiscountRate = DiscountRate.getProductDiscountRate();

        double totalServiceExpense = serviceExpense - (serviceExpense * serviceDiscountRate);
        double totalProductExpense = productExpense - (productExpense * productDiscountRate);

        return totalServiceExpense + totalProductExpense;
    }
}

class BeautySalonTest {
    public static void main(String[] args) {
        Customer customer = new Customer("Alice");
        customer.setMemberType("Premium");
        customer.setMember(true);

        // Create a visit for the customer
        Visit visit = new Visit("Alice",LocalDate.of(2022, 2, 19));
        visit.setServiceExpense(100.0);
        visit.setProductExpense(50.0);

        // Calculate and display the total bill
        double totalBill = visit.getTotalExpense();
        System.out.println("Customer: " + customer);
        System.out.println("Total Bill: $" + totalBill);
    }
}

