package work.model.service;

import java.util.ArrayList;

import work.model.dao.FoodDao;
import work.model.dto.Food;

public class FoodService {
	/** ������ dao Ŭ���� */
	private FoodDao dao = FoodDao.getInstance();
	
	/** ������ ����ϱ� */
	public int registerFood(Food food) {
		return dao.insertFood(food);
	}
	
	/** ������ �����ϱ� */
	public int removeFood(int foodNo) {
		return dao.deleteFood(foodNo);
	}
	
	/** ������ ���� �����ϱ� */
	public int changeFood(Food food) {
		return dao.updateFood(food);
	}
	
	/** ������ �� ��ȸ */
	public Food detailFood(int foodNo) {
		return dao.selectFood(foodNo);
	}
	
	/** ������ ��ü ��ȸ */
	public ArrayList<Food> listFood() {
		return dao.selectList();
	}
	
	/** ������ �˻� */
	public ArrayList<Food> searchFood(String key, String data) {
		return dao.selectData(key, data);
	}
}
