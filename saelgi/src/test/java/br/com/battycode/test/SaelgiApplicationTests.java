package br.com.battycode.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.battycode.config.SaelgiApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SaelgiApplication.class)
@WebAppConfiguration
public class SaelgiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
