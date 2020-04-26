## 模块

### 进度

| 模块         | 是否完成 | 进度说明           |
| ------------ | -------- | ------------------ |
| 登录模块     | 否       | 未完成后台接口     |
| 试题模块     | 否       | 未对应试题分类模块 |
|              | 否       |                    |
| 书籍章节模块 | 否       |                    |
|              | 否       |                    |
| 试题测试模块 | 否       |                    |
|              | 否       |                    |
|              | 否       |                    |



### 登录模块

- 前端已完成,后端未实现相应的接口
- 通过token完成前后端登录连接

### 试题模块

- 说明
  
  - 存储面试相关题目
  
- 数据表

  t_question

| 字段        | 类型    | 说明     |
| ----------- | ------- | -------- |
| id          | int(11) | 主键     |
| type_id     | varchar | 分类id   |
| title       | varchar | 题目     |
| answer      | varchar | 解答     |
| href        | varchar | 链接     |
| img         | varchar | 图片     |
| create_time | date    | 创建时间 |
| user_id     | int(11) | 用户id   |

- 待处理项
  - 未对应分类id
  - 图片未整合录入

### 试题分类模块

- 说明
  - 作为试题模块的分类
  - 树类型数据结构

- 数据表

  t_question_category

| 字段      | 类型    | 说明     |
| --------- | ------- | -------- |
| id        | int(11) | 主键     |
| name      | varchar | 名称     |
| parent_id | int(11) | 父分类id |

- 待处理项
  - 创建管理页面及相应后台逻辑
  - 补全与试题模块的关联

### 试题测试模块

- 说明
  - 对试题的检测

- 数据库表

- t_exam

| 字段        | 类型     | 说明                      |
| ----------- | -------- | ------------------------- |
| id          | int      | 主键                      |
| list        | varchar  | 以下划线分隔的question_id |
| score       | int      | 分数                      |
| user_id     | int      | 用户                      |
| create_time | datetime | 答题时间                  |

- t_exam_info

| 字段        | 类型     | 说明               |
| ----------- | -------- | ------------------ |
| id          | int      | 主键               |
| exam_id     | int      | 试题集id           |
| question_id | int      | 试题id             |
| cate_id     | varchat  | 试题类型(方便统计) |
| answer      | varchar  | 本次答案           |
| degree      | varchar  | 符合程度           |
| is_true     | int      | 是否正确           |
| create_time | datetime | 答题时间           |



### 书籍模块

- 说明
  
  - 存储书籍的书名信息
  
- 数据表

  t_book

| 字段        | 类型    | 说明                             |
| ----------- | ------- | -------------------------------- |
| id          | int(11) | 主键                             |
| name        | varchar | 名称,parent_id为0时,该名称为书名 |
| create_time | date    | 创建时间                         |
| user_id     | int(11) | 用户id   |


### 书籍章节模块

- 说明
  
  - 存储书籍的书名信息
  
- 数据表

  t_book_chapter

| 字段        | 类型    | 说明                             |
| ----------- | ------- | -------------------------------- |
| id          | int(11) | 主键                             |
| name        | varchar | 名称,parent_id为0时,该名称为书名/章节 |
| sort       | int     | 排序                             |
| page        | int(4)  | 页码                             |
| create_time | date    | 创建时间                         |
| parent_id   | int(11) | 父id                             |
| level | int(2) | 层级 |
| user_id     | int(11) | 用户id   |

### 书籍笔记模块

- 说明
  
  - 存储书籍中相关笔记内容
- 数据表

  t_book_note

| 字段        | 类型    | 说明     |
| ----------- | ------- | -------- |
| id          | int(11) | 主键     |
| title       | varchar | 主题     |
| content     | varchat | 内容     |
| think       | varchar | 见解     |
| degree      | int     | 重要程度 |
| create_time | date    | 创建时间 |
| book_id     | int(11) | 书籍id   |
| user_id     | int(11) | 用户id   |

## 待实现

- 鉴权
  - 登录控制
- 全文检索
  - 对内容的检索
- websocket
  - 即时通知
- redis

## 注意

- 图片存储到另一个服务器中
  - 启动一个tomcat
  - 在项目中配置好对应的服务路径



## 说明

### 模块目录

- 后端结构

  以试题模块为例

  - entity

    ```
    com.cl.interview.entity.QuestionEntity
    ```

    

  - dao

    ```
    com.cl.interview.dao.QuestionDao
    ```

    

  - dto

    ```
    com.cl.interview.dto.QuestionDto
    ```

    接口数据接收类

  - po

    ```
    com.cl.interview.po.QuestionPo
    ```

    接口数据输出类

  - service

    ```
    com.cl.interview.service.QuestionService
    ```

    业务接口类

    ```
    com.cl.interview.service.impl.QuestionServiceImpl
    ```

    

  - controller

    ```
    com.cl.interview.controller
    ```

    接口控制类

- 前端结构

  - 路由

    ```
    src\router\index.js
    ```

  - 菜单

    ```
    src\components\account\admin\AppLeft.vue
    ```

    

  - 接口

    ```
    src\store\modules\question.js
    ```

  - 页面

    ```
    src\components\account\menu\question.vue
    ```

    

### 功能说明

- 增加
- 删除
- 修改
- 查询
- 导出
- 导入

