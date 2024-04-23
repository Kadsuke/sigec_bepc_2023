update candidat set typeDateNaissance = 'en' where length(dateNaissance) = 4
update candidat set dateNaissance = substr(dateNaissance,1,4)||'-12-31' where length(dateNaissance) = 4
update candidat set dateNaissance = substr(dateNaissance,1,4)||'-0'|| substr(dateNaissance,6,1) || '-' || substr(dateNaissance,8,2) where length(dateNaissance) = 9 and substr(dateNaissance,7,1) = '-'
update candidat set dateNaissance = substr(dateNaissance,1,4)||'-'|| substr(dateNaissance,6,2) || '-0' || substr(dateNaissance,9,1) where length(dateNaissance) = 9 and substr(dateNaissance,8,1) = '-'
update candidat set dateNaissance = substr(dateNaissance,1,4)||'-0'|| substr(dateNaissance,6,1) || '-0' || substr(dateNaissance,8,1) where length(dateNaissance) = 8 and substr(dateNaissance,5,1) = '-'  and substr(dateNaissance,7,1) = '-'
update candidat set dateNaissance = substr(dateNaissance,1,4)||'-12-'|| substr(dateNaissance,7,2) where length(dateNaissance) = 8 and substr(dateNaissance,5,1) = '-'  and substr(dateNaissance,6,1) = '-'
update candidat set dateNaissance = substr(dateNaissance,1,8)||'01' where length(dateNaissance) = 8 and substr(dateNaissance,5,1) = '-'  and substr(dateNaissance,8,1) = '-';
update candidat set dateNaissance = '2005'|| substr(dateNaissance,1,1) || '0' || substr(dateNaissance,2,4) where length(dateNaissance) = 5 and substr(dateNaissance,1,1) = '-'  and substr(dateNaissance,3,1) = '-';
update candidat set dateNaissance = substr(dateNaissance,1,5)||'12-31' where length(dateNaissance) = 6 and substr(dateNaissance,5,1) = '-'  and substr(dateNaissance,6,1) = '-'
update candidat set dateNaissance = substr(dateNaissance,1,5)|| '12-0' || substr(dateNaissance,7,1)  where length(dateNaissance) = 7 and substr(dateNaissance,5,1) = '-'  and substr(dateNaissance,6,1) = '-'
update candidat set dateNaissance = substr(dateNaissance,1,5)|| '0' || substr(dateNaissance,6,2) || '01'  where length(dateNaissance) = 7 and substr(dateNaissance,5,1) = '-'  and substr(dateNaissance,7,1) = '-'
update candidat set dateNaissance = '2005' || substr(dateNaissance,1,6)  where length(dateNaissance) = 6 and substr(dateNaissance,1,1) = '-'  and substr(dateNaissance,4,1) = '-'
