#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
# readlink : 심볼릭 링크를 따라가면서 해당 파일의 실제 경로를 반환합니다.
# -f : 반환되는 경로를 절대 경로로 반환하도록 지정합니다.
# $0 : 현재 스크립트 파일 또는 셸 스크립트 파일의 이름을 나타냅니다.

ABSDIR=$(dirname $ABSPATH)
# 현재 stop.sh가 속해있는 경로를 찾습니다.
# 하단의 코드와 같이 profile.sh의 경로를 찾기 위해 사용됩니다.
source ${ABSDIR}/profile.sh
# 자바로 치면 일종의 import 구문
# 해당 코드로 인해 stop.sh에서도 profile.sh의 여러 function을 사용할 수 있게 됩니다.

if [ -z ${IDLE_PID} ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi
