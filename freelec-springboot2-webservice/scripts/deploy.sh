#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=freelec=springboot2-webservice

echo "> Build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl freelec-springboot2-webservice | grep jar | awk '{print $1}')
# pgrep : ps + grep, -f=프로세스 이름과 완전히 일치하는, -l=리스트이름,프로세스이름 리스트
# awk : 텍스트 처리 및 명령어 추출을 위한 명령어, 첫번째 필드를 출력하라는 의미

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"
if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  # 프로세스에게 정상적인 종료를 시도로하라는 신호를 보내 프로세스를 종료시킵니다.
  sleep 5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
# ls -t= 파일 및 디렉토리를 수정 시간을 기준으로 정렬, -r=정렬 결과를 역순으로 출력
# tail -n 1 : 명령어 결과의 가장 마지막 라인을 출력합니다.
echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-real-properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
  -Dspring.profiles.active=real \
  $JAR_NAME >$REPOSITORY/nohup.out 2>&1 &
