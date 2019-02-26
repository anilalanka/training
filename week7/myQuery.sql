-- query to create table acccident
create table accident(
  state varchar (32),
  y06 int DEFAULT 0,
  y07 int DEFAULT 0,
  y08 int DEFAULT 0,
  y09 int DEFAULT 0,
  y10 int DEFAULT 0,
  y11 int DEFAULT 0,
  y12 int DEFAULT 0
);

-- query to load data into table accident
LOAD DATA LOCAL INFILE 'accident.csv' INTO TABLE accident
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

-- query to return top 3 accident prone states in 2006
SELECT *
FROM accident
WHERE state != 'All India'
ORDER BY y06 DESC
LIMIT 3;


-- new table population
CREATE TABLE population(
  rank varchar(32),
  state VARCHAR(32),
  y51 int default 0,
  y61 int default 0,
  y71 int DEFAULT 0,
  y81 int DEFAULT 0,
  y91 int DEFAULT 0,
  y01 int DEFAULT 0,
  y11 int DEFAULT 0
);

-- load data to population table
LOAD DATA LOCAL INFILE 'population.csv'
INTO TABLE population
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

-- top 3 per capita accident prone states in 2006
SELECT a.state, a.y06,p.y11 AS population,a.y06*100000/p.y11 AS percapita_times_100000
FROM accident AS a , population AS p
WHERE a.state = p.state
ORDER BY percapita_times_100000
LIMIT 3;
