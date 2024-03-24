#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 Port: $IDLE_PORT"
  echo "> Port 전환"
  echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc
  # tee : 입력받은 데이터를 표준 출력하면서 동시에 파일에 쓸수 있는 명령어입니다.
  # > 기호를 사용하지 않은 이유는 권한 문제로 파일 쓰기가 실패할 수 있기 때문입니다.

  echo "> nginx Reload"
  sudo service nginx reload
  # 엔진엑스 설정을 다시 불러옵니다.
  # restart와는 다릅니다. restart는 잠시 끊기는 현상이 있지만, reload는 끊김 없이 다시 불러옵니다.
  # 단, 중요한 설정들은 반영되지 않으므로 restart를 해야 합니다.
  # 여기선 외부의 설정 파일인 service-url을 다시 불러오는거라 reload로 가능합니다.
}
