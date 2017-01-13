package llyska.services;

import java.util.List;

public interface HistoryService {
	
	List<String> getHistory();
	String getItem(int index);
	void addItem(String result);
	void removeItem(int index);
	void removeItem(int[] indexes);
	void clean(); 
	
}
