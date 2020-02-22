package com.santiago.portal;

import com.santiago.commons.util.JsonUtil;
import com.santiago.commons.util.KryoSerializer;
import com.santiago.portal.entity.domain.MerchantInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test3() {
        KryoSerializer kryoSerializer = new KryoSerializer(MerchantInfo.class);
        byte[] bytes = new byte[300];
        kryoSerializer.serialize(build(), bytes);
        System.out.println(Arrays.toString(bytes));
        MerchantInfo deserialize = (MerchantInfo)kryoSerializer.deserialize(bytes);
        System.out.println(JsonUtil.obj2JsonStrExcludeNull(deserialize));
    }

    private MerchantInfo build() {
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setAccountNo("123");
        return merchantInfo;
    }
}
