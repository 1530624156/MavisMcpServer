package com.mavis.mavismcpserver.mcp;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * CalculateTool
 *
 * @author Mavis郭逸轩
 * @since 2026/4/3 10:28
 */

@Configuration
public class CalculateTool {

    @Bean
    ToolCallbackProvider toolCallBackProvider(CalculateToolService calculateToolService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(calculateToolService)
                .build();
    }

    @Component
    public static class CalculateToolService {
        @Tool(name = "calculate", description = "Perform a simple calculation")
        public int calculate(
                @ToolParam(description = "First number") int a,
                @ToolParam(description = "Second number") int b,
                @ToolParam(description = "Operation: add, subtract, multiply, divide") String operation) {
            String op = operation.toLowerCase();
            if ("add".equals(op)) {
                return a + b;
            } else if ("subtract".equals(op)) {
                return a - b;
            } else if ("multiply".equals(op)) {
                return a * b;
            } else if ("divide".equals(op)) {
                return b != 0 ? a / b : 0;
            }
            return 0;
        }
    }
}
