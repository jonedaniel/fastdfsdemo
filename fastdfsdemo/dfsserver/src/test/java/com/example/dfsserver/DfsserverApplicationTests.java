package com.example.dfsserver;

import com.example.dfsserver.mapper.FileUploadMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DfsserverApplicationTests {


	@Autowired
	private FileUploadMapper fileUploadMapper;
	@Test
	public void contextLoads() {
		fileUploadMapper.selectAll().stream().forEach(x->{
			System.out.println(x);
		});
	}

}
