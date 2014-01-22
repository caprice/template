package com.gm.infobus.repository;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.dao.DataAccessException;

import com.gm.infobus.entity.User;
import com.gm.infobus.entity.UserDetail;
import com.gm.infobus.mapper.BaseMapper;

public interface UserDAO extends BaseMapper<User, String> {
	int addUser(User user) throws DataAccessException;

	int addUserDetail(UserDetail userDetail) throws DataAccessException;

	int updateUserDetail(UserDetail userDetail) throws DataAccessException;

	public List<User> findAllUsers() throws DataAccessException;

	public List<User> findAllUsersWithPagination(RowBounds rowBounds)
			throws DataAccessException;

	public int isUserNameExisted(String userName);

	public int isPlateExisted(String plate);

	public User getLoginUser(User user);

	List<User> getUsersByUserNames(String[] userNames);

	User getUserByPlate(String plate);
}
