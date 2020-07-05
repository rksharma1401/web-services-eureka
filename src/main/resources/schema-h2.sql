CREATE TABLE person(
  first_name  VARCHAR(150),
  last_name   VARCHAR(150),
  age         INTEGER,
  place       VARCHAR(100) ,
  
  CONSTRAINT person_compound_unique UNIQUE (first_name,last_name,age,place)
);