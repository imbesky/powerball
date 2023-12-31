# 파워볼

[우아한테크코스 6기 프리코스 미션 '로또'](https://github.com/woowacourse-precourse/java-lotto-6) 변형 프로젝트

## 프로젝트 개요

### 사용 기술

`Java 17` `Spring 3.1.5`

### 작업 기간

`2023.11.22 ~ 2023.11.27`

## 🚀 기능

미국에서 발행되는 복권의 한 종류인 파워볼 구매, 당첨 확인 시스템 구현

### 파워볼 & 파워 플레이

1. 파워볼

- 2달러에 살 수 있는 미국의 복권
- 화이트볼과 레드 파워볼로 구성됨
    - 화이트볼: 1 ~ 69 사이의 숫자, 중복 없음
    - 레드 파워볼: 1 ~ 26 사이의 숫자
- 당첨은 1등부터 9등까지 존재

```
    - 1등: 화이트볼 5개 일치, 레드 파워볼 일치 / Grand Prize
    - 2등: 화이트볼 5개 일치 / 1,000,000달러
    - 3등: 화이트볼 4개 일치, 레드 파워볼 일치 / 50,000달러
    - 4등: 화이트볼 4개 일치 / 100달러
    - 5등: 화이트볼 3개 일치, 레드 파워볼 일치 / 100달러
    - 6등: 화이트볼 3개 일치 / 7달러
    - 7등: 화이트볼 2개 일치, 레드 파워볼 일치 / 7달러
    - 8등: 화이트볼 1개 일치, 레드 파워볼 일치 / 4달러
    - 9등: 레드 파워볼 일치 / 4달러
```

2. 파워 플레이

- 파워볼 구매시 1달러를 추가하면 파워 플레이를 할 수 있다.
- 파워 플레이 참가시 `2, 3, 4, 5, 10`중에 한 가지 숫자가 배수(multiplier number)로 선택된다.
- 파워 플레이 참가 상태로 당첨시 1, 2등을 제외한 등수의 상금의 배수만큼 수령 가능하다.

### 파워볼 & 파워 플레이 구매 기능

***
![example](/src/main/resources/img/purchase.png)

***

1. 파워볼 구매

- 파워볼 구입 금액을 입력받음
- 파워볼 한 장의 가격은 2달러

- 예외:
    - 파워볼 구입 금액이 음수
    - 파워볼 구입 금액이 2달러로 나누어 떨어지지 않음

- 구입한 수량에 맞춰 파워볼 생성
    - 화이트볼 5개와 레드 파워볼 1개가 무작위로 선택됨

2. 파워 플레이 구매

- 파워 플레이 금액을 입력받음
- 파워 플레이 한 번의 가격은 1달러

- 예외:
    - 파워 플레이 금액이 음수
    - 파워 플레이 금액이 1달러로 나누어 떨어지지 않음
    - 파워 플레이 횟수가 파워볼 구입 수량보다 큼

- 금액에 따른 파워 플레이 횟수만큼 파워 플레이 진행
    - 파워 플레이가 적용되는 파워볼은 무작위로 선택됨
    - `multiplier number`가 무작위로 선택됨

### 구매 정보 조회 기능

***
![example](/src/main/resources/img/buyPowerBalls.png)

***

- 발행한 파워볼 수량 및 번호를 출력
- 번호는 오름차순으로 정렬
- 파워 플레이를 한 경우 `multiplier number` 출력

### 당첨 확인 기능

***
![example](/src/main/resources/img/winPowerBall.png)

***

- 당첨 정보를 입력받음
    - 화이트볼 5개와 레드 파워볼을 입력받음

- 예외:
    - 화이트볼 형식에 맞지 않는 입력
    - 입력된 화이트볼의 개수가 5개가 아님
    - 입력된 화이트볼이 숫자가 아님
    - 입력된 화이트볼의 범위가 올바르지 않음
    - 입력된 레드 파워볼이 숫자가 아님
    - 입력된 레드 파워볼의 범위가 올바르지 않음

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다.
- 로또 1장의 가격은 2달러이다.
- 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.

### 당첨 정보 조회 기능

***
![example](/src/main/resources/img/result.png)

***

- 당첨 내역을 출력
- 수익률은 소수점 둘째 자리에서 반올림 (ex. 100.0%, 51.5%, 1,000,000.0%)
- 파워 플레이를 한 파워볼 당첨시 당첨 개수 옆에 `multiplier number`와 그 개수 기재

### 예외 처리

***
![example](/src/main/resources/img/error.png)

***

- 사용자가 잘못된 값을 입력할 경우 `[ERROR]`로 시작하는 에러 메시지를 출력하는 페이지로 이동
- `이전 페이지로 돌아가기` 버튼을 통해 이전 페이지로 돌아가 다시 입력할 수 있음