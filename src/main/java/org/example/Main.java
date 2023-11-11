package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;

import java.util.*;
import java.util.concurrent.TimeUnit;
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        ArrayList<String> phoneNumbers = new ArrayList<>();

        System.out.println("Enter the number of phone numbers ");
        int numberOfPhoneNumbers = input.nextInt();

        System.out.println("Enter the phone numbers you want to send the message");
        input.nextLine();

        for (int i = 0; i < numberOfPhoneNumbers; i++) {
            String number = input.nextLine();
            phoneNumbers.add(number);
        }

        System.out.println("Enter the message you want to send");
        String message = input.nextLine();

        System.out.println("How many times you want to send it?");
        int num = input.nextInt();

        int flag = 0 ;
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();






        for (String phone : phoneNumbers) {
            driver.get("https://web.whatsapp.com/send/?phone="+phone+"&amp;text&amp;type=phone_number&amp;app_absent=0");

            if (flag == 0) {
                LocalStorage localStorage = driver.getLocalStorage();
                localStorage.setItem("ChatThreadLoggingSecret","\"u0jwVNXECjM48YRPscgH+VUNhXi3PMbrjnjVQcGkOao=\"");
                localStorage.setItem("ChatThreadLoggingLastUploadedStartTs","0");
                localStorage.setItem("ChatThreadLoggingOffset","47768");
                TimeUnit.SECONDS.sleep(30);

                flag =1;
            }
            else
                TimeUnit.SECONDS.sleep(15);

            for (int i = 0; i < num; i++) {

                driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys(message);
                driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button")).click();
                TimeUnit.MILLISECONDS.sleep(500);

            }
        }
    }
}