package com.gm.infobus.repository.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
* @Description: 
* @author liuwei
* @date 2014年1月16日 下午12:31:18
*
*/
public abstract class AbstractDO implements Serializable {
	
	private static final long serialVersionUID = -3942149913171834745L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
