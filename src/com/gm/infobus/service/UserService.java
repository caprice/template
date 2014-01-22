package com.gm.infobus.service;

import java.util.List;
import java.util.Map;

import com.gm.infobus.entity.User;
import com.gm.infobus.entity.UserDetail;
import com.gm.infobus.repository.pagination.page.Pagination;

public interface UserService {
	public List<User> findAllUsers();

	public List<User> findAllUsers(Pagination pagination);

	public void addUser(User user);

	public boolean isUserNameExisted(String userName);

	public boolean isPlateExisted(String plate);

	public User getLoginUser(User user);
	
	void addUserDetail(UserDetail userDetail);

	int updateUserDetail(UserDetail userDetail);

	public Map<String, UserDetail> getUsersByUserNames(String[] userNames);

	public User getUserByPlate(String plate);
}
