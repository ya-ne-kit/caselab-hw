-- Выдайте всю информацию о спортсменах из таблицы sportsman.
select * from trainee.t_sportsman;
-- Выдайте наименование и мировые результаты по всем соревнованиям.
select discipline_description, world_record from trainee.t_discipline;
-- Выберите имена всех спортсменов, которые родились в 1990 году.
select sportsman_name from trainee.t_sportsman where year_of_birth = 1990;
-- Выберите наименование и мировые результаты по всем соревнованиям, установленные 12--05--2010 или 15--05--2010.
select discipline_description, world_record 
from trainee.t_discipline
where set_date in ('12.05.2010', '15.05.2010');
-- Выберите дату проведения всех соревнований, которые проводились в Москве и полученные на них результаты равны 10 секунд.
select hold_date from trainee.t_competition join trainee.t_result 
on trainee.t_competition.competition_id = trainee.t_result.competition_id 
where city = 'Москва' and result = 10;
-- Выберите имена всех спортсменов, у которых персональный рекорд не равен 25 с.
select sportsman_name 
from trainee.t_sportsman_record join trainee.t_sportsman 
on t_sportsman.sportsman_id = t_sportsman_record.sportsman_id 
where record_value <> 25;
-- Выберите названия всех соревнований, у которых мировой рекорд равен 15 с и дата установки рекорда не равна 12--02--2015.
select * from trainee.t_discipline where world_record = 15 and set_date <> '12.02.2015';
-- Выберите города проведения соревнований, где результаты принадлежат множеству {13, 25, 17, 9}.
select distinct city from trainee.t_competition join trainee.t_result 
on trainee.t_competition.competition_id = trainee.t_result.competition_id 
where result in (13, 25, 17, 9);
-- Выберите имена всех спортсменов, у которых год рождения 2000 и разряд не принадлежит множеству {3, 7, 9}.
select sportsman_name from trainee.t_sportsman where rank not in (3, 7, 9) and year_of_birth = 2000;
-- Выберите дату проведения всех соревнований, у которых город проведения начинается с буквы "М".
select hold_date from trainee.t_competition where city like 'М%';
-- Выберите имена всех спортсменов, у которых имена начинаются с буквы "М" и год рождения не заканчивается на "6".
select sportsman_name from trainee.t_sportsman where sportsman_name like '% M%' and year_of_birth % 10 <> 6;
-- Выберите наименования всех соревнований, у которых в названии есть слово "международные".
select competition_name from trainee.t_competition where competition_name like '%международные%';
-- Выберите годы рождения всех спортсменов без повторений.
select distinct year_of_birth from trainee.t_sportsman;
-- Найдите количество результатов, полученных 12--05--2014.
select count(*) from trainee.t_competition join trainee.t_result 
on trainee.t_competition.competition_id = trainee.t_result.competition_id
where hold_date = '12.05.2014';
-- Вычислите максимальный результат, полученный в Москве.
select max(result) from trainee.t_competition join trainee.t_result 
on trainee.t_competition.competition_id = trainee.t_result.competition_id 
where city = 'Москва';
-- Вычислите минимальный год рождения спортсменов, которые имеют 1 разряд.
select min(year_of_birth) from trainee.t_sportsman where rank = 1;
-- Определите имена спортсменов, у которых личные рекорды совпадают с результатами, установленными 12--04--2014.
select sportsman_name from trainee.t_sportsman_record join trainee.t_sportsman 
on t_sportsman_record.sportsman_id = t_sportsman.sportsman_id
where record_value in (select result from trainee.t_competition join trainee.t_result 
on trainee.t_competition.competition_id = trainee.t_result.competition_id 
   where hold_date = '12.04.2014');
-- Выведите наименования соревнований, у которых дата установления мирового рекорда совпадает с датой проведения соревнований в -- Москве 20--04--2015.
select * from trainee.t_discipline;
-- Вычислите средний результат каждого из спортсменов.
select t_sportsman.sportsman_name, avg(result) from trainee.t_result join trainee.t_sportsman 
on t_sportsman.sportsman_id = t_result.sportsman_id
group by t_sportsman.sportsman_id;
-- Выведите годы рождения спортсменов, у которых результат, показанный в Москве выше среднего по всем спортсменам.
select year_of_birth from trainee.t_sportsman where sportsman_id in (
    select sportsman_id from trainee.t_result where competition_id in (
        select competition_id from trainee.t_competition where city = 'Москва'))
