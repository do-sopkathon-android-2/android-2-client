# android-2-client
DO SOPT 솝커톤 Android 2조 클라이언트 레포입니다.

## Contributor

<table>
<tbody>
<tr>
<td align="center" width="20%">
<a href="https://github.com/s9hn">
<img src="https://github.com/do-sopkathon-android-2/android-2-client/assets/52442547/d04f601d-8d1e-4a66-bd93-662d804b2743" width="200px;" alt=""/>
</a>
</td>
<td align="center" width="20%">
<a href="https://github.com/librarywon">
<img src="https://github.com/do-sopkathon-android-2/android-2-client/assets/52442547/feed0a7a-0768-4041-afe4-b7c4bdce27e5" width="200px;" alt=""/>
</a>
</td>
<td align="center" width="20%">
<a href="https://github.com/Jokwanhee">
<img src="https://github.com/do-sopkathon-android-2/android-2-client/assets/52442547/a57b56bf-7948-4bd5-8188-f72b1d9af475" width="200px;" alt=""/>
</a>
</td>
<td align="center" width="20%">
<a href="https://github.com/HAJIEUN02">
<img src="https://github.com/do-sopkathon-android-2/android-2-client/assets/52442547/6ddd4376-7c64-465f-8dd0-323e5354451a" width="200px;" alt=""/>
</a>
</td>
</tr>
<tr>
<td align="center"><b>김세훈</b></td>
<td align="center"><b>서재원</b></td>
<td align="center"><b>조관희</b></td>
<td align="center"><b>하지은</b></td>
</tr
</tbody>
</table>
개발 난이도와 더불어 팀원 모두가 도전하고 싶은 뷰여서 몹프로그래밍으로 진행합니다.

[MOB 프로그래밍](https://helloworld.kurly.com/blog/mob-programming/)

## About Service

서비스명 : My Mircle time (마미타)

서비스설명 : 아침 6시에 일어나는 사람만이 미라클 타임이 아니다!! 나의 미라클타임을 정하고 확인할 수 있는 서비스

주요기능 : 나만의 미라클타임을 시각화해서 보여줍니다. (시간표 형태로), 투두리스트 기능을 제공합니다.

## Directory Tree

```kotlin
├─ app/network
│   ├─ network
│   │   ├─ ApiFactory
│   │   ├─ Dto
│   │   │   ├─ onboard
│   │   │   └─ home
│   │   └─ Service
│   ├─ domain
│   │   └─ model
│   └─ ui
│       ├─ onboard
│       ├─ home
│       └─ util
│

```

## Git Branch

feature 단위로 브랜치를 구성합니다.

- 해당 작업을 위한 issue를 먼저 생성하고 브랜치를 파서 작업합니다.
- 작업 완료 후 PR을 날려서 코드리뷰를 받은 뒤 머지합니다. 머지한 뒤에 브랜치는 사용하지 않습니다.

예시)

- feature/color_setting
- feature/font_setting
- feature/main
- feature/home

## Git Commit Convention

### Commit Message

- [{tag}] : {content}
    - tag
        - 커밋의 단위를 표현해주세요.
    - content
        - 해당 커밋에 담긴 구현 내용을 공유해주세요
            - 여러 책임들이 한 커밋에 있지 않도록 주의해주세요.
- 구현을 하고 commit 을 쪼개지 말고, 작업을 하면서 수시로 commit 을 남겨주세요.

### Tag

- [FEAT] : 새로운 기능 구현 (UI작업도)
- [ADD] : 부수적인 코드 추가 및 라이브러리 추가, 새로운 파일 생성
- [CHORE] : 버전 코드 수정, 패키지 구조 변경, 타입 및 변수명 변경 등의 작은 작업
- [DOCX] : README 및 문서 수정

## Code Convention

PRND 컨벤션 준수합니다.
https://github.com/PRNDcompany/android-style-guide/blob/main/Resource.md


## Before Our Picture

<img src="https://github.com/do-sopkathon-android-2/android-2-client/assets/52442547/ec5993c0-839f-4d5f-8579-435a6ea43a59" width="400px;" alt=""/>
