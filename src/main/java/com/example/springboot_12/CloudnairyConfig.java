package com.example.springboot_12;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudnairyConfig {
    private Cloudinary cloudinary;
    @Autowired
    public CloudnairyConfig(@Value("${cloud.key}") String key,
                            @Value("${cloud.secret}") String secret,
                            @Value("${cloud.name}") String cloud){
        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secret;
        cloudinary.config.apiKey=key;
    }

    //might be a bad version of map
    public Map upload (Object file, Map options){
        try{
            return cloudinary.uploader().upload(file,options);
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String createUrl(String name, int width, int height, String action){
        return cloudinary.url()
                .transformation(new Transformation()
                .width(width).height(height)
                .border("2px_solid_black").crop(action))
                .imageTag(name);
    }
}
