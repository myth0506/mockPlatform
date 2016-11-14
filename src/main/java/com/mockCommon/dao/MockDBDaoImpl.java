package com.mockCommon.dao;

import static org.springframework.util.Assert.notNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Repository;

@Repository("mockDBDao")
public class MockDBDaoImpl extends DaoSupport{
	
	private SqlSession sqlSession;
	private boolean externalSqlSession;
	
	@Autowired(required = false)
	public final void setMySqlSessionFactory(@Qualifier("sqlSessionFactoryMock") SqlSessionFactory sqlSessionFactory){
		if(!this.externalSqlSession){
			this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
		}
	}
	
	@Autowired(required = false)
	public final void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSession = sqlSessionTemplate;
		this.externalSqlSession = true;
	}
	public int delete(String sqlId,Object obj){
		return getSqlSession().delete(sqlId,obj);
	}
	
	public int insertOrUpdate(String sqlId, Object obj){
		return getSqlSession().update(sqlId,obj);
	}

	@Override
	protected void checkDaoConfig() throws IllegalArgumentException {
		notNull(this.sqlSession, "Property sqlSessionFactory or sqlSessionTemplate are required");
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

}