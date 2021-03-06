package macaca.client;

import static org.hamcrest.Matchers.containsString;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;

// API doc https://macacajs.github.io/wd.java/

public class SampleTest {

	MacacaClient driver = new MacacaClient();

	@Before
	public void setUp() throws Exception {
		Logger logger = Logger.getLogger(getClass());
		JSONObject porps = new JSONObject();
		porps.put("autoAcceptAlerts", true);
		porps.put("browserName", "electron");
		porps.put("platformName", "desktop");
		porps.put("version", "");
		porps.put("javascriptEnabled", true);
		porps.put("platform", "ANY");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("desiredCapabilities", porps);
		//desiredCapabilities.put("host", "127.0.0.1"); // custom remote host
		//desiredCapabilities.put("port", 3456);        // custom remote port
		driver.initDriver(desiredCapabilities).setWindowSize(1280, 800).get("https://www.baidu.com");
	}

	@Test
	public void test_case_1() throws Exception {
		driver
			.elementById("kw")
			.sendKeys("中文")
			.sleep(1000)
			.elementById("su")
			.click()
			.sleep(3000);
		
		String html = driver.source();

		Assert.assertThat(html, containsString("<html>"));

		driver
			.elementByCss("#head > div.head_wrapper")
			.elementByXPath("//*[@id=\"kw\"]")
			.sendKeys(" elementByXPath")
			.elementById("su")
			.click()
			.takeScreenshot();
	}

	@Test
	public void test_case_2() throws Exception {
		System.out.println("test case #2");
		
		driver
			.get("https://www.baidu.com")
			.elementById("kw")
			.sendKeys("macaca")
			.elementByXPath("//*[@id=\"kw\"]")
			.click()
			.sleep(3000);
		
		String html = driver.source();
		Assert.assertThat(html, containsString("<html>"));
		driver
			.takeScreenshot()
			.sleep(3000);
	}

	@Test
	public void test_case_3() throws Exception {
		System.out.println("test case #3");
		
		driver
			.get("https://www.baidu.com")
			.elementsByXPath("//*[@id=\"u1\"]/a")
			.getIndex(0)
			.click()
			.sleep(3000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}