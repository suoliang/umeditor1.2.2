package com.cyyun.fm.service.endpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-application.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class FmSvcEndpoint {

	@Test
	public void endpoint() throws Exception {
		System.out.println("");
		System.out.println("started service");
		System.out.println("");

		Thread.sleep(Long.MAX_VALUE);
	}
}