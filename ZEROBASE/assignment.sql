/*
mariadb 계정 접속
mysql -u root(계정명) -p
*/

-- 데이터베이스 인스턴스 생성
-- create database 데이터베이스명;
create database db1;

-- 데이터베이스 인스턴스 삭제
-- drop database 데이터베이스명;
drop database db1;
/*
drop은 데이터를 지우는 것으로 굉장히 위험한 것.
drop 할 일은 거의 없으니 사용해야 되는 경우 주의해서 사용.
*/

-- 데이터베이스 계정 생성
-- create user '유저네임'@'호스트' identified by '비밀번호';
create user 'testuser3'@'localhost' identified by '1234';
create user 'testuser3'@'%' identified by '1234';
/*
localhost : 로컬 호스트에서만 접속하게 하고 싶은 경우
% : 외부 접근을 허용하는 경우
*/

-- 데이터베이스 계정 권한 설정
-- grant all privileges on 데이터베이스명.* to '유저네임'@'호스트' identified by '비밀번호';
-- 특정 데이터베이스의 모든 테이블에 모든 권하을 줌
grant all privileges on testdb3.* to 'testuser3'@'localhost' identified by '1234';
grant all privileges on testdb3.* to 'testuser3'@'%' idntified by '1234';

-- 권한 설정한 게 적용될 수 있도록 해줌.
flush privileges;

-- 사용자 목록 조회
select User, Host, Password from user;

-- mysql 스키마 선택
use mysql;

-- 데이터베이스 목록 확인
show databases;