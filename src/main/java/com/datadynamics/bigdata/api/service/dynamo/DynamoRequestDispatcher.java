package com.datadynamics.bigdata.api.service.dynamo;

import com.datadynamics.bigdata.api.service.dynamo.commands.DynamoRequestCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DynamoRequestDispatcher implements InitializingBean, ApplicationContextAware {

    Map<String, DynamoRequestCommand> commandMap;

    List<DynamoRequestCommand> commands;

    ApplicationContext applicationContext;

    Environment env;

    String targetHeaderPrefix;

    public DynamoRequestCommand getCommand(Map<String, String> headers, HttpServletRequest request, String body) {
        String targetHeader = request.getHeader("X-Amz-Target");
        if (StringUtils.isEmpty(targetHeader)) {
            // TODO 이 요청은 잘못된 요청임
        }
        String commandName = targetHeader.replace(targetHeaderPrefix + ".", "");
        DynamoRequestCommand command = commandMap.get(commandName);



/*
        String[] entries = body.split("&");
        String command = null;
        for (String entry : entries) {
            if (StringUtils.countOccurrencesOf(entry, "Action=") > 0) {
                command = StringUtils.replace(entry, "Action=", "");
                log.info("Command --> {}", command);
                break;
            }
        }
*/

        if (command == null) {
            throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
        }
        return command;
    }

    public void setCommands(List<DynamoRequestCommand> commands) {
        this.commands = commands;
    }

    public List<DynamoRequestCommand> getCommands() {
        return commands;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (commands == null) {
            throw new IllegalArgumentException("Dynamo Command를 지정해 주십시오.");
        }

        this.commandMap = new HashMap<>();
        this.commands.forEach(command -> {
            log.debug("Dynamo Command를 추가합니다 : {} --> {}", command.getName(), command.getClass().getName());
            commandMap.put(command.getName(), command);
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.env = applicationContext.getBean(Environment.class);
        this.targetHeaderPrefix = env.getProperty("app.dynamo.headers.amz-target-prefix");
    }
}
