package com.lyz.security.auth.server.util;

import com.lyz.security.auth.client.user.AuthGrantedAuthority;
import com.lyz.security.common.util.JsonMapperUtil;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/7 10:07
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthGrantedAuthority.class})
public class GenericServiceUtilTest {

    /**
     * 公司标签枚举
     */
    @Getter
    @AllArgsConstructor
    public enum CompanyLabel {
        PROJECT("0", 8000, "项目公司"),
        ACT_CONTROL("1", 10000, "实际控股公司"),
        OTHER_RELATION("2", 6000, "其他关联公司"),
        ;

        private String code;
        private int sort;
        private String desc;

        /**
         * 根据code获取对应枚举
         *
         * @param code
         * @return
         */
        public static CompanyLabel getByCode(String code) {
            if (Objects.nonNull(code)) {
                for (CompanyLabel item : CompanyLabel.values()) {
                    if (item.code.equals(code)) {
                        return item;
                    }
                }
            }
            return null;
        }

        /**
         * 根据标签组获取对应标签名称并且按照权重排好序
         *
         * @param labelStr
         * @return
         */
        public static List<String> getNamesByLabelStr(String labelStr) {
            if (Objects.nonNull(labelStr)) {
                String[] labelArr = labelStr.split(",");
                List<CompanyLabel> nameEnumList = new ArrayList<>();
                Arrays.stream(labelArr).forEach(e -> {
                    for (CompanyLabel item : CompanyLabel.values()) {
                        if (item.code.equals(e)) {
                            nameEnumList.add(item);
                        }
                    }
                });
                return nameEnumList.stream().sorted(
                        (o1, o2) -> o2.getSort() - o1.getSort()
                ).map(CompanyLabel::getDesc).collect(Collectors.toList());
            }
            return null;
        }

        public static int getSortByCode(String code) {
            CompanyLabel companyLabel = getByCode(code);
            return Objects.nonNull(companyLabel) ? companyLabel.getSort() : 0;
        }

        public static int sumLabelSort(String labels) {
            if (StringUtils.isBlank(labels)) {
                return 0;
            }
            return Arrays.stream(labels.split(",")).mapToInt(label -> getSortByCode(label)).sum();
        }
    }

    @Test
    public void jwtTest() {
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjEsImxvZ2luVHlwZSI6MiwiZGV2aWNlIjoyLCJhcHBsaWNhdGlvbk5hbWUiOiJvcGVuLWFwaSIsInJlYWxOYW1lIjoi5p2O6Ziz6Ie7Iiwibmlja05hbWUiOiLoh7Pnp6YiLCJzdWIiOiI4MTI2NzI1OThAcXEuY29tIiwiZXhwIjoxNjYzMTE5ODgyLCJuYmYiOjE2NjI1MTUwODJ9.vexlIxzdQIJb2VYSm-U9DShrgLwdvUr8Hjb1OJtZg_50II2OUxH8Qw4qn_HUqfLeopPyK8oNm1VISpsfyYI_Kg";
        log.info(JwtHelper.decode(jwt).getClaims());
        log.info(JwtHelper.decode(jwt).getEncoded());
        DefaultClaims claims = JsonMapperUtil.readValue(JwtHelper.decode(jwt).getClaims(), DefaultClaims.class);
        log.info(claims.getExpiration().toString());
    }

    @Test
    public void test() {
        String companyLabel = "2,1,0";
        log.info("{}", CompanyLabel.sumLabelSort(companyLabel));
    }
}