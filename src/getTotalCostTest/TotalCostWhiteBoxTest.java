package getTotalCostTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.oms.bean.Book;
import com.oms.bean.Order;
import com.oms.components.cart.controller.CartController;
import com.oms.serverapi.MediaApi;

@RunWith(Parameterized.class)
public class TotalCostWhiteBoxTest {
	
	private String productId;
	
	private String productTitle;

	private float productCost;
	
	private int productQuantity;
	
	private float productWeight;
	
	private Order order;

	private String bookTitle;

	private String expectedResult;
	private MediaApi api = new MediaApi();
	
	
	public TotalCostWhiteBoxTest(String bookTitle, String expectedResult) {
		super();
		this.bookTitle = bookTitle;
		this.expectedResult = expectedResult;
	}
	

	@Parameterized.Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] { 
			{ "C#", "C# Programming" },
			{ "Java", "Java Programming" }, 
			{ "Python", "Python Programming" }
		});
	}
	
	@Test
	public void testGetBooks() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("title", bookTitle);
		
		ArrayList<Book> list= api.getBooks(params);
		assertTrue("No data", list.size() > 0);
		
		
		Book book = list.get(0);
		assertEquals("Eror in getBooks API!", book.getTitle(), expectedResult);
	}
}
