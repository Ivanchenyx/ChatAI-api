# 基础镜像
FROM openjdk:8-jre-slim
# 作者
MAINTAINER yanhee
# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
ADD /ChatAI-api-interface/target/ChatAI-api.jar /ChatAI-api.jar
# 执行镜像；docker run -e PARAMS=" --ChatAI-api.groupId=知识星球ID --ChatAI-api.chatGPTApiKey=自行申请 --ChatAI-api.cookie=登录cookie信息" -p 8090:8090 --name ChatAI-api -d ChatAI-api:1.0
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /ChatAI-api.jar $PARAMS"]