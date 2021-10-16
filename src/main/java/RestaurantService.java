import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        for(Restaurant rest : restaurants) {
            if (rest.getName().equals(restaurantName)) return rest;
        }
        throw new restaurantNotFoundException("Restaurant not found");

    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static void main(String args[]){
        RestaurantService rs = new RestaurantService();
        LocalTime opTime = LocalTime.of(9,0);
        LocalTime clTime = LocalTime.of(21,0);
        rs.addRestaurant("Puri Palace","Mumbai",opTime,clTime);
        System.out.println(restaurants.get(0).getName());
    }
}


