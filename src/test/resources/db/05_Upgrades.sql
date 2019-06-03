insert into `Upgrade`
 (`name`, `description`, `default_number`, `resource_set_id`, `building_set_id`)
VALUES
('	Upgrade Granary	','	Upgrade Granary	',	0	,	2	,	3	),
('	Upgrade Granary and chikens house	','	Upgrade Granary and chikens house	',	0	,	6	,	7	),
('	Initialize the chikens	','	Initialize the chikens	',	0	,	14	,	11	);

INSERT INTO `Building_Set`
(`id`,`amount`,`building_id`,`set_id`)
VALUES
(211,100.2,2,3),
(223,101.2,3,3),
(282,91.6,3,7),
(317,70.6,3,7),
(280,88.6,3,7),
(233,60.5,1,11);

INSERT INTO `Resource_Set`
(`id`,`amount`,`resource_id`,`set_id`)
VALUES
(111,10.2,2,2),
(122,8.6,3,6),
(133,6.5,1,14);
