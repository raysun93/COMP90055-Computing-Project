package com.project.sun.cancertracker;

/**
 * Created by sun on 2/20/2018.
 */

public class BristolImage {
    private String[] imageUrl;
    public BristolImage(){
        imageUrl = new String[7];
        imageUrl[0] = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/what_your_poop_is_trying_to_tell_you_slideshow/493ss_webmd_stool_type_1.jpg";
        imageUrl[1] = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/what_your_poop_is_trying_to_tell_you_slideshow/493ss_webmd_stool_type_2.jpg";
        imageUrl[2] = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/what_your_poop_is_trying_to_tell_you_slideshow/493ss_webmd_stool_type_3.jpg";
        imageUrl[3] = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/what_your_poop_is_trying_to_tell_you_slideshow/493ss_webmd_stool_type_4.jpg";
        imageUrl[4] = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/what_your_poop_is_trying_to_tell_you_slideshow/493ss_webmd_stool_type_5.jpg";
        imageUrl[5] = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/what_your_poop_is_trying_to_tell_you_slideshow/493ss_webmd_stool_type_6.jpg";
        imageUrl[6] = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/what_your_poop_is_trying_to_tell_you_slideshow/493ss_webmd_stool_type_7.jpg";
    }
    public String getImage(int id){
        return this.imageUrl[id-1];
    }
}
