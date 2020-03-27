package com.scaffold.springboot.mybatis.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author majian
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        //MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();

        Configuration config = loadConfiguration(warnings);

        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        //输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

    private static Configuration loadConfiguration(List<String> warnings) throws IOException, XMLParserException {

        try (InputStream inputStream = Generator.class.getResourceAsStream("/generatorConfig.xml")) {
            ConfigurationParser cp = new ConfigurationParser(warnings);
            return cp.parseConfiguration(inputStream);
        }
    }
}