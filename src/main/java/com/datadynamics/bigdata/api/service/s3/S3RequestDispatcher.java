package com.datadynamics.bigdata.api.service.s3;

import com.datadynamics.bigdata.api.service.s3.commands.S3RequestCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class S3RequestDispatcher implements InitializingBean {

    Map<String, S3RequestCommand> commandMap;

    List<S3RequestCommand> commands;

    public S3RequestCommand getCommand(Map<String, String> headers, HttpServletRequest request, String body) {
        String[] entries = body.split("&");
        String command = null;
        for (String entry : entries) {
            if (StringUtils.countOccurrencesOf(entry, "Action=") > 0) {
                command = StringUtils.replace(entry, "Action=", "");
                log.info("Command --> {}", command);
                break;
            }
        }

        if (command == null) {
            throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
        }
        return commandMap.get(command);
    }

    public void setCommands(List<S3RequestCommand> commands) {
        this.commands = commands;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (commands == null) {
            throw new IllegalArgumentException("S3 Command를 지정해 주십시오.");
        }

        this.commandMap = new HashMap<>();
        this.commands.forEach(command -> {
            commandMap.put(command.getName(), command);
        });
    }
}
