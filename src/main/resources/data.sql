-- RECORD LABELS
insert into record_label values(10001, 'RECORD LABEL 1');
insert into record_label values(10002, 'RECORD LABEL 2');
insert into record_label values(10003, 'RECORD LABEL 3');

-- BANDS
insert into band values (11001, 'BAND 1', 10001);
insert into band values (11002, 'BAND 2', 10001);
insert into band values (11003, 'BAND A', 10002);
insert into band values (11004, 'BAND X', 10003);

-- FESTIVALS
insert into festival values(12001, sysdate(), 'FESTIVAL 1', 11001);
insert into festival values(12002, sysdate(), 'FESTIVAL 2', 11002);
insert into festival values(12003, sysdate(), 'FESTIVAL 3', 11003);