package me.nutsjian.springmvc.newbie.system.init;

import me.nutsjian.springmvc.newbie.utils.AppRuntime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.web.WebLookup;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ContextInitListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ContextInitListener.class);

    private ScheduledExecutorService scheduler;

    private static final String CONF_APP_HOME_FOLDER = "app_home";

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdownNow();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) throws RuntimeException {
        logger.debug("context initializing......");

        scheduler = Executors.newSingleThreadScheduledExecutor();
        String contextPath = sce.getServletContext().getRealPath("/");

        // C:\nutsjian\develop_workspace\github_nutsjian_workspace\springmvc-newbie\springmvc-newbie-jdbctemplate\src\main\webapp
        logger.debug("contextPath => " + contextPath);

        File file = new File(contextPath);
        // 递归查找指定的 app_home 目录
        File appHomeFolder = findAppHomeFolder(file);

        logger.debug("app home => " + appHomeFolder.getAbsolutePath());
        String productHomeFolder = appHomeFolder.getAbsolutePath();

        // AppRuntime.class 的成员变量为 productHomeFolder
        // 通过反射设置该成员变量的值
        String appRuntimeProductHomeFolderField = "productHomeFolder";
        setAppRuntimeProductFolder(appRuntimeProductHomeFolderField, productHomeFolder);


        okTest();
    }

    private void okTest() {
        logger.info("ok test [product home folder] => " + AppRuntime.getProductHomeFolder());
        logger.info("ok test [product root folder] => " + AppRuntime.getProductRootFolder());
    }

    private void setAppRuntimeProductFolder(String filedName, String value) {
        Field f = ReflectionUtils.findField(AppRuntime.class, filedName);
        f.setAccessible(true);
        ReflectionUtils.setField(f, AppRuntime.class, value);
    }

    private File findAppHomeFolder(File file) {
        if (file == null || !file.exists()) {
            return null;
        }

        //直接查找 app_home
        File appHomeDir = new File(file, CONF_APP_HOME_FOLDER);
        if (appHomeDir.exists()) {
            return appHomeDir;
        }

        //当前目录下，遍历子目录中是否包含 app_home
        for (File childFile : Objects.requireNonNull(file.listFiles())) {
            appHomeDir = new File(childFile, CONF_APP_HOME_FOLDER);
            if (appHomeDir.exists()) {
                return appHomeDir;
            }
        }
        // 向上查找目标文件夹 app_home
        return findAppHomeFolder(file.getParentFile());
    }

}
