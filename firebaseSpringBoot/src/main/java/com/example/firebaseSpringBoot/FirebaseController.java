package com.example.firebaseSpringBoot;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@PropertySource("classpath:firebase.properties")
@RestController
public class FirebaseController {
	
	@Value("${serverKey}")
	String androidFcmKey;
	
	// Firebase initialization
	@PostConstruct
	public void init() throws IOException{
		
		  
		System.out.println("Post Construct");
		  
	  	//this.getClass().getResource("chattest-cd3fc-firebase-adminsdk-fuzr6-6d9ed20383.json");
		   
	  	//FileInputStream serviceAccount = new FileInputStream("C:/Users/Gabriele/Desktop/chattest-cd3fc-firebase-adminsdk-fuzr6-6d9ed20383.json");
   
		File configFile = new ClassPathResource("firebase_config.json").getFile();
	  	FileInputStream configInputStream = new FileInputStream(configFile);
	  	
	  	FirebaseOptions options = new FirebaseOptions.Builder()
		   .setCredentials(GoogleCredentials.fromStream(configInputStream))
		   .setDatabaseUrl("https://chattest-cd3fc.firebaseio.com")
		   .build();

		   FirebaseApp.initializeApp(options);
		   
	} // end post construct
	
	
	@RequestMapping("/fcm")
    public String test() throws IOException, JSONException{
		
	
	   //String androidFcmKey="AAAAbezFxD0:APA91bEG_B2lIgWxwR3Pg1hdvclUZsCoTnvxbmYYlfWQnVzkWBSqyzUmoVJkTDf0_9Xig-KIHUiGiiYixG8Ru4szt70fr4Z__cWXMy51N_t4npFds_NqZtYrJ4GkD1imi7TvbkJlhAdi";
	   //String androidFcmKey = "AAAAd5JUEqo:APA91bFDnVBtv_gaDe2pQcxSNxuo47xXaA2Czww-xcLYeTgcagVcZFFKUlVmcSbiVuEVyFB-IMBCz3BGNBqqgb3MypAJirtlKUBi6moFPdqMZaa_8G2A5_WFYiK8hn-iFjhciSlwqoH7";
	   
		System.out.println("Fcm Controller");
		String androidFcmUrl="https://fcm.googleapis.com/fcm/send";
	
	   RestTemplate restTemplate = new RestTemplate();
	   HttpHeaders httpHeaders = new HttpHeaders();
	   httpHeaders.set("Authorization", "key=" + androidFcmKey);
	   httpHeaders.set("Content-Type", "application/json");
	     	   
	     	   
 	   /* --- Creazione e settaggio messaggio --- */
 	   JSONObject message = new JSONObject();
 	   //message.put("to","dXQQYLRzelw:APA91bE1oz0YEB7vc5j_DNIXzw_qhmpJEsOGjvjejyOUfzgDL42SrMriS0tEh_ubnC-MmLE68yRhwCye9t7VisF5vsM8sC-KlxDbuzYKbMzcQUDThM73Do08uxJ6p2MbtniUCXWTeAZx" );
 	   //message.put("to","dxgGANF_eGQ:APA91bECk8hNidjM0oWKmcV6xZcogYoYAUM0m2K-1Mg5hVwkFG7c2zQKUATWFn8D_4m49EBentu0NvW6qdWKm8mCw5_hg2TSdomEL_m-zvOGJBTXD7K90iwDX_MGaJvtvAaqoqvS_PEc");
 	   //message.put("to","dSO_Zx7KdOc:APA91bHYlZti70iKDZeizNi3DUzFILWxnkLDiujM2osGJJsLiEtwjtKyW6zOdQEKOsxgGdv60MtuAeOkAjgeWcPgd2jt1hwPVggZuCrWp0UtyrbAvlN1_V693QisYk9IPfKNYjTqfhfc");
 	   //message.put("to","ff-KRqlk9PM:APA91bEaXMMWOy4FxMwu9V1hSimJE7bNByNPHUDuNJc2gl5glE56gCab1pQ_mtm2oBE0s7wODvm1QGVTVK_2TA4saHe2KmbTSoKrN14hsV5LD4QsjwYmnPPlVMIRn0QAOlIamY3ZFWoC");
 	   //message.put("to","e-A1UmYcces:APA91bFdRE4QTB_YfLTCy-jGK_o5IB_xCLifMQ-Ra0Qjol8QSl-R7g7Oa10-45IxzZv8yf5S4HG_tPgeTjk26F8htA97NvHSxTT04fZnYCXJkJPzM_09iIeBCvkSb_5hqu0-hZt8yM1H");
 	   //message.put("to","ep_DZ4JOqhI:APA91bF9mRVrDTvpHPoylNYpTSFwA7Xp2UCSy8wbt9exO916nEEmXC7hFuT3TGkRzhqbQALqIG5Gl03lEnKHFQ_mMquFIrLFOurfd1u-ZAFuRwAXYET9Dw3UPhZkvETps6ooCH96-j0O");
 	   //message.put("to","dpyzLM4bbCY:APA91bHkmKM8-kLXrdIKlw8eJiXHBvBujEjqnpKvcI3v1n_UmlX10urUTu-S3L5jAhtLJ9V4keI5Q0t0opZvMA7PKhRcOzKX-IC4NWMVo3ot3CSXuAlCIrQxXsuTuCx6y2HxU2KcOy5Y");
 	   
 	   message.put("to","/topics/gruppo");
 	   
 	   // Notification
 	   JSONObject notification = new JSONObject();
 	   notification.put("title", "Destino & Friend");
 	   notification.put("body", "Party selvaggio per celebrare la laurea di Francesco Destino - Ritrovo ore 20.00");
 	   
 	   // Data
 	   JSONObject data = new JSONObject();
 	   data.put("action", "http");
 	   //data.put("src", "http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
 	   //data.put("src", "http://www.sample-videos.com/video-fake-gabriele.mp4");
 	   //data.put("name", "Max Allegri");
 	   
 	   //Switch
 	   //message.put("notification", notification);
 	   message.put("data", data);
    
 	   /* --- Invio notifica push --- */
 	   HttpEntity<String> httpEntity = new HttpEntity<String>(message.toString(),httpHeaders);
 	   String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
 	   System.out.println(response);
		
		
		return response;
	}
	
	
	@RequestMapping("/fra")
    public String fra() throws IOException, JSONException{
		
		System.out.println("Fra");
		
		// Array of jsonObject
		JSONArray myArray = new JSONArray();
		
		for(int i=0; i<1000;i++){
			// First object
			JSONObject object = new JSONObject();
			object.put("title", "Loris 4 President!");
			object.put("body", "Sono il Loris numero "+i);
			
			myArray.put(object);
		}
		
	 	return myArray.toString();
	}
	
	
	@RequestMapping("/fra2")
    public String fra2() throws IOException, JSONException{
		
		System.out.println("Fra");
		
		for(int i=0; i<100;i++){
			// First object
			JSONObject object = new JSONObject();
			object.put("title", "Loris 4 President!");
			object.put("body", "Mi chiamo Loris e mi piace pianificare!!!");
		}
		
		// First object
		JSONObject objectOne = new JSONObject();
		objectOne.put("title", "Loris 4 President!");
		objectOne.put("body", "Mi chiamo Loris e mi piace pianificare!!!");
		
		// Second object
		JSONObject objectTwo = new JSONObject();
		objectTwo.put("title", "Luca 4 President!");
		objectTwo.put("body", "Mi chiamo Luca e mi piace android!!!");
		
		// Array of jsonObject
		JSONArray myArray = new JSONArray();
		
		myArray.put(objectOne);
		myArray.put(objectTwo);
		
	 	
	 	return myArray.toString();
	 	
	}
	
//	this.getClass().getResource("chattest-cd3fc-firebase-adminsdk-fuzr6-6d9ed20383.json");
//	   
//	   // start
//	   FileInputStream serviceAccount =
//			   new FileInputStream("C:/Users/Gabriele/Desktop/chattest-cd3fc-firebase-adminsdk-fuzr6-6d9ed20383.json");
//
//			 FirebaseOptions options = new FirebaseOptions.Builder()
//			   .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//			   .setDatabaseUrl("https://chattest-cd3fc.firebaseio.com")
//			   .build();
//
//			 FirebaseApp.initializeApp(options);
//			 
//			 final FirebaseDatabase database = FirebaseDatabase.getInstance();
//			 DatabaseReference ref = database.getReference("channels");
//			 
//			 DatabaseReference usersRef = ref.child(channel);
//
//			 Map<String, Object> users = new HashMap<String, Object>();
//			 users.put("autore", "Francesco");
//			 users.put("messaggio", message);
//
//			 usersRef.setValueAsync(users);
			 //ref.setValueAsync(users);
			 //ref.updateChildrenAsync(users);
	
	

