package com.example.pinche.controller;



import com.example.pinche.domain.User;
import com.example.pinche.repository.Userrepo;
import com.example.pinche.util.UrlUtils;
import org.json.JSONException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RequestMapping
@RestController
public class LoginController {

    @Autowired
    private  Userrepo userrepo;


    private static final String APPID = "wx622c622e9b674630";
    private static final String SECRET = "723ec604d1b2888be6848c84e5387163";

    @ResponseBody
    @RequestMapping("/login")
    public String doLogin(
            Model model, @RequestParam(value = "code",required = false) String code,
            @RequestParam(value = "rawData",required = false) String rawData,
            @RequestParam(value = "signature",required = false) String signature,
            @RequestParam(value = "encrypteData",required = false) String encrypteData,
            @RequestParam(value = "iv",required = false) String iv) throws JSONException {
        System.out.println(code);
        //微信的接口
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+
//                "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";


        JSONObject json = new JSONObject();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, Object> map = new HashMap<>();
        map.put("appid", APPID);
        map.put("secret", SECRET);
        map.put("js_code", code);
        //调用微信接口获取openId用户唯一标识
        String wxReturnValue = UrlUtils.sendPost(url, map);
        System.out.println(wxReturnValue);
        JSONObject jsonObject = new JSONObject(wxReturnValue);
        if(jsonObject.has("errcode")){
            //  System.out.println("code 错误");
            json.put("login", "false");
            return  json.toString();
        }
        else{
            json.put("login", "true");
            if(userrepo.findAllByUserid(jsonObject.get("openid").toString()).isEmpty()||userrepo.findAllByUserid(jsonObject.get("openid").toString()).get(0).getName()==null||userrepo.findAllByUserid(jsonObject.get("openid").toString()).get(0).getLearnid()==null||userrepo.findAllByUserid(jsonObject.get("openid").toString()).get(0).getPhone()==null){
                User user=new User();
                user.setUserid(jsonObject.get("openid").toString());
                userrepo.save(user);
                json.put("openid",jsonObject.get("openid").toString());
                System.out.println(jsonObject.get("openid").toString());
                json.put("name","");
                json.put("phone","");
                json.put("learnid","");
                json.put("qq",user.getQq());
                json.put("firstLogin",true);
            }else {
                User user=userrepo.findAllByUserid(jsonObject.get("openid").toString()).get(0);
                json.put("firstLogin",false);
                json.put("openid",jsonObject.get("openid").toString());
                json.put("name",user.getName());
                json.put("qq",user.getQq());
                json.put("phone",user.getPhone());
                json.put("learnid",user.getLearnid());
            }
            //System.out.println(jsonObject.get("openid").toString());
            return  json.toString();
        }

    }
    //更改个人信息
    @ResponseBody
    @RequestMapping(value="/updataUserInfo",method = RequestMethod.POST)
    public String updataUserInfo(@RequestParam(value="userid",required = true) String userid,
                                 @RequestParam(value="name",required = true )String name,
                                 @RequestParam(value="phone",required = true) String phone,
                                 @RequestParam(value="learnid" ,required = true)String learnid,
                                 @RequestParam(value="qq" ,required = true)String qq

    ) throws JSONException {
//        System.out.println(userid);
//        System.out.println(name);
        JSONObject json = new JSONObject();
//        userid="oNGt75c5IorD_rIf0vmDRMCg_akE";
        if(userrepo.findAllByUserid(userid).isEmpty()){

            System.out.println("进来了111");
            json.put("updataUserInfo", "false");
            return  json.toString();
        }
        else {
            userrepo.updatePassword(userid,name,phone,learnid,qq);
            json.put("updataUserInfo", "true");
            return json.toString();
        }

    }




}