group by year_of_birth having result > (select avg(result) from trainee.t_result);
-- Выведите имена всех спортсменов, у которых год рождения больше, чем год установления мирового рекорда, равного 12 с.
select sportsman_name
from trainee.t_sportsman 
join trainee.t_sportsman_record on t_sportsman_record.sportsman_id = t_sportsman.sportsman_id
join trainee.t_discipline on t_sportsman_record.discipline_id = t_discipline.discipline_id
where world_record = 12 and year_of_birth > extract(YEAR from set_date);
-- Выведите список спортсменов в виде 'Спортсмен ' ['имя спортсмена'] 'показал результат' ['результат'] 'в городе' ['город']
select 'Спортсмен ' || sportsman_name || ' показал результат ' || result || ' в городе ' || city 
from (trainee.t_result join trainee.t_sportsman 
on t_sportsman.sportsman_id = t_result.sportsman_id) join trainee.t_competition 
on t_competition.competition_id = t_result.competition_id;
-- Выведите данные о спортсменах, у которых персональный рекорд совпадает с мировым.
select * from trainee.t_sportsman join trainee.t_sportsman_record 
on t_sportsman.sportsman_id = t_sportsman_record.sportsman_id join trainee.t_discipline on ;
-- Выведите города, в которых были установлены мировые рекорды.
select distinct city from trainee.t_discipline d
RIGHT JOIN trainee.t_competitions_disciplines cd ON d.discipline_id = cd.discipline_id
LEFT JOIN trainee.t_competition c ON cd.competition_id = c.competition_id
-- Выведите названия соревнований, на которых было установлено максимальное количество мировых рекордов.
select competition_name, COUNT(*) as row_count from 
(select * from trainee.t_discipline d
RIGHT JOIN trainee.t_competitions_disciplines cd ON d.discipline_id = cd.discipline_id
LEFT JOIN trainee.t_competition c ON cd.competition_id = c.competition_id)
group by competition_name order by row_count limit 1
-- Определите, спортсмены какой страны участвовали в соревнованиях больше всего.
SELECT country, COUNT(*) as row_count
FROM (select distinct s.sportsman_id, c.competition_id, s.country from trainee.t_sportsman s
RIGHT JOIN trainee.t_result r ON s.sportsman_id = r.sportsman_id
LEFT JOIN trainee.t_competition c ON r.competition_id = c.competition_id)
GROUP BY country order by row_count limit 1;
-- Измените разряд на 1 тех спортсменов, у которых личный рекорд совпадает с мировым.
update trainee.t_sportsman set rank = 1 where trainee.t_sportsman.sportsman_id 
in (select s.sportsman_id from trainee.t_sportsman s right join trainee.t_sportsman_record sr 
on s.sportsman_id = sr.sportsman_id left join trainee.t_discipline d 
on sr.discipline_id = d.discipline_id where record_value = world_record);
-- Вычислите возраст спортсменов, которые участвовали в соревнованиях в Москве.
select distinct s.sportsman_name Спортсмен, date_part('year', CURRENT_DATE) - s.year_of_birth Возраст
from trainee.t_sportsman s right join trainee.t_result r 
on s.sportsman_id = r.sportsman_id left join trainee.t_competition c 
on r.competition_id = c.competition_id
-- Измените дату проведения всех соревнований, проходящих в Москве на 4 дня вперед.
update trainee.t_competition set hold_date = hold_date + 4 where city = 'Москва';
-- Измените страну у спортсменов, у которых разряд равен 1 или 2, с Италии на Россию.
update trainee.t_sportsman set country = replace(country, 'Italy', 'Russia') where rank in (1, 2);
-- Измените название соревнований с 'Бег' на 'Бег с препятствиями'
update trainee.t_discipline set discipline_description = replace(discipline_description, 'Бег', 'Бег с препятствиями');
-- Увеличьте мировой результат на 2 с для соревнований ранее 20--03--2005.
update trainee.t_discipline set world_record = 2 + world_record where set_date < '20.03.2005';
-- Уменьшите результаты на 2 с соревнований, которые проводились 20--05--2012 и показанный результат не менее 45 с.
update trainee.t_result 
set result = result - 2 
where result >= 45 and competition_id in 
(select competition_id from trainee.t_competition where hold_date = '20.05.2012')
-- Удалите все соревнования, у которых результат равен 20 с.
delete from trainee.t_competition where competition_id in 
(select competition_id from trainee.t_result where result = 20)
-- Удалите все результаты спортсменов, которые родились в 2001 году.
delete from trainee.t_result where sportsman_id 
in (select sportsman_id from trainee.t_sportsman where year_of_birth = 2001);