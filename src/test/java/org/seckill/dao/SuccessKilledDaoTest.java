package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() {
		long id = 1001L;
		long phoneNumber = 13843270567L;
		int insertCount = successKilledDao.insertSuccessKilled(id, phoneNumber);
		System.out.println("insertCount" + insertCount);
		/**
		 * 第一次
		 * DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@1be7b63] will not be managed by Spring
		 * 00:23:50.843 [main] DEBUG o.s.d.S.insertSuccessKilled - ==>  Preparing: insert ignore into success_killed(seckill_id, user_phone) values (?, ?)
		 * 00:23:50.969 [main] DEBUG o.s.d.S.insertSuccessKilled - ==> Parameters: 1000(Long), 13843270567(Long)
		 * 00:23:51.176 [main] DEBUG o.s.d.S.insertSuccessKilled - <==    Updates: 1
		 *
		 * 第二次
		 * DEBUG o.s.d.S.insertSuccessKilled - ==>  Preparing: insert ignore into success_killed(seckill_id, user_phone) values (?, ?)
		 * 00:25:56.924 [main] DEBUG o.s.d.S.insertSuccessKilled - ==> Parameters: 1000(Long), 13843270567(Long)
		 * 00:25:56.982 [main] DEBUG o.s.d.S.insertSuccessKilled - <==    Updates: 0
		 */
	}

	@Test
	public void queryByIdWithSeckill() {
		long id = 1001L;
		long phoneNumber = 13843270567L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phoneNumber);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());
		/**
		 * SuccessKilled{seckillId=1000,
		 * userPhone=13843270567,
		 * state=-1,
		 * createTime=null}
		 * Seckill{
		 * seckill=1000,
		 * name='1000元秒杀iphone6',
		 * number=99,
		 * startTime=Tue Jul 10 08:00:00 CST 2018,
		 * endTime=Wed Jul 11 08:00:00 CST 2018,
		 * createTime=Tue Jul 10 22:26:17 CST 2018}
		 */
	}
}