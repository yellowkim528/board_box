--테이블삭제
drop table reply;

--시퀀스삭제
drop sequence reply_reply_id_seq;

---------
--상품관리
--------
create table reply(
    reply_id      number(10),
    reply_body    clob,
    reply_writer  varchar2(60),       
    post_id       number(10),      --외래키
    cdate         timestamp,       --생성일시
    udate         timestamp        --수정일시
);

--기본키
alter table reply add constraint reply_reply_id_pk primary key(reply_id);

--외래키
alter table reply add constraint reply_post_id_fk foreign key(post_id) references post(post_id);


--시퀀스생성
create sequence reply_reply_id_seq;


--디폴트
alter table reply modify cdate default systimestamp; --운영체제 일시를 기본값으로
alter table reply modify udate default systimestamp; --운영체제 일시를 기본값으로

--필수 not null
alter table reply modify reply_body not null;
alter table reply modify reply_writer not null;

--생성--
insert into reply(reply_id, reply_body, reply_writer, post_id)
     values(reply_reply_id_seq.nextval, '댓글1', '댓작성자1', 3);


commit;
     