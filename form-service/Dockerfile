# 使用 Java 17 运行时镜像
FROM eclipse-temurin:17.0.8_7-jre-jammy

# 设置工作目录
WORKDIR /app

# 先把编译好的 JAR 复制进来
COPY target/*.jar app.jar

# 暴露应用默认端口
EXPOSE 8080

# 容器启动时执行这个命令
ENTRYPOINT ["java", "-jar", "app.jar"]
