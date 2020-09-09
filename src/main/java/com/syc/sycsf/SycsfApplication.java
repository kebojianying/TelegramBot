package com.syc.sycsf;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@EnableAsync
@SpringBootApplication
@EnableCaching
@EnableScheduling
@RestController
public class SycsfApplication {
//测试对象账号：zhongtianys  nimaasyc1234
	private final Logger logger = LoggerFactory.getLogger(getClass());
	public Map<String,String> cookies=new HashMap<String,String>();
	public static void main(String[] args) {
		SpringApplication.run(SycsfApplication.class, args);
	}

	@GetMapping("/Auto/suying/login.php")
	public String getSflogin() {
			return  getYzm();
	}
	@PostMapping("/login")
	public String postSflogin(@RequestParam String yzm
	) {
		return submit(yzm);
	}
	@PostMapping("/Pay_Pawxsm_zp.html")
	public void autosf(@RequestParam(value = "user") String user,
					   @RequestParam(value = "amt") String amt,
					   @RequestParam(value = "type") String type
					   ) {
		logger.info("大佬:\t"+user+";\t来啦\t"+amt);
		Cookie cookie=driver.manage().getCookieNamed("ASP.NET_SessionId");
		cookies.put(cookie.getName(),cookie.getValue());
		logger.info(sfSubmit(user,amt,type));
	}
	public static ChromeDriver driver;
	static{
		System.setProperty("webdriver.chrome.driver","D:/shangfen/webdriver/chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
		options.addArguments("--headless");
		//options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(options);
	}
	/*
	获取验证码
	 */
	public String getYzm(){

		driver.get("http://manage.cs66cai.com/Home/Main");
		if(driver.getCurrentUrl().equals("http://manage.cs66cai.com/Home/Main")){
			return "已经是上分状态！无需再次验证！";
		}else{
			driver.findElement(By.xpath("//input[@class='loginuser']")).sendKeys("zdrk888");
			driver.findElement(By.xpath("//input[@class='loginpwd']")).sendKeys("aaa123");
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			long imageName =System.currentTimeMillis();
			try {
				String path = ResourceUtils.getURL("classpath:static").getPath();
				ImgCuter.cutPNG(new FileInputStream(srcFile),
						new FileOutputStream("D:/shangfen/sf/static/"+imageName+".png"), 450,400,100,40);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "<html>\n" +
					"<head>\n" +
					"</head>\n" +
					"\n" +
					"<body>\n" +
					"\n" +
					"        <form action=\"/login\" method=\"post\">\n" +
					"                输入下图验证码：<input type=\"text\" name=\"yzm\" value=\"\"><br>\n" +
					"                <input type=\"submit\" value=\"提交\">\n" +
					"        </form>\n" +
					"                <img src=\"/static/"+imageName+".png\" /><br>\n" +
					"\n" +
					"</body>\n" +
					"\n" +
					"</html>";
		}
	}
	public String sfSubmit(String user,String amt,String type){
		Connection.Response res=null;
		try{
			Map<String,String> data = new HashMap<String, String>();
			data.put("user_name",user);
			data.put("money",amt);
			data.put("commt","转盘抽奖");
			data.put("manual_type","3");
			data.put("consume_money","0");
			res=Jsoup.connect("http://manage.cs66cai.com/Home/A_ManualDeal")
					.cookies(cookies)
					.ignoreContentType(true) // 忽略类型验证
					.followRedirects(false) // 禁止重定向
					.postDataCharset("utf-8")
					.header("Upgrade-Insecure-Requests","1")
					.header("Accept","application/json")
					.header("Content-Type","application/x-www-form-urlencoded")
					.header("X-Requested-With","XMLHttpRequest")
					.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36")
					.data(data)
					.method(Connection.Method.POST)
					.execute();
		}catch (Exception e){
			e.printStackTrace();
		}
		return res.body();
	}
	public String submit(String yzm){
		driver.findElement(By.xpath("//input[@class='loginyzm']")).sendKeys(yzm);
		driver.findElement(By.xpath("//button[@class='loginbtn']")).click();
		return "验证成功！";
	}
	@Scheduled(cron="0 0/5 * * * ? ")
	public void logTime(){
		if(driver!=null) {
			String nowUrl = driver.getCurrentUrl();
			System.out.println("当前链接为：" + nowUrl);
			if (nowUrl.equals("http://manage.cs66cai.com/")) {
				//没有验证码的，可以在这里重新登陆
			} else {
				driver.navigate().refresh();
			}
		}
		logger.info("reflash："+System.currentTimeMillis());
	}
	public void sleep(long second){
		try{
			Thread.sleep(second);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
