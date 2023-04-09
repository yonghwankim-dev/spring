package com.bean_lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 인터페이스 방법을 이용하여 초기화, 종료 메소드 구현하는 방법
 */
public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url=" + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void call(String msg) {
        System.out.println("call=" + url + ", message=" + msg);
    }

    public void connect() {
        System.out.println("connect=" + url);
    }

    public void disconnect() {
        System.out.println("disconnect=" + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
