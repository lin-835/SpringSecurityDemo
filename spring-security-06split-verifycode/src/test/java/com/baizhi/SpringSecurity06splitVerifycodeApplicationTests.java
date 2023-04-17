package com.baizhi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurity06splitVerifycodeApplicationTests {

    @Test
    public void test() {
        //1.BCryptPasswordEncoder
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));

//        //2.Pbkdf2PasswordEncoder
//        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
//        System.out.println(pbkdf2PasswordEncoder.encode("123"));
//
//        //3.SCryptPasswordEncoder //需要额外引入依赖
//        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
//        System.out.println(sCryptPasswordEncoder.encode("123"));
//
//        //4.Argon2PasswordEncoder //需要额外引入依赖
//        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
//        System.out.println(argon2PasswordEncoder.encode("123"));
    }

}
