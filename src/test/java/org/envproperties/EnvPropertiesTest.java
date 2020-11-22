package org.envproperties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

class Props {
    @Env
    private String JAVA_HOME;
    @Env
    private String PATH;

    String getJAVA_HOME() {
        return JAVA_HOME;
    }

    String getPATH() {
        return PATH;
    }
}

public class EnvPropertiesTest {
    Map<String, String> envMap;
    final String JAVA_HOME = "JAVA_HOME";
    final String PATH = "PATH";
    String JAVA_HOME_VALUE = "";
    String PATH_VALUE = "";

    @Before
    public void beforeTest() {
        envMap = System.getenv();
        for (Map.Entry<String, String> entry : envMap.entrySet()) {
            if (JAVA_HOME.equals(entry.getKey())) {
                JAVA_HOME_VALUE = entry.getValue();
            }
            if (PATH.equals(entry.getKey())) {
                PATH_VALUE = entry.getValue();
            }
        }
    }

    @Test
    public void readEnv() {
        Props p = null;
        try {
            p = EnvProperties.readEnv(Props.class);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(p);
        Assert.assertEquals(p.getJAVA_HOME(), JAVA_HOME_VALUE);
        Assert.assertEquals(p.getPATH(), PATH_VALUE);
    }
}