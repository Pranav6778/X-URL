package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;

class XUrlImpl implements XUrl{
    // map1 --> Key = longUrl & Value = shortUrl
    Map <String , String> map1 = new HashMap<>();
    // map2 --> Key = shortUrl & Value = longUrl
    Map <String , String> map2 = new HashMap<>();
    // map3 --> Key = longUrl & Value = Hits
    Map <String , Integer > map3 = new HashMap<>();

    @Override
    public String registerNewUrl(String longUrl){
        String shortUrl;
        String RandomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        //checking if longUrl is already registered
        if(map1.containsKey(longUrl)){
            return map1.get(longUrl);
        }
        //if not registered then registering
        else{
            //generating a random Alphanumeric String
            StringBuilder sb = new StringBuilder(9);
            for(int i = 0 ; i < 9 ; i ++){
                int index = (int)(RandomString.length() * Math.random());
                sb.append(RandomString.charAt(index));
            }
            //creating a shortUrl
            shortUrl = "http://short.url/" + sb.toString() ;
            System.out.println("New URL registered : LongUrl - " + longUrl + " : ShortUrl - " + shortUrl);
            //mapping the shortUrl to longUrl in map1
            map1.put(longUrl,shortUrl);

            map2.put(shortUrl,longUrl);

            map3.put(longUrl,0);

            return shortUrl;  
        }
        
    }    

    @Override
    public String registerNewUrl(String longUrl, String shortUrl){
        if(map2.containsKey(shortUrl)){
            return null;
        }
        else{
            map1.put(longUrl,shortUrl);
            map2.put(shortUrl,longUrl);
            map3.put(longUrl,0);
            System.out.println("New URL registered : LongUrl - " + longUrl + " : ShortUrl - " + shortUrl);
            return shortUrl;
        }
    }

    @Override
    public String getUrl(String shortUrl){
        if(map2.containsKey(shortUrl)){
            String longUrl = map2.get(shortUrl);
            int hit = map3.get(longUrl);
            map3.put(longUrl,hit+1);
            return longUrl;
        }
       return null;
    }

    @Override
    public Integer getHitCount(String longUrl){
        if(map3.containsKey(longUrl)){
            return map3.get(longUrl);
        }
        return 0;
    }

    @Override
    public String delete(String longUrl){
        String shortUrl = map1.get(longUrl);
        map1.remove(longUrl);
        map2.remove(shortUrl);
        return shortUrl;
    }
}