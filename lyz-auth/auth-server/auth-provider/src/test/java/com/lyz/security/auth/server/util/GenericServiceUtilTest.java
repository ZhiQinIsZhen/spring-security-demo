package com.lyz.security.auth.server.util;

import com.lyz.security.common.util.JsonMapperUtil;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/7 10:07
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GenericServiceUtil.class})
public class GenericServiceUtilTest {

    @Test
    public void jwtTest() {
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjEsImxvZ2luVHlwZSI6MiwiZGV2aWNlIjoyLCJhcHBsaWNhdGlvbk5hbWUiOiJvcGVuLWFwaSIsInJlYWxOYW1lIjoi5p2O6Ziz6Ie7Iiwibmlja05hbWUiOiLoh7Pnp6YiLCJzdWIiOiI4MTI2NzI1OThAcXEuY29tIiwiZXhwIjoxNjYzMTE5ODgyLCJuYmYiOjE2NjI1MTUwODJ9.vexlIxzdQIJb2VYSm-U9DShrgLwdvUr8Hjb1OJtZg_50II2OUxH8Qw4qn_HUqfLeopPyK8oNm1VISpsfyYI_Kg";
        log.info(JwtHelper.decode(jwt).getClaims());
        log.info(JwtHelper.decode(jwt).getEncoded());
        DefaultClaims claims = JsonMapperUtil.readValue(JwtHelper.decode(jwt).getClaims(), DefaultClaims.class);
        log.info(claims.getExpiration().toString());
    }
}