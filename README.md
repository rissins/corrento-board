## Run Process Docs


1. application.yaml
    1. 로컬DB: url, username, password 확인
    2. `ddl-auto: update` 확인
        1. `ddl-auto: create` 설정 시 기존 테이블 drop 주의
2. `./gradlew build`
3. `java -jar build/libs/board-0.0.1-SNAPSHOT.jar`
4. /src/http/board.http
    1. 게시판 등록
        1. 자유1
        2. 자유2
        3. 익명
5. src/http/article.http
    1. 게시글 등록
    2. 게시글 조회
       1. `/search` : 전체조회
       2. `/search?boardName=게시판명` : 게시판명으로 조회
       3. `/search?startDateTime=시작날짜&endDateTime=종료날짜` : 날짜로 조회 
    3. 게시글 상세조회
    4. 게시글 수정
    5. 게시글 삭제
