package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Cart;

import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

public class CartTests {

    private static Cart cart;

    //Items data for positive test cases.
    @DataProvider
    public Object[][] itemsData() {
        return new Object[][] {
                {Arrays.asList("A", "B", "C", "D", "A", "B", "A"), 793.60f},
                {Arrays.asList("C", "C", "C", "C", "C", "C", "C"),6.0f},
                {Arrays.asList("A", "B", "C", "D"),632.0f},
                {Arrays.asList("A", "B", "C", "D","F"),649.0f},
                {Arrays.asList("A", "A", "A"),90f},
                {Arrays.asList("A", "A", "A", "A", "B", "C", "D","F"),739f},
                {Arrays.asList("A", "A"),61.0f},
                {Arrays.asList("B", "B", "B"),303.9f},
                {Arrays.asList("C", "C", "C", "C"),4.0f},
                {Arrays.asList("C", "C", "C", "C", "C", "C"),5.0f},
                {Arrays.asList("D", "D", "D", "D"),2000.0f},
                {Arrays.asList("F", "F", "F", "F"),68.0f},
                {Arrays.asList("F", "F", "F", "F","F", "F", "F", "F","F"),135.0f},
                {Arrays.asList("F", "F", "F", "F","F", "F", "F", "F","F", "A", "B", "C", "D","F"),784.0f},

        };
    }
    //Data for negative test cases. I've made it in different arrays because API should throw error after first
    //wrong result.
    @DataProvider
    public Object[][] wrongItemsData() {
        return new Object[][] {
                {Arrays.asList("ABCDF")},
                {Arrays.asList("C", "C", "C", "", "C", "", "C")},
                {Arrays.asList("")},
                {Arrays.asList("123132")},
                {Arrays.asList("bhjSGJHD")},
                {Arrays.asList("*`~-=+$#@!$%^&*()_)")},
                {Arrays.asList("<script>alert()</script> ")},
        };
    }

    @BeforeMethod
    public void setUp(){
        cart = new Cart();
    }

    //Checks if price of given items in itemsData is the same as expected price
    @Test(dataProvider="itemsData")
    public void testCheckItemsPrice(List<String> elem_list, float expected_sum){
        for (String elem:elem_list){
            cart.calculate(elem);
        }
        Assert.assertEquals(cart.calculateTotal(),expected_sum, "Checking sum");
    }

    @Test(dataProvider="wrongItemsData")
    public void testWrongItemsInput(List<String> elem_list){
        for (String elem:elem_list){
            try {
                cart.calculate(elem);
                Assert.assertTrue(false,"Cart accepts wrong input");
            } catch (IllegalFormatException e){
                Assert.assertTrue(true);
            }
        }
    }
}
