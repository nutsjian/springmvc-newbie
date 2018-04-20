package me.nutsjian.springmvc.newbie.system.init;

import me.nutsjian.springmvc.newbie.utils.AppRuntime;
import me.nutsjian.springmvc.newbie.utils.EncryptUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 解析数据库配置文件
 */
@Service
public class InstallPropertiesConfigurer extends PropertyPlaceholderConfigurer {
    public void loadProperties(Properties props) throws IOException {
        //数据库
        String installPropPath = AppRuntime.getProductHomeFolder() + "/database.properties";
        File propFile = new File(installPropPath);
        if (!propFile.exists()) {
            throw new IOException("数据库配置文件缺失");
        }
        try {
            loadInstallProperties(propFile, props);
        } catch (Exception e) {
            throw new IOException("数据配置文件解密失败: ", e);
        }

    }

    private void loadInstallProperties(File propFile, Properties props) throws Exception {
        InputStream in = null;
        try {
            in = new FileInputStream(propFile);
            Properties prop = new Properties();
            prop.load(in);
            for (String key : prop.stringPropertyNames()) {
                props.put(key, prop.getProperty(key));
            }
        } finally {
            IOUtils.closeQuietly(in);
        }
    }


}
