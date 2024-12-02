CREATE TABLE IF NOT EXISTS customer (
  customer_id int auto_increment primary key,
  name varchar(100) not null,
  email varchar(100) not null,
  mobile_number varchar(20) not null,
  created_at date not null,
  created_by varchar(100) not null,
  updated_at date default null,
  updated_by varchar(100) default null
);

CREATE TABLE IF NOT EXISTS accounts (
  customer_id int not null,
  account_id int auto_increment primary key,
  account_type varchar(100) not null,
  branch_address varchar(200) not null,
  created_at date not null,
  created_by varchar(100) not null,
  updated_at date default null,
  updated_by varchar(100) default null
);