package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		"classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	@Test
	public void getSeckillList() throws Exception{
		List<Seckill> list  = seckillService.getSeckillList();
		logger.info("list={}", list);
	}

	@Test
	public void getById() throws Exception{
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}", seckill);
	}

	@Test
	public void exportSeckillUrl() {
		long id = 1002;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		logger.info("exposer={}", exposer);
		/**
		 * exposer=Exposer{exposed=true, md5='b8e534f983458e9e603751ad99ed6a29', seckillId=1002, now=0, start=0, end=0}
		 */
	}

	@Test
	public void executeSeckill() {
		long id = 1002;
		long phone = 13843270564L;
		String md5 = "b8e534f983458e9e603751ad99ed6a29";
		SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
		logger.info("result={}", execution);
		/**
		 * result=
		 * SeckillExecution{
		 * seckillId=1002,
		 * state=1,
		 * stateInfo='秒杀成功',
		 * successKilled=SuccessKilled{
		 *      seckillId=1002,
		 *      userPhone=13843270564,
		 *      state=0,
		 *      createTime=null}}
		 */
	}
}