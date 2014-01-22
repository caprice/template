package com.gm.infobus.repository.DAO;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gm.infobus.entity.User;
import com.gm.infobus.repository.BaseConfigurtionTest;
import com.gm.infobus.repository.UserDAO;

public class UserDAOTest extends BaseConfigurtionTest {
	@Autowired
	private UserDAO userDao;

	@Test
	public void testAddUser() {
		User user = new User();
//		user.setAccountName("liumeng1");
		user.setPassword("123456");
//		user.setPlate("沪MB2342");
//		user.setNickName("小鬼");
		userDao.addUser(user);
		System.out.println("成功往数据库中插入一条数据");
	}

	@Test
	public void testFindAllUsers() {
		List<User> list = userDao.findAllUsers();
		User user = null;
		if (list != null && !list.isEmpty()) {
			System.out.println("所有用户数目" + list.size());
			user = list.get(0);
		}
		if (user != null) {
//			System.out.println("user name:" + user.getName());
		}

	}

	@Test
	public void testFindAllUsersWithPagination() {
		RowBounds rowBounds = new RowBounds(1, 2);
		List<User> list = userDao.findAllUsersWithPagination(rowBounds);
		User user = null;
		if (list != null && !list.isEmpty()) {
			System.out.println("所有用户数目" + list.size());
			user = list.get(0);
		}
		if (user != null) {
			System.out.println("user name:" + user.getUserName());
		}

	}
	
	@Test
	public void testIsUserRegistered() {
		User user = new User();
//		user.setAccountName("liumeng1");
//		user.setPassword("123456");
//		int rows = userDao.isUserRegistered(user);
//		System.out.println("rows = "+ rows);

	}
}
