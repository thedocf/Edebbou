/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.sandy.services;
import com.restfb.BinaryAttachment;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import net.bytebuddy.dynamic.TargetType;
/**
 *
 * @author SLIMEN
 */
public class PartageFb {
     public void partager(String titre,String image) throws FileNotFoundException{
    

        
          String domain="https://www.google.fr/";
          //domain="https://google.fr/";
         String appId="2140222329636792";
         String appSecret="40268e916443baf32f3b18790f3b80b9";
         String authURL="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain
                 +"&scope=ads_management,publish_actions";
         
         System.setProperty("webdriver.chrome.driver", "api/chromedriver_win32/chromedriver.exe");
         WebDriver driver= new ChromeDriver();
         driver.get(authURL);
         String accessToken="EAAeahVeyx7gBAMZCK4S5dPYkZAcTZAndKvxYZBzWCWgOBKrIPnpsKutpYFdzrwOne2yMqQnVqyc1tHo4luemcwFOv65L8KwsgjFuA8AV5Vfbt0GhipN19AOmSQZBTOA3p7zZCOXYiRmwgZBGPzfB7m2b5DYqSAdcW5gQq9VbzlPHZBopnyIMsfhUV2oOBMI23lkZD" ;
         
         boolean ok=true;
         while(ok)
         {
             if ( (! driver.getCurrentUrl().contains("facebook.com")) && (driver.getCurrentUrl()!=authURL) )
             {
                 String url =driver.getCurrentUrl();
                 //accessToken =url.replaceAll(".*#access_token=(.+)&.*", "$1");
                 System.out.println(accessToken);
                
                 ok=false;
              }
             
         }
         
         System.out.println("act:"+accessToken);
         driver.quit();
         FacebookClient fbClient = new DefaultFacebookClient(accessToken);
              User me = fbClient.fetchObject("me", User.class);
             // System.out.println(me.g0<etUsername());
              FileInputStream fs=new FileInputStream(new File(image));
              FacebookType publishPhotoResponse = fbClient.publish("me/photos", FacebookType.class,
  BinaryAttachment.with(image,fs),
  Parameter.with("message", titre));
//            
//              FacebookType publishMessageResponse =
//            fbClient.publish("me/feed", FacebookType.class,
//                    com.restfb.Parameter.with("message","Evenement"));
//          
//
//              System.out.println("Published message ID: " + publishMessageResponse.getId());

    /**
     *
     */
    
        
    
            
    }
}
