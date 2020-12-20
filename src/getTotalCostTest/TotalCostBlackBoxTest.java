package getTotalCostTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.oms.bean.Book;
import com.oms.bean.Order;
import com.oms.bean.OrderLine;
import com.oms.components.cart.controller.CartController;
import com.oms.serverapi.MediaApi;

@RunWith(Parameterized.class)
public class TotalCostBlackBoxTest {
	
	private float totalCost;
	private float expectedResult;
	
	private static Order order;
	
	ArrayList<ArrayList<OrderLine>> testList;
	static ArrayList<OrderLine> t0= new ArrayList<OrderLine>();
	static ArrayList<OrderLine> t1= new ArrayList<OrderLine>();
	static ArrayList<OrderLine> t2= new ArrayList<OrderLine>();
	static ArrayList<OrderLine> t3= new ArrayList<OrderLine>();
	static ArrayList<OrderLine> t4= new ArrayList<OrderLine>();
	static ArrayList<OrderLine> t5= new ArrayList<OrderLine>();
	
	
	@Before
	public void initialize() {
		t0 = new ArrayList<OrderLine>();
		t1 = new ArrayList<OrderLine>();
		t2 = new ArrayList<OrderLine>();
		t3 = new ArrayList<OrderLine>();
		t4 = new ArrayList<OrderLine>();
		t5 = new ArrayList<OrderLine>();
		t0.add(new OrderLine("", "", 0, 0, 0));
		t1.add(new OrderLine("", "", 1, 200000, 2));
		t2.add(new OrderLine("", "", 1, 300000, 4));
		t3.add(new OrderLine("", "", 1, 100000, 0.4f));
		t4.add(new OrderLine("", "", 1, 400000, 1.5f));
		t5.add(new OrderLine("", "", 1, 600000, 4));
	}
	
	
	public TotalCostBlackBoxTest(float totalCost, float expectedResult) {
		super();
		this.totalCost = totalCost;
		this.expectedResult = expectedResult;
	}
	

	public static float getTotalCostFromParameter(ArrayList<OrderLine> orderLineList, String customerAddress) {
		order = new Order();
		for (OrderLine ol : orderLineList) {
			order.addOrderLine(ol);
		}
		order.setCustomerAddress(customerAddress);
		return order.getTotalCost();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] { 
			{getTotalCostFromParameter(t0, "Tự Nhiên"), 0 },
			{getTotalCostFromParameter(t1, "HN"), 222000 },
			{getTotalCostFromParameter(t2, "HCM"), 327000 },
			{getTotalCostFromParameter(t3, "Thái Bình"), 130000 },
			{getTotalCostFromParameter(t4, "Bắc Giang"), 435000 },
			{getTotalCostFromParameter(t5, "Thái Nguyên"), 600000 },
			
		});
	}
	
	@Test
	public void testTotalCost() {
		
		assertEquals(expectedResult, totalCost, 500);
	}
	
}
