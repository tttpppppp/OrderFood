package com.example.project_restaurant.service;

import com.example.project_restaurant.dto.CategoryDTO;
import com.example.project_restaurant.dto.MenuDTO;
import com.example.project_restaurant.dto.RestaurantDTO;
import com.example.project_restaurant.entity.Food;
import com.example.project_restaurant.entity.MenuRestaurant;
import com.example.project_restaurant.entity.RatingRestaurant;
import com.example.project_restaurant.entity.Restaurant;
import com.example.project_restaurant.repository.RestaurantRepository;
import com.example.project_restaurant.service.Imp.RestaurantImp;
import com.example.project_restaurant.service.uploadfile.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantImp {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    FilesStorageService filesStorageService;

    @Override
    public boolean createRestaurant(MultipartFile file, String restaurantTitle, String restaurantSubTitle, String restaurantDesc, int isFreeShip, String address, String openDate) {
       boolean isInsertSucess = false;
        try {
            boolean isSucess = filesStorageService.save(file);
            if(isSucess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantTitle(restaurantTitle);
                restaurant.setRestaurantSubTitle(restaurantSubTitle);
                restaurant.setRestaurantDesc(restaurantDesc);
                restaurant.setIsFreeShip(isFreeShip);
                restaurant.setAddress(address);
                restaurant.setRestaurantImage(file.getOriginalFilename());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm" );
                Date opendatefm = simpleDateFormat.parse(openDate);
                restaurant.setOpenDate(opendatefm);
                restaurantRepository.save(restaurant);
                isInsertSucess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            isInsertSucess = false;
        }
        return isInsertSucess;
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<RestaurantDTO> listreRestaurantDTO = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Restaurant> listdata = restaurantRepository.findAll(pageRequest);

        for (Restaurant restaurant : listdata) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setRestaurantId(restaurant.getRestaurantId());
            restaurantDTO.setRestaurantTitle(restaurant.getRestaurantTitle());
            restaurantDTO.setRestaurantSubTitle(restaurant.getRestaurantSubTitle());
            restaurantDTO.setImage(restaurant.getRestaurantImage());
            restaurantDTO.setIsFreeShip(restaurant.getIsFreeShip());
            restaurantDTO.setRestaurantDes(restaurant.getRestaurantDesc());
            restaurantDTO.setRating(caclRatingRestaurant(restaurant.getRatingRestaurants()));
            listreRestaurantDTO.add(restaurantDTO);
        }
        return listreRestaurantDTO;
    }

    @Override
    public RestaurantDTO getRestaurantById(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setRestaurantId(restaurant.get().getRestaurantId());
            restaurantDTO.setRestaurantTitle(restaurant.get().getRestaurantTitle());
            restaurantDTO.setRestaurantSubTitle(restaurant.get().getRestaurantSubTitle());
            restaurantDTO.setImage(restaurant.get().getRestaurantImage());
            restaurantDTO.setIsFreeShip(restaurant.get().getIsFreeShip());
            restaurantDTO.setRestaurantDes(restaurant.get().getRestaurantDesc());
            restaurantDTO.setRating(caclRatingRestaurant(restaurant.get().getRatingRestaurants()));
            restaurantDTO.setOpenDate(restaurant.get().getOpenDate());

            for (MenuRestaurant menuRestaurant : restaurant.get().getMenuRestaurants()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setNameCategory(menuRestaurant.getCategory().getCategoryName());
                List<MenuDTO> menuDTOList = new ArrayList<>();
                for (Food food : menuRestaurant.getCategory().getFoods()) {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setFoodId(food.getFoodId());
                    menuDTO.setFoodName(food.getFoodName());
                    menuDTO.setFoodImage(food.getFoodImage());
                    menuDTO.setIsFreeShip(food.getIsFreeShip());
                    menuDTOList.add(menuDTO);
                }
                categoryDTO.setMenuDTOList(menuDTOList);
                categoryDTOList.add(categoryDTO);
            }
            restaurantDTO.setCategories(categoryDTOList);
            return restaurantDTO;
        }
        return null;
    }

    public double caclRatingRestaurant(Set<RatingRestaurant> rateRestaurantList) {
        double totalPoints = 0;
        for (RatingRestaurant rateRestaurant : rateRestaurantList) {
            totalPoints += rateRestaurant.getRatePoint();
        }
        return totalPoints / rateRestaurantList.size();
    }
}
