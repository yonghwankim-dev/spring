#!/usr/bin/env bash

# 쉬고 있는 profile 찾기 : real1이 사용중이면 real2가 쉬고 있고, 반대편 real1이 쉬고 있음

function find_idle_profile() {
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)
  # 현재 nginx가 바라보고 있는 스프링부트가 정상적으로 수행중인지 확인합니다.
  # 응답값을 HttpStatus로 받습니다.
  # 정상이면 200, 오류가 발생한다면 400~503 사이로 발생하니 400 이상은 모두 예외로 보고 real2를 현재 profile로 사용합니다.
  # curl -s : slient 모드를 의미합니다. curl이 작업하는 동안 출력되는 메시지를 표시하지 않습니다.
  # curl -o {경로} : curl 출력을 {경로}로 보냅니다. 예를 들어 curl -o /dev/null이면 /dev/null로 curl 출력을 보냅니다.
  # 이 옵션은 응답 데이터를 무시하고 요청이 성공했는지 여부만 확인하려는 경우 유용합니다.
  # curl -w {출력형식} : curl의 출력 형식을 지정합니다.

  if [ ${RESPONSE_CODE} -ge 400 ]; then # 400 보다 큰 경우
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRENT_PROFILE} == real1 ]; then
    IDLE_PROFILE=real2 # nginx와 연결되어 있지 않은 profile입니다.
    # 스프링부트 프로젝트를 이 profile로 연결하기 위해 반환합니다.
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
  # bash 스크립트는 값을 반환하는 기능이 없습니다.
  # 그래서 제일 마지막 줄에 echo로 결과를 출력 후, 클라이언트에서 그 값을 잡아서 ($(find_idle_profile)) 사용합니다.
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port() {
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]; then
    echo "8081"
  else
    echo "8082"
  fi
}
