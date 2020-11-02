package com.datadynamics.bigdata.api.service.s3;

import com.datadynamics.bigdata.api.service.s3.commands.S3RequestCommand;
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
public class S3RequestDispatcher implements InitializingBean, ApplicationContextAware {

    /**
     * Request Command Map
     */
    Map<String, S3RequestCommand> commandMap;

    /**
     * Request Command
     */
    List<S3RequestCommand> commands;

    /**
     * S3 API Context Path
     */
    String s3ContextPath;

    public S3RequestCommand getCommand(Map<String, String> headers, HttpServletRequest request) {
        // Request URI에서 Context Path를 제거한 뒤 HTTP METHOD와 결합하여 Key를 생성한다.
        String commandKey = request.getMethod() + "_" + StringUtils.replace(request.getRequestURI(), s3ContextPath, "");

        // 만약 Request Command가 없다면 아직 지원하지 않는 기능이다.
        S3RequestCommand command = commandMap.get(commandKey);
        if (command == null) {
            throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
        }
        return command;
    }

    public void setCommands(List<S3RequestCommand> commands) {
        this.commands = commands;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (commands == null) {
            throw new IllegalArgumentException("S3 Command를 지정해 주십시오.");
        }

        // Spring이 초기화 되면 Command Key와 Command Impl의 매핑 정보를 생성한다.
        this.commandMap = new HashMap<>();
        this.commands.forEach(command -> {
            String commandKey = command.getHttpMethod() + "_" + command.getUri();
            log.debug("S3 Command를 추가합니다 : {} --> {}", commandKey, command.getClass().getName());
            commandMap.put(commandKey, command);
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Environment env = applicationContext.getBean(Environment.class);
        this.s3ContextPath = env.getProperty("app.service-context-paths.s3");
    }
}
