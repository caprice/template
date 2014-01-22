package com.gm.infobus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gm.infobus.entity.User;
import com.gm.infobus.entity.UserDetail;
import com.gm.infobus.repository.UserDAO;
import com.gm.infobus.repository.pagination.page.Pagination;
import com.gm.infobus.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> findAllUsers() {
		return this.userDAO.findAllUsers();
	}

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
		if (user.getUserDetail() != null) {
			user.getUserDetail().setUserName(user.getUserName());
			userDAO.addUserDetail(user.getUserDetail());
		}
	}

	@Override
	public List<User> findAllUsers(Pagination pagination) {
		RowBounds rowBounds = new RowBounds(pagination.getCurrentPage(), pagination.getPageSize());
		return this.userDAO.findAllUsersWithPagination(rowBounds);
	}

	@Override
	public boolean isUserNameExisted(String userName) {
		int rows = userDAO.isUserNameExisted(userName);
		return rows > 0 ? true : false;
	}

	@Override
	public boolean isPlateExisted(String plate) {
		int rows = userDAO.isPlateExisted(plate);
		return rows > 0 ? true : false;
	}

	@Override
	public User getLoginUser(User user) {
		return userDAO.getLoginUser(user);
	}

	@Override
	public void addUserDetail(UserDetail userDetail) {
		userDAO.addUserDetail(userDetail);
	}

	@Override
	public int updateUserDetail(UserDetail userDetail) {
		return userDAO.updateUserDetail(userDetail);
	}

	@Override
	public Map<String, UserDetail> getUsersByUserNames(String[] userNames) {
		List<User> users = userDAO.getUsersByUserNames(userNames);
		Map<String, UserDetail> map = null;
		if(users!= null){
			map = new HashMap<String, UserDetail>();
			for(User user : users){
				UserDetail userDetail = user.getUserDetail();
				if(userDetail != null){
					userDetail.setEmail(user.getEmail());
				}
				map.put(user.getUserName(), userDetail);
			}
		}
		return map;
	}

	@Override
	public User getUserByPlate(String plate) {
		return userDAO.getUserByPlate(plate);
	}

}
