CREATE TABLE IF NOT EXISTS `s_order` (
                                     `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                     `state` VARCHAR(255) NOT NULL,
                                     PRIMARY KEY (id)
) ENGINE=InnoDB;

-- time-zone issue reference
-- https://blog.csdn.net/CHS007chs/article/details/81348291
