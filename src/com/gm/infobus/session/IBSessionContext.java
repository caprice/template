package com.gm.infobus.session;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
* @Description: when a user is logged in, remember the session id.
* @author liuwei
* @date 2013年12月6日 下午12:33:49
*
*/
public class IBSessionContext {
	private static HashMap<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();

	public static synchronized void addSession(HttpSession session) {
		if (session != null) {
			sessionMap.put(session.getId(), session);
		}
	}

	public static synchronized void removeSession(HttpSession session) {
		if (session != null) {
			sessionMap.remove(session.getId());
		}
	}

	public static synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return (HttpSession) sessionMap.get(session_id);
	}
}
