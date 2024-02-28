--테이블삭제
drop table post;

--시퀀스삭제
drop sequence post_post_id_seq;

---------
--글 관리
--------

create table post(
    post_id     number(10),      --아이디
    post_title  varchar2(2000),  --제목
    post_body   clob,            --내용
    writer      varchar2(30),    --작성자
    cdate       timestamp,       --생성일시
    udate       timestamp        --수정일시
);
--기본키
alter table post add constraint post_post_id_pk primary key(post_id);

--시퀀스생성
create sequence post_post_id_seq;


--필수 not null
alter table post modify post_title not null;
alter table post modify post_body not null;
alter table post modify writer not null;


--디폴트
alter table post modify cdate default systimestamp; --운영체제 일시를 기본값으로
alter table post modify udate default systimestamp; --운영체제 일시를 기본값으로

--생성--
insert into post(post_id, post_title, post_body, writer)
     values(post_post_id_seq.nextval, '글제목1임니당', '게시글1의내용임니당', '홍길동1');

insert into post(post_id, post_title, post_body, writer)
     values(post_post_id_seq.nextval, '글제목2임니당', '게시글2의내용임니당', '홍길동2');

insert into post(post_id, post_title, post_body, writer)
     values(post_post_id_seq.nextval, '글제목3임니당', '게시글3의내용임니당', '홍길동3');

commit;