    @RequestMapping("/send")
    public String greeting(
    		@RequestParam(value="message", defaultValue="msg") String messages,
    		@RequestParam(value="channel", defaultValue="channel") String channel,
    		@RequestParam(value="senderId", defaultValue="") String senderId,
    		@RequestParam(value="senderName", defaultValue="") String senderName
    		) throws IOException {

 			 
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference channelRef = database.getReference("channels");
		 
		DatabaseReference msgRef = channelRef.child(channel).child("messages");
		
		DatabaseReference itemRef = msgRef.push();
 
 		Map<String, Object> users = new HashMap<String, Object>();
 		users.put("text", messages);
 		users.put("senderId", senderId);
 		users.put("senderName", senderName);
 
 			itemRef.setValueAsync(users);
// 			 ref.setValueAsync(users);
// 			 ref.updateChildrenAsync(users);
    	
    	
    	
    	return messages+" "+channel;
        
    }
    
    
    @RequestMapping(value="/msgTest",  method = RequestMethod.POST)
    public String fcm(
//    		@RequestParam(value="id", defaultValue="") String id,
//    		@RequestParam(value="id_chat", defaultValue="") String id_chat,
//    		@RequestParam(value="timestamp", defaultValue="") String timestamp,
//    		@RequestParam(value="author", defaultValue="") String author
//    		@RequestParam(value="message", defaultValue="") String messages,
//    		@RequestParam(value="channel", defaultValue="") String channel,
//    		@RequestParam(value="id", defaultValue="") String senderId,
//    		@RequestParam(value="timestamp", defaultValue="") String senderName
    		@RequestBody CustomMessage msg
    		) throws IOException, JSONException {
    	
    	System.out.println(msg.getId()+" "+msg.getChannel());
    	
    	String androidFcmKey = "AAAAd5JUEqo:APA91bFDnVBtv_gaDe2pQcxSNxuo47xXaA2Czww-xcLYeTgcagVcZFFKUlVmcSbiVuEVyFB-IMBCz3BGNBqqgb3MypAJirtlKUBi6moFPdqMZaa_8G2A5_WFYiK8hn-iFjhciSlwqoH7";
 	   	String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

 	   	RestTemplate restTemplate = new RestTemplate();
 	   	HttpHeaders httpHeaders = new HttpHeaders();
 	   	httpHeaders.set("Authorization", "key=" + androidFcmKey);
 	   	httpHeaders.set("Content-Type", "application/json");
 	   	
 	   JSONObject message = new JSONObject();

 	   
 	   message.put("to","/topics/gruppo");
 	   
 	   JSONObject notification = new JSONObject();
 	   notification.put("title", "Evento 1");
 	   
		notification.put("body", "Cena di gala - Ritrovo ore 20.00");

 	   
 	   JSONObject data = new JSONObject();
 	   //data.put("action", "video");
 	   //data.put("src", "http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
 	  data.put("type", "message");
 	  data.put("channel", msg.getChannel());
 	  data.put("author", msg.getId());
 	   
 	   message.put("notification", notification);
 	   message.put("data", data);

    
 	   /* --- Invio notifica push --- */
 	   HttpEntity<String> httpEntity = new HttpEntity<String>(message.toString(),httpHeaders);
 	   String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
 	   System.out.println(response);
    	
    	
    	return "Spedito da "+msg.getChannel()+" nel gruppo "+msg.getId();
    }
    
    
    
