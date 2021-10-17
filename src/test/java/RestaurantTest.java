import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void addRestaurant_and_addMenu_before_each_testcase(){
        restaurant = new Restaurant("Dave's Delight","Mumbai",LocalTime.parse("09:00:00"),LocalTime.parse("21:00:00"));
        restaurant.addToMenu("Vegetable lasagne",150);
        restaurant.addToMenu("Noodles",140);
        restaurant.addToMenu("Fried Rice",130);

    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant res = Mockito.spy(restaurant);
        Mockito.when(res.getCurrentTime()).thenReturn(LocalTime.parse("11:00:00"));
        boolean isResOpen = res.isRestaurantOpen();
        assertTrue(isResOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant res = Mockito.spy(restaurant);
        Mockito.when(res.getCurrentTime()).thenReturn(LocalTime.parse("22:00:00"));
        boolean isResOpen = res.isRestaurantOpen();
        assertFalse(isResOpen);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void when_selecting_item_from_menu_total_value_to_be_displayed_for_the_selected_items(){

        List<Item> selectMenu = new ArrayList<Item>();
        selectMenu.add(restaurant.getMenu().get(0));
        selectMenu.add(restaurant.getMenu().get(2));
        int totalValue = restaurant.getTotalValue(selectMenu);
        assertEquals(280,totalValue);
    }
}