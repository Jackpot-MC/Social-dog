![logo](./src/main/webapp/resources/images/SocialDog_logo_yellow.png)

# SocialDog
이웃 강아지와 함께 산책을 합시다!

1. [**서비스 소개**](#-서비스-소개)
2. [**기술 스택**](#-기술-스택)
3. [**주요 페이지 안내**](#-주요-페이지-안내)
4. [**향후 보완점**](#-향후-보완점)
5. [**프로젝트 기간**](#-프로젝트-기간)
6. [**팀 잭팟**](#-팀-잭팟)



## 🚀 서비스 소개

####  필요성

> 매년 반려견을 가족의 일원으로서 함께하는 인구가 지속적으로 증가하고 있습니다.
> 
> 하지만, 이웃 반려견을 만나 사회성을 기르는데 현실적인 어려움이 있습니다.
> 
> 그래서 반려견이 이웃 반려견과 만나 사회성을 기르고,
> 더 나아가 이웃 사람들과의 소통을 할 수 있는 플랫폼이 필요합니다.

#### 위치 정보 기반 산책 약속 플랫폼, SocialDog

#####  SocialDog에서 이웃 강아지와 산책 약속을 잡고 함께 산책을 해보세요!

- Kakao Map의 위치 정보를 기반으로 주변 반려견 관련 장소 표시 및 산책 약속 생성
- 정해진 키워드로 카테고리 단위 장소 검색
- 반응형 웹으로 서비스를 제공하여 별도 스마트폰 앱 설치 불필요



## 🧰 기술 스택

- 협업 도구
<img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">

- 서버 관리
<img src="https://img.shields.io/badge/NAVER%20Cloud-03C75A?style=for-the-badge&logo=naver&logoColor=white">
<img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=Ubuntu&logoColor=white">
<img src="https://img.shields.io/badge/letsencrypt-E95420?style=for-the-badge&logo=letsencrypt&logoColor=white">

- 백엔드
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/Let's%20Encrypt-003A70?logo=letsencrypt&logoColor=fff&style=for-the-badge">

- 프론트엔드
<img src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E">
<img src="https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white">

- 활용 API
<img src="https://img.shields.io/badge/kakao%20developers-ffcd00.svg?style=for-the-badge&logo=kakaotalk&logoColor=000000">



## 🧭 주요 페이지 안내

### 홈
*******************************************************************************
![README_-_홈_화면](https://github.com/Jackpot-MC/Social-dog/assets/141387585/67ffab28-51fb-4c26-8e70-b68b59a048a2)
- SocialDog 접속할 때 처음으로 보이는 서비스 초기 화면입니다.
- SocialDog의 서비스를 한 눈에 확인할 수 있습니다.
- 배너 광고나 커뮤니티 관련 콘텐츠가 표시됩니다. 
- 모든 서비스를 정상적으로 이용하려면 로그인을 해야합니다.

### 나의약속
*******************************************************************************
![README_-_산책하기](https://github.com/Jackpot-MC/Social-dog/assets/141387585/c54871ea-fc94-49f1-b2b3-3c7241858df6)
- 내가 참여하고 있는 약속 리스트를 확인할 수 있습니다.
- 본인이 주최자일 경우 약속을 삭제하거나 수정할 수 있습니다.
- 약속 참가자일 경우 약속에 참가하거나 취소를 할 수 있습니다.
- 과거에 참가하거나 주최한 약속을 조회할 수 있습니다.

### 커뮤니티 및 공지사항
*******************************************************************************
![README_-_커뮤니티](https://github.com/Jackpot-MC/Social-dog/assets/141387585/dcfcb0cf-69ff-423a-9c7c-64c4309d9601)
- 커뮤니티에서 사용자 간 소통을 할 수 있습니다.
- 공지사항에서 서비스 이용 관련 안내를 확인할 수 있습니다.

### 마이페이지
*******************************************************************************
![README_-_마이페이지](https://github.com/Jackpot-MC/Social-dog/assets/141387585/390c2974-d185-46cc-9378-519b6135f8ea)
- 사용자나 강아지에 관련된 정보를 추가하거나 수정 및 삭제할 수 있습니다.



## 🔧 향후 보완점

### 장소 정보로 리스트 출력
- Kakao Map API에서 장소 정보 수령 **성공!**
- 장소 정보 DB 입력 **성공!**
- 페이지에서 Kakao Map 장소 ID와 DB 장소 ID 조건 비교 후 리스트 출력 *진행 중...*

### 전국 단위 검색 적용(클러스터러 기능 활용)
- 현재 내 위치 기준 키워드 및 카테고리 검색만 가능
- 클러스터러는 적용되었으나 카테고리 검색 결과에 적용하지 못함
- 전국 단위 검색 적용을 하면 제공할 수 있는 서비스 범위 확장 가능

### 산책 경로 실시간 기록
- Kakao Map에서 위치 정보를 기반으로 위도 및 경도를 받아 산책 경로 기록 가능
- 산책 경로 기록 기능을 통해 산책 서비스의 핵심 목표를 강화할 수 있음 
- 위치 정보 기반 각 고객에 맞춤 광고 제공을 하여 수익 극대화 도모 가능



## ⌛ 프로젝트 기간

### 23.10.31 ~ 22.12.08



## 🎰 팀 잭팟

- [**김영훈(팀장)**](https://github.com/arcticPeng93)
- [**박보헌**](https://github.com/Boheon)
- [**서영아**](https://github.com/syasyass)
- [**유민지**](https://github.com/mjyoo0353)
- [**하민우**](https://github.com/minwoo76430635)