    @RequestMapping(value="/msg",  method = RequestMethod.POST)
    public String fcm2(
    		@RequestBody CustomMessage msg
    		) throws IOException, JSONException {
    	
    	
    	System.out.println(msg.getId()+" "+msg.getChannel());
    	
    	// Set endpoint
    	String androidFcmKey = "AAAAd5JUEqo:APA91bFDnVBtv_gaDe2pQcxSNxuo47xXaA2Czww-xcLYeTgcagVcZFFKUlVmcSbiVuEVyFB-IMBCz3BGNBqqgb3MypAJirtlKUBi6moFPdqMZaa_8G2A5_WFYiK8hn-iFjhciSlwqoH7";
 	   	String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

 	   	// Set request headers
 	   	RestTemplate restTemplate = new RestTemplate();
 	   	HttpHeaders httpHeaders = new HttpHeaders();
 	   	httpHeaders.set("Authorization", "key=" + androidFcmKey);
 	   	httpHeaders.set("Content-Type", "application/json");
 	   	
 	   	JSONObject message = new JSONObject();

 	   	message.put("to","/topics/gruppo");
 	   
 	   	JSONObject notification = new JSONObject();
 	   	notification.put("title", "Evento 1");
 	   	notification.put("body", "Cena di gala - Ritrovo ore 20.00");

 	   
 	   	JSONObject data = new JSONObject();
 	   	data.put("type", "message");
 	   	data.put("channel", msg.getChannel());
 	   	data.put("author", msg.getId());
 	   
 	   	message.put("notification", notification);
 	   	message.put("data", data);

    
 	    /* --- Invio notifica push --- */
 	   	HttpEntity<String> httpEntity = new HttpEntity<String>(message.toString(),httpHeaders);
 	   	String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
 	   	System.out.println(response);
    	
    	return "Spedito da "+msg.getChannel()+" nel gruppo "+msg.getId();
    	
    }
    
}