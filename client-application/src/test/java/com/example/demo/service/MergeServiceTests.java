package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MergeServiceTests {

	@Autowired
	private MergeService mergeService;

	@Test
	public void 테스트1() {
		mergeService.saveAny();

		System.out.println("TEST");
	}

}
