/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Fida
 */
public class Sms {
    
     public static final String ACCOUNT_SID = "ACd1c553500352d1ccc62e7bbd9e906125";
  public static final String AUTH_TOKEN = "ef26d0d700ea08e1379d32e6c6223aa6";
        public static void send(String recepient,String mot) 
    {
   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+216"+recepient),
        new PhoneNumber("+1 864 774 1994"), 
        "Hey  !"+mot
                + " welcom to Eddebou application").create();

    System.out.println(message.getSid());
    }
    
}
