package com.darkside.org.config;

import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryConfig {

  public static Cloudinary cloudinary() {
    Map config = new HashMap();
    config.put("cloud_name", "storedrinks");
    config.put("api_key", "414869814418293");
    config.put("api_secret", "mWG1plNyyL8ufVQEiNNF9NnIcZw");
//    config.put("resource_type", "auto");
    Cloudinary cloudinary = new Cloudinary(config);
    return cloudinary;
  }

}
