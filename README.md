# 요구사항 정의 및 분석
## 유스케이스 다이어그램
![image](https://github.com/ysndy/BridgeGame/assets/49807140/7d4408c8-5597-4d8f-8360-49056b9f0dd3)

## 유스케이스 명세
![image](https://github.com/ysndy/BridgeGame/assets/49807140/d64942b3-327e-45ef-a3fb-93cc3064205d)

## 도메인 모델
![image](https://github.com/ysndy/BridgeGame/assets/49807140/ae7d0971-565b-41da-aaf7-cd77bb94d88d)

## SSD
![image](https://github.com/ysndy/BridgeGame/assets/49807140/2d76c31d-1537-4d61-a497-135dc3fd5ab5)
![image](https://github.com/ysndy/BridgeGame/assets/49807140/fe92b577-dccf-469e-bb5c-b979af93f96e)
![image](https://github.com/ysndy/BridgeGame/assets/49807140/f372ce2c-7475-4f02-9839-2ecd3cceaf73)

## Operation Contract
![image](https://github.com/ysndy/BridgeGame/assets/49807140/ef92f96d-0047-4b11-9f31-1d69b6499409)

# 설계
## Class Diagram
![image](https://github.com/ysndy/BridgeGame/assets/49807140/b0ad58cd-14a0-47cb-bc81-69a6d67c851c)

## Sequence Diagram
![image](https://github.com/ysndy/BridgeGame/assets/49807140/db70d794-9d70-4094-9de5-c8cbb11a7c1c)


# 프로그램 사용 방법
![image](https://github.com/ysndy/BridgeGame/assets/49807140/42e5a544-b7dc-4252-bf2e-efb84d3c7eeb)  </br>
1.	게임 실행  </br>
2.	게임에 참여할 플레이어 수를 입력합니다. (2명~4명)
![image](https://github.com/ysndy/BridgeGame/assets/49807140/cb5290b4-87f9-433e-bb38-e1d38e846e21)  
3.	플레이할 지도를 선택합니다.
![image](https://github.com/ysndy/BridgeGame/assets/49807140/b6283aa1-8892-4402-ad43-927583a9cb2d)  
4.	게임 모드를 선택합니다.  
![image](https://github.com/ysndy/BridgeGame/assets/49807140/d6a241a4-2da7-4b46-9243-2edf1e9d1908)  
4-1. G 선택  
![image](https://github.com/ysndy/BridgeGame/assets/49807140/2c924a48-32a4-451c-a2c3-d09b26a223cf)  
4-1-1.	플레이어 순서에 따라 오른쪽 정보창에 빨간색 테두리가 생깁니다. 그리고 지도의 플레이어 아이콘 또한 빨간색으로 표시됩니다. 빨간색으로 안내된 플레이어는 이번 턴에 할 행동으로 이동 또는 쉬기를 선택합니다. G 또는 S 버튼을 누르고 Enter 버튼을 눌러주세요.  
4-1-1-1. 이동을 선택하면 주사위 수에서 다리카드 개수를 뺀 횟수만큼 이동할 수 있습니다. 횟수에 맞게 명령어를 방향키 버튼으로 입력한 뒤 Enter 버튼을 눌러주세요.  
![image](https://github.com/ysndy/BridgeGame/assets/49807140/9cd75fa6-1bb0-4499-8c10-bd8497cb37b4)  
4-1-1-2. 쉬기를 선택하면 다리카드 개수가 1개 차감되며 다음 플레이어에게 차례가 넘어갑니다.  
4-1-1-3. 한 명의 플레이어가 END 셀에 도착한 뒤에는 뒤로 이동할 수 없습니다.  
4-1-2. 턴을 반복하며 한 명을 제외한 모든 플레이어가 END 셀에 도착할 경우 게임은 종료됩니다.  
![image](https://github.com/ysndy/BridgeGame/assets/49807140/8c88c9c7-6854-4f2c-82cc-409e924f1dcc)  
4-2. C 선택  
![image](https://github.com/ysndy/BridgeGame/assets/49807140/7c248ff9-7d96-41ff-81ff-f6e250e6e714)  
4-2-1.	현재 차례에 표시된 플레이어는 이번 턴에 할 행동으로 이동 또는 쉬기를 선택합니다. G 또는 S 버튼을 누르고 Enter 버튼을 눌러주세요.  
4-2-1-1.	이동을 선택하면 주사위 수에서 다리카드 개수를 뺀 횟수만큼 이동할 수 있습니다. 명령어가 유효할 경우 지도의 플레이어 숫자는 이동한 위치로 갱신됩니다. 횟수에 맞게 명령어를 입력해주세요  
![image](https://github.com/ysndy/BridgeGame/assets/49807140/312f9aa8-e147-41e5-9c40-56ff905f028d)  
4-2-1-2.	쉬기를 선택하면 다리카드 개수가 1개 차감되며 다음 플레이어에게 차례가 넘어갑니다.  
4-2-1-3.	한 명의 플레이어가 END 셀에 도착한 뒤에는 뒤로 이동할 수 없습니다.  
4-2-2.	턴을 반복하며 한 명을 제외한 모든 플레이어가 END 셀에 도착할 경우 게임은 종료됩니다.  
![image](https://github.com/ysndy/BridgeGame/assets/49807140/a2fc12dc-1975-4cc2-a5d3-abf64452c44e)  
