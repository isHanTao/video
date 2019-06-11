package common;

import com.exam.bean.admin.VideoBean;
import com.exam.dao.admin.VideoModifyDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Project: video
 * @Package: common
 * @Author: 韩涛
 * @Date: 2018-10-12 9:18
 * @Description:
 * @Param:
 **/
public class test {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-*.xml");
        VideoModifyDao bean = applicationContext.getBean(VideoModifyDao.class);
        System.out.println(bean.getVideoById(10001));
    }
}
