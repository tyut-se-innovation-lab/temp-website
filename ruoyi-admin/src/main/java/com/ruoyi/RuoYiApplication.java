package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tyut.selab.vote.tools.VoteKeyTool;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        VoteKeyTool.run(args);

        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "  _____  __   __  _   _   _____    ___     ___     _       ___     ___      _\n" +
                " |_   _| \\ \\ / / | | | | |_   _|  / __|   | __|   | |     /   \\   | _ )    | |\n" +
                "   | |    \\ V /  | |_| |   | |    \\__ \\   | _|    | |__   | - |   | _ \\    |_|\n" +
                "  _|_|_   _|_|_   \\___/   _|_|_   |___/   |___|   |____|  |_|_|   |___/   _(_)_\n" +
                "_|\"\"\"\"\"|_| \"\"\" |_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_| \"\"\" |\n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ");

    }
}
