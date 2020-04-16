package test;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mvcoder on 2020/4/16.
 */
public class JWTTest {


    @Test
    public void generateKey(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        byte[] keyBs = key.getEncoded();
        String keyStr = Base64.encode(keyBs);
        System.out.println(keyStr);
    }

    @Test
    public void generateToken(){
        Map<String, String> claims = new HashMap<>();
        claims.put("id", "1234567");
        claims.put("roles", "1");
        //设置 token 3天后过期
        Date expirationDate = DateUtil.offset(Calendar.getInstance().getTime(), DateField.DAY_OF_MONTH, 3).toJdkDate();
        long startTime = System.currentTimeMillis();

        //需要注意用同一个key
        String keyStr = "cqeLD94joMuscs0/J3Ts0SrMfAmlQdSk1cBnD+mMn8Q=";
        byte[] keyBs = Base64.decode(keyStr);
        Key key = Keys.hmacShaKeyFor(keyBs);

        String token = Jwts.builder()
                .signWith(key)
                .setClaims(claims).setExpiration(expirationDate).compact();
        System.out.println("waste time : " + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println("token is : " + token);

        startTime = System.currentTimeMillis();
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        Jws<Claims> jws = jwtParser.parseClaimsJws(token);
        String id = (String) jws.getBody().get("id");
        String roles = (String) jws.getBody().get("roles");
        System.out.println("waste time : " + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println("id : " + id + " , roles : " + roles);
    }

}
