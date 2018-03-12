package com.huamu668.fastdfsdemo;

import com.huamu668.fastdfsdemo.mapper.FileUploadMapper;
import com.huamu668.fastdfsdemo.util.FastDFSClient;
import com.huamu668.fastdfsdemo.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastdfsdemoApplicationTests {


	@Autowired
	private FileUploadMapper fileUploadMapper;

	@Autowired
	private FastDFSClient fastDFSClient;

	@Test
	public void testUpload() throws FileNotFoundException {
		File file = new File("C:/Users/Administrator/Pictures/2221.jpg");
		String name = file.getName();
		String extName = name.substring(name.length()-3,name.length());
		System.out.println(file.getName()+file.length());
		byte[] bytes     = FileUtil.file2Byte("C:/Users/Administrator/Pictures/2221.jpg");
		String storePath = fastDFSClient.uploadFile(bytes, file.getName());
		System.out.println(storePath);
	}

	@Test
	public void contextLoads() {
		System.out.println(FileUtil.getSimpleName("abc.jpg"));
	}


}
