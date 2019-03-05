
package com.hotent.test.dao.test;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.test.model.test.Testasme;

@Repository
public class TestasmeDao extends BaseDao<Testasme>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Testasme.class;
	}

}