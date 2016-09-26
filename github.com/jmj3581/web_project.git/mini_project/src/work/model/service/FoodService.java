package work.model.service;

import java.util.ArrayList;

import work.model.dao.FoodDao;
import work.model.dto.Food;

public class FoodService {
	/** 음식점 dao 클래스 */
	private FoodDao dao = FoodDao.getInstance();
	
	/** 음식점 등록하기 */
	public int registerFood(Food food) {
		return dao.insertFood(food);
	}
	
	/** 음식점 삭제하기 */
	public int removeFood(int foodNo) {
		return dao.deleteFood(foodNo);
	}
	
	/** 음식점 정보 변경하기 */
	public int changeFood(Food food) {
		return dao.updateFood(food);
	}
	
	/** 음식점 상세 조회 */
	public Food detailFood(int foodNo) {
		return dao.selectFood(foodNo);
	}
	
	/** 음식점 전체 조회 */
	public ArrayList<Food> listFood() {
		return dao.selectList();
	}
	
	/** 음식점 검색 */
	public ArrayList<Food> searchFood(String key, String data) {
		return dao.selectData(key, data);
	}
}
