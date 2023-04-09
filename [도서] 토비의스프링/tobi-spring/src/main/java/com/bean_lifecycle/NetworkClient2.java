package com.bean_lifecycle;

public class NetworkClient2 {

    private String url;

    public NetworkClient2() {
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

    public void initialize() {
        // 초기화 콜백 (의존관계 주입이 끝나면 호출)
        connect();
    }

    public void close() {
        // 소멸 전 콜백 (메모리 반납, 연결 종료와 같은 과정)
        disconnect();
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
