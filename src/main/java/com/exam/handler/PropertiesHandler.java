package com.exam.handler;

import com.exam.util.encrypt.DecryptAble;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author 痞老板
 * @Project: examdemo
 * @Package:com.exam.handler
 * @date 2018/9/20 16:41
 * @description
 **/
public class PropertiesHandler extends PropertyPlaceholderConfigurer {
    private DecryptAble decrypt;
    public PropertiesHandler(DecryptAble decrypt) {
        this.decrypt = decrypt;
    }
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {

        for (Object key : props.keySet()) {
            String keys = key.toString();
            if (keys.contains("password") && decrypt != null) {
                //对密码进行解密
                String value = props.getProperty(keys);
                logger.info("解密字段= "+keys+"  "+value);
                props.setProperty(keys, decrypt.decrypt(value));
            }
        }
        logger.info(props);
        super.processProperties(beanFactoryToProcess, props);
    }

}
