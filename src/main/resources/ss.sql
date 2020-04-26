CREATE TABLE `ss` (
  `id` char(32) NOT NULL,
  `username` varchar(30) default NULL,
  `password` varchar(50) default NULL,
  `name` varchar(20) default NULL,
  `age` int(11) default NULL,
  `phone_num` varchar(11) default NULL,
  `department_id` char(32) default NULL,
  `department_name` varchar(50) default NULL,
  `img` varchar(100) default NULL,
  `create_by` char(32) default NULL,
  `create_date` datetime default NULL,
  `update_by` char(32) default NULL,
  `update_date` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

