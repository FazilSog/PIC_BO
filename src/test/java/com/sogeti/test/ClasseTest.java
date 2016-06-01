package com.sogeti.test;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sogeti.bo.IMembreBO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dispatcher-servlet.xml")
@Transactional
public class ClasseTest {
	
	@Autowired
	private IMembreBO membreBO;
	
	@Test
	public void testSaveMembre(){
		
	}
	
	@Test
	public void testUpdateMembre(){
		
		
	}

}
