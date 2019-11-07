package com.saucerman.community.provider;

import com.alibaba.fastjson.JSON;
import com.saucerman.community.dto.AccessTokenDTO;
import com.saucerman.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.Proxy;
import java.net.InetSocketAddress;
@Component
public class GithubProvider {
    public String GetAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType  = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

//        设置代理
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080));
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .proxy(proxy)
//                .build();

        // 创建参数
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        //创建request对象

        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        //发送
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUser GetUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
