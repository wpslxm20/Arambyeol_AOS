# Arambyeol_AOS
<img src="https://github.com/user-attachments/assets/3def15cf-5ac0-4ac4-8dea-491aa9deea07"/>

## 프로젝트 개요
'아람별_AOS'은 경상국립대학교 기숙사식당인 아람관의 메뉴를 스마트폰으로 쉽게 확인할 수 있는 애플리케이션입니다.
앱으로 간편하게 메뉴를 확인함으로써 학생들의 편의와 소통을 도모합니다.

## 기술 스택
- **프로그래밍 언어** : Kotlin
- **프레임워크 및 라이브러리** : Jetpack Compose, Retrofit, Hilt, Glance, Paging
- **데이터베이스** : Room
- **개발 도구 및 환경** : Android Studio, Git, Figma

## 주요 기능
<img src="https://github.com/user-attachments/assets/928f618d-91b7-403d-89b5-d64a95d5307c"/>

- 식단 제공  
  최근 3일 내 식단을 식사 시간대별로 분류하여 제공합니다. 오늘, 내일, 모레를 라디오 버튼 형식으로 구현하여 클릭 시 해당하는 메뉴를 출력합니다.
  
- 배너 광고  
  앱의 하단에 배너 광고를 띄워 수입을 창출합니다.
  
- 위젯
  한 끼 식단 위젯과 하루 식단 위젯을 제공하여 사용자의 요구에 맞추어 위젯을 제공합니다.
  위젯을 통해 사용자가 앱에 직접 접속하지 않아도 메뉴를 확인할 수 있습니다.

- 채팅(미완성)
  사용자는 채팅을 통해 식단에 대한 다양한 의견을 공유할 수 있습니다.

## 기능별 설계 및 구현
- 식단 제공
  - 앱이 실행될 때, 로컬 데이터베이스(RoomDB)에 저장된 식단 정보가 존재하지 않을 경우, 인터넷 연결을 확인하고 연결되어 있으면 네트워크 요청을 통해 식단정보를 받아옵니다.
  - 매일 1시 30분에 알람이 작동하도록 AlarmManager를 설정합니다. 알람이 실행되면 RoomDB의 식단 정보를 삭제하고, 새로운 식단 정보로 업데이트합니다.
  - View에서는 RoomDB에 저장되어 있는 식단 정보를 로드합니다.
  <img src="https://github.com/user-attachments/assets/5c7aac32-ac90-459b-89c9-8d99736c05c4"/>
  
- 위젯
  - 앱의 위젯은 30분 간격으로 업데이트되며, 현재 시간을 기준으로 사용자가 확인해야 할 식단 정보를 뷰에 표시합니다.
  - 위젯의 종류로는 한 끼 식단을 제공하는 위젯과 하루 식단을 제공하는 위젯이 있습니다.

- 채팅(미완성)
  - 앱이 실행될 때, deviceId를 이용하여 회원가입 및 로그인 요청을 보냅니다.
    로그인 성공 시, 서버로부터 받은 토큰값을 SharedPreferences에 저장합니다.
  - 채팅 페이지에 진입했을 때, 저장된 토큰값을 사용하여 채팅 리스트를 서버로부터 가져옵니다.
    Paing 라이브러리를 사용하여 페이징 처리를 함으로써, 스크롤 시 자동으로 이전 채팅 내용을 네트워크에서 가져와 표시합니다.
  - 실시간 채팅 전송 및 수신은 구현 중에 있음
  - 기존 MVC패턴에서 MVVM패턴으로 전환하여 적용

## 패키지 구조
- 식단 제공 및 위젯 등 기존 기능  
  **MVC 패턴 사용**  
  - controller 파일을 view와 분리하여 WidgetDayPlan과 WidgetCourses에서 WidgetController를 참조하였습니다. 이로 인해 함수의 재사용성을 높여 중복 코드를 줄였습니다.
  <img src="https://github.com/user-attachments/assets/3ce1430c-4dd8-42e4-866b-fe70a6e950bc" width="400rem"/>

- 채팅 기능  
  **MVVM 패턴 사용**
  
  https://github.com/user-attachments/assets/8c4d88ef-b49a-45b1-bda3-f0148b1fd759


