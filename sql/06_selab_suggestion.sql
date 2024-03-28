

-- ----------------------------
-- 1、意见箱存储表
-- ----------------------------
drop table if exists selab_suggestion;
CREATE TABLE `selab_suggestion` (
                                    `suggestion_id` bigint NOT NULL AUTO_INCREMENT COMMENT '建议id',
                                    `suggestion_topic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '建议主题',
                                    `suggestion_content` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '建议内容',
                                    `suggestion_user` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                    `creat_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`suggestion_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
